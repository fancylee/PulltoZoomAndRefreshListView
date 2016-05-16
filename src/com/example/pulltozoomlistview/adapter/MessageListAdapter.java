package com.example.pulltozoomlistview.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pulltozoomlistview.R;

public class MessageListAdapter extends BaseAdapter {

	
	
	
	private List<HashMap<String, Object>> listItem;
	private int resource;
	private LayoutInflater inflater;
	private Context context;

	
//	public static final String STATUS_UNFINISH_NAME="(已派发)";
//	public static final String STATUS_FINISH_NAME="(不存在)";
//	
//	public static final String STATUS_SENDED_CODE="1";
//	public static final String STATUS_UNEXIST_CODE="-1";
	
	public MessageListAdapter(
			List<HashMap<String, Object>> listItem, int resource,LayoutInflater inflater,Context context) {
		this.listItem = listItem;
		this.resource = resource;
		this.inflater = inflater;
		this.context=context;
	
	}

	public int getCount() {
		return listItem.size();
		
	}

	public List<HashMap<String, Object>> getListItem() {
		return listItem;
	}

	@Override
	public Object getItem(int position) {
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater
					.inflate(resource, null);
		
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		
		
		HashMap<String, Object> map = listItem.get(position);
	
		
		
		
		
		
		return convertView;
	}

	static class ViewHolder {
		public TextView tv_content;
		public TextView tv_title;
		public TextView tv_time;
		public ImageView img_dot;
	}

	

	

	
	
}
