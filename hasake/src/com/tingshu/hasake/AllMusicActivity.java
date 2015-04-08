package com.tingshu.hasake;

import java.util.HashMap;
import java.util.List;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.adapter.AllMusicLvAdapter;
import com.tingshu.hasake.bean.MusicBean;
import com.tingshu.hasake.bean.UserBean;
import com.tingshu.hasake.utils.Constans;

public class AllMusicActivity extends BaseActivity {
	private int typeId;
	private ListView lv_music;
	private AllMusicLvAdapter adapter;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_type_all_music);

	}

	@Override
	protected void initView() {
		lv_music = (ListView) findViewById(R.id.lv_all_music);
	}

	@Override
	protected void initData() {
		typeId = getIntent().getIntExtra("typeId", 0);
		adapter = new AllMusicLvAdapter(context);
		lv_music.setAdapter(adapter);
		getMusicByType();

	}

	@Override
	protected void initListener() {

	}

	private void getMusicByType() {
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("type", typeId);
		new HaskHttpUtils().sendGet(Constans.GetAlbum, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						JSONObject jsonObject = (JSONObject) JSONObject
								.parse(result);
						if (jsonObject.containsKey("Result")) {

							List<MusicBean> list = JSON.parseArray(jsonObject
									.getJSONArray("Result").toJSONString(),
									MusicBean.class);
							adapter.addMore(list);
						} else {

						}

					}

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(String error) {
						toast(error);
					}
				});
	}

}
