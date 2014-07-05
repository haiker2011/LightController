package com.android.ui;

import com.android.server.RGB2HSV;
import com.android.server.UDPServer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;


public class SecondActivity extends Activity implements 
		OnClickListener,OnLongClickListener,OnTouchListener {
	private static final String TAG = "SecondActivity";
	static final boolean DEBUG = false;
	
	private ImageButton lightup,lightdown;
	private ImageButton saturationup,saturationdown;
	private ImageButton color_ring;
	private ImageButton sceneup,scenedown;
	private ImageButton on,off;	
	private Bitmap bitmap;
	
	UDPServer server = new UDPServer();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	
		lightup = (ImageButton) findViewById(R.id.lightup_2);
		lightdown = (ImageButton) findViewById(R.id.lightdown_2);
		saturationup = (ImageButton) findViewById(R.id.saturationup_2);
		saturationdown = (ImageButton) findViewById(R.id.saturationdown_2);
		sceneup = (ImageButton) findViewById(R.id.sceneup_2);
		scenedown = (ImageButton) findViewById(R.id.scenedown_2);
		on = (ImageButton) findViewById(R.id.on_2);
		off = (ImageButton) findViewById(R.id.off_2);
		
		color_ring = (ImageButton) findViewById(R.id.color_ring);
		
		
		lightup.setOnClickListener(this);
		lightdown.setOnClickListener(this);
		saturationup.setOnClickListener(this);
		saturationdown.setOnClickListener(this);
		sceneup.setOnClickListener(this);
		scenedown.setOnClickListener(this);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		color_ring.setOnTouchListener(this);
		
		lightup.setOnLongClickListener(this);
		lightdown.setOnLongClickListener(this);
		saturationup.setOnLongClickListener(this);
		saturationdown.setOnLongClickListener(this);
		sceneup.setOnLongClickListener(this);
		scenedown.setOnLongClickListener(this);
		on.setOnLongClickListener(this);
		off.setOnLongClickListener(this);
		
	}
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lightup_2:
			
			byte[] byteLightUp = {(byte)0xa3,0x00};
			server.sendMessage(byteLightUp);//使用sendMessage()函数进行重构减少重复代码，其他条件相同代码删除
			break;
		case R.id.lightdown_2:
			
			byte[] byteLightDown = {(byte)0xa4,0x00};
			server.sendMessage(byteLightDown);
		
			break;
		case R.id.saturationup_2:
			byte[] byteSaturationUp = {(byte)0xa7,0x00};
			server.sendMessage(byteSaturationUp);
			
			break;
		case R.id.saturationdown_2:
			byte[] byteSaturationDown = {(byte)0xa8,0x00};
			server.sendMessage(byteSaturationDown);
			
			break;
		case R.id.sceneup_2:
			byte[] byteSceneUp = {(byte)0xa5,0x00};
			server.sendMessage(byteSceneUp);
			break;
		case R.id.scenedown_2:
			byte[] byteSceneDown = {(byte)0xa6,0x00};
			server.sendMessage(byteSceneDown);
			break;
		case R.id.on_2:
			byte[] byteOn = {(byte)0xa2,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_2:
			byte[] byteOff = {(byte)0xa1,0x00};
			server.sendMessage(byteOff);
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.lightup_2:
			byte[] byteLightUp = {0x23,0x00};
			server.sendMessage(byteLightUp);
				
			break;
		case R.id.lightdown_2:
			byte[] byteLightDown = {0x24,0x00};
			server.sendMessage(byteLightDown);
					
			break;
		case R.id.saturationup_2:
			byte[] byteSaturationUp = {0x27,0x00};
			server.sendMessage(byteSaturationUp);
			
			break;
		case R.id.saturationdown_2:
			byte[] byteSaturationDown = {0x28,0x00};
			server.sendMessage(byteSaturationDown);
			
			break;
		case R.id.sceneup_2:
			byte[] byteSceneUp = {0x25,0x00};
			server.sendMessage(byteSceneUp);
			break;
		case R.id.scenedown_2:
			byte[] byteSceneDown = {0x26,0x00};
			server.sendMessage(byteSceneDown);
			break;
		case R.id.on_2:
			byte[] byteOn = {0x22,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_2:
			byte[] byteOff = {0x21,0x00};
			server.sendMessage(byteOff);
			break;

		default:
			break;
		}
		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		RGB2HSV rgbt = new RGB2HSV();		
		bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.color_ring)).getBitmap();		
		int eventaction = event.getAction();
		switch (eventaction) {
		case MotionEvent.ACTION_UP:				
			float x = event.getX();
			float y = event.getY();
			if(DEBUG){
				Log.d(TAG, "x: " + x);
				Log.d(TAG, "y: " + y);
				}
		 if((Math.pow(x-260, 2) + Math.pow(y-260,2) < 240*240)&&(Math.pow(x-260, 2) + Math.pow(y-260,2) > 120*120)){	
			int h = rgbt.getRGB2HSV((int)x, (int)y,bitmap);
			byte[] byteOntouch = {0x10,(byte)h};
			server.sendMessage(byteOntouch);
				break;
			}
						
		default:
			break;
		}
				
		return true;
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}	
	

}
