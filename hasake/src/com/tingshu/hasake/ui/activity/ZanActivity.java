package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.ZanItemAdapter;
import com.tingshu.hasake.utils.Constans;

public class ZanActivity extends BaseActivity {

	private ListView mListView;
	private ZanItemAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_zan);
	}

	@Override
	protected void initView() {
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("赞过的");
		
		mListView = (ListView) findViewById(R.id.act_zan_list);
		adapter = new ZanItemAdapter(this);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		getNetData();
		
	}

	@Override
	protected void initListener() {
		
	}
	
	@Override
	public void getNetData() {
		super.getNetData();
		 //Constans
	}


}
