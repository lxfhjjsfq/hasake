package com.tingshu.hasake.widget;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class BannerLunBoView extends RelativeLayout {

	private ViewPager viewPager;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private List<ImageView> imageViewsList;
	private int[] imgIds = { R.drawable.home_lunbo, R.drawable.home_lunbo,
			R.drawable.home_lunbo, R.drawable.home_lunbo, };

	public BannerLunBoView(Context context) {
		super(context);
		initData(context);
	}

	public BannerLunBoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData(context);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	private void initData(Context context) {
		imageViewsList = new ArrayList<ImageView>();
		for (int i = 0; i < 4; i++) {
			ImageView iv = new ImageView(context);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			imageViewsList.add(iv);
		}
	}

	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		viewPager.setAdapter(new MyViewPagerAdapter());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rb1.setChecked(true);
					break;
				case 1:
					rb2.setChecked(true);
					break;
				case 2:
					rb3.setChecked(true);
					break;
				case 3:
					rb4.setChecked(true);
					break;

				default:
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private class MyViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			// ((ViewPag.er)container).removeView((View)object);
			((ViewPager) container).removeView(imageViewsList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView = imageViewsList.get(position);
			imageView.setImageResource(imgIds[position]);
			((ViewPager) container).addView(imageViewsList.get(position));
			return imageViewsList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

	}

}
