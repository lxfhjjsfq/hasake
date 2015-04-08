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
import android.widget.ImageView;
import android.widget.TextView;

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
		return list.size();
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
		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.pinglun_item, null);
			holder = new Holder();
			holder.tv_pinglun_content = (TextView) convertView
					.findViewById(R.id.tv_pinglun_content);
			holder.tv_pinglun_name = (TextView) convertView
					.findViewById(R.id.tv_pinglun_name);
			holder.tv_pinglun_time = (TextView) convertView
					.findViewById(R.id.tv_pinglun_time);
			holder.tv_pinglun_title = (TextView) convertView
					.findViewById(R.id.tv_pinglun_title);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		PingLunBean bean = list.get(position);
		holder.tv_pinglun_content.setText(bean.getContent());
		holder.tv_pinglun_name.setText("我评论了 " + bean.getNickname() + "的声音");
		holder.tv_pinglun_time.setText(bean.getAddTime());
		return convertView;
	}

	public void addMore(List<PingLunBean> pingLunBeans) {
		list.addAll(pingLunBeans);
		notifyDataSetChanged();

	}

	private class Holder {
		TextView tv_pinglun_title;
		TextView tv_pinglun_name;
		TextView tv_pinglun_content;
		TextView tv_pinglun_time;
		ImageView iv_pinglun_face;
		ImageView iv_pinglun_zhuanji;
	}

}
