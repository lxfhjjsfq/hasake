package com.tingshu.hasake.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tingshu.hasake.R;

public class KindSelectDialog extends Dialog{

	private String[] kinds;
	
	public KindSelectDialog(Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initView();
		initPosition();
	}
	
	public void setSelectStr(String[] kinds){
		this.kinds = kinds;
	}
	
	private void initView(){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		LinearLayout container = (LinearLayout) inflater.inflate(R.layout.dialog_album_select, null);
		for(int i = 0; i < kinds.length; i ++){
			if(!TextUtils.isEmpty(kinds[i])){
				container.addView(getItem(inflater, kinds[i]));
			}
		}
	}
	
	private void initPosition(){
		getWindow().setWindowAnimations(R.style.DialogAnim);
		WindowManager m = getWindow().getWindowManager();
		Display display = m.getDefaultDisplay(); //  获取屏幕宽、高用
		WindowManager.LayoutParams params = getWindow().getAttributes(); // 获取对话框当前的参数值
		params.width = (int) (display.getWidth() * 0.70); // 宽度设置为屏幕的0.7
		getWindow().setAttributes(params);
	}
	
	private TextView getItem(LayoutInflater inflater, final String kind){
		TextView tv = (TextView) inflater.inflate(R.layout.item_dialog_album_text, null);
		tv.setText(kind);
		
		tv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(mKindClickListener != null){
					mKindClickListener.onKindSelect(kind);
				}
			}
		});
		return tv;
	}
	
	public void setKindClickListener(KindClickListener listener){
		mKindClickListener = listener;
	}
	private KindClickListener mKindClickListener;
	public interface KindClickListener{
		public void onKindSelect(String Kind);
	}
}
