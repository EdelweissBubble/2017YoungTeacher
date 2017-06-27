package com.sict.shakedemo.app;

import com.sict.shakedemo.MyApp;
import com.sict.shakedemo.R;
import com.sict.shakedemo.adapter.FuncListAdapter;
import com.sict.shakedemo.model.FuncScreenModel;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ScreenMain extends BaseActivity {

	private ListView myFuncListView;

	private FuncListAdapter fla = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_main);
		myFuncListView = (ListView) findViewById(R.id.func_list);
		fla = new FuncListAdapter(this, MyApp.getInstance().getFuncList());
		myFuncListView.setAdapter(fla);
		fla.notifyDataSetChanged();
		myFuncListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				FuncScreenModel fsm = MyApp.getInstance().getFuncList().get(position);
				if (fsm != null) {
					fsm.startFunction(ScreenMain.this);
				}
			}
		});
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
		super.onBackPressed();
		this.finish();
	}
}
