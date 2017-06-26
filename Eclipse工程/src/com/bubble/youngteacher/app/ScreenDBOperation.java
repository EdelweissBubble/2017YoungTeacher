package com.bubble.youngteacher.app;

import java.util.List;
import java.util.Random;

import org.apache.http.conn.scheme.SchemeRegistry;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.database.DBUtils;
import com.bubble.youngteacher.model.Person;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenDBOperation extends BaseActivity implements View.OnClickListener {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_db_operation_main);
		init();
	}

	private void insert() {
		int num = random.nextInt(max) % (max - min + 1) + min;

		String name = new String("小红" + num);
		String sex = (num % 2 == 0 ? "1" : "2");
		Person person = new Person(name, sex);
		if (mDBUtil.Insert(person)) {
			LogUtils.e("插入数据", "数据添加成功：" + name + "，" + sex);
		} else {
			LogUtils.e("插入数据", "数据添加失败：" + name + "，" + sex);
		}
	}

	private void delDate() {
		mDBUtil.Delete();
		LogUtils.e("删除数据", "数据删除成功");
	}

	private void update() {
		int num = random.nextInt(max) % (max - min + 1) + min;

		String name = new String("小红" + num);
		String sex = (num % 2 == 0 ? "1" : "2");
		Person person = new Person(name, sex);
		int rows = mDBUtil.Update(person);
		LogUtils.e("更新数据", "更新了： " + rows + "  行数据，" + name);
	}

	private void search() {
		List<Person> resList = mDBUtil.queryAll();
		if (resList != null) {
			LogUtils.e("查询数据", "数据：" + resList.size());
			for (int i = 0; i < resList.size(); i++) {
				Person person = resList.get(i);
				if (person == null) {
					LogUtils.e("查询数据", "第  " + (i + 1) + " 条数据为null");
				} else {
					LogUtils.e("查询数据",
							(i + 1) + "    " + person.getId() + "    " + person.getName() + "    " + person.getSex());
				}
			}

		} else {
			LogUtils.e("查询数据", "未查询到数据");
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenDBOperation.class,
				false, ScreenContents.class);
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
		mDBUtil = new DBUtils(ScreenDBOperation.this);
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