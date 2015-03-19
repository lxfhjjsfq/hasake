package com.fengwei.app.media;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.text.TextUtils;
import android.util.Log;

/**
 * 播放音乐管理类
 * 
 * @author xianfeng
 * 
 */
public class MediaPlayManager {
	private MediaPlayer mediaPlayer;
	private static MediaPlayManager playManager;
	private OnMediaPlayStateListener playStateListener;
	private String playUrl;

	public static MediaPlayManager getInstance() {
		if (playManager == null) {
			playManager = new MediaPlayManager();
		}
		return playManager;
	}

	private MediaPlayManager() {
		super();

	}

	public synchronized void play(final String playUrl) {
		if (playUrl == null) {
			Log.d("MediaPlayManager", "播放异常null");
			return;
		}
		Log.d("MediaPlayManager", "playUrl:" + playUrl);
		if (mediaPlayer != null) {
			stopPlay();
		}
		this.playUrl = playUrl;
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.reset();
			mediaPlayer.setDataSource(playUrl);
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {
					arg0.start();
					if (playStateListener != null) {
						playStateListener.onPlayStart(playUrl);
					}
				}
			});
			// 播放完成
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					if (playStateListener != null) {
						playStateListener.onPlayComplete(playUrl);
					}

				}
			});
			// mediaPlayer.setOnC
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (playStateListener != null) {
				playStateListener.onPlayErro(playUrl);
			}
			Log.d("palyerActivity", "播放异常" + e.toString());
		}

	}

	/**
	 * 播放
	 * 
	 * @param playUrl
	 */
	public synchronized void play(final String playUrl,
			final OnMediaPlayStateListener stateListener) {
		if (playUrl == null) {
			Log.d("MediaPlayManager", "播放异常null");
			return;
		}
		Log.d("MediaPlayManager", "playUrl:" + playUrl);
		if (mediaPlayer != null) {
			stopPlay();
		}
		this.playUrl = playUrl;
		this.playStateListener = stateListener;
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.reset();
			mediaPlayer.setDataSource(playUrl);
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {
					arg0.start();
					if (playStateListener != null) {
						playStateListener.onPlayStart(playUrl);
					}
				}
			});
			// 播放完成
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					if (playStateListener != null) {
						playStateListener.onPlayComplete(playUrl);
					}

				}
			});
			// mediaPlayer.setOnC
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (playStateListener != null) {
				playStateListener.onPlayErro(playUrl);
			}
			Log.d("palyerActivity", "播放异常" + e.toString());
		}

	}

	public void setOnMediaPlayStateListener(OnMediaPlayStateListener listener) {
		playStateListener = listener;
	}

	/**
	 * 停止播放
	 */
	public synchronized void stopPlay() {
		if (null != mediaPlayer) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			mediaPlayer.release();
			mediaPlayer = null;
			if (playStateListener != null && !TextUtils.isEmpty(playUrl)) {
				playStateListener.onPlayStop(playUrl);
			}
		}
	}

	/**
	 * 暂停播放
	 */
	public synchronized void pausePlay() {
		if (null != mediaPlayer) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
				if (playStateListener != null && !TextUtils.isEmpty(playUrl)) {
					playStateListener.onPlayPause(playUrl);
				}
			}
		}
	}

	public synchronized void continuePlay() {
		if (null != mediaPlayer) {
			mediaPlayer.start();
		}
	}

	public int getProgress(){
		if (null != mediaPlayer) {
			if(mediaPlayer.getDuration() > 0){
				return mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration();
			}
		}
		return 0;
	}
	
	public int getDuration(){
		if (null != mediaPlayer) {
			return mediaPlayer.getDuration();
		}
		return 0;
	}
	
	/**
	 * 播放状态
	 */
	public interface OnMediaPlayStateListener {
		public void onPlayStart(String playUrl);

		public void onPlayComplete(String playUrl);

		public void onPlayStop(String playUrl);

		public void onPlayErro(String playUrl);

		public void onPlayPause(String playUrl);
	}
}
