package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ScreenMultiTouch extends BaseActivity {

	private View naviBar;
	private EditText logView;
	private RelativeLayout funcRL;

	private double lastDistance;
	private double currentDistance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_gesture_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		funcRL = (RelativeLayout) findViewById(R.id.content_rl);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenGesture.class,
				false, ScreenContents.class);
		funcRL.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					if (event.getPointerCount() == 2) {
						currentDistance = getDistance(event.getX(0) - event.getX(1), event.getY(0) - event.getY(1));
						if (currentDistance - lastDistance >= 5) {
							// 放大
							LogUtils.e("多点触控", "两个点间距增大：" + System.currentTimeMillis());
						} else if (lastDistance - currentDistance >= 5) {
							// 缩小
							LogUtils.e("多点触控", "两个点间距缩小：" + System.currentTimeMillis());
						}
						lastDistance = currentDistance;
					}
					break;
				case MotionEvent.ACTION_UP:
					break;
				default:
					break;
				}
				return true;
			}
		});
	}

	/**
	 * 计算两点间距离
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private double getDistance(double x, double y) {
		return Math.sqrt(x * x + y * y);
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