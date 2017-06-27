package com.sict.shakedemo.utils;


import com.sict.shakedemo.MyApp;

import android.content.Intent;
import android.util.Log;

public class LogUtils {

	public static void v(String tag, String msg) {
		Log.v(tag, msg);
		Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
		intent.putExtra("logRec", msg);
		MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
	}

	public static void i(String tag, String msg) {
		Log.i(tag, msg);
		Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
		intent.putExtra("logRec", msg);
		MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
	}

	public static void d(String tag, String msg) {
		Log.d(tag, msg);
		Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
		intent.putExtra("logRec", msg);
		MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
	}

	public static void w(String tag, String msg) {
		Log.w(tag, msg);
		Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
		intent.putExtra("logRec", msg);
		MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
	}

	public static void e(String tag, String msg) {
		Log.e(tag, msg);
		Intent intent = new Intent(MyApp.LOG_RECEIVER_ACTION);
		intent.putExtra("logRec", msg);
		MyApp.getInstance().getApplicationContext().sendBroadcast(intent);
	}

	public static void v(String tag, String msg, boolean isShowOnUI) {
		if (isShowOnUI) {
			v(tag, msg);
		} else {
			Log.v(tag, msg);
		}
	}

	public static void i(String tag, String msg, boolean isShowOnUI) {
		if (isShowOnUI) {
			i(tag, msg);
		} else {
			Log.i(tag, msg);
		}
	}

	public static void d(String tag, String msg, boolean isShowOnUI) {
		if (isShowOnUI) {
			d(tag, msg);
		} else {
			Log.d(tag, msg);
		}
	}

	public static void w(String tag, String msg, boolean isShowOnUI) {
		if (isShowOnUI) {
			w(tag, msg);
		} else {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg, boolean isShowOnUI) {
		if (isShowOnUI) {
			e(tag, msg);
		} else {
			Log.e(tag, msg);
		}
	}

}
