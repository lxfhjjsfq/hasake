package com.tingshu.hasake.adapter;

import com.tingshu.hasake.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class FansItemAdapter extends BaseAdapter {

	private Context context;

	public FansItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		View view = LayoutInflater.from(context).inflate(R.layout.act_fans_fans_item, null);
		
		final ImageView op = (ImageView) view.findViewById(R.id.act_fans_fans_item_button);
		op.getDrawable().setLevel(1);
		op.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(op.getDrawable().getLevel() == 1){
					op.getDrawable().setLevel(2);
				}else{
					op.getDrawable().setLevel(1);
				}
			}
		});
		
		return view;
	}

}
