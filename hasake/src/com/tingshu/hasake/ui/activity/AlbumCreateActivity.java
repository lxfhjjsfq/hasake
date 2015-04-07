package com.tingshu.hasake.ui.activity;

import java.util.HashMap;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.config.HasakeConfig;
import com.tingshu.hasake.dialog.KindSelectDialog;
import com.tingshu.hasake.dialog.KindSelectDialog.KindClickListener;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

public class AlbumCreateActivity extends BaseActivity {

	private ImageButton selectType;
	private TextView typeName;	//类型名称
	private EditText albumName;	//专辑名称
	private EditText albumDes;	//专辑描述
	private Button createButton;//创建
	
	
	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_create_album);
		
	}

	@Override
	protected void initView() {
		setTitleContent(getResources().getString(R.string.title_create_album));
		
		selectType = (ImageButton) findViewById(R.id.act_create_album_add_type);
		typeName = (TextView) findViewById(R.id.act_create_album_type_name);
		albumName = (EditText) findViewById(R.id.act_create_album_edit_name);
		albumDes = (EditText) findViewById(R.id.act_create_album_edit_des);
		createButton = (Button) findViewById(R.id.act_create_album_create_button);
	}

	@Override
	protected void initData() {
		HasakeConfig.initHomeType(this);
	}

	@Override
	protected void initListener() {
		selectType.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				KindSelectDialog dialog = new KindSelectDialog(AlbumCreateActivity.this);
				dialog.setKindClickListener(new KindClickListener() {
					public void onKindSelect(String Kind) {
						typeName.setText(Kind);
					}
				});
				dialog.show();
			}
		});
		createButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//创建专辑接口
				createAlbum();
			}
		});
	}

	private void createAlbum(){
		if(TextUtils.isEmpty(typeName.getText())){
			//需要适配语言
			Toast.makeText(this, "类型不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(albumName.getText())){
			//需要适配语言
			Toast.makeText(this, "专辑名称", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(albumDes.getText())){
			//需要适配语言
			Toast.makeText(this, "专辑描述", Toast.LENGTH_SHORT).show();
			return;
		}
		int aid = SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID,0);
		if(aid == 0 ){
			Toast.makeText(this, "还没有登陆哦", Toast.LENGTH_SHORT).show();
			return;
		}
		HashMap<String, Object> parms = new HashMap<String, Object>();
		int typeId = HasakeConfig.getSubTypeId(typeName.getText().toString());
		parms.put("AlbumName", albumName.getText());
		parms.put("Type", typeId);
		parms.put("AlbumBackgroundImg", "");
		parms.put("AccountID", aid);
		parms.put("Content", "ceshi");
		HaskHttpUtils.post(Constans.AddAlbum, parms, new HttpRequestCallBack() {
			public void onSuccess(String result) {
				Toast.makeText(AlbumCreateActivity.this, "创建专辑成功", Toast.LENGTH_SHORT).show();
				finish();
			}
			public void onStart() {
//				Toast.makeText(AlbumCreateActivity.this, "onStart", Toast.LENGTH_SHORT).show();
			}
			public void onFailure(String error) {
				Toast.makeText(AlbumCreateActivity.this, error, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
