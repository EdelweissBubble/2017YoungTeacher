package com.bubble.youngteacher.utils;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.app.ScreenAbout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class NavigationBarUtils {

	/**
	 * 初始化导航栏
	 * 
	 * @param context：当前页面Activity
	 * @param contentView：导航栏View
	 * @param canBackUse：是否允许返回
	 * @param backClass：上一个Activity
	 * @param canGoNext：是否允许进入下一章
	 * @param nextClass：下一个Activity
	 * @param isMenu：当前页面是否为目录
	 * @param menuMain：目录所在的Activity
	 */
	public static void initNavigationBar(final Activity context, View contentView, final boolean canBackUse,
			final Class<?> backClass, final boolean canGoNext, final Class<?> nextClass, final boolean isMenu,
			final Class<?> menuMain) {

		ImageView back;
		ImageView next;
		ImageView menu;
		ImageView close;
		ImageView info;

		back = (ImageView) contentView.findViewById(R.id.back);
		next = (ImageView) contentView.findViewById(R.id.next);
		menu = (ImageView) contentView.findViewById(R.id.menu);
		close = (ImageView) contentView.findViewById(R.id.close);
		info = (ImageView) contentView.findViewById(R.id.info);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (canBackUse) {
					Intent i = new Intent(context, backClass);
					context.startActivity(i);
				}
			}
		});
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (canGoNext) {
					Intent i = new Intent(context, nextClass);
					context.startActivity(i);
				}
			}
		});
		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isMenu) {
					Intent i = new Intent(context, menuMain);
					context.startActivity(i);
				}
			}
		});
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.finish();
			}
		});
		info.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, ScreenAbout.class);
				context.startActivity(i);
			}
		});
	}
}
