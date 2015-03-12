package com.tingshu.hasake.widget;


import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class MusicPoint{

    /**
     * 显示区域的宽度
     */
    protected int width;
    /**
     * 显示区域的高度
     */
    protected int height;
    /**
     * 效果元素的随机对象
     */
    protected Random rand;
	private Paint paint = new Paint();
	private int x;
	private int centerY;
	private int maxH;
	private int distance;
	private int itemSize;
	private int num;
	
	public MusicPoint(int x, int width, int height){
		this(width, height);
		this.x = x;
	}
	
	public MusicPoint(int width, int height) {

		centerY = height / 2;
		maxH = height / 3;
		itemSize = maxH / 15;
		distance = itemSize / 2;
        rand = new Random();
		paint.setColor(0xffffffff);
	}

	public void draw(Canvas canvas) {
		//绘制向上的音符块
		paint.setAlpha(100);
		for(int i = 0; i < num; i ++){
			canvas.drawRect(x + distance, centerY - (i + 1) * itemSize - i * distance, x + itemSize + distance, centerY - i * (itemSize + distance), paint);
		}
		//绘制向下的镜像音符块
		paint.setAlpha(50);
		for(int i = 0; i < num; i ++){
			canvas.drawRect(x + distance, centerY + i * (itemSize + distance), x + itemSize + distance, centerY + (i + 1) * itemSize + i * distance, paint);
		}
	}

	int count = 0;
	public void move() {
		count ++;
		if(count >= 6){
			count = 0;
			num = 1 + rand.nextInt(10);
		}
	}
	
}
