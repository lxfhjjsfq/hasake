package com.tingshu.hasake.dialog;


import com.tingshu.hasake.R;

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

public class AlbumSelectDialog extends Dialog{

	private String[] albums;
	
	public AlbumSelectDialog(Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initView();
		initPosition();
	}
	
	public void setSelectStr(String[] albums){
		this.albums = albums;
	}
	
	private void initView(){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		LinearLayout container = (LinearLayout) inflater.inflate(R.layout.dialog_album_select, null);
		for(int i = 0; i < albums.length; i ++){
			if(!TextUtils.isEmpty(albums[i])){
				container.addView(getItem(inflater, albums[i]));
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
	
	private TextView getItem(LayoutInflater inflater, final String album){
		TextView tv = (TextView) inflater.inflate(R.layout.item_dialog_album_text, null);
		tv.setText(album);
		
		tv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(mAlbumClickListener != null){
					mAlbumClickListener.onAlbumSelect(album);
				}
			}
		});
		return tv;
	}
	
	public void setAlbumClickListener(AlbumClickListener listener){
		mAlbumClickListener = listener;
	}
	private AlbumClickListener mAlbumClickListener;
	public interface AlbumClickListener{
		public void onAlbumSelect(String album);
	}
}
