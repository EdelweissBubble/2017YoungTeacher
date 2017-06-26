package com.bubble.youngteacher.service;

import com.bubble.youngteacher.utils.LogUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CountService extends Service {

	/** 创建参数 */
	boolean threadDisable;
	int count;

	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.e("CountService", "CountService onCreate");
		/** 创建一个线程，每秒计数器加一，并在控制台进行Log输出 */
		new Thread(new Runnable() {
			public void run() {
				while (!threadDisable) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					count++;
					LogUtils.e("CountService", "CountService，当前计数：" + count);
				}
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.threadDisable = true;
		LogUtils.e("CountService", "CountService onDestroy");
	}

	public int getConunt() {
		return count;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onLowMemory");
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onTrimMemory");
		super.onTrimMemory(level);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onRebind");
		super.onRebind(intent);
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		// TODO Auto-generated method stub
		LogUtils.e("CountService", "CountService onTaskRemoved");
		super.onTaskRemoved(rootIntent);
	}

	public class ServiceBinder extends Binder {
		public CountService getService() {
			LogUtils.e("CountService", "CountService ServiceBinder Binder getService（）");
			return CountService.this;
		}
	}
}