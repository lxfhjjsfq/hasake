package com.tingshu.hasake.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tingshu.hasake.R;
import com.tingshu.hasake.widget.MusicTypeView;

public class HomeFragment extends Fragment {
	private View view;
	private LinearLayout ll_music_type;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, null);
		initView();
		return view;
	}

	private void initView() {
		ll_music_type = (LinearLayout) view.findViewById(R.id.ll_music_type);
		for (int i = 0; i < 3; i++) {
			ll_music_type.addView(new MusicTypeView(getActivity()));
		}

	}

}
