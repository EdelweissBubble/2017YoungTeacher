package com.bubble.youngteacher.app;

import com.bubble.youngteacher.MyApp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends Activity {

	private ProgressDialog myPD = null;

	private EditText logView;

	private BroadcastReceiver receiver;

	public void initBase(EditText logView) {
		this.logView = logView;
		regBroadCastReceiver();
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

	/**
	 * 记录日志到程序界面并展示Toast
	 * 
	 * @param msg
	 */
	public void recordLogAndShowToast(String msg) {
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
				Toast.makeText(getApplicationContext(), log, Toast.LENGTH_SHORT).show();
				if (logView.length() >= MyApp.MAX_LOG_LINES_ON_UI) {
					logView.setText("");
				}
				logView.append(log + "\n");
				logView.setSelection(logView.length());
			}
		});
	}

	public void showDialog(String text) {
		myPD = new ProgressDialog(BaseActivity.this);
		myPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		myPD.setCanceledOnTouchOutside(false);
		myPD.setCancelable(false);
		myPD.setMessage(text + "");
		myPD.show();

	}

	public void dismissDialog() {
		if (myPD != null) {
			myPD.dismiss();
			myPD = null;
		}
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
		super.onBackPressed();
		System.exit(0);
		finish();
	}
}
