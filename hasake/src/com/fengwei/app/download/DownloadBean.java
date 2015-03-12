package com.fengwei.app.download;


import java.io.Serializable;

/**
 * 下载实体，记录下载信息
 *
 * @author lxf
 */
public class DownloadBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1490912668215627404L;
    private String url;        //下载url字符串
    private String path;    //存储路径
    private int state;
    private int curLength;    //当前下载长度
    private int TotalLength;//文件总长度
    private int percent;    //下载百分比


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurLength() {
        return curLength;
    }

    public void setCurLength(int curLength) {
        this.curLength = curLength;
    }

    public int getTotalLength() {
        return TotalLength;
    }

    public void setTotalLength(int totalLength) {
        TotalLength = totalLength;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
