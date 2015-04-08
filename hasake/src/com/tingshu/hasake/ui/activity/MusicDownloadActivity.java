package com.tingshu.hasake.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

public class MusicDownloadActivity extends FragmentActivity {

	private ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setcontentView();
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_title_view_down);

		initView();
		 initData();
		 initListener();
	}

	protected void setcontentView() {
		setContentView(R.layout.activity_down);
	}

	protected void initView() {
		TextView album = (TextView) findViewById(R.id.act_title_one_text);
		TextView music = (TextView) findViewById(R.id.act_title_two_text);
		TextView down = (TextView) findViewById(R.id.act_title_three_text);

		album.setText("专辑");
		music.setText("音乐");
		down.setText("正在下载");

		mViewPager = (ViewPager) findViewById(R.id.act_down_viewpager);
	}

	protected void initData() {
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		// 获取下载列表

		// 遍历列表将已下载状态取出（并且将已下载中取出不同专辑名称）

		// 分别给专辑，音乐（已下载）,正在下载的fragment初始化话

		list.add(new DownAlbumFragment());
		list.add(new DownMusicFragment());
		list.add(new DownRunningFragment());

		DownPagerAdapter adapter = new DownPagerAdapter(
				getSupportFragmentManager(), list);
		mViewPager.setAdapter(adapter);
	}

	protected void initListener() {

	}

	class DownPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> list;

		public DownPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return false;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

	}

}
