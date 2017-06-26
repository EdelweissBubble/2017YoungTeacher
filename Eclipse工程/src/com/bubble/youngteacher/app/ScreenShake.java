package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.listener.ShakeListener;
import com.bubble.youngteacher.listener.ShakeListener.OnShakeListener;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class ScreenShake extends BaseActivity {

	private EditText logView;
	private ShakeListener mShakeListener;
	private View naviBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_gesture_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		logView = (EditText) findViewById(R.id.logview);
		naviBar = findViewById(R.id.navi_bar);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenBroadCast.class,
				false, ScreenContents.class);
		mShakeListener = new ShakeListener(ScreenShake.this);
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(double speed) {
				// TODO Auto-generated method stub
				LogUtils.e("onShake()", "摇到啦~" + "     速度：" + speed);
			}
		});
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
		if (mShakeListener != null) {
			mShakeListener.stop();
			mShakeListener = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}
}
