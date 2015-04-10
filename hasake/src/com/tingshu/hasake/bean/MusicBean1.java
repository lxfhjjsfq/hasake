package com.tingshu.hasake.bean;

public class MusicBean1 {
	//{"Name":"鬼吹灯第一集",
	//"Background":"http://www.baidu.com",
	//"ID":1,
	//"AddTime":null,
	//"Praise":0,
	//"Count":0,
	//"Repair":0,
	//"Nickname":"????",
	//"IsPraise":0}

	private String Name;
	private String Background;
	private int ID;
	private String AddTime;
	private int Praise;
	private int Count;
	private int Repair;
	private String Nickname;
	private int IsPraise;//是否已点赞0未点赞1已点赞
	private String VideoUrl;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBackground() {
		return Background;
	}
	public void setBackground(String background) {
		Background = background;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAddTime() {
		return AddTime;
	}
	public void setAddTime(String addTime) {
		AddTime = addTime;
	}
	public int getPraise() {
		return Praise;
	}
	public void setPraise(int praise) {
		Praise = praise;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public int getRepair() {
		return Repair;
	}
	public void setRepair(int repair) {
		Repair = repair;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getIsPraise() {
		return IsPraise;
	}
	public void setIsPraise(int isPraise) {
		IsPraise = isPraise;
	}
	public String getVideoUrl() {
		return VideoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		VideoUrl = videoUrl;
	}
	
}
