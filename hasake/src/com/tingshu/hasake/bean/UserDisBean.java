package com.tingshu.hasake.bean;

public class UserDisBean {
	// {"Result":{"id":1,"nickname":"玩玩樂樂","Sex":0,"Email":"1441135@qq.com","Phone":null,"HeadImg":null,"FrontCover":null,"Summary":"??SB","Birthday":"1920-08-09T00:00:00","City":null,"Province":null,"Attest":null,"ViderCount":6,"FansCount":4,"Good":0},"Code":10000}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public String getFrontCover() {
		return FrontCover;
	}
	public void setFrontCover(String frontCover) {
		FrontCover = frontCover;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public int getViderCount() {
		return ViderCount;
	}
	public void setViderCount(int viderCount) {
		ViderCount = viderCount;
	}
	public int getFansCount() {
		return FansCount;
	}
	public void setFansCount(int fansCount) {
		FansCount = fansCount;
	}
	public int getGood() {
		return Good;
	}
	public void setGood(int good) {
		Good = good;
	}
	private String nickname;
	private int Sex;
	private String HeadImg;
	private String FrontCover;
	private String Summary;
	private int ViderCount;
	private int FansCount;
	private int Good;

}
