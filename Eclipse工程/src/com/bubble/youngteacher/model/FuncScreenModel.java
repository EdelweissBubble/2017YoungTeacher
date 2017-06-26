package com.bubble.youngteacher.model;

import android.content.Context;
import android.content.Intent;

public class FuncScreenModel {

	private String funcName;
	private String funcDesc;
	private Class<?> funcActivity;
	private int type;

	public FuncScreenModel() {
		super();
	}

	/**
	 * 
	 * @param type
	 * @param funcName：方法名称
	 * @param funcDesc：方法描述
	 * @param funcActivity：方法对应的Activity
	 */
	public FuncScreenModel(int type, String funcName, String funcDesc, Class<?> funcActivity) {
		super();
		this.funcName = funcName;
		this.funcDesc = funcDesc;
		this.funcActivity = funcActivity;
		this.type = type;
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

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void startFunction(Context context) {
		Intent i = new Intent(context, funcActivity);
		context.startActivity(i);
	}

}
