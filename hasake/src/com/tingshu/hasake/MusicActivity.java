package com.tingshu.hasake;

import java.util.HashMap;
import java.util.List;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.adapter.MusicAdapter;
import com.tingshu.hasake.bean.MusicBean1;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

public class MusicActivity extends BaseActivity {
	private ListView lv_music;
	private MusicAdapter adapter;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_my_music);

	}

	@Override
	protected void initView() {
		lv_music = (ListView) findViewById(R.id.lv_my_music);
		setTitleContent(getResources().getString(R.string.title_my_misic));
	}

	@Override
	protected void initData() {
		adapter = new MusicAdapter(context);
		lv_music.setAdapter(adapter);
		getMusic();

	}

	@Override
	protected void initListener() {

	}

	private void getMusic(){
		
		//可以根据不同条件展示不同的音乐列表，如我的音乐或专辑下的音乐
		getMyMusic();
	}
	
	private void getMyMusic(){
		HashMap<String, Object> parms = new HashMap<String, Object>();
//		searchType=2&userid=1&Cur=1&Rows=10
		int aid = SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID,-1);
		if(aid != -1 ){
			parms.put("userid", aid);
		}
		parms.put("searchType", 3);//1最热2,最新,3原创
		parms.put("Cur", 1);
		parms.put("Rows", 10);
		HaskHttpUtils.sendGet(Constans.GetMyVideo, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						JSONObject jsonObject = (JSONObject) JSONObject
								.parse(result);
						if (jsonObject.containsKey("Result")) {

							List<MusicBean1> list = JSON.parseArray(jsonObject
									.getJSONArray("Result").toJSONString(),
									MusicBean1.class);
							adapter.addMore(list);
						} else {

						}

						hideDialog();
					}

					@Override
					public void onStart() {
						showDailog();
					}

					@Override
					public void onFailure(String error) {
						toast(error);
						hideDialog();
					}
				});
	}
	

}
