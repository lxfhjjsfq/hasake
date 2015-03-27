package com.tingshu.hasake.bean;

public class TypeBean {
	private int typeId;
	private int icon_id;
	private String type_name;
	public TypeBean(int typeId, int icon_id, String type_name) {
		super();
		this.typeId = typeId;
		this.icon_id = icon_id;
		this.type_name = type_name;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	

}
