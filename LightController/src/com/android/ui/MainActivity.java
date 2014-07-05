package com.android.ui;

import com.android.adpater.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(this);
		

	}

@Override
public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	// TODO Auto-generated method stub
	Intent intent = new Intent();
	switch (position) {
	case 0:		
		intent.setClass(MainActivity.this, FirstActivity.class);
		startActivity(intent);
//		MainActivity.this.finish();//加入此行代码，点击返回按钮时会直接退出			
		break;
	case 1:		
		intent.setClass(MainActivity.this, SecondActivity.class);
		startActivity(intent);				
		break;
	case 2:		
		intent.setClass(MainActivity.this, ThirdActivity.class);
		startActivity(intent);
		break;
	case 3:		
		intent.setClass(MainActivity.this, FourthActivity.class);
		startActivity(intent);
		break;

	default:
		break;
	}
	
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}

}
