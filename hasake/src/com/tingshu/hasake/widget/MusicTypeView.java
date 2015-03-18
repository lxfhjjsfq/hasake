package com.tingshu.hasake.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tingshu.hasake.AllMusicActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.HomeMusicGvAdapter;

public class MusicTypeView extends LinearLayout {
	private View v;
	private GridView gv;
	private RelativeLayout rl_more;
	private Context context;

	public MusicTypeView(Context context) {
		super(context);
		this.context = context;
		initView(context);
		initListener();
	}

	private void initView(Context context) {
		v = LayoutInflater.from(context).inflate(R.layout.home_musictype_itme,
				null);
		rl_more = (RelativeLayout) v.findViewById(R.id.rl_more);
		gv = (GridView) v.findViewById(R.id.gv_music_type);
		gv.setAdapter(new HomeMusicGvAdapter(context));
		addView(v);
	}

	private void initListener() {
		rl_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, AllMusicActivity.class);
				context.startActivity(intent);
			}
		});
	}

}
