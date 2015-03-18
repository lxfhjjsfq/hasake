package com.tingshu.hasake;

import android.widget.GridView;

import com.tingshu.hasake.adapter.MusicTypeGvAdapter;

public class AllMusicActivity extends BaseActivity {
	
	private GridView gv_all_music;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_all_music);

	}

	@Override
	protected void initView() {
		gv_all_music=(GridView) findViewById(R.id.gv_all_music);
	}

	@Override
	protected void initData() {
		gv_all_music.setAdapter(new MusicTypeGvAdapter(context));

	}

	@Override
	protected void initListener() {

	}

}
