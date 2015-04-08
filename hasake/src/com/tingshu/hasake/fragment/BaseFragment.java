package com.tingshu.hasake.fragment;

import com.tingshu.hasake.HaskApplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment{
	public HaskApplication application;
	public Context context;
	public ProgressDialog pd;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application=(HaskApplication) getActivity().getApplication();
		context=getActivity();
		pd=new ProgressDialog(context);
		pd.setMessage("数据加载");
		
	}
	
	public void Toast(String msg){
		android.widget.Toast.makeText(getActivity(), msg, android.widget.Toast.LENGTH_SHORT).show();
	}
	
	public void showPd(){
		pd.show();
	}
	
	public void hiedePd(){
		pd.dismiss();
	}

}
