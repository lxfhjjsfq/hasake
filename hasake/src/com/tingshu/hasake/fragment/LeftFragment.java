package com.tingshu.hasake.fragment;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.GuanZhuActivity;
import com.tingshu.hasake.MyMsgActivity;
import com.tingshu.hasake.PingLunActvity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.ui.activity.AlbumCreateActivity;
import com.tingshu.hasake.bean.UserDisBean;
import com.tingshu.hasake.ui.activity.FansActivity;
import com.tingshu.hasake.ui.activity.HistoryActivity;
import com.tingshu.hasake.ui.activity.SpecialActivity;
import com.tingshu.hasake.ui.activity.UploadMusicActivity;
import com.tingshu.hasake.ui.activity.ZanActivity;
import com.tingshu.hasake.utils.Constans;

import android.content.Intent;
import android.media.UnsupportedSchemeException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftFragment extends BaseFragment implements OnClickListener {
	private View view;
	private TextView tv_guanzhu;
	private TextView tv_fans;
	private TextView tv_msg;
	private TextView tv_zan;
	private TextView tv_bofang_jilu;
	private TextView tv_dingyue;
	private TextView tv_pinglun;
	
	private Button bt_create;
	private Button bt_upload;
	private ImageView iv_head;
	private TextView tv_nick;
	private TextView tv_dis;
	private TextView tv_guanzhu_count;
	private TextView tv_fans_count;
	private TextView tv_my_zhuanji;
	private TextView tv_my_voice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.left_side_item, null);
		initView();
		initListener();
		initData();
		return view;
	}

	private void initView() {
		iv_head = (ImageView) view.findViewById(R.id.iv_head);
		tv_nick = (TextView) view.findViewById(R.id.tv_nickname);
		tv_dis = (TextView) view.findViewById(R.id.tv_dis);
		tv_guanzhu_count = (TextView) view.findViewById(R.id.tv_guanzhu_count);
		tv_fans_count = (TextView) view.findViewById(R.id.tv_fans_count);
		tv_my_zhuanji = (TextView) view.findViewById(R.id.tv_zhuanji_count);
		tv_my_voice = (TextView) view.findViewById(R.id.tv_my_voice);
		tv_guanzhu = (TextView) view.findViewById(R.id.tv_guanzhu);
		tv_fans = (TextView) view.findViewById(R.id.tv_fans);
		tv_msg = (TextView) view.findViewById(R.id.tv_msg);
		tv_zan = (TextView) view.findViewById(R.id.tv_zan);
		tv_bofang_jilu = (TextView) view.findViewById(R.id.tv_bofang_jilu);
		tv_dingyue = (TextView) view.findViewById(R.id.dingyue);
		tv_dingyue.setOnClickListener(this);
		tv_pinglun = (TextView) view.findViewById(R.id.tv_pinglun);
		tv_pinglun.setOnClickListener(this);

		bt_create = (Button) view.findViewById(R.id.bt_create);
		bt_upload = (Button) view.findViewById(R.id.bt_upload);
	}

	private void initData() {
		getNetData();
	}

	private void initListener() {
		tv_zan.setOnClickListener(this);
		tv_msg.setOnClickListener(this);
		tv_bofang_jilu.setOnClickListener(this);
		bt_create.setOnClickListener(this);
		bt_upload.setOnClickListener(this);
		tv_guanzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), GuanZhuActivity.class);
				startActivity(intent);

			}
		});
		tv_fans.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), FansActivity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_msg:
			Intent intent = new Intent(getActivity(), MyMsgActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_zan:
			Intent zanintent = new Intent(getActivity(), ZanActivity.class);
			startActivity(zanintent);
			break;
		case R.id.tv_bofang_jilu:
			Intent jiluintent = new Intent(getActivity(), HistoryActivity.class);
			startActivity(jiluintent);
			break;
		case R.id.dingyue:
			Intent dinyueIntent = new Intent(getActivity(),
					SpecialActivity.class);
			startActivity(dinyueIntent);
			break;
		case R.id.tv_pinglun:
			Intent pinglunIntent = new Intent(getActivity(),
					PingLunActvity.class);
			startActivity(pinglunIntent);
			break;
		case R.id.bt_create:
			Intent createIntent = new Intent(getActivity(),
					AlbumCreateActivity.class);
			startActivity(createIntent);
			break;
		case R.id.bt_upload:
			Intent uploadIntent = new Intent(getActivity(),
					UploadMusicActivity.class);
			startActivity(uploadIntent);
			break;
		default:
			break;
		}

	}

	private void getNetData() {
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("Id", application.getUserId());
		new HaskHttpUtils().sendGet(Constans.GetUser, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						String json = JSON.parseObject(result)
								.getJSONObject("Result").toJSONString();
						UserDisBean disBean = JSON.parseObject(json,
								UserDisBean.class);
						bindDataToView(disBean);

					}

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(String error) {
						Toast(error);
					}
				});
	}

	private void bindDataToView(UserDisBean disBean) {
		tv_nick.setText(disBean.getNickname());
		//tv_dis=disBean.set
		//tv_guanzhu_count.setText(disBean.get)
		tv_fans_count.setText(String.valueOf(disBean.getFansCount()));
		tv_my_zhuanji.setText(String.valueOf(disBean.getViderCount()));
	}
}
