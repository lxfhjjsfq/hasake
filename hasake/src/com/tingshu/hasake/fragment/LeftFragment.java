package com.tingshu.hasake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tingshu.hasake.R;
import com.tingshu.hasake.ui.activity.FansActivity;

public class LeftFragment extends Fragment {
	private View view;
	private LinearLayout slidingMenu_attention;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.left_side_item, null);
		initView(view);
		return view;
	}

	private void initView(View view){
		slidingMenu_attention = (LinearLayout) view.findViewById(R.id.act_main_attetion);
		slidingMenu_attention.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent it = new Intent(getActivity(), FansActivity.class);
				startActivity(it);
			}
		});
	}
	
}
