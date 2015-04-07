package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.FansBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GuanzhuLvAdapter extends BaseAdapter {

	private Context context;
	private List<FansBean> list = new ArrayList<FansBean>();

	public GuanzhuLvAdapter(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
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
		return LayoutInflater.from(context)
				.inflate(R.layout.guanzhu_item, null);
	}

	public void addMore(List<FansBean> fansBeans) {
		list.addAll(fansBeans);
		notifyDataSetChanged();
	}

}
