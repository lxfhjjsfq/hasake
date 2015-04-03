package com.tingshu.hasake.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.tingshu.hasake.R;
import com.tingshu.hasake.ui.activity.MusicDownloadActivity;

public class AlbumDownItemAdapter extends BaseAdapter {

	private Context context;

	public AlbumDownItemAdapter(Context context) {
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
		
		View view = LayoutInflater.from(context).inflate(R.layout.item_act_down_album, null);
		
		LinearLayout centerContainer = (LinearLayout) view.findViewById(R.id.item_act_down_album_container);
		
		centerContainer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent it = new Intent(context, MusicDownloadActivity.class);
				context.startActivity(it);
			}
		});
		
		return view;
	}

}
