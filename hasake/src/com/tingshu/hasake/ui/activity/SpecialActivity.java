package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.FansItemAdapter;
import com.tingshu.hasake.adapter.SpecialItemAdapter;

public class SpecialActivity extends BaseActivity {

	private ListView mListView;
	private SpecialItemAdapter adapter;
	
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
		title.setText("订阅的专辑");
		
		mListView = (ListView) findViewById(R.id.act_special_list);
		adapter = new SpecialItemAdapter(this);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void initListener() {
		
	}


}
