package com.example.pulltozoomlistview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
		 mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
			 @Override
			 public void onScrollStateChanged(AbsListView view, int scrollState) {

			 }

			 @Override
			 public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				 Log.d("MainActivity", "第一个可见的item" + firstVisibleItem);
				 int mHeaderHight = mListView.getHeaderView().getHeight();
				 Log.d("MainActivity","得到头部的高度"+mHeaderHight);
				 int mNormalHight = mListView.getChildAt(1).getHeight();
				 Log.d("MainActivity","得到普通子类的高度"+mNormalHight);
				 int mTop = mListView.getChildAt(1).getTop();
				 Log.d("MainActivity","得到headview头部所在的位置"+mTop);
				 int mScrollY = -mTop + firstVisibleItem*mNormalHight;
				 Log.d("MainActivity","得到在滑动的距离是" +mScrollY);
			 }
		 });


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
		} else {
//			mListView.setLoadFinish(true);
//			new Handler().postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
					mListView.setLoadFinish(true);
			Toast.makeText(this,"没有跟多数据了",Toast.LENGTH_SHORT).show();
//				}
//			}, 3000);
		}
//		else {
//			new Handler().postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					getData();
//					mAdapter.notifyDataSetChanged();
//					mListView.setLoadFinish(false);
//				}
//			}, 3000);
//		}
	}

}
