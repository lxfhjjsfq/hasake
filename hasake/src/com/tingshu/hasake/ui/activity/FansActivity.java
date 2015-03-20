package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.FansItemAdapter;

public class FansActivity extends BaseActivity {

	private ListView mListView;
	private FansItemAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_fans);
	}

	@Override
	protected void initView() {
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("粉丝");
		
		mListView = (ListView) findViewById(R.id.act_fans_list);
		adapter = new FansItemAdapter(this);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void initListener() {
		
	}


}
