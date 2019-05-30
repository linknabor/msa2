'自动生成Entity
'自动生成BAK表
'自动生成OPER表
'自动生成sequence，并设置到Entity中

Option Explicit

'实体就是表、视图等通称
Dim aDiagram As Diagram
Dim aModel As Model
Dim dict As Dictionary

'定义哪些表需要生成BAK表用来存储注销的记录
Dim needBakTables() As String
Dim needOperTables() As String

'生成的JAVA文件和DB文件的根目录
Dim javafile_rootpath As String
Dim dbscript_rootpath As String

'索引表空间名
Dim indexTSName As String

Dim primaryKeyTS As String 
Dim isGenJavaFile As Boolean, isGenDBScript As Boolean '初始为FALSE，根据构选，设置其为TRUE

Dim only_entity As String '仅仅生成该实体

Sub Main
	Dim tmpString As String
	Dim dlgOP As Variant, tmpVar As Variant
	Dim mySelObj As SelectedObject
	Dim submdl As SubModel
	'MsgBox fillString(3,"a")
	'Call LoopEntities
	'tmpString = EchoDialog
	Debug.Clear
	isGenJavaFile = False
	isGenDBScript = False
	Set aDiagram = DiagramManager.ActiveDiagram '所有数据元素集合
	Set aModel = aDiagram.ActiveModel '一个实体集合
	Set submdl = aModel.ActiveSubModel '取得当前选中的一个Submodel
	For Each mySelObj In submdl.SelectedObjects
		If mySelObj.Type = 1 Or mySelObj.Type = 16 Then
		End If
	Next

	Begin Dialog UserDialog 620,245,"Reference Value Export" ' %GRID:10,7,1,1
		CheckBox 40,21,120,14,"生成实体",.chkEntityEnable
		CheckBox 40,70,130,14,"生成DB脚本",.chkDBEnable
		TextBox 180,70,220,21,.txtDBFilesDir
		TextBox 180,21,220,21,.txtEntityFilesDir
		CancelButton 510,63,80,21
		TextBox 180,105,220,21,.txtEntity
		PushButton 500,21,90,21,"TestFunc",.btnTestFunc
		Text 90,105,80,14,"单表生成",.Text1
		PushButton 480,203,110,21,"处理全部",.btnDealAll
		PushButton 30,203,130,21,"处理子视图",.btnDealSubmodel
		TextBox 180,133,220,21,.txtIndexTSName
		Text 50,133,120,14,"索引表空间名",.Text2
		PushButton 230,203,190,21,"处理选中的实体",.btnDealSelectedEn
	End Dialog
	Dim dlg As UserDialog
	
	dlg.txtEntityFilesDir = "D:\tmp\db\msa"
	dlg.txtDBFilesDir = "D:\tmp\db\msa\script"

	dlgOP=Dialog(dlg)
	'MsgBox dlgOP
	If dlg.chkEntityEnable Then
		isGenJavaFile = True
		only_entity = UCase(Trim(dlg.txtEntity))
	End If
	If dlg.chkDBEnable Then
		isGenDBScript = True
		only_entity = UCase(Trim(dlg.txtEntity))
	End If
	If Not (isGenJavaFile Or isGenDBScript) Then
		MsgBox "没有选择【生成实体/生成DB】"
		Exit Sub
	End If
	javafile_rootpath = dlg.txtEntityFilesDir
	dbscript_rootpath = dlg.txtDBFilesDir

	indexTSName = dlg.txtIndexTSName
	
	If indexTSName <> "" Then
		primaryKeyTS=" using index tablespace  " & indexTSName
	Else
		primaryKeyTS=""
	End If 
	
	If dlgOP = 1 Then '仅仅为了测试
		Call testSomthing
	ElseIf dlgOP = 2 Then '处理所有实体
		Call dealAll
	ElseIf dlgOP = 3 Then '按照选择的submodel处理其中的实体
		Call dealSubmodel
	ElseIf dlgOP = 4 Then '处理选中的实体
		Call dealSelectedEntities
	Else
		MsgBox "系统异常！"
	End If
End Sub

Sub testSomthing
	Dim submdl As SubModel
	Dim mySelObj As SelectedObject
	Dim aEntity As Entity
	Dim curTableName As String, tmpString As String
	Dim attrEntity As AttributeObj

	Set submdl = aModel.ActiveSubModel '取得当前选中的一个Submodel
	MsgBox "MainSubModel=" & submdl.MainSubModel & ", Name=" & submdl.Name

	'SelectedObjects表示用鼠标等圈选的对象，包括：实体、VIEW、注释、关系等等
	For Each mySelObj In submdl.SelectedObjects
		'MsgBox mySelObj.ID

		'1为实体，16为VIEW
		If mySelObj.Type = 1 Or mySelObj.Type = 16 Then
			Set aEntity = aModel.Entities.Item(mySelObj.ID)
			curTableName = UCase(Trim(aEntity.TableName))
			'MsgBox curTableName
			genDBIndexScript(aEntity)
			For Each attrEntity In aEntity.Attributes

				tmpString = attrEntity.AttributeName
				If tmpString = "交易编号" Then
					tmpString = attrEntity.CheckConstraint

					tmpString = attrEntity.CheckConstraintName
					If Not (tmpString = "") Then MsgBox curTableName
				End If
			Next
		End If
	Next
End Sub

Sub dealAll
		If isGenJavaFile Then
			Call LoopEntities
		End If
		If isGenDBScript Then
			Call LoopDBs
		End If
End Sub

'处理选中的实体
Sub dealSelectedEntities
	Dim submdl As SubModel
	Dim mySelObj As SelectedObject
	Dim aEntity As Entity
	Dim curTableName As String, tmpString As String, dbscript As String
	Dim attrEntity As AttributeObj
	Dim nums As Integer
	Dim tmpVar As Variant

	'清除seq
	'Call deleteSEQUENCEFile
	Set submdl = aModel.ActiveSubModel '取得当前选中的一个Submodel
	tmpVar = MsgBox("处理实体所属子视图为：" & submdl.Name & vbCrLf & vbCrLf & "××× 确定吗？", vbOkCancel)
	If Not (tmpVar = 1) Then Exit Sub
	nums=0
	dbscript=""
	'SelectedObjects表示用鼠标等圈选的对象，包括：实体、VIEW、注释、关系等等
	For Each mySelObj In submdl.SelectedObjects
		'MsgBox mySelObj.ID
		'1为实体，16为VIEW
		If mySelObj.Type = 1 Or mySelObj.Type = 16 Then
			Set aEntity = aModel.Entities.Item(mySelObj.ID)
			'MsgBox aEntity.TableName
			If isGenJavaFile Then
				createJavaFile(aEntity)
			End If
			If isGenDBScript Then
				dbscript = dbscript & genDBScript(aEntity)
			End If
			nums = nums +1
		End If
	Next
	If isGenDBScript And Not(dbscript="") Then
		createDBFile(dbscript)
	End If
	MsgBox "共处理了  " & nums & "  个实体！"
End Sub

