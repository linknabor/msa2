package com.eshequ.msa.common;

public class Constants {

	public static final String USER = "sessionUser";

	// 获取access_token的url
	public static final String ACCESS_TOKER_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	
	// 根据code获取成员信息的url
	public static final String GET_OAUTH2_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    
	//获取成员详细信息
	public static final String GET_UserInfo_URL="https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN";
	
	//根据userId获取成员信息
	public static final String GET_USERINFO="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
	
	//获取部门成员list
	public static final String GET_DEPARTMENT_USERINFO="https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=0";

    //获取企业的jsapi_ticket
	public static final String GET_jsapi_ticket="https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";
	
	//发送消息
	public static final String SEND_MESSAGE="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
}
