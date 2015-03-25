package com.tingshu.hasake;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Canvas;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tingshu.hasake.fragment.HomeFragment;
import com.tingshu.hasake.fragment.LeftFragment;
import com.tingshu.hasake.utils.FileUtil;

public class MainActivity extends SlidingFragmentActivity {
	private HomeFragment homeFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
		initData();

	}

	private void initView() {
		setResideMenu();

	}

	private void initData() {
		if(!fileExist()){
			addFileToPath();
		}
	}

	private void initListener() {

	}

	private void setResideMenu() {
		setBehindContentView(R.layout.menu_frame);
		getSlidingMenu().setSlidingEnabled(true);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		homeFragment = new HomeFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_home, homeFragment).commit();

		// set the Behind View Fragment
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftFragment()).commit();

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeEnabled(false);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);

		sm.setBackgroundImage(R.drawable.home_left_bg);
		sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, -canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		});

		sm.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (1 - percentOpen * 0.1);
				canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
			}
		});

	}

	/*************************测试代码****************************/
	private boolean fileExist(){
		File file = new File(FileUtil.getPath() + "damoqiuliang.mp3");
		if(file.exists()){
			return true;
		}
		return false;
	}
	private void addFileToPath(){
		//将assets中的音乐拷贝到手机
		AddFileThread thread = new AddFileThread();
		thread.start();
	}
	
	class AddFileThread extends Thread{
		@Override
		public void run() {
			File dir = new File(FileUtil.getPath());
			if(!dir.exists()){
				dir.mkdir();
			}
			addFile("baitiane.mp3");
			addFile("kanon.mp3");
			addFile("damoqiuliang.mp3");
		}
		
		
		private void addFile(String fileName) {
			InputStream fio = null;
			OutputStream fos = null;
			try {
				fio = getAssets().open(fileName);
				File to = new File(FileUtil.getPath(), fileName);
				fos = new FileOutputStream(to);
				copy(fio, fos);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fio != null) try {
					fio.close();
				} catch (Exception e) {
				}
				if (fos != null) try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		
		
		private long copy(InputStream from, OutputStream to) throws IOException {
			byte[] buf = new byte[4096];
			long total = 0L;
			
			while(true) {
				int r = from.read(buf);
				if(r == -1) {
					return total;
				}
				
				to.write(buf, 0, r);
				total += (long)r;
			}
		}
	}
	
}