'处理选中的Submodel中所有的实体
Sub dealSubmodel
	Dim submdl As SubModel
	Dim Entdisp As EntityDisplay
	Dim aEntity As Entity
	Dim dbscript As String
	Dim curTableName As String
	Dim tmpVar As Variant

	'清除seq
	'Call deleteSEQUENCEFile
	'For Each submdl In aModel.SubModels
	Set submdl = aModel.ActiveSubModel '取得当前选中的一个Submodel
	If Not (submdl Is Nothing) Then
		tmpVar = MsgBox("将要开始处理SubModel:【  " & submdl.Name & "  】。" & vbCrLf & vbCrLf & "××× 确定吗？", vbOkCancel)
		If Not (tmpVar = 1) Then Exit Sub
		For Each Entdisp In submdl.EntityDisplays
			Set aEntity = Entdisp.ParentEntity
			'MsgBox aEntity.TableName
			If isGenJavaFile Then
				createJavaFile(aEntity)
			End If
			If isGenDBScript Then
				dbscript = dbscript & genDBScript(aEntity)
			End If
		Next
		If isGenDBScript Then
			createDBFile(dbscript)
		End If
		MsgBox "SubModel:【  " & submdl.Name & "  】 ... ... 处理完毕！"
	Else
		MsgBox "必须选择一个SubModel！"
	End If
End Sub

'循环所有实体
Sub LoopEntities
	Dim dbscript As String
	Dim curTableName As String
	
	Dim tblnote As String
	Dim tblnote2 As String
	Dim sizeModel As Integer '实体的个数
	Dim aEntity As Entity '一个实体
	'Call deleteSEQUENCEFile
	sizeModel = aModel.Entities.Count
	For Each aEntity In aModel.Entities
		tblnote = aEntity.Note
		tblnote = UCase(Mid(Trim(tblnote), 1, 8))
		tblnote2 = UCase(Mid(Trim(aEntity.Note), 10, 8))
		'MsgBox aEntity.EntityName
		'showEntity(aEntity)
		curTableName = UCase(Trim(aEntity.TableName))
		'If Not (curTableName="KEYTABLE") Then
			If Not only_entity="" Then
				curTableName = aEntity.TableName
				If UCase(Trim(aEntity.TableName))=only_entity Then
					createJavaFile(aEntity)
					If tblnote="NEEDOPER" Then
						createJavaFileForOper(aEntity)
						createJavaFileForBiz(aEntity)
					End If
					If tblnote2="NEEDTEMP" Then
						createJavaFileForTemp(aEntity)
					End If
				End If
			Else
				createJavaFile(aEntity)
				If tblnote="NEEDOPER" Then
					createJavaFileForOper(aEntity)
					createJavaFileForBiz(aEntity)
				End If
				If tblnote2="NEEDTEMP" Then
					createJavaFileForTemp(aEntity)
				End If
			End If
		'End If
		
	Next
Break:
	MsgBox "Entity 创建完成！"
End Sub

'循环生成所有DB
Sub LoopDBs
	Dim dbscript As String
	Dim curTableName As String
	
	Dim sizeModel As Integer '实体的个数
	Dim aEntity As Entity '一个实体

	sizeModel = aModel.Entities.Count
	For Each aEntity In aModel.Entities
		
		curTableName = UCase(Trim(aEntity.TableName))
		'If curTableName="CODE_INFO" Or curTableName="KEYTABLE" Then
		'Else
			If Not only_entity="" Then
				If curTableName=only_entity Then
					dbscript = dbscript & genDBScript(aEntity)
				End If
			Else
				dbscript = dbscript & genDBScript(aEntity)
			End If
		'End If
		'GoTo Break
	Next
	createDBFile(dbscript)
Break:
	MsgBox "DB脚本 创建完成！"
End Sub

'显示一个实体的详细信息
Sub showEntity(aEntity As Entity)

	'一个实体里的所有属性信息对象
	'每个AttributeObj，可以认为是一个实体里一个元素的所有详细信息的集合
	'比如实体：操作员表，里面包含操作员编码、操作员姓名等等
	'那么一个AttributeObj就分别对应操作员编码、操作员姓名
	Dim attrEntity As AttributeObj
	'MsgBox aEntity.Attributes
	For Each attrEntity In aEntity.Attributes
		MsgBox aEntity.TableName & _
			", SequenceNumber=" & attrEntity.SequenceNumber & _
			", AttributeName=" & attrEntity.AttributeName & _
			", Datatype=" & attrEntity.Datatype & _
			", DataScale=" & attrEntity.DataScale & _
			", DeclaredDefault=" & attrEntity.DeclaredDefault & _
			", Definition=" & attrEntity.Definition & _
			", ColumnName=" & attrEntity.ColumnName & _
			", PrimaryKey=" & attrEntity.PrimaryKey
	Next
	'MsgBox Split("sdf.wer", ".")

End Sub

