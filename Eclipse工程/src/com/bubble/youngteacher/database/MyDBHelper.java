package com.bubble.youngteacher.database;

import com.bubble.youngteacher.utils.LogUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

	public final static String DB_NAME = "mydata.db"; // 数据库名称
	public final static String TABLE_NAME = "employee"; // 表名

	public final static int version = 2;

	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table
		String sql = "CREATE TABLE " + TABLE_NAME + "(" + "_id INTEGER PRIMARY KEY," + "name TEXT," + "sex);";
		db.execSQL(sql); // 创建表
		LogUtils.e("table oncreate", "create table：" + TABLE_NAME);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e("update", "update");
		// db.execSQL("ALTER TABLE "+ MyHelper.TABLE_NAME+" ADD sex TEXT");
		// //修改字段

	}
}
