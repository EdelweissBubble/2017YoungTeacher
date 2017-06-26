package com.bubble.youngteacher.app;

import com.bubble.youngteacher.MyApp;
import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenBroadCast extends Activity {

	private View naviBar;
	private Button sendBroadCast;
	private EditText logView;

	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_broadcast_main);
		init();
		regBroadCastReceiver();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		sendBroadCast = (Button) findViewById(R.id.send_broadcast);
		logView = (EditText) findViewById(R.id.logview);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenBroadCast.class,
				false, ScreenContents.class);
		sendBroadCast.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
				intent.putExtra("logRec", "发送了一条广播消息：" + System.currentTimeMillis());
				MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
			}
		});
	}

	public void regBroadCastReceiver() {
		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				final String action = intent.getAction();
				if (MyApp.LOG_RECEIVER_ACTION.equals(action)) {
					String log = intent.getStringExtra("logRec");
					recordLog(log);
				} else if (MyApp.LOG_RECEIVER_WITH_TOAST_ACTION.equals(action)) {
					String log = intent.getStringExtra("logRec");
					recordLog(log);
				}
			}
		};
		IntentFilter filter = new IntentFilter();
		filter.addAction(MyApp.LOG_RECEIVER_ACTION);
		filter.addAction(MyApp.LOG_RECEIVER_WITH_TOAST_ACTION);
		registerReceiver(receiver, filter);
	}

	/**
	 * 记录日志到程序界面
	 * 
	 * @param msg
	 */
	public void recordLog(String msg) {
		if (logView == null) {
			Log.e("Error", "日志View未指定！");
			return;
		}

		if (TextUtils.isEmpty(msg)) {
			msg = "^null^";
		}
		final String log = msg;
		new Handler().post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (logView.length() >= MyApp.MAX_LOG_LINES_ON_UI) {
					logView.setText("");
				}
				logView.append(log + "\n");
				logView.setSelection(logView.length());
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
		if (receiver != null) {
			unregisterReceiver(receiver);
			receiver = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}
}