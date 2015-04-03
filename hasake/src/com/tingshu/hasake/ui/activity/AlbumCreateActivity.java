package com.tingshu.hasake.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.dialog.AlbumSelectDialog;

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

	}

	@Override
	protected void initListener() {
		selectType.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlbumSelectDialog dialog = new AlbumSelectDialog(AlbumCreateActivity.this);
				
			}
		});
		createButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//创建专辑接口
			}
		});
	}

}
