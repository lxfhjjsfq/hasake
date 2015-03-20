package com.tingshu.hasake;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.tingshu.hasake.adapter.MusicTypeGvAdapter;
import com.tingshu.hasake.ui.activity.PlayActivity;

public class AllMusicActivity extends BaseActivity {

	private GridView gv_all_music;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_all_music);

	}

	@Override
	protected void initView() {
		gv_all_music = (GridView) findViewById(R.id.gv_all_music);
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("全部");
	}

	@Override
	protected void initData() {
		gv_all_music.setAdapter(new MusicTypeGvAdapter(context));

	}

	@Override
	protected void initListener() {
		gv_all_music.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, PlayActivity.class);
				context.startActivity(intent);
			}
		});

	}

}
