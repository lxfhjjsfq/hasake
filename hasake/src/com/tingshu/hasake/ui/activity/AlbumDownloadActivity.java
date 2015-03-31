package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.AlbumDownItemAdapter;

public class AlbumDownloadActivity extends BaseActivity {

	private ListView mListView;
	private AlbumDownItemAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_special);
	}

	@Override
	protected void initView() {
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("下载的专辑");
		
		mListView = (ListView) findViewById(R.id.act_special_list);
		adapter = new AlbumDownItemAdapter(this);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void initListener() {
		
	}


}
