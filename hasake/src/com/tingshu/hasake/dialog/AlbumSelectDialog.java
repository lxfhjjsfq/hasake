package com.tingshu.hasake.dialog;


import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.AlbumBean;
import com.tingshu.hasake.bean.MusicBean;
import com.tingshu.hasake.ui.activity.UploadMusicActivity;
import com.tingshu.hasake.utils.Constans;

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
import android.widget.Toast;

public class AlbumSelectDialog extends Dialog{

	private String[] albums;
	private int[] albumIds;
	private boolean isDiss = false;
	
	public AlbumSelectDialog(Context context) {
		super(context, R.style.SelectDialogTheme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initView();
		initPosition();
		
	}
	
	private void initView(){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View layout = inflater.inflate(R.layout.dialog_album_select, null);
		LinearLayout container = (LinearLayout) layout.findViewById(R.id.dialog_album_select_layout);
		for(int i = 0; albums != null && i < albums.length; i ++){
			if(!TextUtils.isEmpty(albums[i])){
				container.addView(getItem(inflater, albums[i], albumIds[i]));
			}
		}
		setContentView(layout);
	}
	
	public void initAlubmList(int typeId){
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("type", typeId);
		parms.put("Type1", 0);
		parms.put("Des", "");
		parms.put("Cur", 1);
		parms.put("Rows", 10);
		HaskHttpUtils.sendGet(Constans.GetAlbum, parms, new HttpRequestCallBack() {
			public void onSuccess(String result) {
				JSONObject jsonObject = (JSONObject) JSONObject
						.parse(result);
				if (jsonObject.containsKey("Result")) {

					List<AlbumBean> list = JSON.parseArray(jsonObject
							.getJSONArray("Result").toJSONString(),
							AlbumBean.class);
					albums = new String[list.size()];
					albumIds = new int[list.size()];
					for(int i = 0; i < albums.length; i ++){
						albums[i] = list.get(i).getName();
						albumIds[i] = list.get(i).getID();
					}
					initView();
					initPosition();
				} else {
					Toast.makeText(getContext(), "还没有创建节目类型的专辑", Toast.LENGTH_SHORT).show();
					dismiss();
				}
			}
			public void onStart() {
			}
			public void onFailure(String error) {
			}
		});
	}
	
	private void initPosition(){
		getWindow().setWindowAnimations(R.style.DialogAnim);
		WindowManager m = getWindow().getWindowManager();
		Display display = m.getDefaultDisplay(); //  获取屏幕宽、高用
		WindowManager.LayoutParams params = getWindow().getAttributes(); // 获取对话框当前的参数值
		params.width = (int) (display.getWidth() * 0.90); // 宽度设置为屏幕的0.7
		getWindow().setAttributes(params);
	}
	
	
	
	private TextView getItem(LayoutInflater inflater, final String album, final int albumId){
		TextView tv = (TextView) inflater.inflate(R.layout.item_dialog_album_text, null);
		tv.setText(album);
		tv.setPadding(0, 20, 0, 20);
		
		tv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(mAlbumClickListener != null){
					mAlbumClickListener.onAlbumSelect(album, albumId);
				}
				dismiss();
			}
		});
		return tv;
	}
	
	@Override
	public void dismiss() {
		
		super.dismiss();
	}
	
	public void setAlbumClickListener(AlbumClickListener listener){
		mAlbumClickListener = listener;
	}
	private AlbumClickListener mAlbumClickListener;
	public interface AlbumClickListener{
		public void onAlbumSelect(String album, int albumId);
	}
}
