package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.MsgBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MsgItemAdapter extends BaseAdapter {

	private Context context;
	private List<MsgBean> list = new ArrayList<MsgBean>();

	public MsgItemAdapter(Context context) {
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
		Holder holder = null;

		if (arg1 == null) {
			holder=new Holder();
			arg1 = LayoutInflater.from(context)
					.inflate(R.layout.msg_item, null);
			holder.tv_msg_content = (TextView) arg1
					.findViewById(R.id.tv_msg_content);
			holder.tv_msg_time = (TextView) arg1.findViewById(R.id.tv_msg_time);
			holder.tv_msg_name = (TextView) arg1.findViewById(R.id.tv_msg_name);
			holder.iv_msg_head = (ImageView) arg1
					.findViewById(R.id.iv_msg_head);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}

		MsgBean bean = list.get(arg0);
		holder.tv_msg_content.setText(bean.getContent());
		holder.tv_msg_name.setText(bean.getNickname());
		holder.tv_msg_time.setText(bean.getAddTime());

		return arg1;
	}

	private class Holder {
		TextView tv_msg_name;
		TextView tv_msg_content;
		TextView tv_msg_time;
		ImageView iv_msg_head;
	}

	public void addMore(List<MsgBean> beans) {
		list.addAll(beans);
		notifyDataSetChanged();

	}

}
