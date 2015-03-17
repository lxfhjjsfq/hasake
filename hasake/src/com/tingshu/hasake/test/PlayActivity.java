package com.tingshu.hasake.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.fengwei.app.media.MediaPlayManager;
import com.fengwei.app.media.MediaPlayManager.OnMediaPlayStateListener;
import com.tingshu.hasake.R;
import com.tingshu.hasake.widget.MusicPlayView;
import com.tingshu.hasake.widget.MusicPlayView.MusicClickListener;

public class PlayActivity extends Activity implements MusicClickListener, OnMediaPlayStateListener {

	private MusicPlayView mMusicPlayView;

	private int index = 0;
	private String[] test_titles={"白天鹅","卡农","大漠秋凉"};
	private String[] test_urls = {
	"http://bj.dl.baidupcs.com/file/d38d18cd4420c9e5a133b68759d76aeb?bkt=p2-qd-915&fid=3020055452-250528-578110588467393&time=1426585606&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-D1%2B8gq%2Fu66lwxwVZd4wbQUWELy8%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=79429708&expires=8h&rt=sh&r=858124164&mlogid=3842963396&vuk=3020055452&vbdid=1915860146&fin=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3&fn=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3",
	"http://bj.dl.baidupcs.com/file/0a3e467e0f813e1b655b7fb09a10764b?bkt=p2-qd-915&fid=3020055452-250528-461622072047359&time=1426585648&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-IWKG9NdRfgOWZGIgU2j%2FcqlK1%2BE%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=79429708&expires=8h&rt=sh&r=209903236&mlogid=1215875637&vuk=3020055452&vbdid=1915860146&fin=%E5%8D%A1%E5%86%9C.mp3&fn=%E5%8D%A1%E5%86%9C.mp3",								
	"http://bj.dl.baidupcs.com/file/ab50197b8350e273070276e9b4b6f51b?bkt=p2-qd-915&fid=3020055452-250528-669644702059677&time=1426585668&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-EGgbWIxN9q0W9nVn3qhsSOEEQaU%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=79429708&expires=8h&rt=sh&r=317190010&mlogid=1064198547&vuk=3020055452&vbdid=1915860146&fin=Various%20Artists%20-%20%E5%A4%A7%E6%BC%A0%E7%A7%8B%E5%87%89-%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F.mp3&fn=Various%20Artists%20-%20%E5%A4%A7%E6%BC%A0%E7%A7%8B%E5%87%89-%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F.mp3"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_play);
		
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
	/*******************播放状态监听**********************/
	@Override
	public void onPlayStart(String playUrl) {
		Toast.makeText(this, "onPlayStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPlayComplete(String playUrl) {
		Toast.makeText(this, "onPlayComplete", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPlayStop(String playUrl) {
		Toast.makeText(this, "onPlayStop", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPlayErro(String playUrl) {
		Toast.makeText(this, "onPlayErro", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPlayPause(String playUrl) {
		Toast.makeText(this, "onPlayPause", Toast.LENGTH_SHORT).show();
	}

}
