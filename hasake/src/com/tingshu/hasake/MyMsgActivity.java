package com.tingshu.hasake;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.adapter.MsgItemAdapter;
import com.tingshu.hasake.bean.GuanZhuBean;
import com.tingshu.hasake.bean.MsgBean;
import com.tingshu.hasake.utils.Constans;

import android.widget.ListView;
import android.widget.TextView;

public class MyMsgActivity extends BaseActivity {
	private ListView lv_msg;
	private MsgItemAdapter adapter;

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
		adapter=new MsgItemAdapter(context);
		lv_msg.setAdapter(adapter);
		getNetData();
	}

	@Override
	protected void initListener() {

	}

	
	@Override
	public void getNetData() {
		super.getNetData();
		HashMap<String, Object> parms=new HashMap<String, Object>();
		parms.put("Userid", application.getUserId());
		parms.put("Cur", 1);
		parms.put("Rows", 10);
		new HaskHttpUtils().sendGet(Constans.getshowletter, parms, new HttpRequestCallBack() {
			
			@Override
			public void onSuccess(String result) {
				hideDialog();
				JSONObject jsonObject = JSON.parseObject(result);
				if (jsonObject.containsKey("Result")) {

					List<MsgBean> list = JSON.parseArray(jsonObject
							.getJSONArray("Result").toJSONString(),
							MsgBean.class);
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
				
			}
		});
	}
	
}
