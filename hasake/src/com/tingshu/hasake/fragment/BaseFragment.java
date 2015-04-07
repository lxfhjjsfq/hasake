package com.tingshu.hasake.fragment;

import com.tingshu.hasake.HaskApplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment{
	public HaskApplication application;
	public Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application=(HaskApplication) getActivity().getApplication();
		context=getActivity();
		
	}

}
