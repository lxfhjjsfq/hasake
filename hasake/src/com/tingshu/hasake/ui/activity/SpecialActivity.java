package com.tingshu.hasake.ui.activity;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.FansItemAdapter;
import com.tingshu.hasake.adapter.SpecialItemAdapter;
import com.tingshu.hasake.bean.DingyueBean;
import com.tingshu.hasake.bean.FansBean;
import com.tingshu.hasake.utils.Constans;

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
		getNetData();
	}

	@Override
	protected void initListener() {
		
	}

	@Override
	public void getNetData() {
		super.getNetData();
		HashMap<String, Object> parms=new HashMap<String, Object>();
		parms.put("Id", application.getUserId());
		parms.put("Cur", 1);
		parms.put("rows", 10);
		new HaskHttpUtils().sendGet(Constans.GetMySubscription, parms, new HttpRequestCallBack() {
			
			@Override
			public void onSuccess(String result) {
				hideDialog();
				JSONObject jsonObject = JSON.parseObject(result);
				if (jsonObject.containsKey("Result")) {

					List<DingyueBean> list = JSON.parseArray(jsonObject
							.getJSONArray("Result").toJSONString(),
							DingyueBean.class);
					if (list.size() == 0) {
						toast("没有更多了");
					} else {
						adapter.addMore(list);
					}

				} else {

				}
				
			}
			
			@Override
			public void onStart() {
				showDailog();
				
			}
			
			@Override
			public void onFailure(String error) {
				hideDialog();
				toast(error);
			}
		});
	}

}
