package com.tingshu.hasake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tingshu.hasake.R;

public class HistoryItemAdapter extends BaseAdapter {

	private Context context;

	public HistoryItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		View view = LayoutInflater.from(context).inflate(R.layout.act_history_history_item, null);
		
		return view;
	}

}