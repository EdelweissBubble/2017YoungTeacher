package com.sict.shakedemo.adapter;

import java.util.List;

import com.sict.shakedemo.R;
import com.sict.shakedemo.app.ScreenMain;
import com.sict.shakedemo.model.FuncScreenModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FuncListAdapter extends BaseAdapter {

	private List<FuncScreenModel> funcList = null;
	private ScreenMain context;
	
	private LayoutInflater inflater = null;

	public FuncListAdapter(ScreenMain context, List<FuncScreenModel> funcList) {
		super();
		this.funcList = funcList;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (funcList == null) {
			return 0;
		} else {
			return funcList.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (funcList == null) {
			return null;
		} else {
			return funcList.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		FuncHolder funcHolder = null;
		FuncScreenModel fsm = (FuncScreenModel) getItem(position);
		if (convertView == null) {
			funcHolder = new FuncHolder();
			convertView = inflater.inflate(R.layout.func_list_item, null);
			funcHolder.funcName = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(funcHolder);
			
		}else{
			funcHolder = (FuncHolder) convertView.getTag();
		}
		funcHolder.funcName.setText(fsm.getFuncName());
		
		
		return convertView;
	}
	
	class FuncHolder{
		TextView funcName;
	}

}
