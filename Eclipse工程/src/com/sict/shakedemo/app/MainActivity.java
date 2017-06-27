package com.sict.shakedemo.app;

import com.sict.shakedemo.MyApp;
import com.sict.shakedemo.R;
import com.sict.shakedemo.R.id;
import com.sict.shakedemo.R.layout;
import com.sict.shakedemo.utils.LogUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private EditText userNameET;
	private EditText passWdET;
	private EditText logView;

	private Button confirmButton;
	private Button enterButton;

	private String userName = null;
	private String passWD = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
	}

	private void initListener() {
		// TODO Auto-generated method stub
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!checkData()) {
					return;
				}
				LogUtils.e("MainActivity", "用户名:" + userName);
				LogUtils.e("MainActivity", "密    码 :" + passWD);

			}

		});
		enterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, ScreenMain.class);
				startActivity(i);
				MainActivity.this.finish();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		userNameET = (EditText) findViewById(R.id.username_et);
		passWdET = (EditText) findViewById(R.id.passwd_et);
		confirmButton = (Button) findViewById(R.id.confirm_button);
		enterButton = (Button) findViewById(R.id.enter_button);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
	}

	private boolean checkData() {
		// TODO Auto-generated method stub
		String tempUserName = userNameET.getEditableText().toString();
		if (TextUtils.isEmpty(tempUserName)) {
			Toast.makeText(MyApp.getContext(), "用户名不可为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		String tempPassWD = passWdET.getEditableText().toString();
		if (TextUtils.isEmpty(tempPassWD)) {
			Toast.makeText(MyApp.getContext(), "用户名不可为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		userName = new String(tempUserName);
		passWD = new String(tempPassWD);
		return true;

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
		super.onBackPressed();
		System.exit(0);
		this.finish();
	}

}
