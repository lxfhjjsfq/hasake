package com.tingshu.hasake.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.tingshu.hasake.R;

public class PlayAttentionItemView extends LinearLayout{

	private LinearLayout mContainer;

	public PlayAttentionItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public PlayAttentionItemView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context){
		LayoutInflater inflater = LayoutInflater.from(context);
		mContainer = (LinearLayout) inflater.inflate(R.layout.act_play_attention_item, this);
	}
	
}
