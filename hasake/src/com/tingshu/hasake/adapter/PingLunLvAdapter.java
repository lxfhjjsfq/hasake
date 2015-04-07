package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.PingLunBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PingLunLvAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<PingLunBean> list = new ArrayList<PingLunBean>();

	public PingLunLvAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public PingLunBean getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=LayoutInflater.from(context).inflate(R.layout.pinglun_item, null);
		
		return convertView;
	}

	public void addMore(List<PingLunBean> pingLunBeans) {
		list.addAll(pingLunBeans);
		notifyDataSetChanged();

	}
	
	private class Holder{
		
	}

}
