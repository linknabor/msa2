package com.eshequ.msa.crm.model.repairmng;

import java.io.Serializable;

public class AccessToken implements Serializable{
 
    private String errcode;
 
    private String errmsg;
 
    private String access_token;
 
    private Long expires_in;
 
    public String getErrcode() {
        return errcode;
    }
 
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
 
    public String getErrmsg() {
        return errmsg;
    }
 
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
 
    public String getAccess_token() {
        return access_token;
    }
 
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
 
    public Long getExpires_in() {
        return expires_in;
    }
 
    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
 
    @Override
    public String toString() {
        return "AccessToken{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
