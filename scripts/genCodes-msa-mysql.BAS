'MACRO TITLE: EXPORT REFERENCE VALUE INFO TO EXCEL (Enterprise).BAS
'This macro exports the reference values and their properties to excel.
'Each record in the Excel spread sheet represents one ref val from the
'data dictionary.  The spread sheet can be used to store reference values
'outside of ER/Studio.  The properties can be updated in the
'spread sheet and imported back into an existing model or a new model
'using the "Import Reference Values From Excel" macro.  The columns in the
'spread sheet must remain in the same order to import them properly.
'	REQUIREMENT: You must have MS Excel 97 or later installed
'AUTHOR:  Jason Tiret
'EMAIL:  jason.tiret@embarcadero.com
'LAST UPDATE:  5/8/2003

Dim curRow As Integer
Dim curCol As Integer
Dim clrBack As Variant
Dim clrFore As Variant
Dim clrTitleBack As Variant
Dim clrTitleFore As Variant

' Dim MS Excel variables.
	
Dim Excel As Object
	
' Dim ER/Studio variables.
	
Dim diag As Diagram
Dim mdl As Model
Dim dict As Dictionary
Dim refval As ReferenceValue
Dim Value As ValuePair
Dim dictionary_list () As String
Public csClassFileHead As String
Dim csClassMiddl As String
Dim csClassTail As String
Dim csSubClassHead As String






