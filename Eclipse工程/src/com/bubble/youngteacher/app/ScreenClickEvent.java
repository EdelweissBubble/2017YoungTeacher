package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenClickEvent extends BaseActivity
		implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

	private View naviBar;
	private EditText logView;

	private Button clickButton;
	private Button touchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_click_main);
		init();
		initListener();
	}

	private void initListener() {
		// TODO Auto-generated method stub
		clickButton.setOnClickListener(this);
		clickButton.setOnLongClickListener(this);
		logView.setOnClickListener(this);
		touchButton.setOnTouchListener(this);
		touchButton.setOnClickListener(this);
		touchButton.setOnLongClickListener(this);
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		clickButton = (Button) findViewById(R.id.click_button);
		touchButton = (Button) findViewById(R.id.touch_button);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenClickEvent.class,
				false, ScreenContents.class);
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		// LogUtils.e("点击事件", "您刚刚触发了一个touch事件。" + "ViewID:" + v.getId());
		LogUtils.e("点击事件", "touch事件:" + event.getRawX() + "，" + event.getRawY());
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		LogUtils.e("点击事件", "您刚刚触发了一个长按事件。" + "ViewID:" + v.getId());
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		LogUtils.e("点击事件", "您刚刚触发了一个点击事件。" + "ViewID:" + v.getId());

	}
}