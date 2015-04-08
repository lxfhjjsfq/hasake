package com.tingshu.hasake.ui.activity;

import java.io.File;
import java.util.HashMap;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.fengwei.app.http.HaskHttpUtils.UploadCallback;
import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.config.HasakeConfig;
import com.tingshu.hasake.dialog.AlbumSelectDialog;
import com.tingshu.hasake.dialog.AlbumSelectDialog.AlbumClickListener;
import com.tingshu.hasake.dialog.KindSelectDialog;
import com.tingshu.hasake.dialog.KindSelectDialog.KindClickListener;
import com.tingshu.hasake.dialog.MusicSelectDialog;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

public class UploadMusicActivity extends BaseActivity {

	private ImageButton ib_addType;
	private ImageButton ib_addAlbum;
	private ImageButton ib_addMusic;
	private TextView tv_addType;
	private TextView tv_addAlbum;
	private TextView tv_addMusic;
	private EditText et_addDes;
	private EditText et_musicName;
	private Button bt_cancel;
	private Button bt_upload;
	
	private String filePath;
	private int albumId;
	
	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_upload_music);
		
	}

	@Override
	protected void initView() {
		setTitleContent(getResources().getString(R.string.title_create_album));
		
		ib_addType = (ImageButton) findViewById(R.id.act_upload_music_add_type);
		ib_addAlbum = (ImageButton) findViewById(R.id.act_upload_music_add_album);
		ib_addMusic = (ImageButton) findViewById(R.id.act_upload_music_add_music);
		tv_addType = (TextView) findViewById(R.id.act_upload_music_type_name);
		tv_addAlbum = (TextView) findViewById(R.id.act_upload_music_album_name);
		tv_addMusic = (TextView) findViewById(R.id.act_upload_music_music_name);
		et_addDes = (EditText) findViewById(R.id.act_upload_music_edit_des);
		et_musicName = (EditText) findViewById(R.id.act_upload_music_edit_name);
		bt_cancel = (Button) findViewById(R.id.act_upload_music_cancel_button);
		bt_upload = (Button) findViewById(R.id.act_upload_music_upload_button);
		
	}

	@Override
	protected void initData() {
		HasakeConfig.initHomeType(this);
	}

	@Override
	protected void initListener() {
		ib_addType.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				KindSelectDialog dialog = new KindSelectDialog(UploadMusicActivity.this);
				dialog.setKindClickListener(new KindClickListener() {
					public void onKindSelect(String Kind) {
						tv_addType.setText(Kind);
					}
				});
				dialog.show();
			}
		});
		ib_addAlbum.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(TextUtils.isEmpty(tv_addType.getText())){
					Toast.makeText(UploadMusicActivity.this, "请先选择类型", Toast.LENGTH_SHORT).show();
					return;
				}
				AlbumSelectDialog dialog = new AlbumSelectDialog(UploadMusicActivity.this);
				int typeId = HasakeConfig.getSubTypeId(tv_addType.getText().toString());
				dialog.initAlubmList(typeId);
				dialog.setAlbumClickListener(new AlbumClickListener() {
					public void onAlbumSelect(String album, int albumId) {
						tv_addAlbum.setText(album);
					}
				});
				dialog.show();
			}
		});
		ib_addMusic.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(TextUtils.isEmpty(tv_addAlbum.getText())){
					Toast.makeText(UploadMusicActivity.this, "请先选择专辑", Toast.LENGTH_SHORT).show();
					return;
				}
				//mp3格式的列表
				MusicSelectDialog dialog = new MusicSelectDialog(UploadMusicActivity.this);
				dialog.setMusicClickListener(new MusicSelectDialog.MusicClickListener() {
					public void onMusicSelect(String music, String path) {
						filePath = path;
						tv_addMusic.setText(music);
					}
				});
				dialog.show();
//				KindSelectDialog dialog = new KindSelectDialog(UploadMusicActivity.this);
//				dialog.setKindClickListener(new KindClickListener() {
//					public void onKindSelect(String Kind) {
//						tv_addMusic.setText(Kind);
//					}
//				});
//				dialog.show();
			}
		});
		bt_cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		bt_upload.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				uploadMusic();
			}
		});
	}

	private void uploadMusic(){
		if(TextUtils.isEmpty(tv_addType.getText())){
			//需要适配语言
			Toast.makeText(this, "类型不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(tv_addAlbum.getText())){
			//需要适配语言
			Toast.makeText(this, "专辑不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(et_musicName.getText())){
			//需要适配语言
			Toast.makeText(this, "音乐名称不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(et_addDes.getText())){
			//需要适配语言
			Toast.makeText(this, "音乐简介不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		int aid = SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID,0);
		if(aid == 0 ){
			Toast.makeText(this, "还没有登陆哦", Toast.LENGTH_SHORT).show();
			return;
		}
		HashMap<String, Object> parms = new HashMap<String, Object>();
//		parms.put("AlbumID", albumName.getText());
		parms.put("AlbumID", albumId);
		parms.put("VideoName", et_musicName.getText());
		parms.put("Des", et_addDes.getText());
		parms.put("AccountID", aid);
		parms.put("VideoImg", "");
		parms.put("ViderUrl", "123");
		HaskHttpUtils.upload(Constans.AddMusic, parms, filePath, new UploadCallback() {
			public void onSuccess(String result) {
				Toast.makeText(UploadMusicActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
				finish();
			}
			public void onStart() {
				Toast.makeText(UploadMusicActivity.this, "onStart", Toast.LENGTH_SHORT).show();
			}
			public void onFailure(String error) {
				Toast.makeText(UploadMusicActivity.this, error, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				Log.e("upload ...", "total : " + total + " ,percent : " + (current * 100 / total));
			}
		});
	}
	
}
