package com.tingshu.hasake;

import java.util.ArrayList;

import com.tingshu.hasake.adapter.PingLunFragmentAdapter;
import com.tingshu.hasake.fragment.PingLunFragment;
import com.tingshu.hasake.utils.Constans;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class PingLunActvity extends FragmentActivity {
	private ViewPager vp_pinglun;
	private ArrayList<Fragment> list = new ArrayList<Fragment>();
	private RadioGroup rg;
	private RadioButton rb_rec, rb_send;
	private PingLunFragment receFragment,sendFragment;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setcontentView();
		initView();
		initData();
		initListener();
		
	}
	protected void setcontentView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_pinglun);

	}

	protected void initView() {
		vp_pinglun = (ViewPager) findViewById(R.id.vp_pinglun);
		rg = (RadioGroup) findViewById(R.id.rg_pl);
		rb_rec = (RadioButton) findViewById(R.id.rb_rece);
		rb_send = (RadioButton) findViewById(R.id.rb_send);

	}

	protected void initData() {
		receFragment=PingLunFragment.newInstance(Constans.GetMyReview);
		sendFragment=PingLunFragment.newInstance(Constans.GetMyReviewMy);
		list.add(receFragment);
		list.add(sendFragment);
		vp_pinglun.setAdapter(new PingLunFragmentAdapter(
				getSupportFragmentManager(), list));

	}

	protected void initListener() {
		vp_pinglun.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) { 
				switch (arg0) {
				case 0:
					rb_rec.setChecked(true);
					break;
				case 1:
					rb_send.setChecked(true);
					break;

				default:
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_rece:
					vp_pinglun.setCurrentItem(0);
					break;
				case R.id.rb_send:
					vp_pinglun.setCurrentItem(1);
					break;
				default:
					break;
				}

			}
		});
	}

}
