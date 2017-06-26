package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.service.CountService;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenCommonService extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;

	private Button startButton;
	private Button stopButton;
	private Button startBindButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_common_service_main);
		init();
	}

	private void startService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(ScreenCommonService.this, CountService.class);
		startService(intent);
	}

	private void startBindSerVice() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(ScreenCommonService.this, ScreenUseBrider.class);
		startActivity(intent);
	}

	private void stopService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(ScreenCommonService.this, CountService.class);
		stopService(intent);
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false,
				ScreenCommonService.class, false, ScreenContents.class);
		startBindButton = (Button) findViewById(R.id.start_bind_service);
		startButton = (Button) findViewById(R.id.start);
		stopButton = (Button) findViewById(R.id.stop);
		startBindButton.setOnClickListener(this);
		startButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start:
			startService();
			break;
		case R.id.start_bind_service:
			startBindSerVice();
			break;
		case R.id.stop:
			stopService();
			break;

		default:
			break;
		}
	}

}