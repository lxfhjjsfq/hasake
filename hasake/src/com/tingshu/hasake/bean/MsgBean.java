package com.tingshu.hasake.bean;

public class MsgBean {
	private String AddTime;
	private String Content;
	private String Nickname;
	
	public String getAddTime() {
		return AddTime;
	}
	public void setAddTime(String addTime) {
		AddTime = addTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	//{"Result":[{"AddTime":"2015-04-05T22:36:31","Content":"新年快乐","Nickname":null}],"Code":10000}

}
