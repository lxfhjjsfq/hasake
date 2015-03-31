package com.tingshu.hasake.database;

public class DownloadDao {

	private int id;
	private String album;
	private String kind;
	private String name;
	private String pic;
	private String downUrl;
	private String path;
	private String state;
	private long curLength;
	private long totalLength;
	
	
	
	public DownloadDao(int id, String album, String kind, String name,
			String pic, String downUrl, String path, String state,
			long curLength, long totalLength) {
		super();
		this.id = id;
		this.album = album;
		this.kind = kind;
		this.name = name;
		this.pic = pic;
		this.downUrl = downUrl;
		this.path = path;
		this.state = state;
		this.curLength = curLength;
		this.totalLength = totalLength;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getCurLength() {
		return curLength;
	}
	public void setCurLength(long curLength) {
		this.curLength = curLength;
	}
	public long getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(long totalLength) {
		this.totalLength = totalLength;
	}
	
	
}
