package com.tingshu.hasake;

import java.util.HashMap;
import java.util.List;

import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.adapter.MyAlbumAdapter;
import com.tingshu.hasake.bean.AlbumBean;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

public class AlbumActivity extends BaseActivity {
	private ListView lv_album;
	private MyAlbumAdapter adapter;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_my_album);

	}

	@Override
	protected void initView() {
		lv_album = (ListView) findViewById(R.id.lv_my_album);
		setTitleContent(getResources().getString(R.string.title_my_album));
	}

	@Override
	protected void initData() {
		adapter = new MyAlbumAdapter(context);
		lv_album.setAdapter(adapter);
		getMyAlbum();

	}

	@Override
	protected void initListener() {

	}

	private void getMyAlbum(){
		HashMap<String, Object> parms = new HashMap<String, Object>();
		int aid = SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID,-1);
		if(aid != -1 ){
			parms.put("userid", aid);
		}else{
			Toast.makeText(this, "还没有登陆哦", Toast.LENGTH_SHORT).show();
			return;
		}
		HaskHttpUtils.sendGet(Constans.GetMyAlbum, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						JSONObject jsonObject = (JSONObject) JSONObject
								.parse(result);
						if (jsonObject.containsKey("Result")) {

							List<AlbumBean> list = JSON.parseArray(jsonObject
									.getJSONArray("Result").toJSONString(),
									AlbumBean.class);
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
