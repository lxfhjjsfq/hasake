package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.DingyueBean;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SpecialItemAdapter extends BaseAdapter {

	private Context context;
	private List<DingyueBean> list = new ArrayList<DingyueBean>();

	public SpecialItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		View view = LayoutInflater.from(context).inflate(
				R.layout.act_special_special_item, null);
		ImageView iv_dele=(ImageView) view.findViewById(R.id.act_fans_fans_item_button);
		iv_dele.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dele(0);
				
			}  
		});

		return view;
	}

	public void addMore(List<DingyueBean> dingyueBeans) {
		list.addAll(dingyueBeans);
	}
	
	/**
	 * 删除订阅
	 * @param id
	 */
	private void dele(int id){
		HashMap<String, Object> parms=new HashMap<String, Object>();
		parms.put("UserID", SfpUtils.getIntDataToSp(context, SfpUtils.USER_ID, -1));
		parms.put("Id", id);
		HaskHttpUtils.sendGet(Constans.DelSubscription, parms, new HttpRequestCallBack() {
			
			@Override
			public void onSuccess(String result) {
				
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(String error) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
