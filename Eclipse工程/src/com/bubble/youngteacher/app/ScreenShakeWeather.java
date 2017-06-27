package com.bubble.youngteacher.app;

import org.json.JSONObject;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.listener.ShakeListener;
import com.bubble.youngteacher.listener.ShakeListener.OnShakeListener;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScreenShakeWeather extends BaseActivity {

	private EditText logView;
	private ShakeListener mShakeListener;
	private View naviBar;
	private OkHttpClient client;
	private boolean hasShake = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_gesture_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		logView = (EditText) findViewById(R.id.logview);
		naviBar = findViewById(R.id.navi_bar);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenBroadCast.class,
				false, ScreenContents.class);
		client = new OkHttpClient();
		mShakeListener = new ShakeListener(ScreenShakeWeather.this);
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(double speed) {
				// TODO Auto-generated method stub

				getWeather();
			}

		});

	}

	private void getWeather() {
		// TODO Auto-generated method stub
		if (hasShake) {
			return;
		}
		hasShake = true;
		LogUtils.e("onShake()", "摇到啦~" + "正在获取天气...");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Request request = new Request.Builder().url("http://www.weather.com.cn/data/sk/101070101.html")
							.build();
					Response response = client.newCall(request).execute();
					String res = response.body().string();
					LogUtils.e("reqWeather", "reqWeather:\n" + (TextUtils.isEmpty(res) ? "res = null" : res));

					if (res != null) {
						JSONObject result = new JSONObject(res);
						if (!result.isNull("weatherinfo")) {
							JSONObject weatherInfo = result.getJSONObject("weatherinfo");
							if (!weatherInfo.isNull("city")) {
								LogUtils.e("reqWeather", "城市：" + weatherInfo.getString("city"));
							}
							if (!weatherInfo.isNull("cityid")) {
								LogUtils.e("reqWeather", "城市ID：" + weatherInfo.getString("cityid"));
							}
							if (!weatherInfo.isNull("temp")) {
								LogUtils.e("reqWeather", "温度：" + weatherInfo.getString("temp"));
							}
							if (!weatherInfo.isNull("WD")) {
								LogUtils.e("reqWeather", "风向：" + weatherInfo.getString("WD"));
							}
							if (!weatherInfo.isNull("WS")) {
								LogUtils.e("reqWeather", "风速：" + weatherInfo.getString("WS"));
							}
							if (!weatherInfo.isNull("SD")) {
								LogUtils.e("reqWeather", "湿度：" + weatherInfo.getString("SD"));
							}

						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtils.e("HttpGET", "异常:" + e.getMessage());
				}
			}
		}).start();

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
		if (mShakeListener != null) {
			mShakeListener.stop();
			mShakeListener = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}
}
