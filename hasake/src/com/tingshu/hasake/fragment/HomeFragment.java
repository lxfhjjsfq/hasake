package com.tingshu.hasake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tingshu.hasake.AllMusicTypeActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.widget.MusicTypeView;

public class HomeFragment extends Fragment implements OnClickListener {
	private View view;
	private LinearLayout ll_music_type;
	private TextView tv_wenxue;
	private TextView tv_yule;
	private TextView tv_lishi;
	private TextView tv_wenhua;
	private TextView tv_yinyue;
	private TextView tv_ertong;
	private TextView tv_jiaoyu;
	private TextView tv_more;
	private final int TYPE_ICONS[] = { R.drawable.home_type_yinyue,
			R.drawable.home_type_zhuanji, R.drawable.home_type_geren };
	private final String TYPE_NAMSE[] = { "音乐", "专辑推荐", "个人" };

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
		tv_ertong = (TextView) view.findViewById(R.id.tv_ertong);
		tv_ertong.setOnClickListener(this);
		tv_jiaoyu = (TextView) view.findViewById(R.id.tv_jiankang);
		tv_jiaoyu.setOnClickListener(this);
		tv_wenhua = (TextView) view.findViewById(R.id.tv_wenhua);
		tv_wenhua.setOnClickListener(this);

		tv_wenxue = (TextView) view.findViewById(R.id.tv_wenxue);
		tv_wenxue.setOnClickListener(this);

		tv_yule = (TextView) view.findViewById(R.id.tv_yule);
		tv_yule.setOnClickListener(this);

		tv_lishi = (TextView) view.findViewById(R.id.tv_lishi);
		tv_lishi.setOnClickListener(this);

		tv_yinyue = (TextView) view.findViewById(R.id.tv_yinyue);
		tv_yinyue.setOnClickListener(this);

		tv_more = (TextView) view.findViewById(R.id.tv_more);
		tv_more.setOnClickListener(this);

		ll_music_type = (LinearLayout) view.findViewById(R.id.ll_music_type);
		for (int i = 0; i < 3; i++) {
			MusicTypeView musicTypeView = new MusicTypeView(getActivity());
			musicTypeView.setTypeIconAndName(TYPE_ICONS[i], TYPE_NAMSE[i]);
			ll_music_type.addView(musicTypeView);
		}

	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(getActivity(), AllMusicTypeActivity.class);
		int typeId = -1;
		switch (arg0.getId()) {
		case R.id.tv_wenxue:
			typeId = 0;
			break;
		case R.id.tv_yule:
			typeId = 1;
			break;
		case R.id.tv_lishi:
			typeId = 2;
			break;

		case R.id.tv_yinyue:
			typeId = 3;
			break;
		case R.id.tv_ertong:
			typeId = 4;
			break;
		case R.id.tv_jiankang:
			typeId = 5;
			break;

		default:
			break;
		}
		intent.putExtra("typeId", typeId);
		startActivity(intent);

	}

}
