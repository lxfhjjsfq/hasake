package com.tingshu.hasake;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.adapter.GuanzhuLvAdapter;
import com.tingshu.hasake.bean.FansBean;
import com.tingshu.hasake.utils.Constans;

import android.widget.ListView;
import android.widget.TextView;

public class GuanZhuActivity extends BaseActivity {

	private ListView lv_guanzhu;
	private GuanzhuLvAdapter adapter;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_guanzhu);

	}

	@Override
	protected void initView() {
		lv_guanzhu = (ListView) findViewById(R.id.lv_guanzhu);
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("关注");

	}

	@Override
	protected void initData() {
		adapter = new GuanzhuLvAdapter(this);
		lv_guanzhu.setAdapter(adapter);

	}

	@Override
	protected void initListener() {

	}

	@Override
	public void getNetData() {
		super.getNetData();
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("id", application.getUserId());
		parms.put("Cur", 1);
		parms.put("rows", 10);
		HaskHttpUtils.sendGet(Constans.GetMyFans, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						JSONObject jsonObject = JSON.parseObject(result);
						if (jsonObject.containsKey("Result")) {

							List<FansBean> list = JSON.parseArray(jsonObject
									.getJSONArray("Result").toJSONString(),
									FansBean.class);
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

					}

					@Override
					public void onFailure(String error) {

					}
				});
	}

}