Sub Main
Dim csPath As String


	' Init the ER/Studio variables.
	
	Set diag = DiagramManager.ActiveDiagram
	'Set dict = diag.Dictionary


	curRow = 1
	curCol = 1

	Debug.Clear


	clrBack = CLR_WHITE
	clrFore = CLR_BLACK
	clrTitleBack = CLR_GREY
	clrTitleFore = CLR_BLACK

	Begin Dialog UserDialog 510,133,"Reference Value Export" ' %GRID:10,7,1,1
		Text 20,21,130,14,"Select Dictionary:",.Text1
		DropListBox 150,21,330,112,dictionary_list(),.dictionary_select
		CancelButton 380,84,90,28
		OKButton 270,84,100,28
	End Dialog
	Dim dlg As UserDialog

	init_dictionary_list

	If Dialog(dlg) = -1 Then

		'choose the proper dictionary
		If dictionary_list(dlg.dictionary_select) = "Local" Then
	
			Set dict = diag.Dictionary
	
		Else
	
			Set dict = diag.EnterpriseDataDictionaries.Item(dictionary_list(dlg.dictionary_select))
	
		End If


		csPath="D:\tmp\codes\msa\codes\04-insterCodes.sql"
		If(csPath<>" " And csPath <> Empty) Then
			temp1=StrReverse(csPath)
			pos=InStr(1,temp1,"\")
			csPath=StrReverse(Mid(temp1,pos,Len(temp1)-pos+1))
			CreateClass(csPath)
		End If
		PrintData
		MsgBox("Export   Complete!",,"导出完成")
	
		' make Excel spread sheet visible

	End If


End Sub


'initialize the dictionary drop down list
Sub init_dictionary_list

	ReDim dictionary_list (0 To diag.EnterpriseDataDictionaries.Count) As String

	dictionary_list (0) = "Local"
	i = 1

	For Each dict In diag.EnterpriseDataDictionaries

		dictionary_list (i) = dict.Name
		i = i + 1

	Next

End Sub

Function PrintData()

	Dim bHideFont As Boolean
	bHideFont = False
	Dim aCode As String
	Dim preSql As String
	Dim csValueName  As String
	Dim pos1 As Integer
	Dim countMsg As String
	Dim deleteSQL As String
	Dim itemCount As Integer




	preSql="INSERT INTO CODE_INFO  VALUES ('"


	'FileName = GetFilePath("InsterCodes.sql","sql",,"导出代码值到数据库的插入SQL脚本文件及相关类文件",3)
	If FileName = Empty Or FileName = "" Then
		FileName = "D:\tmp\codes\msa\codes\04-insterCodes.sql"
		MsgBox "使用默认文件：" & FileName
	End If
	If FileName = Empty Or FileName = "" Then
		FileSave = False
	Else
		' Save file
 	    Open FileName For Output As #1
 	PrintData=FileName
'//////////////////////////////////////////////////////////////////////////////////
	countMsg="#　系统的引用值个数:" & dict.ReferenceValues.Count
	Print #1, countMsg
	Dim i As Integer

	For Each refval In dict.ReferenceValues
		i=i+1
		If refval.IsRange = True Then

		Else
			countMsg="#　本引用值的代码个数:" &  refval.Values.Count & " -------i==" & i
			deleteSQL = "delete from code_info where ci_sp_class='" & refval.ID & "';"
			Print #1, countMsg
			Print #1, deleteSQL
			itemCount=0
			For Each Value In refval.Values
				pos1=InStr(1,Value.ValueDescription,"<")
				If(pos1>1)Then
				csValueName=Mid(Value.ValueDescription,1,pos1-1)
				Else
				csValueName=Value.ValueDescription
				End If

				'aCode=refval.ID & refval.Name+Value.Value+Value.ValueDescription

				aCode=preSql + Value.Value + "', '" & refval.ID & "', '" + refval.Name + "', '" + csValueName + "', '" + refval.Description+ "');"
				Print #1, aCode
				itemCount=itemCount+1
	'				MsgBox("引用值:"""+refval.Name & """有重复的代码值,导出程序不能正常导出!请改正重复的代码值！",,"zhangcs")

				If(itemCount>refval.Values.Count) Then
					MsgBox("引用值:"""+refval.Name & """有重复的代码值,导出程序不能正常导出!请改正重复的代码值！",,"zhangcs")
					End
				End If
			Next Value

		End If

	Next





'//////////////////////////////////////////////////////////////////////////////////
	Close #1
    End If

	'loop through the domains and export the properties of the domains

	'MsgBox("Export SQL finished! count=" & i,,"导出完成")


End Function
Function CreateClass(p As String)

	Dim bHideFont As Boolean
	bHideFont = False
	Dim aCode As String
	Dim preSql As String
	Dim refEnglishName As String
	Dim csFisrtLow As String
	Dim csValueName1 As String
	Dim csValueName2 As String
	
	Dim pos1 As Integer
	Dim pos2 As Integer
	Dim iNo As Integer
	iNo=0


	'FileName="CodesItem.java"
	'FileName = GetFilePath(FileName,"java",,"导出代码类文件到...",1)
	'If FileName = Empty Or FileName = "" Then
	'	FileSave = False
	'Else
		' Save file
 '	    Open p For Output As #1
 	'在InitCodeItems中所有的代码项目默认调用一次
 	Dim strAllCodeItemObject As String, aTmpName As String

'//////////////////////////////////////////////////////////////////////////////////
	Open p+"CodesItem.java" For Output As #1
	csClassFileHead=InitCodeItemClass()
	Print #1,csClassFileHead
	Close #1
	Dim i As Integer

'////////////////////////////////////////////////////////
	For Each refval In dict.ReferenceValues
		i=i+1
		If refval.IsRange = True Then
		Else
			refEnglishName=Trim(Format(refval.Description,"&&&&&&&&&&&&&&"))
			If(refEnglishName="" Or refEnglishName =Empty) Then
				iNo=iNo+1
				refEnglishName="EnglishNameIsNULL" & iNo
				MsgBox "["+refval.Name+"] 没有设置英文类名，将被跳过！"
			Else
				csFisrtLow=LCase(Mid(refEnglishName,1,1))+Mid(refEnglishName,2,Len(refEnglishName)-1)
	 	
	 			Open p+refEnglishName+".java" For Output As #1
				Print #1,InitSubHead(refval.Name,refEnglishName,csFisrtLow)

				For Each Value In refval.Values
					pos1=InStr(1,Value.ValueDescription,"<")
					pos2=InStr(1,Value.ValueDescription,">")
					If(pos1>1 And pos2>2)Then
						csValueName1=Mid(Value.ValueDescription,1,pos1-1)
						csValueName2=Mid(Value.ValueDescription,pos1+1,pos2-pos1-1)
						aTmpName=csValueName2
					Else
						csValueName1=Value.ValueDescription
						csValueName2=Value.ValueDescription
						aTmpName=""
					End If

					Print #1,vbTab+"public static final "+refEnglishName +" "+ Format(csValueName2,"!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+vbTab+vbTab)+"= getObject(""" &Value.Value & """);"

				Next
				Print #1, "}"+vbCrLf
				Close #1
				
				If Not (aTmpName = "") Then
					strAllCodeItemObject = strAllCodeItemObject & _
						vbTab + "private " + refEnglishName + " " + csFisrtLow + _
						" = " + refEnglishName + "." + csValueName2 + ";" + vbCrLf
				End If
			End If
		End If

	Next
	Call createInitCodeItems(strAllCodeItemObject)
End Function

Sub createInitCodeItems(codesString As String)
	Dim context As String

End Sub

Function genStaticClassInit(refval As ReferenceValue, refEnglishName As String, csFisrtLow As String)
	Dim count As Integer, pos1 As Integer
	Dim curValue As ValuePair
	Dim context As String, csValueName As String

	count = refval.Values.Count
	context = 	"	static{"+vbCrLf+ _
				"		Map mapCodeAndNamePair=new HashMap(" & count & ");"+vbCrLf+ _
				"		Map mapCodeAndObjPair=new HashMap(" & count & ");"+vbCrLf

	For Each curValue In refval.Values
		pos1=InStr(1,curValue.ValueDescription,"<")
		If(pos1>1)Then
			csValueName=Mid(curValue.ValueDescription,1,pos1-1)
		Else
			csValueName=curValue.ValueDescription
		End If
		context = context + _
			"		mapCodeAndNamePair.put(""" + curValue.Value + """, """+csValueName+""");"+vbCrLf+ _
			"		mapCodeAndObjPair.put(""" + curValue.Value + """, new "+refEnglishName+"(""" + curValue.Value + """));"+vbCrLf
	Next
	context = context + _
				"		mapCategoryCode.put(CodesItem."+csFisrtLow+",mapCodeAndNamePair);"+vbCrLf+ _
				"		mapCodeObject.put(CodesItem."+csFisrtLow+",mapCodeAndObjPair);"+vbCrLf+ _
				"	}"+vbCrLf

	genStaticClassInit = context
End Function


Function CatList()
	Dim iNo As Integer
	Dim strCatMap As String
	iNo=0
	strCatMap=vbCrLf+vbTab+"protected static final Map mapCat= new HashMap(100);"+vbCrLf+vbTab+"static{"+vbCrLf

	For Each refval In dict.ReferenceValues

			refEnglishName=Trim(Format(refval.Description,"&&&&&&&&&&&&&&"))
			If(refEnglishName="" Or refEnglishName=Empty) Then
				iNo=iNo+1
				refEnglishName="EnglishNameIsNULL" & iNo
			End If
			csFisrtLow=LCase(Mid(refEnglishName,1,1))+Mid(refEnglishName,2,Len(refEnglishName)-1)
			CatList=CatList+vbTab+vbCrLf+"	protected static final String "+csFisrtLow+"=new String(""" & refval.ID & """);"+vbCrLf
			strCatMap=strCatMap+vbTab+vbTab+"mapCat.put("""+refEnglishName+""","+csFisrtLow+");"+vbCrLf
	Next
	strCatMap = strCatMap+vbTab+"}"+vbCrLf
	CatList = CatList+vbCrLf+strCatMap+vbCrLf
End Function
Function CatMap()
	Dim iNo As Integer
	iNo=0
	CatMap=vbCrLf+vbTab+"protected static final Map mapCat= new HashMap(100);"+vbCrLf+vbTab+"static{"+vbCrLf

	For Each refval In dict.ReferenceValues

			refEnglishName=Trim(Format(refval.Description,"&&&&&&&&&&&&&&"))
			If(refEnglishName="" Or refEnglishName=Empty) Then
				iNo=iNo+1
				refEnglishName="EnglishNameIsNULL" & iNo
			End If
			'csFisrt=Mid(refEnglishName,1,1))+Mid(refEnglishName,2,Len(refEnglishName)-1
			csFisrtLow=LCase(refEnglishName)
			CatMap=CatMap+vbTab+vbTab+"mapCat.put("""+csFisrtLow+""","+csFisrtLow+");"+vbCrLf
	Next
	CatMap = CatMap+vbTab+"}"+vbCrLf
End Function

Sub genInitCodeItemsClass
	Dim javacontent As String
	Dim iNo As Integer

End Sub

Function InitCodeItemClass()
	'Call genInitCodeItemsClass
	InitCodeItemClass= _
"package com.eshequ.msa.codes;																																"+vbCrLf+ _
"/**Auto Created by VBScript Do not modify!*/																								 	"+vbCrLf+ _
"import java.util.ArrayList;                                                                 	"+vbCrLf+ _
"import java.util.Collections;                                                         				"+vbCrLf+ _
"import java.util.Comparator;                                                    							"+vbCrLf+ _
"import java.util.HashMap;                                       															"+vbCrLf+ _
"import java.util.Iterator;                                      															"+vbCrLf+ _
"import java.util.List;                                                           						"+vbCrLf+ _
"import java.util.Map;                                                           							"+vbCrLf+ _
"import javax.annotation.PostConstruct;                                                       "+vbCrLf+ _
"import org.springframework.beans.factory.annotation.Autowired;                               "+vbCrLf+ _
"import org.springframework.stereotype.Component;                                             "+vbCrLf+ _
"import com.eshequ.msa.codes.mapper.CodeInfoMapper;                                           "+vbCrLf+ _
"import com.eshequ.msa.codes.model.CodeInfo;                                                  "+vbCrLf+ _
"import com.eshequ.msa.exception.NotFoundException;                                           "+vbCrLf+ _
"@Component                                                                                   "+vbCrLf+ _
"public class CodesItem {                                                      			"+vbCrLf+ _
CatList()+vbCrLf+ _
"	protected static final  Map mapCategoryCode=new HashMap(100);																			" + vbCrLf + _
"	protected static final  Map mapCodeObject=new HashMap(100);																				" + vbCrLf + _
" private static CodeInfoMapper mapper;"  + vbCrLf + _
" @Autowired"  + vbCrLf + _
" private CodeInfoMapper codeInfoMapper;"  + vbCrLf + _
"	public boolean equals(String obj) {                                                               " + vbCrLf + _
"		return this.code.equals(obj);                                                        						" + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	public boolean equals(Object obj) {  " 																															+ vbCrLf + _
"		return this.code.equals(obj);                                                           				" + vbCrLf + _
"	}                                                                                            			" + vbCrLf + _
"	public String toString(){ return this.code; }                                                     " + vbCrLf + _
"	                                                                                                  " + vbCrLf + _
"	/**convert 2 cn value                                                                 " + vbCrLf + _
"	 * @param category                                                     " + vbCrLf + _
"	 * @param code                                                                   " + vbCrLf + _
"	 * @return                                                                                        " + vbCrLf + _
"	 */                                                                                               " + vbCrLf + _
"	protected static String getValue(String category,String code){                                    " + vbCrLf + _
"		Map map=(Map)mapCategoryCode.get(category);                                                     " + vbCrLf + _
"		if(map==null) return """";                                    						              				" + vbCrLf + _
"		String temp=(String)map.get(code);                                    						  						" + vbCrLf + _
"		return (temp==null)? """" : temp;                                    														" + vbCrLf + _
"	}" + vbCrLf + _
"" + vbCrLf + _
"		public static List<CodeInfo> getCodeList(String categoryCode){" + vbCrLf + _
"		   List<CodeInfo> list = new ArrayList<CodeInfo>();" + vbCrLf + _
"		   Map mp = (Map)mapCategoryCode.get(categoryCode);" + vbCrLf + _
"		   if(mp==null) return list;"           + vbCrLf + _
"		   String temp;" + vbCrLf + _
"		   for (Iterator it = mp.keySet().iterator(); it.hasNext();){" + vbCrLf + _
"		      temp = (String)it.next();" + vbCrLf + _
"		      CodeInfo codeInfo = new CodeInfo();" + vbCrLf + _
"		      codeInfo.setCiSpClass(categoryCode);" + vbCrLf + _
"		      codeInfo.setCiSpCode(temp);" + vbCrLf + _
"		      codeInfo.setCiSpName((String)mp.get(temp));" + vbCrLf + _
"		      list.add(codeInfo);" + vbCrLf + _
"		      }" + vbCrLf + _
"		      Collections.sort(list, new Comparator<CodeInfo>() {" + vbCrLf + _
"		         @Override" + vbCrLf + _
"		         public int compare(CodeInfo o1, CodeInfo o2) {" + vbCrLf + _
"		            int i = o1.getCiSpCode().compareTo(o2.getCiSpCode());" + vbCrLf + _
"		      			if (i==0) {return o1.getCiSpName().compareTo(o2.getCiSpName());}" + vbCrLf + _
"		            return i;" + vbCrLf + _
"		         }" + vbCrLf + _
"		      });" + vbCrLf + _
"		      return list;" + vbCrLf + _
"   }" + vbCrLf + _
"" + vbCrLf + _
"		public static Map getCategoryItems(String categoryName){" + vbCrLf + _
"		   String categoryCode = (String)mapCat.get(categoryName);" + vbCrLf + _
"		   if(categoryCode==null)" + vbCrLf + _
"		      throw new RuntimeException(categoryName + ""code category dose not initialize!"");" + vbCrLf + _
"		   " + vbCrLf + _
"		   Map mp=(Map)mapCategoryCode.get(categoryCode);" + vbCrLf + _
"		   return mp;" + vbCrLf + _
"		}" + vbCrLf + _
"" + vbCrLf + _
"	/**                                                                                               " + vbCrLf + _
"	 * convert 2 cn value                                                                             " + vbCrLf + _
"	 * @return                                                                                        " + vbCrLf + _
"	 */                                                                                               " + vbCrLf + _
"	public  String getValue(){                                                                        " + vbCrLf + _
"		 return getValue( this.category,this.code);                                                     " + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	public CodesItem() {" + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	" + vbCrLf + _
"	@PostConstruct                                                                                    " + vbCrLf + _
"	public void init() {                                                                              " + vbCrLf + _
"	   mapper = this.codeInfoMapper;                                                                  " + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	/**                                                                                               " + vbCrLf + _
"	 * construction                                                                                      " + vbCrLf + _
"	 * @param category                                                       " + vbCrLf + _
"	 * @param code                                                                 " + vbCrLf + _
"	 */                                                                                               " + vbCrLf + _
"	protected  CodesItem(String category,String code){                                                " + vbCrLf + _
"		this.category=category;                                                                         " + vbCrLf + _
"		this.code=code;                                                                                 " + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	                                                                                                  " + vbCrLf + _
"	protected static final List<CodeInfo> getCodeFromDB(String category) {             							" + vbCrLf + _
"			List<CodeInfo> list = mapper.selectByClass(category);                                  " + vbCrLf + _
"			if(list.size()<1){	                                                                        " + vbCrLf + _
"				throw new NotFoundException(""current code item can't be found in db! code="" + category);             " + vbCrLf + _
"			}                                                                                     " + vbCrLf + _
"		return list;                                                                                      " + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"	private String code;                                                                              " + vbCrLf + _
"	protected String category;                                                                        " + vbCrLf + _
"	public  String getCategoryCode(){                                                " + vbCrLf + _
"		return category;                                                                                 " + vbCrLf + _
"	}                                                                                                 " + vbCrLf + _
"}                                                                                                  "	+ vbCrLf + vbCrLf + vbCrLf


End Function
Function  InitSubHead(csCategory As String,csSubName As String ,firstLow As String)

	InitSubHead= _
"package com.eshequ.msa.codes;																														"+vbCrLf+ _
"/**Auto Created by VBScript Do not modify!*/												 "+vbCrLf+ _
"import java.util.HashMap;                                                                  "+vbCrLf+ _
"import java.util.List;                                                       "+vbCrLf+ _
"import java.util.Map;                                                           "+vbCrLf+ _
"import com.eshequ.msa.codes.model.CodeInfo;                     "+vbCrLf+ _
"/** Auto generated */												 "+vbCrLf+ _
"public class " +csSubName+ " extends CodesItem {                                      "+vbCrLf+ _
"	private " +csSubName+ "(String code){super(CodesItem." +firstLow+ ",code);}	        "+vbCrLf+ _
"	public static String getValue(String code){                                         "+vbCrLf+ _
"		return CodesItem.getValue(CodesItem." +firstLow+ ",code);                         "+vbCrLf+ _
"	}                                                                                   "+vbCrLf+ _
"	public static String getValue(" +csSubName+ " code){	                                "+vbCrLf+ _
"		return CodesItem.getValue(CodesItem." +firstLow+ ",code.toString());              "+vbCrLf+ _
"	}                                                                                   "+vbCrLf+ _
"	public static List<CodeInfo> getCodeList(){	                                                "+vbCrLf+ _
"		return CodesItem.getCodeList(CodesItem." +firstLow+ ");                           "+vbCrLf+ _
"	}                                                                                   "+vbCrLf+ _
"	public static final " +csSubName+ " getObject(String code){                          "+vbCrLf+ _
"		Map map=(Map)mapCodeObject.get(CodesItem." +firstLow+ ");                         "+vbCrLf+ _
"		return map==null?null:(" +csSubName+ ")map.get(code);                                             "+vbCrLf+ _
"	}                                                                                   "+vbCrLf+ _
"	static {		fillObjectToMap();	}                                                   "+vbCrLf+ _
"	private static final void fillObjectToMap(){                                        "+vbCrLf+ _
"		List<CodeInfo> listCode = getCodeFromDB(CodesItem." +firstLow+ ");                                                                 "+vbCrLf+ _
"		Map map = new HashMap(listCode.size());        "+vbCrLf+ _
"		String temp = null;	                          "+vbCrLf+ _
"		Map map2 = new HashMap(listCode.size());                           "+vbCrLf+ _
"		for (int i=0; i<listCode.size();i++){                                            "+vbCrLf+ _
"			CodeInfo codeInfo = listCode.get(i);                                              "+vbCrLf+ _
"			temp = codeInfo.getCiSpCode();                                              "+vbCrLf+ _
"			map.put(temp, codeInfo.getCiSpName());                                     "+vbCrLf+ _
"			map2.put(temp, new " +csSubName+ "(temp));                                        "+vbCrLf+ _
"		}                                                                                 "+vbCrLf+ _
"		mapCategoryCode.put(CodesItem." +firstLow+ ", map);                                "+vbCrLf+ _
"		mapCodeObject.put(CodesItem." +firstLow+ ", map2);		                              "+vbCrLf+ _
"	}                                                                                   "+vbCrLf+ _
"/////////////////////////////////////////////////////////////////////////////////		"+vbCrLf+ _
"	public int hashCode(){return super.hashCode();}"+vbCrLf+ _
"	public boolean equals(Object obj)"+vbCrLf+ _
"	{"+vbCrLf+ _
"		if( obj instanceof CodesItem)" + vbCrLf+ _
"			return (this == obj);" + vbCrLf+ _
"		else" + vbCrLf+ _
"			return super.equals(obj);" + vbCrLf+ _
"	}" + vbCrLf+ _
"/////////////////////////////////////////////////////////////////////////////////    "+vbCrLf+vbCrLf+vbCrLf+vbCrLf

End Function


