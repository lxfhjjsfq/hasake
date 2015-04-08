package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.FansBean;
import com.tingshu.hasake.bean.GuanZhuBean;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GuanzhuLvAdapter extends BaseAdapter {

	private Context context;
	private List<GuanZhuBean> list = new ArrayList<GuanZhuBean>();

	public GuanzhuLvAdapter(Context context) {
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
		Holdr holdr = null;
		if (arg1 == null) {
			holdr = new Holdr();
			arg1 = LayoutInflater.from(context).inflate(R.layout.guanzhu_item,
					null);
			holdr.tv_fans = (TextView) arg1.findViewById(R.id.tv_fans);
			holdr.tv_music_name = (TextView) arg1
					.findViewById(R.id.tv_music_name);
			holdr.tv_voice = (TextView) arg1.findViewById(R.id.tv_voice);
			holdr.tv_name = (TextView) arg1.findViewById(R.id.tv_name);
			holdr.iv_isv = (ImageView) arg1.findViewById(R.id.iv_isv);
			arg1.setTag(holdr);
		} else {
			holdr = (Holdr) arg1.getTag();
		}
		GuanZhuBean fansBean = list.get(arg0);
		holdr.tv_fans.setText("粉丝 " + fansBean.getFansCount());
		holdr.tv_voice.setText("声音" + fansBean.getVideoCount());
		holdr.tv_name.setText(fansBean.getNickname());
		holdr.tv_music_name.setText(fansBean.getDes());
		if (fansBean.getIsVip() == 0) {
			holdr.iv_isv.setVisibility(View.GONE);
		} else {
			holdr.iv_isv.setVisibility(View.VISIBLE);
		}
		return arg1;
	}

	public void addMore(List<GuanZhuBean> fansBeans) {
		list.addAll(fansBeans);
		notifyDataSetChanged();
	}

	private class Holdr {
		TextView tv_name;
		TextView tv_voice;
		TextView tv_fans;
		TextView tv_music_name;
		ImageView iv_sixin;
		ImageView iv_isv;

	}

}
