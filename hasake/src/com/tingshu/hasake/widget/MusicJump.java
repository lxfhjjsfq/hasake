package com.tingshu.hasake.widget;


import android.graphics.Canvas;

import java.util.ArrayList;

public class MusicJump{
    protected int itemNum = 0;//效果元素数量
    //效果场景宽高
    protected int width;
    protected int height;
    //效果容器
    protected ArrayList<MusicPoint> list = new ArrayList<MusicPoint>();

    /**
     * 效果场景构造
     * @param width		显示区域宽
     * @param height	显示区域宽
     * @param itemNum	显示区域元素数量
     */
    public MusicJump(int width, int height, int itemNum){
        this.width = width;
        this.height = height;
        this.itemNum = itemNum;
        initScence();
    }

    public void draw(Canvas canvas){
        if(list.size() == 0){
            throw new RuntimeException("请初在initScence的方法中加入效果元素!");
        }
        for(MusicPoint item : list){
            item.draw(canvas);
        }
    }

    public void move(){
        if(list.size() == 0){
            throw new RuntimeException("请初在initScence的方法中加入效果元素!");
        }
        for(MusicPoint item : list){
            item.move();
        }
    }

	protected void initScence() {
		for(int i = 0; i < itemNum; i ++){
			list.add(new MusicPoint(i * width / itemNum, width, height));
		}		
	}
	
}