'创建一个JAVA对象实体
'规定：
'表名、字段名必须是小写且以下划线分割（因为本程序会把这些名字统一转换为小写）
Sub createJavaFile(aEntity As Entity)
	Dim tmpVar As Variant

	Dim m_package_prefix As String
	m_package_prefix = "msa.entity"

	Dim m_getMethodTemple As String
	m_getMethodTemple = vbTab & "/** 取得：{property_desc} */" & vbCrLf & _
						vbTab & "public {type} get{Property}(){" & vbCrLf & _
						vbTab & vbTab & "return {property};" & vbCrLf & _
						vbTab & "}" & vbCrLf
	Dim m_setMethodTemple As String
	m_setMethodTemple = vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
						vbTab & "public void set{Property}({type} {property}){" & vbCrLf & _
						vbTab & vbTab & "this.{property}={property};" & vbCrLf & _
						vbTab & "}" & vbCrLf

	Dim m_setNonStringMethodTemple As String
	m_setNonStringMethodTemple = 	vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
									vbTab & "public void set{Property}(String {property}){" & vbCrLf & _
									vbTab & vbTab & "if(!com.hongzhitech.util.ObjectUtil.isEmpty({property}))" & vbCrLf & _
									vbTab & vbTab & vbTab & "this.{property}=new {type}({property});" & vbCrLf & _
									vbTab & "}" & vbCrLf

	Dim file_fix As String
	file_fix = ";" & vbCrLf


	Dim tablename As String
	tablename = LCase(Trim(aEntity.TableName))
	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName)
	Dim javaclassname As String
	javaclassname = Uword(tablename)

	Dim context As String
	context = _
	"package " & m_package_prefix & ";" & vbCrLf & _
	"/**Auto Created by VBScript Do not modify!*/" & vbCrLf & _
	"import java.math.BigDecimal;" & vbCrLf & _
	"import java.util.Set;" & vbCrLf & _
	"import java.util.HashSet;" & vbCrLf & _
	"import java.util.Map;" & vbCrLf & _
	"import java.util.HashMap;" & vbCrLf & _
	"import com.hongzhitech.commons.TableEntity;" & vbCrLf & vbCrLf & _
	"/**" & vbCrLf & _
	" * " & tablename_cn & vbCrLf & _
	" */" & vbCrLf & _
	"public class " & javaclassname & " extends TableEntity" & vbCrLf & "{" & vbCrLf

	'处理每个属性字段
	Dim properties As String '每个属性拼接的串
	Dim getsets As String '每个属性对应的getset方法拼接的串
	Dim propertyName As String, propertyDesc As String, defaultValue As String, javadatatype As String
	Dim javainfo() As String
	Dim propertyArray() As String, propertyTypeArray() As String
	Dim primaryString As String '把表里所有主键已逗号分割存到这个变量里，以便使用isPrimary判断
	Dim sequences As String, sequencesSQL As String '所有被定义为sequence的字段
	sequencesSQL = "#" & tablename_cn & vbCrLf
	Dim i As Integer, max_sequence_name_length As Integer, tmp_i As Integer

	max_sequence_name_length = 40
	i = 0
	Dim attrEntity As AttributeObj
	Dim toString As String
	For Each attrEntity In aEntity.Attributes
		propertyName = LCase(Trim(attrEntity.ColumnName))
		propertyDesc = Trim(attrEntity.AttributeName)
		ReDim javainfo(2) As String
		javainfo = getJavaInfo(attrEntity.Datatype, attrEntity.DeclaredDefault, attrEntity.DataLength, attrEntity.DataScale)
		javadatatype = javainfo(0)
		defaultValue = javainfo(1)

		'拼toString
		toString = toString & vbTab & vbTab & "if(" & propertyName & "!=null)" & vbCrLf
		toString = toString & vbTab & vbTab & "buf.append(""" & propertyName & "="").append("
		Dim tmpPropertyName As String
		tmpPropertyName = propertyName
		If javadatatype <> "String" Then tmpPropertyName = propertyName & ".toString()"
		toString = toString & tmpPropertyName & ").append(',');" & vbCrLf
		toString = toString & vbTab & vbTab & "else" & vbCrLf
		toString = toString & vbTab & vbTab & vbTab & "buf.append(""" & propertyName & "=,"");" & vbCrLf

		'propertyArray(i) = propertyName
		'propertyTypeArray(i) = javadatatype
		'i = i + 1

		'拼接主键
		If (attrEntity.PrimaryKey = "True") Then
			primaryString = primaryString & vbTab & vbTab & "primaryKeys.add(""" & propertyName & """);" & vbCrLf
		End If

		Dim tmp As String
		'替换get
		tmp = m_getMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp, "{type}", javadatatype)
		tmp = Replace(tmp,"{Property}", Uword(propertyName))
		tmp = Replace(tmp,"{property}", propertyName)
		getsets = getsets + tmp
		'替换set
		tmp = m_setMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp,"{type}", javadatatype)
    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
    	tmp = Replace(tmp,"{property}", propertyName)
    	getsets = getsets + tmp
    	'替换非STRING类型的SET
    	If javadatatype <> "String" Then
    		tmp = m_setNonStringMethodTemple
    		tmp = Replace(tmp, "{property_desc}", propertyDesc)
			tmp = Replace(tmp,"{type}", javadatatype)
	    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
	    	tmp = Replace(tmp,"{property}", propertyName)
	    	getsets = getsets + tmp
    	End If

		'替换属性
		properties = properties & vbTab & "private " & _
			javadatatype & " " & propertyName & "; //" & propertyDesc & vbCrLf

		'记录sequence字段，且sequence默认使用表名_字段名字
		If attrEntity.Identity And attrEntity.PrimaryKey Then '实体中把字段名作为key，"表名_字段"作为value
			tmp = tablename '& "_" & propertyName 'sequence名
			If Len(tmp) > max_sequence_name_length Then
				tmp_i = Len(tmp) - max_sequence_name_length
				tmp = Mid(tablename, 1, max_sequence_name_length-Len(propertyName)-1) '超过sequence最长限制，则取max_sequence_name_length
				tmp = tmp & "_" & propertyName
				MsgBox "表：" & tablename & ", sequence:" & propertyName & ", 超长，将被截取为：" & tmp
			End If
			sequences = sequences & vbTab & vbTab & "sequencesMap.add(""" & propertyName & """,""" & tmp & """);" & vbCrLf
			sequencesSQL = sequencesSQL & "DROP SEQUENCE SEQ_" & tmp & ";" & vbCrLf
			sequencesSQL = sequencesSQL & "CREATE SEQUENCE SEQ_" & tmp & vbCrLf & _
				"START WITH 1 MAXVALUE 999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;" & vbCrLf
			'tmpVar = attrEntity.IdentityIncrement
			'tmpVar = attrEntity.IdentitySeed
		End If

	Next

	context = context & properties & vbCrLf & getsets
	'生成toString() 以下被注掉是因为extends TableEntity以后不再需要子类的toString()函数了
	'context = context & vbTab & "public String toString(){" & vbCrLf
	'context = context & vbTab & vbTab & "StringBuffer buf = new StringBuffer();" & vbCrLf
	'context = context & toString
	'context = context & vbTab & vbTab & "return buf.toString();" & vbCrLf & vbTab & "}" & vbCrLf

	'生成判断主键的函数
	context = context & vbTab & "private Set primaryKeys = new HashSet();" & vbCrLf
	context = context & vbTab & "public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } " & vbCrLf

	context = context & vbTab & "public String getPrimaryKey(){return primaryKeys.iterator().next().toString();} " & vbCrLf

	'生成取sequence的函数
	'如果打开以下两行，则需要修改TableEntity，增加一行：public abstract String zrgSequence(String name);
	'context = context & vbTab & "private Map sequencesMap = new HashMap();" & vbCrLf
	'context = context & vbTab & "public String zrgSequence(String name){ return (String)(sequencesMap.get(name)); } " & vbCrLf
	'Call appendSEQUENCEFile(sequencesSQL)

	'建立构造方法
	context = context & _
		vbTab & "/** " & tablename_cn & " */" & vbCrLf & _
		vbTab & "public " & javaclassname & "(){" & vbCrLf & _
		primaryString & _
		vbTab & "}" & vbCrLf

	'结尾
	context = context & "}" & vbCrLf

	'MsgBox context
	Call createFile(m_package_prefix, javaclassname, context)

End Sub

'创建一个JAVA对象操作表实体
Sub createJavaFileForOper(aEntity As Entity)
	Dim tmpVar As Variant

	Dim m_package_prefix As String
	m_package_prefix = "msa.entity"

	Dim m_getMethodTemple As String
	m_getMethodTemple = vbTab & "/** 取得：{property_desc} */" & vbCrLf & _
						vbTab & "public {type} get{Property}(){" & vbCrLf & _
						vbTab & vbTab & "return {property};" & vbCrLf & _
						vbTab & "}" & vbCrLf
	Dim m_setMethodTemple As String
	m_setMethodTemple = vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
						vbTab & "public void set{Property}({type} {property}){" & vbCrLf & _
						vbTab & vbTab & "this.{property}={property};" & vbCrLf & _
						vbTab & "}" & vbCrLf

	Dim m_setNonStringMethodTemple As String
	m_setNonStringMethodTemple = 	vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
									vbTab & "public void set{Property}(String {property}){" & vbCrLf & _
									vbTab & vbTab & "if(!com.hongzhitech.util.ObjectUtil.isEmpty({property}))" & vbCrLf & _
									vbTab & vbTab & vbTab & "this.{property}=new {type}({property});" & vbCrLf & _
									vbTab & "}" & vbCrLf

	Dim file_fix As String
	file_fix = ";" & vbCrLf


	Dim tablename As String
	'表名前增加oper_前缀
	tablename = "oper_" & LCase(Trim(aEntity.TableName))
	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName) & "操作表"
	Dim javaclassname As String
	javaclassname = Uword(tablename)

	Dim context As String
	context = _
	"package " & m_package_prefix & ";" & vbCrLf & vbCrLf & _
	"/**Auto Created by VBScript Do not modify!*/" & vbCrLf & _
	"import java.math.BigDecimal;" & vbCrLf & _
	"import java.util.Set;" & vbCrLf & _
	"import java.util.HashSet;" & vbCrLf & _
	"import java.util.Map;" & vbCrLf & _
	"import java.util.HashMap;" & vbCrLf & _
	"import com.hongzhitech.commons.TableEntity;" & vbCrLf & vbCrLf & _
	"/**" & vbCrLf & _
	" * " & tablename_cn & vbCrLf & _
	" */" & vbCrLf & _
	"public class " & javaclassname & " extends TableEntity" & vbCrLf & "{" & vbCrLf & vbTab & _
	"private String work_id;" & vbCrLf & vbTab & _
	"private String data_type;" & vbCrLf
	
	'处理每个属性字段
	Dim properties As String '每个属性拼接的串
	Dim getsets As String '每个属性对应的getset方法拼接的串
	Dim propertyName As String, propertyDesc As String, defaultValue As String, javadatatype As String
	Dim javainfo() As String
	Dim propertyArray() As String, propertyTypeArray() As String
	Dim primaryString As String '把表里所有主键已逗号分割存到这个变量里，以便使用isPrimary判断
	Dim sequences As String, sequencesSQL As String '所有被定义为sequence的字段
	sequencesSQL = "#" & tablename_cn & vbCrLf
	Dim i As Integer, max_sequence_name_length As Integer, tmp_i As Integer
	
	max_sequence_name_length = 40
	i = 0
	Dim attrEntity As AttributeObj
	Dim toString As String
	For Each attrEntity In aEntity.Attributes
		propertyName = LCase(Trim(attrEntity.ColumnName))
		propertyDesc = Trim(attrEntity.AttributeName)
		ReDim javainfo(2) As String
		javainfo = getJavaInfo(attrEntity.Datatype, attrEntity.DeclaredDefault, attrEntity.DataLength, attrEntity.DataScale)
		javadatatype = javainfo(0)
		defaultValue = javainfo(1)

		'拼toString
		toString = toString & vbTab & vbTab & "if(" & propertyName & "!=null)" & vbCrLf
		toString = toString & vbTab & vbTab & "buf.append(""" & propertyName & "="").append("
		Dim tmpPropertyName As String
		tmpPropertyName = propertyName
		If javadatatype <> "String" Then tmpPropertyName = propertyName & ".toString()"
		toString = toString & tmpPropertyName & ").append(',');" & vbCrLf
		toString = toString & vbTab & vbTab & "else" & vbCrLf
		toString = toString & vbTab & vbTab & vbTab & "buf.append(""" & propertyName & "=,"");" & vbCrLf

		'propertyArray(i) = propertyName
		'propertyTypeArray(i) = javadatatype
		'i = i + 1

		'拼接主键
		If (attrEntity.PrimaryKey = "True") Then
			primaryString = primaryString & vbTab & vbTab & "primaryKeys.add(""" & propertyName & """);" & vbCrLf
		End If

		Dim tmp As String
		'替换get
		tmp = m_getMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp, "{type}", javadatatype)
		tmp = Replace(tmp,"{Property}", Uword(propertyName))
		tmp = Replace(tmp,"{property}", propertyName)
		getsets = getsets + tmp
		'替换set
		tmp = m_setMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp,"{type}", javadatatype)
    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
    	tmp = Replace(tmp,"{property}", propertyName)
    	getsets = getsets + tmp
    	'替换非STRING类型的SET
    	If javadatatype <> "String" Then
    		tmp = m_setNonStringMethodTemple
    		tmp = Replace(tmp, "{property_desc}", propertyDesc)
			tmp = Replace(tmp,"{type}", javadatatype)
	    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
	    	tmp = Replace(tmp,"{property}", propertyName)
	    	getsets = getsets + tmp
    	End If

		'替换属性
		properties = properties & vbTab & "private " & _
			javadatatype & " " & propertyName & "; //" & propertyDesc & vbCrLf

		'记录sequence字段，且sequence默认使用表名_字段名字
		If attrEntity.Identity And attrEntity.PrimaryKey Then '实体中把字段名作为key，"表名_字段"作为value
			tmp = tablename '& "_" & propertyName 'sequence名
			If Len(tmp) > max_sequence_name_length Then
				tmp_i = Len(tmp) - max_sequence_name_length
				tmp = Mid(tablename, 1, max_sequence_name_length-Len(propertyName)-1) '超过sequence最长限制，则取max_sequence_name_length
				tmp = tmp & "_" & propertyName
				MsgBox "表：" & tablename & ", sequence:" & propertyName & ", 超长，将被截取为：" & tmp
			End If
			sequences = sequences & vbTab & vbTab & "sequencesMap.add(""" & propertyName & """,""" & tmp & """);" & vbCrLf
			sequencesSQL = sequencesSQL & "DROP SEQUENCE SEQ_" & tmp & ";" & vbCrLf
			sequencesSQL = sequencesSQL & "CREATE SEQUENCE SEQ_" & tmp & vbCrLf & _
				"START WITH 1 MAXVALUE 999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;" & vbCrLf
			'tmpVar = attrEntity.IdentityIncrement
			'tmpVar = attrEntity.IdentitySeed
		End If

	Next

	context = context & properties & vbCrLf
	context = context & vbCrLf & vbTab & _
	"/** 取得：操作ID */" & vbCrLf & vbTab & _
	"public String getWork_id(){" & vbCrLf & vbTab & vbTab & _
	"return work_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf & vbTab & _
	"/** 设置：操作ID */" & vbCrLf & vbTab & _
	"public void setWork_id(String work_id){" & vbCrLf & vbTab & vbTab & _
	"this.work_id=work_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf & vbTab & _
	"/** 取得：操作数据类型 */" & vbCrLf & vbTab & _
	"public String getData_type(){" & vbCrLf & vbTab & vbTab & _
	"return data_type;" & vbCrLf & vbTab & _
	"}" & vbCrLf & vbTab & _
	"/** 设置：操作数据类型 */" & vbCrLf & vbTab & _
	"public void setData_type(String data_type){" & vbCrLf & vbTab & vbTab & _
	"this.data_type=data_type;" & vbCrLf & vbTab & _
	"}" & vbCrLf
	
	context = context & getsets
	
	'生成toString() 以下被注掉是因为extends TableEntity以后不再需要子类的toString()函数了
	'context = context & vbTab & "public String toString(){" & vbCrLf
	'context = context & vbTab & vbTab & "StringBuffer buf = new StringBuffer();" & vbCrLf
	'context = context & toString
	'context = context & vbTab & vbTab & "return buf.toString();" & vbCrLf & vbTab & "}" & vbCrLf

	'生成判断主键的函数
	context = context & vbTab & "private Set primaryKeys = new HashSet();" & vbCrLf
	context = context & vbTab & "public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } " & vbCrLf

	context = context & vbTab & "public String getPrimaryKey(){return primaryKeys.iterator().next().toString();} " & vbCrLf

	'生成取sequence的函数
	'如果打开以下两行，则需要修改TableEntity，增加一行：public abstract String zrgSequence(String name);
	'context = context & vbTab & "private Map sequencesMap = new HashMap();" & vbCrLf
	'context = context & vbTab & "public String zrgSequence(String name){ return (String)(sequencesMap.get(name)); } " & vbCrLf
	'Call appendSEQUENCEFile(sequencesSQL)

	'建立构造方法
	context = context & _
		vbTab & "/** " & tablename_cn & " */" & vbCrLf & _
		vbTab & "public " & javaclassname & "(){" & vbCrLf & vbTab & vbTab & _
		"primaryKeys.add(""work_id"");" & vbCrLf & vbTab & vbTab & _
		"primaryKeys.add(""data_type"");" & vbCrLf & _
		primaryString & _
		vbTab & "}" & vbCrLf

	'结尾
	context = context & "}" & vbCrLf
	
	'MsgBox m_package_prefix
	'MsgBox javaclassname
	'MsgBox context
	Call createFile(m_package_prefix, javaclassname, context)

End Sub

'创建一个JAVA对象业务表实体
Sub createJavaFileForBiz(aEntity As Entity)
	Dim tmpVar As Variant

	Dim m_package_prefix As String
	m_package_prefix = "msa.entity"

	Dim m_getMethodTemple As String
	m_getMethodTemple = vbTab & "/** 取得：{property_desc} */" & vbCrLf & _
						vbTab & "public {type} get{Property}(){" & vbCrLf & _
						vbTab & vbTab & "return {property};" & vbCrLf & _
						vbTab & "}" & vbCrLf
	Dim m_setMethodTemple As String
	m_setMethodTemple = vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
						vbTab & "public void set{Property}({type} {property}){" & vbCrLf & _
						vbTab & vbTab & "this.{property}={property};" & vbCrLf & _
						vbTab & "}" & vbCrLf

	Dim m_setNonStringMethodTemple As String
	m_setNonStringMethodTemple = 	vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
									vbTab & "public void set{Property}(String {property}){" & vbCrLf & _
									vbTab & vbTab & "if(!com.hongzhitech.util.ObjectUtil.isEmpty({property}))" & vbCrLf & _
									vbTab & vbTab & vbTab & "this.{property}=new {type}({property});" & vbCrLf & _
									vbTab & "}" & vbCrLf

	Dim file_fix As String
	file_fix = ";" & vbCrLf


	Dim tablename As String
	'表名前增加biz_前缀
	tablename = "biz_" & LCase(Trim(aEntity.TableName))
	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName) & "操作表"
	Dim javaclassname As String
	javaclassname = Uword(tablename)

	Dim context As String
	context = _
	"package " & m_package_prefix & ";" & vbCrLf & vbCrLf & _
	"/**Auto Created by VBScript Do not modify!*/" & vbCrLf & _
	"import java.math.BigDecimal;" & vbCrLf & _
	"import java.util.Set;" & vbCrLf & _
	"import java.util.HashSet;" & vbCrLf & _
	"import java.util.Map;" & vbCrLf & _
	"import java.util.HashMap;" & vbCrLf & _
	"import com.hongzhitech.commons.TableEntity;" & vbCrLf & vbCrLf & _
	"/**" & vbCrLf & _
	" * " & tablename_cn & vbCrLf & _
	" */" & vbCrLf & _
	"public class " & javaclassname & " extends TableEntity" & vbCrLf & "{" & vbCrLf & vbTab & _
	"private String biz_id;" & vbCrLf
	
	'处理每个属性字段
	Dim properties As String '每个属性拼接的串
	Dim getsets As String '每个属性对应的getset方法拼接的串
	Dim propertyName As String, propertyDesc As String, defaultValue As String, javadatatype As String
	Dim javainfo() As String
	Dim propertyArray() As String, propertyTypeArray() As String
	Dim primaryString As String '把表里所有主键已逗号分割存到这个变量里，以便使用isPrimary判断
	Dim sequences As String, sequencesSQL As String '所有被定义为sequence的字段
	sequencesSQL = "#" & tablename_cn & vbCrLf
	Dim i As Integer, max_sequence_name_length As Integer, tmp_i As Integer
	
	max_sequence_name_length = 40
	i = 0
	Dim attrEntity As AttributeObj
	Dim toString As String
	For Each attrEntity In aEntity.Attributes
		propertyName = LCase(Trim(attrEntity.ColumnName))
		propertyDesc = Trim(attrEntity.AttributeName)
		ReDim javainfo(2) As String
		javainfo = getJavaInfo(attrEntity.Datatype, attrEntity.DeclaredDefault, attrEntity.DataLength, attrEntity.DataScale)
		javadatatype = javainfo(0)
		defaultValue = javainfo(1)

		'拼toString
		toString = toString & vbTab & vbTab & "if(" & propertyName & "!=null)" & vbCrLf
		toString = toString & vbTab & vbTab & "buf.append(""" & propertyName & "="").append("
		Dim tmpPropertyName As String
		tmpPropertyName = propertyName
		If javadatatype <> "String" Then tmpPropertyName = propertyName & ".toString()"
		toString = toString & tmpPropertyName & ").append(',');" & vbCrLf
		toString = toString & vbTab & vbTab & "else" & vbCrLf
		toString = toString & vbTab & vbTab & vbTab & "buf.append(""" & propertyName & "=,"");" & vbCrLf

		'propertyArray(i) = propertyName
		'propertyTypeArray(i) = javadatatype
		'i = i + 1

		'拼接主键
		If (attrEntity.PrimaryKey = "True") Then
			primaryString = primaryString & vbTab & vbTab & "primaryKeys.add(""" & propertyName & """);" & vbCrLf
		End If

		Dim tmp As String
		'替换get
		tmp = m_getMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp, "{type}", javadatatype)
		tmp = Replace(tmp,"{Property}", Uword(propertyName))
		tmp = Replace(tmp,"{property}", propertyName)
		getsets = getsets + tmp
		'替换set
		tmp = m_setMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp,"{type}", javadatatype)
    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
    	tmp = Replace(tmp,"{property}", propertyName)
    	getsets = getsets + tmp
    	'替换非STRING类型的SET
    	If javadatatype <> "String" Then
    		tmp = m_setNonStringMethodTemple
    		tmp = Replace(tmp, "{property_desc}", propertyDesc)
			tmp = Replace(tmp,"{type}", javadatatype)
	    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
	    	tmp = Replace(tmp,"{property}", propertyName)
	    	getsets = getsets + tmp
    	End If

		'替换属性
		properties = properties & vbTab & "private " & _
			javadatatype & " " & propertyName & "; //" & propertyDesc & vbCrLf

		'记录sequence字段，且sequence默认使用表名_字段名字
		If attrEntity.Identity And attrEntity.PrimaryKey Then '实体中把字段名作为key，"表名_字段"作为value
			tmp = tablename '& "_" & propertyName 'sequence名
			If Len(tmp) > max_sequence_name_length Then
				tmp_i = Len(tmp) - max_sequence_name_length
				tmp = Mid(tablename, 1, max_sequence_name_length-Len(propertyName)-1) '超过sequence最长限制，则取max_sequence_name_length
				tmp = tmp & "_" & propertyName
				MsgBox "表：" & tablename & ", sequence:" & propertyName & ", 超长，将被截取为：" & tmp
			End If
			sequences = sequences & vbTab & vbTab & "sequencesMap.add(""" & propertyName & """,""" & tmp & """);" & vbCrLf
			sequencesSQL = sequencesSQL & "DROP SEQUENCE SEQ_" & tmp & ";" & vbCrLf
			sequencesSQL = sequencesSQL & "CREATE SEQUENCE SEQ_" & tmp & vbCrLf & _
				"START WITH 1 MAXVALUE 999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;" & vbCrLf
			'tmpVar = attrEntity.IdentityIncrement
			'tmpVar = attrEntity.IdentitySeed
		End If

	Next

	context = context & properties & vbCrLf
	context = context & vbCrLf & vbTab & _
	"/** 取得：业务ID */" & vbCrLf & vbTab & _
	"public String getBiz_id(){" & vbCrLf & vbTab & vbTab & _
	"return biz_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf & vbTab & _
	"/** 设置：业务ID */" & vbCrLf & vbTab & _
	"public void setBiz_id(String biz_id){" & vbCrLf & vbTab & vbTab & _
	"this.biz_id=biz_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf
	
	context = context & getsets
	
	'生成toString() 以下被注掉是因为extends TableEntity以后不再需要子类的toString()函数了
	'context = context & vbTab & "public String toString(){" & vbCrLf
	'context = context & vbTab & vbTab & "StringBuffer buf = new StringBuffer();" & vbCrLf
	'context = context & toString
	'context = context & vbTab & vbTab & "return buf.toString();" & vbCrLf & vbTab & "}" & vbCrLf

	'生成判断主键的函数
	context = context & vbTab & "private Set primaryKeys = new HashSet();" & vbCrLf
	context = context & vbTab & "public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } " & vbCrLf

	context = context & vbTab & "public String getPrimaryKey(){return primaryKeys.iterator().next().toString();} " & vbCrLf

	'生成取sequence的函数
	'如果打开以下两行，则需要修改TableEntity，增加一行：public abstract String zrgSequence(String name);
	'context = context & vbTab & "private Map sequencesMap = new HashMap();" & vbCrLf
	'context = context & vbTab & "public String zrgSequence(String name){ return (String)(sequencesMap.get(name)); } " & vbCrLf
	'Call appendSEQUENCEFile(sequencesSQL)

	'建立构造方法
	context = context & _
		vbTab & "/** " & tablename_cn & " */" & vbCrLf & _
		vbTab & "public " & javaclassname & "(){" & vbCrLf & vbTab & vbTab & _
		"primaryKeys.add(""biz_id"");" & vbCrLf & _
		primaryString & _
		vbTab & "}" & vbCrLf

	'结尾
	context = context & "}" & vbCrLf
	
	'MsgBox m_package_prefix
	'MsgBox javaclassname
	'MsgBox context
	Call createFile(m_package_prefix, javaclassname, context)

End Sub

'创建一个JAVA对象临时表实体
Sub createJavaFileForTemp(aEntity As Entity)
	Dim tmpVar As Variant

	Dim m_package_prefix As String
	m_package_prefix = "msa.entity"

	Dim m_getMethodTemple As String
	m_getMethodTemple = vbTab & "/** 取得：{property_desc} */" & vbCrLf & _
						vbTab & "public {type} get{Property}(){" & vbCrLf & _
						vbTab & vbTab & "return {property};" & vbCrLf & _
						vbTab & "}" & vbCrLf
	Dim m_setMethodTemple As String
	m_setMethodTemple = vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
						vbTab & "public void set{Property}({type} {property}){" & vbCrLf & _
						vbTab & vbTab & "this.{property}={property};" & vbCrLf & _
						vbTab & "}" & vbCrLf

	Dim m_setNonStringMethodTemple As String
	m_setNonStringMethodTemple = 	vbTab & "/** 设置：{property_desc} */" & vbCrLf & _
									vbTab & "public void set{Property}(String {property}){" & vbCrLf & _
									vbTab & vbTab & "if(!com.hongzhitech.util.ObjectUtil.isEmpty({property}))" & vbCrLf & _
									vbTab & vbTab & vbTab & "this.{property}=new {type}({property});" & vbCrLf & _
									vbTab & "}" & vbCrLf

	Dim file_fix As String
	file_fix = ";" & vbCrLf


	Dim tablename As String
	'表名前增加oper_前缀
	tablename = "temp_" & LCase(Trim(aEntity.TableName))
	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName) & "临时表"
	Dim javaclassname As String
	javaclassname = Uword(tablename)

	Dim context As String
	context = _
	"package " & m_package_prefix & ";" & vbCrLf & vbCrLf & _
	"/**Auto Created by VBScript Do not modify!*/" & vbCrLf & _
	"import java.math.BigDecimal;" & vbCrLf & _
	"import java.util.Set;" & vbCrLf & _
	"import java.util.HashSet;" & vbCrLf & _
	"import java.util.Map;" & vbCrLf & _
	"import java.util.HashMap;" & vbCrLf & _
	"import com.hongzhitech.commons.TableEntity;" & vbCrLf & vbCrLf & _
	"/**" & vbCrLf & _
	" * " & tablename_cn & vbCrLf & _
	" */" & vbCrLf & _
	"public class " & javaclassname & " extends TableEntity" & vbCrLf & "{" & vbCrLf & vbTab & _
	"private String work_id;" & vbCrLf
	
	'处理每个属性字段
	Dim properties As String '每个属性拼接的串
	Dim getsets As String '每个属性对应的getset方法拼接的串
	Dim propertyName As String, propertyDesc As String, defaultValue As String, javadatatype As String
	Dim javainfo() As String
	Dim propertyArray() As String, propertyTypeArray() As String
	Dim primaryString As String '把表里所有主键已逗号分割存到这个变量里，以便使用isPrimary判断
	Dim sequences As String, sequencesSQL As String '所有被定义为sequence的字段
	sequencesSQL = "#" & tablename_cn & vbCrLf
	Dim i As Integer, max_sequence_name_length As Integer, tmp_i As Integer
	
	max_sequence_name_length = 40
	i = 0
	Dim attrEntity As AttributeObj
	Dim toString As String
	For Each attrEntity In aEntity.Attributes
		propertyName = LCase(Trim(attrEntity.ColumnName))
		propertyDesc = Trim(attrEntity.AttributeName)
		ReDim javainfo(2) As String
		javainfo = getJavaInfo(attrEntity.Datatype, attrEntity.DeclaredDefault, attrEntity.DataLength, attrEntity.DataScale)
		javadatatype = javainfo(0)
		defaultValue = javainfo(1)

		'拼toString
		toString = toString & vbTab & vbTab & "if(" & propertyName & "!=null)" & vbCrLf
		toString = toString & vbTab & vbTab & "buf.append(""" & propertyName & "="").append("
		Dim tmpPropertyName As String
		tmpPropertyName = propertyName
		If javadatatype <> "String" Then tmpPropertyName = propertyName & ".toString()"
		toString = toString & tmpPropertyName & ").append(',');" & vbCrLf
		toString = toString & vbTab & vbTab & "else" & vbCrLf
		toString = toString & vbTab & vbTab & vbTab & "buf.append(""" & propertyName & "=,"");" & vbCrLf

		'propertyArray(i) = propertyName
		'propertyTypeArray(i) = javadatatype
		'i = i + 1

		'拼接主键
		If (attrEntity.PrimaryKey = "True") Then
			primaryString = primaryString & vbTab & vbTab & "primaryKeys.add(""" & propertyName & """);" & vbCrLf
		End If

		Dim tmp As String
		'替换get
		tmp = m_getMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp, "{type}", javadatatype)
		tmp = Replace(tmp,"{Property}", Uword(propertyName))
		tmp = Replace(tmp,"{property}", propertyName)
		getsets = getsets + tmp
		'替换set
		tmp = m_setMethodTemple
		tmp = Replace(tmp, "{property_desc}", propertyDesc)
		tmp = Replace(tmp,"{type}", javadatatype)
    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
    	tmp = Replace(tmp,"{property}", propertyName)
    	getsets = getsets + tmp
    	'替换非STRING类型的SET
    	If javadatatype <> "String" Then
    		tmp = m_setNonStringMethodTemple
    		tmp = Replace(tmp, "{property_desc}", propertyDesc)
			tmp = Replace(tmp,"{type}", javadatatype)
	    	tmp = Replace(tmp,"{Property}", Uword(propertyName))
	    	tmp = Replace(tmp,"{property}", propertyName)
	    	getsets = getsets + tmp
    	End If

		'替换属性
		properties = properties & vbTab & "private " & _
			javadatatype & " " & propertyName & "; //" & propertyDesc & vbCrLf

		'记录sequence字段，且sequence默认使用表名_字段名字
		If attrEntity.Identity And attrEntity.PrimaryKey Then '实体中把字段名作为key，"表名_字段"作为value
			tmp = tablename '& "_" & propertyName 'sequence名
			If Len(tmp) > max_sequence_name_length Then
				tmp_i = Len(tmp) - max_sequence_name_length
				tmp = Mid(tablename, 1, max_sequence_name_length-Len(propertyName)-1) '超过sequence最长限制，则取max_sequence_name_length
				tmp = tmp & "_" & propertyName
				MsgBox "表：" & tablename & ", sequence:" & propertyName & ", 超长，将被截取为：" & tmp
			End If
			sequences = sequences & vbTab & vbTab & "sequencesMap.add(""" & propertyName & """,""" & tmp & """);" & vbCrLf
			sequencesSQL = sequencesSQL & "DROP SEQUENCE SEQ_" & tmp & ";" & vbCrLf
			sequencesSQL = sequencesSQL & "CREATE SEQUENCE SEQ_" & tmp & vbCrLf & _
				"START WITH 1 MAXVALUE 999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;" & vbCrLf
			'tmpVar = attrEntity.IdentityIncrement
			'tmpVar = attrEntity.IdentitySeed
		End If

	Next

	context = context & properties & vbCrLf
	context = context & vbCrLf & vbTab & _
	"/** 取得：操作ID */" & vbCrLf & vbTab & _
	"public String getWork_id(){" & vbCrLf & vbTab & vbTab & _
	"return work_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf & vbTab & _
	"/** 设置：操作ID */" & vbCrLf & vbTab & _
	"public void setWork_id(String work_id){" & vbCrLf & vbTab & vbTab & _
	"this.work_id=work_id;" & vbCrLf & vbTab & _
	"}" & vbCrLf
	
	context = context & getsets
	
	'生成toString() 以下被注掉是因为extends TableEntity以后不再需要子类的toString()函数了
	'context = context & vbTab & "public String toString(){" & vbCrLf
	'context = context & vbTab & vbTab & "StringBuffer buf = new StringBuffer();" & vbCrLf
	'context = context & toString
	'context = context & vbTab & vbTab & "return buf.toString();" & vbCrLf & vbTab & "}" & vbCrLf

	'生成判断主键的函数
	context = context & vbTab & "private Set primaryKeys = new HashSet();" & vbCrLf
	context = context & vbTab & "public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } " & vbCrLf

	context = context & vbTab & "public String getPrimaryKey(){return primaryKeys.iterator().next().toString();} " & vbCrLf

	'生成取sequence的函数
	'如果打开以下两行，则需要修改TableEntity，增加一行：public abstract String zrgSequence(String name);
	'context = context & vbTab & "private Map sequencesMap = new HashMap();" & vbCrLf
	'context = context & vbTab & "public String zrgSequence(String name){ return (String)(sequencesMap.get(name)); } " & vbCrLf
	'Call appendSEQUENCEFile(sequencesSQL)

	'建立构造方法
	context = context & _
		vbTab & "/** " & tablename_cn & " */" & vbCrLf & _
		vbTab & "public " & javaclassname & "(){" & vbCrLf & vbTab & vbTab & _
		"primaryKeys.add(""work_id"");" & vbCrLf & _
		primaryString & _
		vbTab & "}" & vbCrLf

	'结尾
	context = context & "}" & vbCrLf
	
	'MsgBox m_package_prefix
	'MsgBox javaclassname
	'MsgBox context
	Call createFile(m_package_prefix, javaclassname, context)

End Sub

'转换单词首字母
Function Uword(word As String)
	Dim first_word As String

	first_word = UCase(Mid(word, 1,1)) & Mid(word,2,Len(word))

	Uword = first_word
End Function

Function getJavaInfo(entityType As String, defaultValue As String, datalen As Integer, dataScale As Integer)
	Dim returnValue(2) As String
	Dim cur_entityType As String, javaType As String

	cur_entityType = LCase(Trim(entityType))
	Select Case cur_entityType
		Case "char","varchar", "varchar"
			javaType = "String"
		Case "date","time"
			javaType = "String"
		Case "tinyint","int","integer"
			javaType = "Integer"
		Case "bigint"
			javaType = "Long"
		Case "numeric","decimal","DECIMAL"
			If dataScale>0 Then
				javaType = "BigDecimal"
			Else
				If datalen>9 Then
					javaType = "Long"
				Else
					javaType = "Integer"
				End If
			End If
		Case Else
			javaType = "WrongType"
	End Select
	returnValue(0) = javaType
	If defaultValue="" Then
		returnValue(1)="null"
	Else
		returnValue(1)="new " & javaType & "(""" & defaultValue & """)"
	End If
	getJavaInfo = returnValue
End Function

'创建文件
Sub createFile(package_prefix As String, filename As String, context As String)
	Dim m_filerootdir As String
	m_filerootdir = javafile_rootpath & "\"
	
	Dim m_package_arr (2) As String
	m_package_arr(0) = "msa"
	m_package_arr(1) = "entity"
	'使用包名字数组m_package_arr创建包目录
	
	Open m_filerootdir & Replace(package_prefix, ".", "\") & "\" & filename & ".java" For Output As #1
	Print #1, context
	Close #1

End Sub

'生成索引 例如： create index ou_remark on ounit(ou_remark) tablespace msa_index01;
Function genDBIndexScript(aEntity As Entity)
	Dim context As String, indexname As String, tmpString As String
	Dim tablename As String, tblnote As String
	Dim isForeignKey As Boolean
	Dim var As Variant

	tablename = UCase(Trim(aEntity.TableName))

	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName)

	Dim ColumnName As String, primaryString As String
	Dim attrEntity As AttributeObj
	Dim fillspace_nums As Integer

	For Each attrEntity In aEntity.Attributes
		tmpString = UCase(Trim(attrEntity.CheckConstraint))
		isForeignKey = attrEntity.ForeignKey
		ColumnName = UCase(Trim(attrEntity.AttributeName))

		If tmpString="UNIQUE" Or isForeignKey Then
			indexname = tablename & "_IDX"
			context = "CREATE INDEX " & indexname & "ON " & tablename & "(" & ColumnName & ")"
			If Not (indexTSName = "") Then
				context = context & " tablespace " & Trim(indexTSName)
			End If
			context = context & vbCrLf
		End If

	Next

End Function

Function genDBScript(aEntity As Entity)
	Dim context As String, context_head As String, attrName As String
	Dim tablename As String, tblnote As String, tmpString As String
	Dim dicts As Dictionary
	Dim dom As Domain

	Set dicts = aDiagram.Dictionary
	tablename = UCase(Trim(aEntity.TableName))

	Dim tablename_cn As String
	tablename_cn = Trim(aEntity.EntityName)
	
	context_head = "#" & tablename_cn & vbCrLf & _
		"DROP TABLE IF EXISTS " & tablename & " ;" & vbCrLf & _
		"CREATE TABLE " & tablename & "(" & vbCrLf
	Dim ColumnName As String, primaryString As String, chkString As String, operPKString As String
	Dim attrEntity As AttributeObj
	Dim fillspace_nums As Integer
	Dim attrArray() As AttributeObj
	ReDim attrArray(aEntity.Attributes.Count) As AttributeObj
	
	For Each attrEntity In aEntity.Attributes
		Set attrArray(attrEntity.SequenceNumber) = attrEntity
	Next

	Dim i As Integer, nums As Integer
	nums = aEntity.Attributes.Count
	For i=1 To nums
		Set attrEntity = attrArray(i)

		ColumnName = UCase(Trim(attrEntity.ColumnName))
		attrName = attrEntity.AttributeName
		fillspace_nums = 50-Len(ColumnName)
		context = context & _
			ColumnName & fillString(fillspace_nums, " ") & _
			genTableLine(attrEntity.Datatype, attrEntity.DeclaredDefault, attrEntity.DataLength, attrEntity.DataScale) & " " & _
			attrEntity.NullOption & ", #" & attrName & vbCrLf
		
		If (attrEntity.PrimaryKey = "True") Then
			primaryString = primaryString & ColumnName & ","
		End If
		'处理各种约束
		Set dom = dicts.Domains.Item(attrEntity.DomainId)
		'比例－两位精度
		If Not (dom Is Nothing) Then
			tmpString = dom.Name
			If tmpString="比例－两位精度" Then
				chkString = chkString & "CONSTRAINT " & tablename & "_CHK" & i & " CHECK(" & ColumnName & "<=100.00)," & vbCrLf
			End If
		End If
	Next
	
	primaryString = Mid(primaryString, 1, Len(primaryString)-1)
	genDBScript = context_head & context & chkString & "CONSTRAINT " & tablename & "_PK PRIMARY KEY(" & primaryString & ") " & primaryKeyTS & "  );" & vbCrLf & vbCrLf
	
	
	
	'如果需要则自动生成OPER表
	tblnote = aEntity.Note
	tblnote = UCase(Mid(Trim(tblnote), 1, 8))
	If tblnote="NEEDOPER" Then
		context_head = "#" & tablename_cn & " （操作表）" & vbCrLf & _
			"DROP TABLE " & "OPER_" & tablename & ";" & vbCrLf & _
			"CREATE TABLE " & "OPER_" & tablename & "(" & vbCrLf
		genDBScript = genDBScript & context_head
		genDBScript = genDBScript & "WORK_ID VARCHAR(38) NOT NULL," & vbCrLf
		genDBScript = genDBScript & "DATA_TYPE CHAR(1) NOT NULL," & vbCrLf
		genDBScript = genDBScript & context
		operPKString = "WORK_ID, DATA_TYPE, " & primaryString
		genDBScript = genDBScript & "CONSTRAINT " & "OPER_" & tablename & "_PK PRIMARY KEY(" & operPKString & ") " & primaryKeyTS & "  );" & vbCrLf & vbCrLf
	End If
	
	'如果需要则自动生成BIZ表
	tblnote = aEntity.Note
	tblnote = UCase(Mid(Trim(tblnote), 1, 8))
	If tblnote="NEEDOPER" Then
		context_head = "#" & tablename_cn & " （业务表）" & vbCrLf & _
			"DROP TABLE " & "BIZ_" & tablename & ";" & vbCrLf & _
			"CREATE TABLE " & "BIZ_" & tablename & "(" & vbCrLf
		genDBScript = genDBScript & context_head
		genDBScript = genDBScript & "BIZ_ID VARCHAR(38) NOT NULL," & vbCrLf
		genDBScript = genDBScript & context
		operPKString = "BIZ_ID, " & primaryString
		genDBScript = genDBScript & "CONSTRAINT " & "BIZ_" & tablename & "_PK PRIMARY KEY(" & operPKString & ") " & primaryKeyTS & "  );" & vbCrLf & vbCrLf
	End If
	
	'如果需要则自动生成TEMP表
	tblnote = aEntity.Note
	tblnote = UCase(Mid(Trim(tblnote), 10, 8))
	If tblnote="NEEDTEMP" Then
		context_head = "#" & tablename_cn & " （临时表）" & vbCrLf & _
			"DROP TABLE " & "TEMP_" & tablename & ";" & vbCrLf & _
			"CREATE TABLE " & "TEMP_" & tablename & "(" & vbCrLf
		genDBScript = genDBScript & context_head
		genDBScript = genDBScript & "WORK_ID VARCHAR(38) NOT NULL," & vbCrLf
		genDBScript = genDBScript & context
		operPKString = "WORK_ID, " & primaryString
		genDBScript = genDBScript & "CONSTRAINT " & "TEMP_" & tablename & "_PK PRIMARY KEY(" & operPKString & ") " & primaryKeyTS & "  );" & vbCrLf & vbCrLf
	End If
End Function

Function genTableLine(entityType As String, defaultValue As String, datalen As Integer, dataScale As Integer)
	Dim returnValue(2) As String
	Dim cur_entityType As String, columnType As String

	cur_entityType = LCase(Trim(entityType))
	Select Case cur_entityType
		Case "char"
			columnType = "CHAR(" & datalen & ")"
			If defaultValue <> "" Then columnType = columnType & " default '" & defaultValue & "'"
		Case "varchar", "varchar"
			columnType = "VARCHAR(" & datalen & ")"
			If defaultValue <> "" Then columnType = columnType & " default '" & defaultValue & "'"
		Case "date"
			columnType = "CHAR(8)"
			If defaultValue <> "" Then columnType = columnType & " default '" & defaultValue & "'"
		Case "time"
			columnType = "CHAR(6)"
			If defaultValue <> "" Then columnType = columnType & " default '" & defaultValue & "'"
		Case "datetime"
			columnType = "CHAR(14)"
			If defaultValue <> "" Then columnType = columnType & " default '" & defaultValue & "'"
		Case "tinyint","int","integer","bigint"
			columnType = UCase(cur_entityType)
			If defaultValue <> "" Then
				columnType = columnType & " default " & defaultValue
			Else
				columnType = columnType & " default 0"
			End If
		Case "numeric","decimal","DECIMAL"
			If dataScale>0 Then
				columnType = "DECIMAL(" & datalen & "," & dataScale & ")"
			Else
				columnType = "DECIMAL(" & datalen & ")"
			End If
			If defaultValue <> "" Then
				columnType = columnType & " default " & defaultValue
			Else
				'columnType = columnType & " default 0" '由于存在确实需要把numeric字段设置为NULL的情况，所以注释掉该句
			End If

		Case Else
			columnType = "WrongType"
	End Select
	genTableLine = columnType
End Function

Sub createDBFile(context As String)
	Dim filename As String
	filename = dbscript_rootpath & "\01-create_table-mysql.sql"
	
	Open filename For Output As #1
	Print #1, context
	Close #1

End Sub

Sub appendSEQUENCEFile(context As String)
	Dim filename As String
	filename = dbscript_rootpath & "\02-seq-mysql.sql"
	
	Open filename For Append As #1
	Print #1, context
	Close #1
End Sub
Sub deleteSEQUENCEFile
	Dim filename As String
	filename = dbscript_rootpath & "\02-seq-mysql.sql"
	
	Open filename For Output As #1
	Print #1, ""
	Close #1
End Sub

Function fillString(size As Integer, ch As String)
	Dim i As Integer
	For i=1 To size
		fillString = fillString & ch
	Next
End Function

Sub initNeedBakTables
	Dim strNeedBakTables As String

	'needBakTables
End Sub

Sub initNeedOperTables
	Dim strNeedOperTables As String

	'needOperTables
End Sub






