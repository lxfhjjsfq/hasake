package com.tingshu.hasake.fragment;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.PingLunLvAdapter;
import com.tingshu.hasake.bean.PingLunBean;
import com.tingshu.hasake.utils.Constans;

@SuppressLint("ValidFragment")
public class PingLunFragment extends BaseFragment {
	private View view;
	private ListView lv_pinglu;
	private PingLunLvAdapter adapter;
	private String url;

	 public static PingLunFragment newInstance(String url) {
		PingLunFragment lunFragment=new PingLunFragment();
		Bundle bundle = new Bundle();  
        bundle.putString("url", url);  
        lunFragment.setArguments(bundle);
		return lunFragment;
	}
	 private PingLunFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pinglu_fragment, null);
		initView();
		initData();
		return view;
	}

	private void initView() {
		lv_pinglu = (ListView) view.findViewById(R.id.lv_pinglun);
	}

	private void initData() {
		adapter = new PingLunLvAdapter(getActivity());
		lv_pinglu.setAdapter(adapter);
		getNetData();
	}

	public void getNetData() {
		url=getArguments().getString("url");
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("userid", application.getUserId());
		parms.put("Cur", 1);
		parms.put("Rows", 10);
		new HaskHttpUtils().sendGet(url, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						hiedePd();
						JSONObject jsonObject = (JSONObject) JSONObject
								.parse(result);
						if (jsonObject.containsKey("Result")) {

							List<PingLunBean> list = JSON.parseArray(jsonObject
									.getJSONArray("Result").toJSONString(),
									PingLunBean.class);
							if (list.size() == 0) {
								Toast.makeText(context, "没有更多评论了",
										Toast.LENGTH_SHORT).show();
							} else {
								adapter.addMore(list);
							}

						} else {

						}
					}

					@Override
					public void onStart() {
						showPd();
					}

					@Override
					public void onFailure(String error) {
						hiedePd();
					}
				});
	}

}
