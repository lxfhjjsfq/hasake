package com.tingshu.hasake.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tingshu.hasake.R;
import com.tingshu.hasake.bean.MusicBean;
import com.tingshu.hasake.bean.MusicBean1;
import com.tingshu.hasake.bean.TypeBean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {

	private Context context;
	private List<MusicBean1> list = new ArrayList<MusicBean1>();

	public MusicAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
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
		Holder holder = null;
		if (arg1 == null) {
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.item_music, null);
			holder = new Holder();
			holder.tv_name = (TextView) arg1.findViewById(R.id.item_music_name);
			holder.tv_nickname = (TextView) arg1.findViewById(R.id.item_music_nickname);
			holder.tv_addtime = (TextView) arg1.findViewById(R.id.item_music_addtime);
			holder.tv_praise_count = (TextView) arg1.findViewById(R.id.item_music_praise_count);
			holder.tv_repair = (TextView) arg1.findViewById(R.id.item_music_repair);
			holder.tv_ispraise = (TextView) arg1.findViewById(R.id.item_music_ispraise);
			arg1.setTag(holder);

		} else {
			holder = (Holder) arg1.getTag();
		}
		MusicBean1 bean = list.get(arg0);
		
		holder.tv_name.setText(bean.getName());
		holder.tv_nickname.setText(bean.getNickname());
		holder.tv_addtime.setText(bean.getAddTime());
		holder.tv_repair.setText(bean.getRepair() + "");
		holder.tv_praise_count.setText(bean.getPraise() + "");
		if(bean.getIsPraise() == 1){
			//已赞
			Drawable drawable= context.getResources().getDrawable(R.drawable.yi_zan); 
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());  
			holder.tv_ispraise.setCompoundDrawables(drawable, null, null, null);
			holder.tv_ispraise.setText("已赞");
			holder.tv_ispraise.setTextColor(0xfff94325);
			
		}else{
			Drawable drawable= context.getResources().getDrawable(R.drawable.zan); 
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());  
			holder.tv_ispraise.setCompoundDrawables(drawable, null, null, null);
			holder.tv_ispraise.setText("赞");
			holder.tv_ispraise.setTextColor(0xff000000);
		}
		
		return arg1;
	}

	class Holder {
		TextView tv_name;
		TextView tv_nickname;
		TextView tv_addtime;
		TextView tv_praise_count;
		TextView tv_repair;
		TextView tv_ispraise;
		
	}

	public void addMore(List<MusicBean1> muList) {
		if (list != null) {
			list.addAll(muList);
			notifyDataSetChanged();
		}
	}

}
