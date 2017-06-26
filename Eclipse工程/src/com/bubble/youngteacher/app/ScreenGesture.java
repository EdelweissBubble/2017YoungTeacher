package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class ScreenGesture extends BaseActivity {

	private View naviBar;
	private EditText logView;

	// 用GestureDetectorCompat替换GestureDetector,GestureDetectorCompat兼容的版本较广
	private GestureDetectorCompat mDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_gesture_main);
		init();
		initDetector();
	}

	private void initDetector() {
		// TODO Auto-generated method stub
		mDetector = new GestureDetectorCompat(ScreenGesture.this, new MytGestureListener());
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenGesture.class,
				false, ScreenContents.class);

	}

	// 让GestureDetectorCompat来接替处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.mDetector.onTouchEvent(event);
		// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
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

	/**
	 * 手势监听器
	 */
	private class MytGestureListener extends SimpleOnGestureListener {

		// Touch down时触发
		@Override
		public boolean onDown(MotionEvent e) {
			LogUtils.e("onDown", "onDown");
			return super.onDown(e);
		}

		// 在Touch down之后一定时间（115ms）触发
		@Override
		public void onShowPress(MotionEvent e) {
			LogUtils.e("onShowPress", "onShowPress");
		}

		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			LogUtils.e("onSingleTapUp", "onSingleTapUp");
			return super.onSingleTapUp(e);
		}

		// 滑动时触发
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			LogUtils.e("onScroll", "onScroll");
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		// 抛掷
		// 滑动一段距离，up时触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			LogUtils.e("onFling", "onFling");
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		// 长按后触发(Touch down之后一定时间（500ms）)
		@Override
		public void onLongPress(MotionEvent e) {
			LogUtils.e("onLongPress", "onLongPress");
		}

		// 双击
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			LogUtils.e("onLongPress", "onDoubleTap");
			return super.onDoubleTap(e);
		}

	}
}