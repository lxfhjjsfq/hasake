package com.tingshu.hasake.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.HomeMusicGvAdapter;

public class MusicTypeView extends LinearLayout{
	private View v;
	private GridView gv;

	public MusicTypeView(Context context) {
		super(context);
		initView(context);
	}
	
	private void initView(Context context){
		v=LayoutInflater.from(context).inflate(R.layout.home_musictype_itme, null);
		gv=(GridView) v.findViewById(R.id.gv_music_type);
		gv.setAdapter(new HomeMusicGvAdapter(context));
		addView(v);
	}

}
