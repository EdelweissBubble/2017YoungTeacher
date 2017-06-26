package com.bubble.youngteacher.adapter;

import java.util.List;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.app.ScreenContents;
import com.bubble.youngteacher.model.FuncScreenModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContentListAdapter extends BaseAdapter {

	private List<FuncScreenModel> funcList = null;

	private LayoutInflater inflater = null;

	private ScreenContents context;

	public ContentListAdapter(ScreenContents context, List<FuncScreenModel> funcList) {
		super();
		this.funcList = funcList;
		this.context = context;
		this.inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (funcList != null) {
			return funcList.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (funcList != null && funcList.size() > position && position >= 0) {
			return funcList.get(position);
		} else {
			return null;
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
			funcHolder.blank = convertView.findViewById(R.id.blank_view);
			convertView.setTag(funcHolder);

		} else {
			funcHolder = (FuncHolder) convertView.getTag();
		}
		funcHolder.funcName.setText(fsm.getFuncName());
		if (position == 0) {
			funcHolder.blank.setVisibility(View.GONE);
		} else {
			if (((FuncScreenModel) getItem(position - 1)).getType() == fsm.getType()) {
				funcHolder.blank.setVisibility(View.GONE);
			} else {
				funcHolder.blank.setVisibility(View.VISIBLE);
			}
		}
		return convertView;

	}

	class FuncHolder {
		TextView funcName;
		View blank;
	}
}
