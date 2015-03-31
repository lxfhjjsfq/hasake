package com.tingshu.hasake.ui.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.fragment.DownAlbumFragment;
import com.tingshu.hasake.fragment.DownMusicFragment;
import com.tingshu.hasake.fragment.DownRunningFragment;

public class MusicDownloadActivity extends BaseActivity {

	private ViewPager mViewPager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_title_view_down);
	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_down);
	}

	@Override
	protected void initView() {
		TextView album = (TextView) findViewById(R.id.act_title_one_text);
		TextView music = (TextView) findViewById(R.id.act_title_two_text);
		TextView down = (TextView) findViewById(R.id.act_title_three_text);

		album.setText("专辑");
		music.setText("音乐");
		down.setText("正在下载");
		
		mViewPager = (ViewPager) findViewById(R.id.act_down_viewpager);
	}

	@Override
	protected void initData() {
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		//获取下载列表
		
		//遍历列表将已下载状态取出（并且将已下载中取出不同专辑名称）
		
		//分别给专辑，音乐（已下载）,正在下载的fragment初始化话
		
		list.add(new DownAlbumFragment());
		list.add(new DownMusicFragment());
		list.add(new DownRunningFragment());
		
		DownPagerAdapter adapter = new DownPagerAdapter(list);
		mViewPager.setAdapter(adapter);
	}

	@Override
	protected void initListener() {
		
	}


	class DownPagerAdapter extends PagerAdapter{

		private ArrayList<Fragment> list;
		
		public DownPagerAdapter(ArrayList<Fragment> list){
			this.list = list;
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return false;
		}
		
	}
	
}
