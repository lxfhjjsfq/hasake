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
//	private String test_url = "http://bj.dl.baidupcs.com/file/d38d18cd4420c9e5a133b68759d76aeb?bkt=p2-qd-915&fid=3020055452-250528-578110588467393&time=1426495270&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-nwMBs%2FmKHPJpELAZLemIWKBjMR8%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=80085068&expires=8h&rt=pr&r=176115217&mlogid=1054068229&vuk=3020055452&vbdid=1915860146&fin=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3&fn=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3";
//	private String test_url = "http://ws.stream.qqmusic.qq.com/34754489.mp3?vkey=41932A4239A64EE95F69A7CE0A7E94A38B002B3BD8C7852B4869A4A3F1D05DC4&fromtag=52&guid=36E807A1DB0BCE31CEE8A9C8E1C1C6A0";
	private boolean started = false;
	
	private String[] test_titles={"白天鹅","卡农","大漠秋凉"};
	
	private String[] test_urls = {"http://bj.dl.baidupcs.com/file/d38d18cd4420c9e5a133b68759d76aeb?bkt=p2-qd-915&fid=3020055452-250528-578110588467393&time=1426495270&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-nwMBs%2FmKHPJpELAZLemIWKBjMR8%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=80085068&expires=8h&rt=pr&r=176115217&mlogid=1054068229&vuk=3020055452&vbdid=1915860146&fin=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3&fn=%E6%9C%B1%E5%93%B2%E7%90%B4%20-%20%E6%96%B0%E7%96%86%E4%BC%8A%E7%8A%81%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F%E5%86%AC%E4%B8%8D%E6%8B%89%E7%8B%AC%E5%A5%8F%E3%80%8A%E7%99%BD%E5%A4%A9%E9%B9%85%E3%80%8B.mp3",
	"http://bj.dl.baidupcs.com/file/0a3e467e0f813e1b655b7fb09a10764b?bkt=p2-qd-915&fid=3020055452-250528-461622072047359&time=1426495447&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-OvwI2uNq%2B5pVZ5pWwRfJtLHoQ80%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=73662543&expires=8h&rt=pr&r=461291606&mlogid=3999768877&vuk=3020055452&vbdid=1915860146&fin=%E5%8D%A1%E5%86%9C.mp3&fn=%E5%8D%A1%E5%86%9C.mp3",								
	"http://bj.dl.baidupcs.com/file/ab50197b8350e273070276e9b4b6f51b?bkt=p2-qd-915&fid=3020055452-250528-669644702059677&time=1426495357&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-REx9XN2TpSFTtNXxIuqfBApef3o%3D&to=abp&fm=Qin,B,U,tt&newver=1&newfm=1&flow_ver=3&sl=80085068&expires=8h&rt=pr&r=930669440&mlogid=3837792626&vuk=3020055452&vbdid=1915860146&fin=Various%20Artists%20-%20%E5%A4%A7%E6%BC%A0%E7%A7%8B%E5%87%89-%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F.mp3&fn=Various%20Artists%20-%20%E5%A4%A7%E6%BC%A0%E7%A7%8B%E5%87%89-%E5%93%88%E8%90%A8%E5%85%8B%E6%97%8F.mp3"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.music_paly_view);
		
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
			MediaPlayManager.getInstance().play(test_urls[1], new OnMediaPlayStateListener() {
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
