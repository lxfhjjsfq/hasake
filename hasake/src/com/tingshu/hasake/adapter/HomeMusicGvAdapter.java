package com.tingshu.hasake.adapter;

import com.tingshu.hasake.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeMusicGvAdapter extends BaseAdapter {

	private Context context;

	public HomeMusicGvAdapter(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return LayoutInflater.from(context).inflate(R.layout.home_music_item, null);
	}

}
