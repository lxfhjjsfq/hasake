package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.FansBean;

public class FansItemAdapter extends BaseAdapter {

	private Context context;
	private List<FansBean> list = new ArrayList<FansBean>();

	public FansItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
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

		View view = LayoutInflater.from(context).inflate(
				R.layout.act_fans_fans_item, null);

		final Holdr holdr;
		if (arg1 == null) {
			holdr = new Holdr();
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.act_fans_fans_item, null);
			holdr.tv_fans = (TextView) arg1.findViewById(R.id.tv_fans_count);
			holdr.tv_music_name = (TextView) arg1
					.findViewById(R.id.tv_zhuanjie);
			holdr.tv_voice = (TextView) arg1.findViewById(R.id.tv_fans_voice);
			holdr.tv_name = (TextView) arg1.findViewById(R.id.tv_fans_nick);
			holdr.iv_isv = (ImageView) arg1.findViewById(R.id.iv_isv);
			holdr.op = (ImageView) view
					.findViewById(R.id.act_fans_fans_item_button);
			arg1.setTag(holdr);
		} else {
			holdr = (Holdr) arg1.getTag();
		}
		FansBean fansBean = list.get(arg0);
		holdr.tv_fans.setText("粉丝 " + fansBean.getFansCount());
		holdr.tv_voice.setText("声音" + fansBean.getVideoCount());
		holdr.tv_name.setText(fansBean.getNickname());
		holdr.tv_music_name.setText(fansBean.getDes());
		holdr.op.getDrawable().setLevel(1);
		holdr.op.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (holdr.op.getDrawable().getLevel() == 1) {
					holdr.op.getDrawable().setLevel(2);
				} else {
					holdr.op.getDrawable().setLevel(1);
				}
			}
		});
		if (fansBean.getIsVip() == 0) {
			holdr.iv_isv.setVisibility(View.GONE);
		} else {
			holdr.iv_isv.setVisibility(View.VISIBLE);
		}
		if(fansBean.isIsFriend()){
			holdr.op.getDrawable().setLevel(1);
		}else{
			holdr.op.getDrawable().setLevel(2);
		}
		return arg1;
	}

	public void addMore(List<FansBean> fansBeans) {
		list.addAll(fansBeans);
		notifyDataSetChanged();
	}

	private class Holdr {
		TextView tv_name;
		TextView tv_voice;
		TextView tv_fans;
		TextView tv_music_name;
		ImageView op;
		ImageView iv_isv;

	}

}
