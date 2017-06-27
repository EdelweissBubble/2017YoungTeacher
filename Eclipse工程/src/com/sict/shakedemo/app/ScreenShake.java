package com.sict.shakedemo.app;

import com.sict.shakedemo.R;
import com.sict.shakedemo.ShakeListener;
import com.sict.shakedemo.ShakeListener.OnShakeListener;
import com.sict.shakedemo.utils.LogUtils;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScreenShake extends BaseActivity {

	private EditText logView;

	private Button startButton;

	private ShakeListener mShakeListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_lbs);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		logView = (EditText) findViewById(R.id.logview);
		startButton = (Button) findViewById(R.id.confirm_button);
		initBase(logView);
		startButton.setText("开始");
		mShakeListener = new ShakeListener(ScreenShake.this);
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(double speed) {
				// TODO Auto-generated method stub
				LogUtils.e("onShake()", "摇到啦~" + "     速度：" + speed);
			}
		});
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}
}
