package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private View naviBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		NavigationBarUtils.initNavigationBar(this, naviBar, false, MainActivity.class, true, ScreenContents.class, true,
				MainActivity.class);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}

}
