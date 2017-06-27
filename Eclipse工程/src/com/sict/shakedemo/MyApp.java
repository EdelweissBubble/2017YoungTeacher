package com.sict.shakedemo;

import java.util.ArrayList;
import java.util.List;

import com.sict.shakedemo.app.ScreenLBS;
import com.sict.shakedemo.app.ScreenMain;
import com.sict.shakedemo.app.ScreenShake;
import com.sict.shakedemo.model.FuncScreenModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApp extends Application {

	public final static String LOG_RECEIVER_ACTION = "com.sict.webRTC.demo.log";
	public final static String LOG_RECEIVER_WITH_TOAST_ACTION = "com.sict.webRTC.demo.logToast";

	public final static int MAX_LOG_LINES_ON_UI = 5000;

	private static MyApp sInstance;

	private static List<FuncScreenModel> funcList = null;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		sInstance = this;
		Log.e("MyApp", "MyApp init success!");
		initFuncList();
	}

	private void initFuncList() {
		// TODO Auto-generated method stub
		funcList = new ArrayList<FuncScreenModel>();
		funcList.add(new FuncScreenModel("地理位置信息", ScreenLBS.class));
		funcList.add(new FuncScreenModel("摇一摇", ScreenShake.class));
	}

	public MyApp() {

	}

	public static MyApp getInstance() {
		return sInstance;
	}

	public static Context getContext() {
		return getInstance();
	}

	public List<FuncScreenModel> getFuncList() {
		if (funcList == null) {
			initFuncList();
		}
		return funcList;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}
}
