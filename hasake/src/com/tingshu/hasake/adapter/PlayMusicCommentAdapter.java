package com.tingshu.hasake.adapter;

import com.tingshu.hasake.R;
import com.tingshu.hasake.utils.ImageUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PlayMusicCommentAdapter extends BaseAdapter {
	private Context context;
	
	public PlayMusicCommentAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView view = new ImageView(context);
		Drawable drawable = context.getResources().getDrawable(R.drawable.test_play_background);
		view.setBackgroundDrawable(ImageUtil.getRoundedCornerDrawable(drawable));
		return view;
	}

}
