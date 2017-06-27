package com.bubble.youngteacher.app;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.CommonUtils;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenGetContacts extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;

	private Button getContactsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_getcontacts_main);
		init();
	}

	private void function1() {
		// 获取联系人数据
		ContentResolver cr = getContentResolver();
		// 获取所有电话信息（而不是联系人信息），这样方便展示
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // 姓名
				ContactsContract.CommonDataKinds.Phone.NUMBER,// 电话号码
		};
		Cursor cursor = cr.query(uri, projection, null, null, null);
		if (cursor == null) {
			LogUtils.e("获取联系人", "联系人获取失败！");
			return;
		}

		while (cursor.moveToNext()) {
			String name = cursor.getString(0);
			String number = cursor.getString(1);
			LogUtils.e("获取到的数据：", "姓名：" + name + "，电话：" + CommonUtils.handlePhoneNum(number));
		}
		cursor.close();
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenVibrator.class,
				false, ScreenContents.class);
		getContactsButton = (Button) findViewById(R.id.func1);
		getContactsButton.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.func1:
			function1();
			break;

		default:
			break;
		}
	}

}
