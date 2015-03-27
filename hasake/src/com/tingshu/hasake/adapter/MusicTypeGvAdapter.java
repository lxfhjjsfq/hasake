package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.TypeBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicTypeGvAdapter extends BaseAdapter {

	private Context context;
	private List<TypeBean> list=new ArrayList<TypeBean>();

	public MusicTypeGvAdapter(Context context,List<TypeBean> list) {
		this.list=list;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		View v= LayoutInflater.from(context).inflate(R.layout.home_music_item,
				null);
		ImageView iv=(ImageView) v.findViewById(R.id.iv_type_icon);
		TextView tv=(TextView) v.findViewById(R.id.tv_type_name);
		TypeBean bean=list.get(arg0);
		iv.setImageResource(bean.getIcon_id());
		tv.setText(bean.getType_name());
		return v;
	}

}
