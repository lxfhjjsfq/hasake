package com.tingshu.hasake.dialog;


import java.io.File;

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

import com.tingshu.hasake.R;
import com.tingshu.hasake.utils.FileUtil;

public class MusicSelectDialog extends Dialog{

	private String[] files;
	private String filePath;
	
	public MusicSelectDialog(Context context) {
		super(context, R.style.SelectDialogTheme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initView(FileUtil.getExternalDir());
		initPosition();
		
	}
	
	private void initView(String dir){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View layout = inflater.inflate(R.layout.dialog_album_select, null);
		LinearLayout container = (LinearLayout) layout.findViewById(R.id.dialog_album_select_layout);
		
		File file = new File(dir);
		files = file.list();
		filePath = dir;
		if(files.length == 0){
			Toast.makeText(getContext(), "目录下没有文件了哦", Toast.LENGTH_SHORT).show();
			dismiss();
			return;
		}
		for(int i = 0; files != null && i < files.length; i ++){
			if(!TextUtils.isEmpty(files[i]) && files[i].indexOf(".") != 0){
				container.addView(getItem(inflater, files[i], filePath + "/" + files[i]));
			}
		}
		setContentView(layout);
	}
	
	private void initPosition(){
		getWindow().setWindowAnimations(R.style.DialogAnim);
		WindowManager m = getWindow().getWindowManager();
		Display display = m.getDefaultDisplay(); //  获取屏幕宽、高用
		WindowManager.LayoutParams params = getWindow().getAttributes(); // 获取对话框当前的参数值
		params.width = (int) (display.getWidth() * 0.90); // 宽度设置为屏幕的0.7
		params.height = (int) (display.getHeight() * 0.70); // 宽度设置为屏幕的0.7
		getWindow().setAttributes(params);
	}
	
	
	
	private TextView getItem(LayoutInflater inflater, final String file, final String path){
		TextView tv = (TextView) inflater.inflate(R.layout.item_dialog_album_text, null);
		tv.setText(file);
		tv.setPadding(0, 20, 0, 20);
		
		tv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(mMusicClickListener != null){
					File mFile = new File(path);
					if(mFile.isDirectory()){
						initView(path);
					}else if(mFile.isFile()){
						if(file.lastIndexOf(".mp3") > 0){
							mMusicClickListener.onMusicSelect(file, path);
						}else{
							Toast.makeText(getContext(), "文件格式不对哦", Toast.LENGTH_SHORT).show();
						}
						dismiss();
					}
				}
			}
		});
		return tv;
	}
	
	@Override
	public void dismiss() {
		
		super.dismiss();
	}
	
	public void setMusicClickListener(MusicClickListener listener){
		mMusicClickListener = listener;
	}
	private MusicClickListener mMusicClickListener;
	public interface MusicClickListener{
		public void onMusicSelect(String music, String path);
	}
}
