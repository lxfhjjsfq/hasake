package com.tingshu.hasake;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.renderscript.Type;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.tingshu.hasake.adapter.MusicTypeGvAdapter;
import com.tingshu.hasake.bean.TypeBean;
import com.tingshu.hasake.ui.activity.PlayActivity;

public class AllMusicActivity extends BaseActivity {

	private GridView gv_all_music;
	private int typeId;
	private int[] ertong = { R.drawable.ertongdaquan, R.drawable.ertongjiaoyu,
			R.drawable.yuyangushi };
	private String[] ertongName = { "儿童大全", "儿童教育", "寓言故事" };

	private String[] jiankang = { "健康课堂", "女性养生", "催眠音乐" };
	private int[] jiankangIcon = { R.drawable.jiankangketang,
			R.drawable.nvxingyangsheng, R.drawable.cuimianyiyue };

	private String[] lishi = { "艾特斯", "历史", "习俗文化" };
	private int[] lishiicon = { R.drawable.aites, R.drawable.lishi,
			R.drawable.xisuwenhua };

	private String[] wenxuemingzhu = { "阿拜", "诗歌", "小说", "长篇小说" };
	private int wenxueIcon[] = { R.drawable.abai, R.drawable.shige,
			R.drawable.xiaoshuo, R.drawable.changpianxiaoshuo };

	private String[] yinyue = { "传统民歌", "流行音乐", "民谣", "民族器乐" };
	private int[] yinyueIcon = { R.drawable.chuangtongminge,
			R.drawable.liuxingyinyue, R.drawable.minyao, R.drawable.minyaoxueqi };

	private String[] yule = { "名人采访", "相声笑话", "综艺娱乐" };
	private int[] yuleIcon = { R.drawable.mingrencaifang,
			R.drawable.xiangshengxiaohua, R.drawable.zongyieyule };

	@Override
	protected void setcontentView() {
		setContentView(R.layout.activity_all_music);

	}

	@Override
	protected void initView() {
		gv_all_music = (GridView) findViewById(R.id.gv_all_music);
		TextView title = (TextView) findViewById(R.id.act_tiltle_text);
		title.setText("全部");
	}

	@Override
	protected void initData() {
		typeId = getIntent().getIntExtra("typeId", 0);
		gv_all_music.setAdapter(new MusicTypeGvAdapter(context,
				initTypeIcon(typeId)));

	}

	@Override
	protected void initListener() {
		gv_all_music.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, PlayActivity.class);
				context.startActivity(intent);
			}
		});

	}

	private List<TypeBean> initTypeIcon(int typeId) {
		switch (typeId) {
		case 0:
			setTitleContent("文学名著") ;
			return getTypeist(wenxuemingzhu, wenxueIcon);
			
		case 1:
			setTitleContent("娱乐") ;
			return getTypeist(yule, yuleIcon);
		case 2:
			setTitleContent("历史人文") ;
			return getTypeist(lishi, lishiicon);
		case 3:

			setTitleContent("音乐") ;
			return getTypeist(yinyue, yinyueIcon);
		case 4:
			setTitleContent("儿童") ;
			return getTypeist(ertongName, ertong);
		case 5:
			setTitleContent("健康养生") ;
			return getTypeist(jiankang, jiankangIcon);

		default:
			List<TypeBean> list=new ArrayList<TypeBean>();
			for(int i=0;i<10;i++){
				list.add(new TypeBean(0, R.drawable.zhoujielu, "周杰伦"));
				
			}
			return list;
		}

	}

	private List<TypeBean> getTypeist(String[] names, int[] iconIds) {
		List<TypeBean> list = new ArrayList<TypeBean>();
		for (int i = 0; i < names.length; i++) {
			TypeBean bean = new TypeBean(i, iconIds[i], names[i]);
			list.add(bean);
		}
		return list;
	}

}
