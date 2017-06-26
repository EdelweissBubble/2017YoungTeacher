package com.bubble.youngteacher.app;

import com.bubble.youngteacher.MyApp;
import com.bubble.youngteacher.R;
import com.bubble.youngteacher.adapter.ContentListAdapter;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ScreenContents extends Activity {

	private ListView contentList;
	private TextView contentTitle;
	private TextView contentDesc;

	private Button enterButton;

	private View naviBar;

	private int listLastClickPos = -1;

	private ContentListAdapter cla;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_contents_layout);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, MainActivity.class, false, ScreenContents.class,
				false, MainActivity.class);
		contentList = (ListView) findViewById(R.id.content_list);
		contentTitle = (TextView) findViewById(R.id.desc_tv);
		contentDesc = (TextView) findViewById(R.id.content_desc);
		enterButton = (Button) findViewById(R.id.enter);
		cla = new ContentListAdapter(this, MyApp.getInstance().getFuncList());
		contentList.setAdapter(cla);
		cla.notifyDataSetChanged();
		contentList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				listLastClickPos = position;
				initShow();
			}
		});
		enterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItemButtonClick();
			}

		});
		initShow();
	}

	private void initShow() {
		if (listLastClickPos == -1) {
			enterButton.setVisibility(View.GONE);
			enterButton.setClickable(false);
			contentTitle.setText("关于");
			contentDesc.setText("移动互联网Android应用研发\n\n中国科学院沈阳计算技术研究所\n\n" + "主讲人：李凯");
			contentDesc.setGravity(Gravity.CENTER);
		} else if (MyApp.getInstance().getFuncList() != null
				&& MyApp.getInstance().getFuncList().size() > listLastClickPos) {
			contentDesc.setGravity(Gravity.CENTER_VERTICAL);
			enterButton.setClickable(true);
			enterButton.setVisibility(View.VISIBLE);
			contentTitle.setText(MyApp.getInstance().getFuncList().get(listLastClickPos).getFuncName());
			contentDesc.setText(MyApp.getInstance().getFuncList().get(listLastClickPos).getFuncDesc());
		}
	}

	private void onItemButtonClick() {
		// TODO Auto-generated method stub
		initShow();
		if (MyApp.getInstance().getFuncList() != null && MyApp.getInstance().getFuncList().size() > listLastClickPos) {
			MyApp.getInstance().getFuncList().get(listLastClickPos).startFunction(ScreenContents.this);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}

}
