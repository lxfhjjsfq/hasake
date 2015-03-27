package com.tingshu.hasake.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.fengwei.app.media.MediaPlayManager;
import com.fengwei.app.media.MediaPlayManager.OnMediaPlayStateListener;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.PlayMusicGvAdapter;
import com.tingshu.hasake.utils.DateUtil;
import com.tingshu.hasake.widget.MusicPlayView;
import com.tingshu.hasake.widget.MusicPlayView.MusicClickListener;

public class PlayActivity extends Activity implements MusicClickListener, OnMediaPlayStateListener {

	private MusicPlayView mMusicPlayView;
	private ProgressThread mThread;
	
	private int index = 0;
	private String[] test_titles={"白天鹅","卡农","大漠秋凉"};
	private String[] test_urls = {
	"/mnt/sdcard/hasake/baitiane.mp3",
	"/mnt/sdcard/hasake/kanon.mp3",								
	"/mnt/sdcard/hasake/damoqiuliang.mp3"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_play);
		mMusicPlayView = (MusicPlayView) findViewById(R.id.act_play_music_view);
		
		mMusicPlayView.setMusicClickListener(this);
		MediaPlayManager.getInstance().setOnMediaPlayStateListener(this);
		
		mMusicPlayView = (MusicPlayView) findViewById(R.id.act_play_music_view);
		mMusicPlayView.setMusicClickListener(this);
		MediaPlayManager.getInstance().setOnMediaPlayStateListener(this);
	}

	@Override
	public void onMusicPlayNow() {
		MediaPlayManager.getInstance().play(test_urls[index]);
		mMusicPlayView.setTitle(test_titles[index]);
	}

	@Override
	public void onMusicPlayUp() {
		if(index - 1 < 0){
			//第一首
			return;
		}
		index -= 1;
		MediaPlayManager.getInstance().play(test_urls[index]);
		mMusicPlayView.setTitle(test_titles[index]);
	}

	@Override
	public void onMusicPlayNext() {
		if(index + 1 > test_urls.length - 1){
			//第一首
			return;
		}
		index += 1;
		MediaPlayManager.getInstance().play(test_urls[index]);
		mMusicPlayView.setTitle(test_titles[index]);
	}

	@Override
	public void onMusicPlayContinue() {
		MediaPlayManager.getInstance().continuePlay();
	}
	
	@Override
	public void onMusicPlayPause() {
		MediaPlayManager.getInstance().pausePlay();
	}
	
	@Override
	public void onMusicSpecial() {
		Intent it = new Intent(this, SpecialActivity.class);
		startActivity(it);
	}
	@Override
	public void onMusicZan() {
		Intent it = new Intent(this, ZanActivity.class);
		startActivity(it);
	}
	@Override
	public void onMusicHistory() {
		Intent it = new Intent(this, HistoryActivity.class);
		startActivity(it);
	}
	/*******************播放状态监听**********************/
	@Override
	public void onPlayStart(String playUrl) {
		Toast.makeText(this, "onPlayStart", Toast.LENGTH_SHORT).show();
		//设置播放时长
		mMusicPlayView.setTime(DateUtil.getFormatTime(MediaPlayManager.getInstance().getDuration() / 1000));
		mThread = new ProgressThread();
		mThread.start();
	}

	@Override
	public void onPlayComplete(String playUrl) {
		Toast.makeText(this, "onPlayComplete", Toast.LENGTH_SHORT).show();
		if(mThread!=null){
			mThread.stopPlay();
		}
		
	}

	@Override
	public void onPlayStop(String playUrl) {
		Toast.makeText(this, "onPlayStop", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPlayErro(String playUrl) {
		Toast.makeText(this, "onPlayErro", Toast.LENGTH_SHORT).show();
		mThread.stopPlay();
	}

	@Override
	public void onPlayPause(String playUrl) {
		Toast.makeText(this, "onPlayPause", Toast.LENGTH_SHORT).show();
	}

	class ProgressThread extends Thread{
		private int progress = 0;
		private boolean playing = true;
		@Override
		public void run() {
			
			while(playing){
				progress = MediaPlayManager.getInstance().getProgress();
				
				if(progress > 0){
					//设置播放器进度
					Message msg = mHandler.obtainMessage(); 
					msg.what = PLAY_UPDATE_PROGRESS;
					msg.arg1 = progress;
					mHandler.sendMessage(msg);
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		public void stopPlay(){
			playing = false;
		}
	}
	
	private final int PLAY_UPDATE_PROGRESS = 1;
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case PLAY_UPDATE_PROGRESS:
				mMusicPlayView.setProgress(msg.arg1);
				Log.e("play music", "progress : " + msg.arg1);
				break;
			}
		};
	};
}
