package com.tingshu.hasake.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MusicAnimation extends View {


    protected final String TAG = "EffectAnimation";

    private Thread spriteThread;
    private boolean running = false;
    protected MusicJump scence;
    private int itemNum = 80;


    public MusicAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MusicAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MusicAnimation(Context context) {
		super(context);
	}

	protected MusicJump initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();
		
		if(width == 0 || height == 0){
			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display dis = wm.getDefaultDisplay();
			width = dis.getWidth();
			height = dis.getHeight();
		}
		return new MusicJump(width, height, itemNum);
	}


    private void init(){
        running = false;
        if(itemNum == 0){
            itemNum = 100;
        }
        scence = initScence(itemNum);
        spriteThread = new Thread(run);
        spriteThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(scence != null){
            scence.draw(canvas);
        }
        else{
            init();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        running = false;
    }

    private Runnable run = new Runnable(){
        public void run() {
            running = true;
            while (running) {
                if(scence != null){
                    scence.move();
                }

                mHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    break;
                }
            }

        }
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        };
    };
}
