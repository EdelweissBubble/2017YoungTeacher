package com.bubble.youngteacher.app;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScreenNetWork extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;

	private Button httpGetButton;
	private Button httpPostButton;
	private Button getWeather;
	private Button clearLog;
	private OkHttpClient client;

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_network_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		httpGetButton = (Button) findViewById(R.id.http_get);
		httpPostButton = (Button) findViewById(R.id.http_post);
		getWeather = (Button) findViewById(R.id.get_weather);
		clearLog = (Button) findViewById(R.id.clear);
		httpGetButton.setOnClickListener(this);
		httpPostButton.setOnClickListener(this);
		getWeather.setOnClickListener(this);
		clearLog.setOnClickListener(this);
		client = new OkHttpClient();
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenNetWork.class,
				false, ScreenContents.class);
	}

	private void httpGet() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Request request = new Request.Builder().url("https://raw.github.com/square/okhttp/master/README.md")
							.build();
					Response response = client.newCall(request).execute();
					String res = response.body().string();
					LogUtils.e("HttpGET", "HttpGET:\n" + (TextUtils.isEmpty(res) ? "res = null" : res));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtils.e("HttpGET", "异常:" + e.getMessage());
				}
			}
		}).start();

	}

	private void httpPost() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String json = new String("{'winCondition':'HIGH_SCORE'," + "'name':'Bowling'," + "'round':4,"
							+ "'lastSaved':1367702411696," + "'dateStarted':1367702378785," + "'players':["
							+ "{'name':'" + "Jesse" + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
							+ "{'name':'" + "Jake" + "','history':[6,10,5,10,10],'color':-48060,'total':41}" + "]}");
					RequestBody body = RequestBody.create(JSON, json);
					Request request = new Request.Builder().url("http://www.roundsapp.com/post").post(body).build();

					Response response = client.newCall(request).execute();
					String res = response.body().string();
					LogUtils.e("HttpPOST", "HttpPOST:\n" + (TextUtils.isEmpty(res) ? "res = null" : res));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtils.e("HttpPOST", "异常:" + e.getMessage());
				}
			}
		}).start();

	}

	private void reqWeather() {
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.http_get:
			httpGet();
			break;
		case R.id.http_post:
			httpPost();
			break;
		case R.id.get_weather:
			reqWeather();
			break;
		case R.id.clear:
			logView.setText("");
			break;
		default:
			break;
		}
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
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}

}