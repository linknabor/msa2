package com.eshequ.msa.crm.model.repairmng;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
private String userid;
 private String name;
 private String mobile;
 private String gender;
 private String email;
 private String avatar;//头像url
 private String qr_code;//二维码
 private String position;//职位
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAvatar() {
	return avatar;
}
public void setAvatar(String avatar) {
	this.avatar = avatar;
}
public String getQr_code() {
	return qr_code;
}
public void setQr_code(String qr_code) {
	this.qr_code = qr_code;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
 
 
}
