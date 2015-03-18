package com.tingshu.hasake;

import com.tingshu.hasake.adapter.GuanzhuLvAdapter;

import android.widget.ListView;

public class GuanZhuActivity extends BaseActivity {

	private ListView lv_guanzhu;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_guanzhu);

	}

	@Override
	protected void initView() {
		lv_guanzhu = (ListView) findViewById(R.id.lv_guanzhu);

	}

	@Override
	protected void initData() {
		lv_guanzhu.setAdapter(new GuanzhuLvAdapter(context));

	}

	@Override
	protected void initListener() {

	}

}
