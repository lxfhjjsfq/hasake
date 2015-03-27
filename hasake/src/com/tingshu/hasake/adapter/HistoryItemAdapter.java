package com.tingshu.hasake.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tingshu.hasake.R;

public class HistoryItemAdapter extends BaseAdapter {

	private ArrayList<HistoryDao> list = new ArrayList<HistoryDao>();
	private Context context;
	private boolean isCanCheck = false;// 编辑删除状态

	public HistoryItemAdapter(Context context) {
		this.context = context;
	}

	public void addHistory(HistoryDao history) {
		list.add(history);
	}

	public boolean isCanCheck() {
		return isCanCheck;
	}

	public void setCanCheck(boolean canCheck) {
		isCanCheck = canCheck;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCheck(false);
		}
		notifyDataSetChanged();
	}

	public void removeHistory(){
		ArrayList<HistoryDao> delete = new ArrayList<HistoryDao>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isCheck()){
				delete.add(list.get(i));
			}
		}
		for(int i = 0; i < delete.size(); i ++){
			list.remove(delete.get(i));
		}
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public HistoryDao getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		HistoryDao history = list.get(arg0);
		View view = null;
		Holder holder = null;

		if (arg1 != null) {
			view = arg1;
			holder = (Holder) view.getTag();
		} else {
			view = LayoutInflater.from(context).inflate(
					R.layout.act_history_history_item, null);
			holder = new Holder();
			holder.container = (LinearLayout) view.findViewById(R.id.act_history_item_container);
			holder.check = (ImageView) view
					.findViewById(R.id.act_history_item_check);
			holder.name = (TextView) view
					.findViewById(R.id.act_history_item_name);
			holder.shortTime = (TextView) view
					.findViewById(R.id.act_history_item_showtime);
			view.setTag(holder);
		}

		holder.name.setText(history.getName());
		holder.shortTime.setText(history.getTime());
		if (isCanCheck) {
			holder.check.setVisibility(View.VISIBLE);
			if (history.isCheck()) {
				holder.check.getDrawable().setLevel(2);
			} else {
				holder.check.getDrawable().setLevel(1);
			}
			holder.container.setOnClickListener(new CheckListener(history, holder));
		} else {
			holder.check.setVisibility(View.GONE);
			holder.container.setOnClickListener(null);
		}

		return view;
	}

	class CheckListener implements View.OnClickListener {
		private HistoryDao history;
		private Holder holder;
		
		public CheckListener(HistoryDao history, Holder holder) {
			this.history = history;
			this.holder = holder;
		}

		public void onClick(View v) {
			ImageView iv = holder.check;
			if (iv.getDrawable().getLevel() == 1) {
				iv.getDrawable().setLevel(2);
				history.setCheck(true);
			} else {
				iv.getDrawable().setLevel(1);
				history.setCheck(false);
			}
		}
	}

	class Holder {
		LinearLayout container;
		ImageView check;
		TextView name;
		TextView shortTime;
	}

	public static class HistoryDao {
		private String name;
		private String time;
		private boolean check;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public boolean isCheck() {
			return check;
		}

		public void setCheck(boolean check) {
			this.check = check;
		}
	}

}
