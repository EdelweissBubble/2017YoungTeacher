package com.sict.shakedemo.model;

import com.sict.shakedemo.MyApp;

import android.content.Context;
import android.content.Intent;

public class FuncScreenModel {

	private String funcName;
	private Class<?> funcActivity;

	public FuncScreenModel(String funcName, Class<?> funcActivity) {
		super();
		this.funcName = funcName;
		this.funcActivity = funcActivity;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Class<?> getFuncActivity() {
		return funcActivity;
	}

	public void setFuncActivity(Class<?> funcActivity) {
		this.funcActivity = funcActivity;
	}

	public void startFunction(Context context) {
		Intent i = new Intent(context, funcActivity);
		context.startActivity(i);
	}

}
