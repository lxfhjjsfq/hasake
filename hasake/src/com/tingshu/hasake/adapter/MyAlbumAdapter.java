package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.SpecialItemAdapter.Holder;
import com.tingshu.hasake.bean.AlbumBean;
import com.tingshu.hasake.bean.DingyueBean;
import com.tingshu.hasake.bean.MusicBean;
import com.tingshu.hasake.bean.MusicBean1;
import com.tingshu.hasake.bean.TypeBean;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAlbumAdapter extends BaseAdapter {

	private Context context;
	private List<AlbumBean> list = new ArrayList<AlbumBean>();

	public MyAlbumAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list.size();
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

		Holder holder = null;

		if (arg1 == null) {
			holder = new Holder();
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.act_special_special_item, null);
			holder.iv_dele = (ImageView) arg1
					.findViewById(R.id.act_special_special_item_button);
			holder.iv_dingyue_head = (ImageView) arg1
					.findViewById(R.id.iv_dingyue_head);
			holder.tv_dingyu_name = (TextView) arg1
					.findViewById(R.id.tv_dingyu_name);
			holder.tv_dingyue_time = (TextView) arg1
					.findViewById(R.id.tv_dingyue_time);
			holder.tv_jiemu_count = (TextView) arg1
					.findViewById(R.id.tv_jiemu_count);
			arg1.setTag(holder);

		} else {
			holder = (Holder) arg1.getTag();
		}
		AlbumBean album = list.get(arg0);
		holder.tv_dingyu_name.setText(album.getName());
		holder.tv_dingyue_time.setText(album.getUpdateTime());
		holder.tv_jiemu_count.setText("节目数 "+album.getCount() + "");
		holder.iv_dele.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// dele(dingyueBean.get);

			}
		});
		return arg1;
	}

	public void addMore(List<AlbumBean> albumList) {
		list.addAll(albumList);
		notifyDataSetChanged();
	}

	class Holder {
		TextView tv_dingyu_name;
		TextView tv_jiemu_count;
		TextView tv_dingyue_time;
		ImageView iv_dingyue_head;
		ImageView iv_dele;
	}

	/**
	 * 删除订阅
	 * 
	 * @param id
	 */
	private void dele(int id) {
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("UserID",
				SfpUtils.getIntDataToSp(context, SfpUtils.USER_ID, -1));
		parms.put("Id", id);
		new HaskHttpUtils().sendGet(Constans.DelSubscription, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT)
								.show();

					}

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(String error) {
						// TODO Auto-generated method stub

					}
				});
	}
}
