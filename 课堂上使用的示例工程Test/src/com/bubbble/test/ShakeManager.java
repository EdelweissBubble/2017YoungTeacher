package com.bubbble.test;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ShakeManager implements SensorEventListener {

	// 速度阈值，当摇晃速度达到这值后产生作用
	private static final int SPEED_SHRESHOLD = 2000;
	// 两次检测的时间间隔
	private static final int UPTATE_INTERVAL_TIME = 70;
	// 传感器管理器
	private SensorManager sensorManager;
	// 传感器
	private Sensor sensor;
	// 重力感应监听器
	private OnShakeListener onShakeListener;
	// 上下文
	private Context mContext;
	// 手机上一个位置时重力感应坐标
	private float lastX;
	private float lastY;
	private float lastZ;
	// 上次检测时间
	private long lastUpdateTime;

	public ShakeManager(Context mContext) {
		super();
		this.mContext = mContext;
		start();
	}

	public void start() {
		sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager != null) {
			// 获得重力传感器
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		} // 注册
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		}
	}

	// 停止检测
	public void stop() {
		sensorManager.unregisterListener(this);
	}

	public OnShakeListener getOnShakeListener() {
		return onShakeListener;
	}

	public void setOnShakeListener(OnShakeListener onShakeListener) {
		this.onShakeListener = onShakeListener;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		long currentTime = System.currentTimeMillis();
		long timeInterval = currentTime - lastUpdateTime;
		if (timeInterval < UPTATE_INTERVAL_TIME) {
			return;
		}
		lastUpdateTime = currentTime;
		// 获得x,y,z坐标
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		// 获得x,y,z的变化值
		float deltaX = x - lastX;
		float deltaY = y - lastY;
		float deltaZ = z - lastZ;

		// 将现在的坐标变成last坐标
		lastX = x;
		lastY = y;
		lastZ = z;

		double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / timeInterval * 10000;

		// 达到速度阀值，发出提示
		if (speed >= SPEED_SHRESHOLD) {
			Log.e("ShakeListener", "达到速度阀值，发出提示");
			if (onShakeListener != null) {
				onShakeListener.onShake(speed);
			}
		}
	}

	// 摇晃监听接口
	public interface OnShakeListener {
		public void onShake(double speed);
	}
}
