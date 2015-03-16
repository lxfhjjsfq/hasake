package com.tingshu.hasake.widget;

import com.tingshu.hasake.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 播放音乐控件
 * @author xianfeng
 *
 */
public class MusicPlayView extends LinearLayout{

	private LinearLayout mContainer;
	
	private TextView tv_title;	//标题
	private TextView tv_special;	//专辑
	private TextView tv_zan;		//赞
	private TextView tv_history;	//播放记录
	private TextView tv_time;		//音乐总时长
	private SeekBar seek_progress;	//进度条
	
	private ImageView imgv_up;	//上一首
	private ImageView imgv_now;	//播放
	private ImageView imgv_next;	//下一首 
	
	private ImageView imgv_background;//播放页面背景
	
	public MusicPlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public MusicPlayView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context){
		LayoutInflater inflater = LayoutInflater.from(context);
		mContainer = (LinearLayout) inflater.inflate(R.layout.music_paly_view, this);
		
		tv_title = (TextView) mContainer.findViewById(R.id.music_play_title);
		tv_special = (TextView) mContainer.findViewById(R.id.music_play_special);
		tv_zan = (TextView) mContainer.findViewById(R.id.music_play_zan);
		tv_history = (TextView) mContainer.findViewById(R.id.music_play_history);
		tv_time = (TextView) mContainer.findViewById(R.id.music_play_time);
		seek_progress = (SeekBar) mContainer.findViewById(R.id.music_play_progress);
		
		imgv_up = (ImageView) mContainer.findViewById(R.id.music_play_up);
		imgv_now = (ImageView) mContainer.findViewById(R.id.music_play_now);
		imgv_next = (ImageView) mContainer.findViewById(R.id.music_play_next);
		imgv_background = (ImageView) mContainer.findViewById(R.id.music_play_background);
	
	}
	
	public void setTitle(String title){
		tv_title.setText(title);
	}
	public void setSpecial(String special){
		tv_special.setText(special);
	}
	public void setZan(String zan){
		tv_zan.setText(zan);
	}
	public void setHistory(String histroy){
		tv_history.setText(histroy);
	}
	public void setTime(String time){
		tv_time.setText(time);
	}
	public void setProgress(int progress){
		seek_progress.setProgress(progress);
	}
	
}
