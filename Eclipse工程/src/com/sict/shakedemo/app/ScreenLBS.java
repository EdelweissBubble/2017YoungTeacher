package com.sict.shakedemo.app;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.sict.shakedemo.R;
import com.sict.shakedemo.utils.LogUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScreenLBS extends BaseActivity implements AMapLocationListener {

	private EditText logView;

	private Button confirmButton;

	private AMapLocationClient locationClient = null;
	private AMapLocationClientOption locationOption = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_lbs);
		init();
		initLocation();
	}

	private void init() {
		// TODO Auto-generated method stub
		logView = (EditText) findViewById(R.id.logview);
		confirmButton = (Button) findViewById(R.id.confirm_button);
		initBase(logView);
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				locationClient.startLocation();
				Toast.makeText(ScreenLBS.this, "正在定位!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 初始化定位
	 */
	private void initLocation() {

		locationClient = new AMapLocationClient(this.getApplicationContext());
		locationOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式
		locationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置定位监听
		locationClient.setLocationListener(this);
		// 设置是否返回地址信息（默认返回地址信息）
		locationOption.setNeedAddress(true);
		// 设置是否只定位一次,默认为false
		locationOption.setOnceLocation(false);
		// 设置是否强制刷新WIFI，默认为强制刷新
		locationOption.setWifiActiveScan(true);
		// 设置是否允许模拟位置,默认为false，不允许模拟位置
		locationOption.setMockEnable(false);
		// 设置定位间隔,单位毫秒,默认为5000ms
		locationOption.setInterval(5000);
		// 给定位客户端对象设置定位参数
		locationClient.setLocationOption(locationOption);
		// 启动定位
		// locationClient.startLocation();

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
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			locationClient.onDestroy();
			locationClient = null;
			locationOption = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}

	@Override
	public void onLocationChanged(AMapLocation arg0) {
		// TODO Auto-generated method stub
		if (arg0.getErrorCode() == 0) {
			LogUtils.e("定位信息", "结果来源：" + arg0.getLocationType());
			LogUtils.e("定位信息", "纬度：" + arg0.getLatitude());
			LogUtils.e("定位信息", "经度：" + arg0.getLongitude());
			LogUtils.e("定位信息", "精确度：" + arg0.getAccuracy());
			LogUtils.e("定位信息", "省份：" + arg0.getProvince());
			LogUtils.e("定位信息", "城市：" + arg0.getCity());
			LogUtils.e("定位信息", "地区：" + arg0.getDistrict());
			LogUtils.e("定位信息", "街道：" + arg0.getRoad());
		} else {
			LogUtils.e("定位信息", arg0.getErrorInfo());
		}
	}
}
