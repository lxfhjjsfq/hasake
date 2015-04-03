package com.tingshu.hasake.fragment;

import com.tingshu.hasake.GuanZhuActivity;
import com.tingshu.hasake.MyMsgActivity;
import com.tingshu.hasake.PingLunActvity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.ui.activity.FansActivity;
import com.tingshu.hasake.ui.activity.HistoryActivity;
import com.tingshu.hasake.ui.activity.SpecialActivity;
import com.tingshu.hasake.ui.activity.ZanActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftFragment extends Fragment implements OnClickListener {
	private View view;
	private TextView tv_guanzhu;
	private TextView tv_fans;
	private TextView tv_msg;
	private TextView tv_zan;
	private TextView tv_bofang_jilu;
	private TextView tv_dingyue;
	private TextView tv_pinglun;

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
		return view;
	}

	private void initView() {
		tv_guanzhu = (TextView) view.findViewById(R.id.tv_guanzhu);
		tv_fans = (TextView) view.findViewById(R.id.tv_fans);
		tv_msg = (TextView) view.findViewById(R.id.tv_msg);
		tv_zan = (TextView) view.findViewById(R.id.tv_zan);
		tv_bofang_jilu = (TextView) view.findViewById(R.id.tv_bofang_jilu);
		tv_dingyue = (TextView) view.findViewById(R.id.dingyue);
		tv_dingyue.setOnClickListener(this);
		tv_pinglun = (TextView) view.findViewById(R.id.tv_pinglun);
		tv_pinglun.setOnClickListener(this);

	}

	private void initListener() {
		tv_zan.setOnClickListener(this);
		tv_msg.setOnClickListener(this);
		tv_bofang_jilu.setOnClickListener(this);
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
		default:
			break;
		}

	}

}
