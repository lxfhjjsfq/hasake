package com.tingshu.hasake.test;

import com.fengwei.app.media.MediaPlayManager;
import com.fengwei.app.media.MediaPlayManager.OnMediaPlayStateListener;
import com.tingshu.hasake.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnClickListener {

	private ImageView play_button;
	private final int PLAY = 1;
	private final int PAUSE = 2;
//	private String test_url = "/mnt/sdcard/tonghua.mp3";
	private String test_url = "http://ws.stream.qqmusic.qq.com/34754489.mp3?vkey=41932A4239A64EE95F69A7CE0A7E94A38B002B3BD8C7852B4869A4A3F1D05DC4&fromtag=52&guid=36E807A1DB0BCE31CEE8A9C8E1C1C6A0";
	private boolean started = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_play);
		play_button = (ImageView) findViewById(R.id.act_play_Pbutton);
		play_button.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		if(play_button.getDrawable().getLevel() < PAUSE){
			play_button.getDrawable().setLevel(PAUSE);
			if(started){
				MediaPlayManager.getInstance().continuePlay();
				return;
			}
			started = true;
			MediaPlayManager.getInstance().play(test_url, new OnMediaPlayStateListener() {
				public void onPlayStop(String playUrl) {
					Toast.makeText(PlayActivity.this, "play stop", Toast.LENGTH_SHORT).show();
				}
				public void onPlayPause(String playUrl) {
					Toast.makeText(PlayActivity.this, "play pause", Toast.LENGTH_SHORT).show();
				}
				public void onPlayErro(String playUrl) {
					Toast.makeText(PlayActivity.this, "play error", Toast.LENGTH_SHORT).show();
				}
				public void onPlayComplete(String playUrl) {
					Toast.makeText(PlayActivity.this, "play complete", Toast.LENGTH_SHORT).show();
				}
				@Override
				public void onPlayStart(String playUrl) {
					Toast.makeText(PlayActivity.this, "play start", Toast.LENGTH_SHORT).show();
				}
			});
		}else{
			play_button.getDrawable().setLevel(PLAY);
			MediaPlayManager.getInstance().pausePlay();
		}
	}
	
	
}
