package com.example.pulltozoomlistview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.pulltozoomlistview.adapter.MessageListAdapter;
import com.example.pulltozoomlistview.util.DensityUtil;
import com.example.pulltozoomlistview.view.PullToZoomListView;
import com.example.pulltozoomlistview.view.PullToZoomListView.PullToZoomListViewListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements PullToZoomListViewListener{

	int i = 0;
	private  PullToZoomListView mListView;
	private MessageListAdapter mAdapter;
	private List<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initListView();
	}

	 

	 private void initListView() {
		getData();
		mListView=(PullToZoomListView) findViewById(R.id.listView01);
		mAdapter = new MessageListAdapter(listItem,R.layout.news_list_item_view,getLayoutInflater(),MainActivity.this);

		/*========================= 设置头部的图片====================================*/
		mListView.getHeaderView().setScaleType(ImageView.ScaleType.FIT_XY);
		mListView.getHeaderView().setImageResource(R.drawable.ic_launcher);
		
		/*========================= 设置头部的高度 ====================================*/
		mListView.setmHeaderHeight(DensityUtil.dip2px(MainActivity.this, 260));

		/*========================= 设置头部的的布局====================================*/
		 View mHeaderView=getLayoutInflater().inflate(R.layout.layout_story_userinfo, null);
		 mListView.getHeaderContainer().addView(mHeaderView);
		 mListView.setHeaderView();
		 mListView.setAdapter(mAdapter);
		 mListView.setPullToZoomListViewListener(this);

	}
	

	private List<HashMap<String, Object>> getData()	{
		;
		for(int i=0;i<20;i++)	{
			HashMap<String,Object> map=new HashMap<String, Object>();
			map.put("key", ""+i);
			listItem.add(map);
		}
		return listItem; 
	}
	@Override
	public void onLoadMore() {
		i++;
		if (i < 3) {

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					getData();
					mAdapter.notifyDataSetChanged();
					mListView.setLoadFinish(false);
				}
			}, 3000);
		} else if (i == 3) {
			mListView.setLoadFinish(true);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					mListView.setLoadFinish(false);
				}
			}, 3000);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					getData();
					mAdapter.notifyDataSetChanged();
					mListView.setLoadFinish(false);
				}
			}, 3000);
		}
	}

}
