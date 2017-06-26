package com.bubble.youngteacher;

import java.util.ArrayList;
import java.util.List;

import com.bubble.youngteacher.app.ScreenAbout;
import com.bubble.youngteacher.app.ScreenBroadCast;
import com.bubble.youngteacher.app.ScreenClickEvent;
import com.bubble.youngteacher.app.ScreenCommonService;
import com.bubble.youngteacher.app.ScreenContenProvider;
import com.bubble.youngteacher.app.ScreenDBOperation;
import com.bubble.youngteacher.app.ScreenGesture;
import com.bubble.youngteacher.app.ScreenGetContacts;
import com.bubble.youngteacher.app.ScreenImageSwitch3D;
import com.bubble.youngteacher.app.ScreenLBS;
import com.bubble.youngteacher.app.ScreenMultiTouch;
import com.bubble.youngteacher.app.ScreenMusicPlayer;
import com.bubble.youngteacher.app.ScreenNetWork;
import com.bubble.youngteacher.app.ScreenShake;
import com.bubble.youngteacher.app.ScreenVibrator;
import com.bubble.youngteacher.app.ScreenViewPager;
import com.bubble.youngteacher.model.FuncScreenModel;
import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApp extends Application {

	public final static String LOG_RECEIVER_ACTION = "com.bubble.youngteacher.demo.log";
	public final static String LOG_RECEIVER_WITH_TOAST_ACTION = "com.bubble.youngteacher.demo.logToast";

	public final static int MAX_LOG_LINES_ON_UI = 5000;

	private static List<FuncScreenModel> funcList = null;

	private static MyApp sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		CrashReport.initCrashReport(getApplicationContext(), "d67d01d46d", true);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		sInstance = this;
		Log.e("MyApp", "MyApp init success!");
		initFuncList();
	}

	private void initFuncList() {
		funcList = new ArrayList<FuncScreenModel>();
		funcList.add(new FuncScreenModel(1, "ViewPager", "  简单介绍使用ViewPager实现图片左右滑动切换。", ScreenViewPager.class));
		funcList.add(new FuncScreenModel(1, "3D图片轮播控件", "简单介绍了，3D图片轮播控件的使用，自定义控件。", ScreenImageSwitch3D.class));

		funcList.add(new FuncScreenModel(2, "点击事件", "图形界面中的常用点击事件Demo，如：onclick、onTouch、onLongClick",
				ScreenClickEvent.class));
		funcList.add(new FuncScreenModel(2, "BroadCast", "点击按钮，发送广播，接收广播，以及将广播中携带的内容打印到日志上。", ScreenBroadCast.class));
		funcList.add(new FuncScreenModel(2, "手势监听", "使用GestureDetectorCompat对手势事件进行监听。", ScreenGesture.class));
		funcList.add(new FuncScreenModel(2, "多点触控", "手势监听的高级功能，监听多点触控事件", ScreenMultiTouch.class));

		funcList.add(new FuncScreenModel(7, "获取联系人", "使用ContentProvider获取联系人", ScreenGetContacts.class));
		funcList.add(new FuncScreenModel(7, "ContentProvider", "构建属于自己的ContentProvider，并完成数据库的增删改查操作。",
				ScreenContenProvider.class));

		funcList.add(new FuncScreenModel(3, "摇一摇", "使用加速仪，监听加速度，实现摇一摇功能", ScreenShake.class));
		funcList.add(new FuncScreenModel(3, "地理位置信息", "使用高德地图定位SDK，获取地理位置信息", ScreenLBS.class));
		funcList.add(new FuncScreenModel(3, "振动器", "创建一个可以带有自定义节奏的振动器，并实现振动功能", ScreenVibrator.class));

		funcList.add(new FuncScreenModel(4, "音乐播放器", "使用MediaPlayer播放一段音乐，实现简易的音乐播放器", ScreenMusicPlayer.class));

		funcList.add(new FuncScreenModel(5, "网络交互技术", "使用OkHttp框架，实现网络数据交互", ScreenNetWork.class));

		funcList.add(new FuncScreenModel(6, "数据库操作", "Android SQLlite数据库基本操作，增删改查", ScreenDBOperation.class));
		funcList.add(new FuncScreenModel(6, "后台服务", "创建一个Service，了解service基本启动方法及生命周期", ScreenCommonService.class));

	}

	public MyApp() {

	}

	public List<FuncScreenModel> getFuncList() {
		if (funcList == null) {
			initFuncList();
		}
		return funcList;
	}

	public static MyApp getInstance() {
		return sInstance;
	}

	public static Context getContext() {
		return getInstance();
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}
}
