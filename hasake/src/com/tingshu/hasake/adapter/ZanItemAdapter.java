package com.tingshu.hasake.adapter;

import com.tingshu.hasake.R;
import com.tingshu.hasake.database.DatabaseUtil;
import com.tingshu.hasake.database.DownloadDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZanItemAdapter extends BaseAdapter {

	private Context context;

	public ZanItemAdapter(Context context) {
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
		
		View view = LayoutInflater.from(context).inflate(R.layout.act_zan_zan_item, null);
		
		TextView tv = (TextView) view.findViewById(R.id.act_zan_zan_item_down);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView t = (TextView) v;
				if(t.getText().toString().equals("下载")){
					//添加下载测试代码
					DownloadDao dao = new DownloadDao("周四:你还没来-白雪【晚上10点整第189期】", "music", "", "");
					DatabaseUtil.getInstance().insertCache(dao);
					
				}
			}
		});
		
		return view;
	}

}
