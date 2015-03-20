package com.tingshu.hasake.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tingshu.hasake.AllMusicActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.HomeMusicGvAdapter;
import com.tingshu.hasake.ui.activity.PlayActivity;

public class MusicTypeView extends LinearLayout {
	private View v;
	private GridView gv;
	private RelativeLayout rl_more;
	private Context context;
	private TextView tv_type;
	private ImageView iv_type;

	public MusicTypeView(Context context) {
		super(context);
		this.context = context;
		initView(context);
		initListener();
	}

	private void initView(Context context) {
		v = LayoutInflater.from(context).inflate(R.layout.home_musictype_itme,
				null);
		iv_type=(ImageView) v.findViewById(R.id.iv_type);
		tv_type=(TextView) v.findViewById(R.id.tv_type_name);
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
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent=new Intent(context,PlayActivity.class);
				context.startActivity(intent);
				
			}
		});
	}
	
	public void setTypeIconAndName(int re,String name){
		tv_type.setText(name);
		iv_type.setImageResource(re);
	}

}
