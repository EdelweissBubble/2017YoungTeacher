package com.bubble.youngteacher.app;

import java.util.ArrayList;
import java.util.List;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.NavigationBarUtils;
import com.bubble.youngteacher.view.DepthPageTransformer;
import com.bubble.youngteacher.view.ViewPagerCompat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ScreenViewPager extends Activity {
	private int[] mImgIds = new int[] { R.drawable.item1_01, R.drawable.item1_02, R.drawable.item1_03,
			R.drawable.item1_04 };
	private List<ImageView> mImageViews = new ArrayList<ImageView>();

	private ViewPagerCompat mViewPager;

	private View naviBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_common_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		initData();
		mViewPager = (ViewPagerCompat) findViewById(R.id.id_viewpager);
		naviBar = findViewById(R.id.navi_bar);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenViewPager.class,
				false, ScreenContents.class);
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
		// mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		mViewPager.setAdapter(new PagerAdapter() {
			@Override
			public Object instantiateItem(ViewGroup container, int position) {

				container.addView(mImageViews.get(position));
				return mImageViews.get(position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {

				container.removeView(mImageViews.get(position));
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public int getCount() {
				return mImgIds.length;
			}
		});
	}

	private void initData() {
		for (int imgId : mImgIds) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setImageResource(imgId);
			mImageViews.add(imageView);
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