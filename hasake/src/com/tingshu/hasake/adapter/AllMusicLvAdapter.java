package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.MusicBean;
import com.tingshu.hasake.bean.TypeBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AllMusicLvAdapter extends BaseAdapter {

	private Context context;
	private List<MusicBean> list = new ArrayList<MusicBean>();

	public AllMusicLvAdapter(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
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
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.all_music_item, null);
			holder = new Holder();
			holder.tv_name = (TextView) arg1.findViewById(R.id.tv_music_name);
			arg1.setTag(holder);

		} else {
			holder = (Holder) arg1.getTag();
		}
		MusicBean bean = list.get(arg0);
		holder.tv_name.setText(bean.getName());
		return arg1;
	}

	class Holder {
		TextView tv_name;

	}

	public void addMore(List<MusicBean> muList) {
		if (list != null) {
			list.addAll(muList);
			notifyDataSetChanged();
		}
	}

}
