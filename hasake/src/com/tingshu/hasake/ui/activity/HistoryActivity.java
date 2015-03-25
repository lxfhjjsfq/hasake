package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.HistoryItemAdapter;

public class HistoryActivity extends BaseActivity {

	private ListView mListView;
	private HistoryItemAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_history);
	}

	@Override
	protected void initView() {
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("播放记录");
		
		mListView = (ListView) findViewById(R.id.act_history_list);
		adapter = new HistoryItemAdapter(this);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void initListener() {
		
	}


}