package com.example.mybleproject;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ShopAdapter extends BaseExpandableListAdapter{
private Context ctx;
private HashMap<String, List<String>> shop_Category;
private List<String> shop_list;

public ShopAdapter(Context ctx, HashMap<String, List<String>> shop_Category,List<String> shop_list){
	this.ctx=ctx;
	this.shop_Category=shop_Category;
	this.shop_list=shop_list;
	
}
	@Override
	public Object getChild(int parent, int child) {
		// TODO Auto-generated method stub
		return shop_Category.get(shop_list.get(parent)).get(child);
	}

	@Override
	public long getChildId(int parent, int child) {
		// TODO Auto-generated method stub
		return child;
	}

	@Override
	public View getChildView(int parent, int child, boolean lastchild, View convertview,
			ViewGroup parentview) {
		// TODO Auto-generated method stub
		String child_title = (String) getChild(parent,child);
		if(convertview==null)
		{
			LayoutInflater inflator=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertview=inflator.inflate(R.layout.child_layout,parentview,false);
		}
		TextView child_textview=(TextView) convertview.findViewById(R.id.childView1);
		child_textview.setText(child_title);
		return convertview;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return shop_Category.get(shop_list.get(arg0)).size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return shop_list.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return shop_list.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int parent, boolean isExpended, View convertview, ViewGroup parentview) {
		// TODO Auto-generated method stub
		String group_title = (String)getGroup(parent);
		if(convertview ==null)
		{
			LayoutInflater inflator=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertview=inflator.inflate(R.layout.parant_layout,parentview,false);
		}
		TextView parent_textview=(TextView) convertview.findViewById(R.id.parentView1);
		parent_textview.setTypeface(null, Typeface.BOLD);
		parent_textview.setText(group_title);
		return convertview;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
