package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenVibrator extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;

	private Button func1;
	private Button func2;

	private Vibrator vibrator = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_vibrator_main);
		init();
	}

	private void function1() {

		if (vibrator == null) {
			LogUtils.e("ScreenVibrator", "开始振动");
			vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			// vibrator.vibrate(new long[] { 0,1000 }, 0);
			vibrator.vibrate(new long[] { 50, 200, 50, 200, 50, 200, 50, 200 }, 0);
		} else {
			LogUtils.e("ScreenVibrator", "停止振动");
			vibrator.cancel();
			vibrator = null;
		}
	}

	private void function2() {

		if (vibrator == null) {
			LogUtils.e("ScreenVibrator", "开始振动");
			vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(new long[] { 100, 1000, 200, 2000, 1000, 300, 3000, 1000 }, 0);// 间隔，振动时长。间隔，振动时长
		} else {
			LogUtils.e("ScreenVibrator", "停止振动");
			vibrator.cancel();
			vibrator = null;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenVibrator.class,
				false, ScreenContents.class);
		func1 = (Button) findViewById(R.id.func1);
		func2 = (Button) findViewById(R.id.func2);
		func1.setOnClickListener(this);
		func2.setOnClickListener(this);
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
		if (vibrator != null) {
			vibrator.cancel();
			vibrator = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.func1:
			function1();
			break;
		case R.id.func2:
			function2();
			break;

		default:
			break;
		}
	}

}