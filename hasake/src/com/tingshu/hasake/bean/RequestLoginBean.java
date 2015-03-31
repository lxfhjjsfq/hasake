package com.tingshu.hasake.bean;

/**
 * 用户注册
 * @author Dell
 *
 */
public class RequestLoginBean {
	private String Email;
	private String Phone;
	private String Password;
	private String NickName;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	
	

}
