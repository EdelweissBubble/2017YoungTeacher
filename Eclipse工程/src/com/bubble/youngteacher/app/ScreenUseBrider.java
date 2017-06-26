package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.service.CountService;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenUseBrider extends BaseActivity {

	private View naviBar;
	private EditText logView;
	private Button close;

	private CountService countService;

	private ServiceConnection conn = new ServiceConnection() {
		/** 获取服务对象时的操作 */
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			countService = ((CountService.ServiceBinder) service).getService();
			LogUtils.e("ServiceConnection", "onServiceConnected");

		}

		/** 无法获取到服务对象时的操作 */
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			LogUtils.e("onServiceDisconnected", "onServiceDisconnected");
			countService = null;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_bind_service_main);
		init();
		bindService();
	}

	private void bindService() {
		Intent intent = new Intent(ScreenUseBrider.this, CountService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		close = (Button) findViewById(R.id.close_button);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenUseBrider.class,
				false, ScreenContents.class);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ScreenUseBrider.this.finish();
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
		this.unbindService(conn);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}

}