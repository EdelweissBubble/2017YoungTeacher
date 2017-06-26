package com.bubble.youngteacher.database;

import java.util.ArrayList;
import java.util.List;

import com.bubble.youngteacher.model.Person;
import com.bubble.youngteacher.utils.LogUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBUtils {

	private MyDBHelper helper;

	public DBUtils(Context context) {
		super();
		helper = new MyDBHelper(context);
	}

	/**
	 * 插入数据
	 * 
	 * @param String
	 */
	public boolean Insert(Person person) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "insert into " + MyDBHelper.TABLE_NAME + "(name,sex) values (" + "'" + person.getName() + "' ,"
				+ "'" + person.getSex() + "'" + ")";
		try {
			db.execSQL(sql);
			return true;
		} catch (SQLException e) {
			LogUtils.e("插入数据", "数据添加失败：" + e.getMessage());
			return false;
		} finally {
			db.close();
		}

	}

	/**
	 * 更新数据
	 * 
	 * @param person
	 */
	public int Update(Person person) {

		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("sex", person.getSex());
		int rows = db.update(MyDBHelper.TABLE_NAME, values, "sex=?", new String[] { 1 + "" });

		db.close();
		return rows;
	}

	/**
	 * 删除数据
	 * 
	 * 
	 */

	public void Delete() {

		SQLiteDatabase db = helper.getWritableDatabase();
		int raw = db.delete(MyDBHelper.TABLE_NAME, "sex=?", new String[] { 1 + "" });
		db.close();
	}

	/**
	 * 查询所有数据
	 * 
	 */
	public List<Person> queryAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Person> list = new ArrayList<Person>();
		Cursor cursor = db.query(MyDBHelper.TABLE_NAME, null, null, null, null, null, null);

		while (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
			list.add(person);
		}
		db.close();
		return list;
	}

	/**
	 * 按姓名进行查找并排序
	 * 
	 */
	public List<Person> queryByname(String name) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Person> list = new ArrayList<Person>();
		Cursor cursor = db.query(MyDBHelper.TABLE_NAME, new String[] { "_id", "name", "sex" }, "name like ? ",
				new String[] { "%" + name + "%" }, null, null, "name asc");
		// Cursor cursor = db.query(table, columns, selection, selectionArgs,
		// groupBy, having, orderBy)
		while (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
			list.add(person);
		}
		db.close();
		return list;
	}

	/**
	 * 按id查询
	 * 
	 */
	public Person queryByid(int id) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Person person = new Person();
		Cursor cursor = db.query(MyDBHelper.TABLE_NAME, new String[] { "name", "sex" }, "_id=?",
				new String[] { id + "" }, null, null, null);
		// db.delete(table, whereClause, whereArgs)
		while (cursor.moveToNext()) {
			person.setId(id);
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
		}
		db.close();
		return person;
	}
}
