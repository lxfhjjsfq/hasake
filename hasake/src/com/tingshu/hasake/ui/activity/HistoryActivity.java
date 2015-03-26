package com.tingshu.hasake.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.tingshu.hasake.BaseActivity;
import com.tingshu.hasake.R;
import com.tingshu.hasake.adapter.HistoryItemAdapter;
import com.tingshu.hasake.adapter.HistoryItemAdapter.HistoryDao;

public class HistoryActivity extends BaseActivity implements OnClickListener {

	private TextView titleRightMenu;
	private ListView mListView;
	private HistoryItemAdapter adapter;
	private TextView delete_history;
	private TextView clear_history;
	
	private String[] operation = {"选择删除", "删除", "一键清空", "完成"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_history);
	}

	@Override
	protected void initView() {
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		titleRightMenu =  (TextView) findViewById(R.id.act_tiltle_right_text);
		title.setText("播放记录");
		
		delete_history = (TextView) findViewById(R.id.act_history_delete);
		clear_history = (TextView) findViewById(R.id.act_history_clear);
		mListView = (ListView) findViewById(R.id.act_history_list);
		adapter = new HistoryItemAdapter(this);
		mListView.setAdapter(adapter);
		
		delete_history.setText(operation[0]);
		clear_history.setText(operation[2]);
		
		delete_history.setOnClickListener(this);
		clear_history.setOnClickListener(this);
		titleRightMenu.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		testData();
	}

	@Override
	protected void initListener() {
		
	}

	private void testData(){
		for(int i = 1; i < 15; i ++){
			HistoryDao history = new HistoryDao();
			history.setName("晚上十点" + "【周" + getDay(i) + "】" + "-诉说你的心声");
			history.setTime("by " + "晚上十点");
			adapter.addHistory(history);
		}
	}
	
	private String getDay(int i){
		int day = i % 8;
		String ret = "";
		switch(day){
		case 1:
			ret = "一";
			break;
		case 2:
			ret = "二";
			break;
		case 3:
			ret = "三";
			break;
		case 4:
			ret = "四";
			break;
		case 5:
			ret = "五";
			break;
		case 6:
			ret = "六";
			break;
		case 7:
			ret = "日";
			break;
		}
		
		return ret;
	}

	@Override
	public void onClick(View v) {
		TextView tv = (TextView) v;
		if(tv.getText().toString().equals(operation[0])){
			//变为可编辑状态
			adapter.setCanCheck(true);
			delete_history.setText(operation[1]);
			titleRightMenu.setText(operation[3]);
			titleRightMenu.setVisibility(View.VISIBLE);
		}else if(tv.getText().toString().equals(operation[1])){
			//删除历史记录
			adapter.removeHistory();
		}else if(tv.getText().toString().equals(operation[2])){
			//一键清空(是否提示框)
			
		}else if(tv.getText().toString().equals(operation[3])){
			//完成编辑
			adapter.setCanCheck(false);
			titleRightMenu.setVisibility(View.GONE);
			delete_history.setText(operation[0]);
		}
	}
}
