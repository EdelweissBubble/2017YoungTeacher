package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.NavigationBarUtils;
import com.bubble.youngteacher.view.Image3DSwitchView;
import com.bubble.youngteacher.view.Image3DSwitchView.OnImageSwitchListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ScreenImageSwitch3D extends Activity {

	private View naviBar;
	private Image3DSwitchView imageSwitchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_image_switcher_3d_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenMusicPlayer.class,
				false, ScreenContents.class);
		imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
		imageSwitchView.setOnImageSwitchListener(new OnImageSwitchListener() {
			@Override
			public void onImageSwitch(int currentImage) {
				// Log.d("TAG", "current image is " + currentImage);
			}
		});
		imageSwitchView.setCurrentImage(1);
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
		imageSwitchView.clear();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}
}