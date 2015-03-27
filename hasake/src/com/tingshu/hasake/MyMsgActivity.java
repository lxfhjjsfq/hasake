package com.tingshu.hasake;

import com.tingshu.hasake.adapter.MsgItemAdapter;

import android.widget.ListView;
import android.widget.TextView;

public class MyMsgActivity extends BaseActivity {
	private ListView lv_msg;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_msg);
		
	}

	@Override
	protected void initView() {
		lv_msg=(ListView) findViewById(R.id.lv_msg);
		setTitleContent("私信");
	}

	@Override
	protected void initData() {
		lv_msg.setAdapter(new MsgItemAdapter(context));
	}

	@Override
	protected void initListener() {

	}

	
}
