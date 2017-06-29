package com.bubbble.test;


import org.json.JSONException;
import org.json.JSONObject;

import com.bubbble.test.ShakeManager.OnShakeListener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity implements OnShakeListener{

	private TextView logView;
	private ShakeManager mShakeListener;
	private OkHttpClient client;
	private boolean hasShake = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		logView = (TextView) findViewById(R.id.log_view);
		logView.setText("hello TextView");
		client = new OkHttpClient();
		mShakeListener = new ShakeManager(MainActivity.this);
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(double speed) {
				// TODO Auto-generated method stub
				logView.append("速度：" + speed + "\n\n");
				getWeather();
			}

		});
//		mShakeListener.setOnShakeListener(this);
	}

	private void getWeather() {
		// TODO Auto-generated method stub
		if (hasShake) {
			return;
		}
		hasShake = true;
		logView.append("正在获取天气信息....." + "\n");

		new AsyncTask<Object, Object, Object>() {

			@Override
			protected Object doInBackground(Object... params) {
				// TODO Auto-generated method stub
				try {
					Request request = new Request.Builder().url("http://www.weather.com.cn/data/sk/101070101.html")
							.build();
					Response response = client.newCall(request).execute();
					if (response.body() != null) {
						String result = response.body().string();
						Log.e("获取结果", result + "");
						return result;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void onPostExecute(Object result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if (result == null) {
					logView.append("获取失败，结果为 null！" + "\n");
				} else {
					String json = (String) result;
					if (TextUtils.isEmpty(json)) {
						logView.append("结果是空字符串！" + "\n");
					} else {
						logView.append("获取结果：" + result + "\n");
					}
					try {
						JSONObject info = new JSONObject(json);
						if (!info.isNull("weatherinfo")) {
							JSONObject weatherInfo = info.getJSONObject("weatherinfo");
							if (weatherInfo != null) {
								if (!weatherInfo.isNull("city")) {
									String city = weatherInfo.getString("city");
									logView.append("城市：" + city);
								}
							}
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onShake(double speed) {
		// TODO Auto-generated method stub
		
	}
}
