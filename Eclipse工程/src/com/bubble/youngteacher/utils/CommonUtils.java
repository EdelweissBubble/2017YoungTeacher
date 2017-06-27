package com.bubble.youngteacher.utils;

import android.text.TextUtils;

public class CommonUtils {

	/**
	 * 电话号码脱敏
	 * 
	 * @param phoneNum
	 * @return
	 */

	public static String handlePhoneNum(String phoneNum) {
		if (TextUtils.isEmpty(phoneNum)) {
			return "";
		}
		phoneNum = phoneNum.replace("-", "").replace(" ", "").replace("+86", "");
		if (phoneNum.length() == 11) {
			String temp = phoneNum.substring(3, 7);
			phoneNum = phoneNum.replace(temp, "****");
		}
		return phoneNum;

	}

}
