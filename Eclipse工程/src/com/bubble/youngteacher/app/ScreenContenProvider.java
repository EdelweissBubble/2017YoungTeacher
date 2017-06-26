package com.bubble.youngteacher.app;

import java.util.Random;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.database.DBUtils;
import com.bubble.youngteacher.model.Employees.Employee;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenContenProvider extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;

	private Button insertDataButton;
	private Button delDataButton;
	private Button updateDataButton;
	private Button showDataButton;

	private DBUtils mDBUtil;

	private int max = 99;
	private int min = 10;
	private Random random;

	public Uri uri = Employee.CONTENT_URI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_content_provider_main);
		init();
	}

	private void insert() {

		int num = random.nextInt(max) % (max - min + 1) + min;

		String name = new String("小红" + num);
		String sex = (num % 2 == 0 ? "male" : "female");

		ContentValues values = new ContentValues();
		values.put(Employee.NAME, name);
		values.put(Employee.GENDER, sex);
		values.put(Employee.AGE, num);
		Uri res = getContentResolver().insert(uri, values);
		if (res != null) {
			LogUtils.e("插入数据", "数据添加成功：" + name + "，" + sex + "，" + num);
			LogUtils.e("插入数据", "数据添加成功：" + res.getEncodedPath());
		} else {
			LogUtils.e("插入数据", "数据添加失败：" + name + "，" + sex);
		}
	}

	private void delDate() {

		String[] deleteValue = { "male" };
		String where = "gender";
		int res = getContentResolver().delete(uri, where + "=?", deleteValue);
		LogUtils.e("删除数据", "数据删除成功，res = " + res);

	}

	private void update() {

		ContentValues values = new ContentValues();
		values.put(Employee.NAME, "小明");
		values.put(Employee.GENDER, "female");
		values.put(Employee.AGE, 18);

		String where = "gender";
		String[] selectValue = { "male" };
		int res = getContentResolver().update(uri, values, where + "=?", selectValue);
		LogUtils.e("更新数据", "数据更新成功，res = " + res);
	}

	private void search() {

		String[] PROJECTION = new String[] { Employee._ID, // 0
				Employee.NAME, // 1
				Employee.GENDER, // 2
				Employee.AGE // 3
		};
		Cursor cursor = getContentResolver().query(Employee.CONTENT_URI, PROJECTION, null, null,
				Employee.DEFAULT_SORT_ORDER);
		if (cursor != null && cursor.moveToFirst()) {
			// 遍历游标
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				String name = cursor.getString(1);
				String gender = cursor.getString(2);
				int age = cursor.getInt(3);
				LogUtils.e("查询数据", (i + 1) + "    " + name + "    " + gender + "    " + age);
			}
		} else {
			LogUtils.e("查询数据", "返回结果为null");
		}
		if (cursor != null) {
			cursor.close();
		}

	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false,
				ScreenContenProvider.class, false, ScreenContents.class);
		insertDataButton = (Button) findViewById(R.id.insert_db);
		delDataButton = (Button) findViewById(R.id.del_db);
		updateDataButton = (Button) findViewById(R.id.update_db);
		showDataButton = (Button) findViewById(R.id.search_db);
		insertDataButton.setOnClickListener(this);
		delDataButton.setOnClickListener(this);
		updateDataButton.setOnClickListener(this);
		showDataButton.setOnClickListener(this);
		random = new Random();
		// 获取数据库
		mDBUtil = new DBUtils(ScreenContenProvider.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.insert_db:
			insert();
			break;
		case R.id.del_db:
			delDate();
			break;
		case R.id.update_db:
			update();
			break;
		case R.id.search_db:
			search();
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