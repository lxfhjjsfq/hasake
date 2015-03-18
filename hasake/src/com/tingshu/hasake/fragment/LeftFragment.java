package com.tingshu.hasake.fragment;

import com.tingshu.hasake.GuanZhuActivity;
import com.tingshu.hasake.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftFragment extends Fragment {
	private View view;
	private TextView tv_guanzhu;
	private TextView tv_face;

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

	}

	private void initListener() {
		tv_guanzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), GuanZhuActivity.class);
				startActivity(intent);

			}
		});
	}

}
