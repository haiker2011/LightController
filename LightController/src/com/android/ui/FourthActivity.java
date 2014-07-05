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

public class FourthActivity extends Activity 
		implements OnClickListener,OnLongClickListener,OnTouchListener{
	private static final String TAG = "FourthActivity";
	static final boolean DEBUG = true;

	/**
	 * 圆心坐标(270,260)
	 */
	private final int height = 270;
	private final int weidth = 260;
	private final int r1 = 120;//圆环内半径
	private final int r2 = 240; //圆环外半径
	private ImageButton group1_on,group1_off;
	private ImageButton group2_on,group2_off;
	private ImageButton group3_on,group3_off;
	private ImageButton group4_on,group4_off;
	private ImageButton on,off;
	private ImageButton flicker,changecolor;
	private ImageButton color_ring;
	
	private Bitmap bitmap;
	
	
	UDPServer server = new UDPServer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		
		group1_off = (ImageButton) findViewById(R.id.fourth_group1_off);
		group1_on = (ImageButton) findViewById(R.id.fourth_group1_on);
		group2_off = (ImageButton) findViewById(R.id.fourth_group2_off);
		group2_on = (ImageButton) findViewById(R.id.fourth_group2_on);
		group3_off = (ImageButton) findViewById(R.id.fourth_group3_off);
		group3_on = (ImageButton) findViewById(R.id.fourth_group3_on);
		group4_off = (ImageButton) findViewById(R.id.fourth_group4_off);
		group4_on = (ImageButton) findViewById(R.id.fourth_group4_on);
		on = (ImageButton) findViewById(R.id.on_4);
		off = (ImageButton) findViewById(R.id.off_4);
		flicker = (ImageButton) findViewById(R.id.flicker);
		changecolor = (ImageButton) findViewById(R.id.changecolor);
		color_ring = (ImageButton) findViewById(R.id.color_ring);
		
		group1_off.setOnClickListener(this);
		group1_on.setOnClickListener(this);
		group2_off.setOnClickListener(this);
		group2_on.setOnClickListener(this);
		group3_off.setOnClickListener(this);
		group3_on.setOnClickListener(this);
		group4_off.setOnClickListener(this);
		group4_on.setOnClickListener(this);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		flicker.setOnClickListener(this);
		changecolor.setOnClickListener(this);
		
		
		group1_off.setOnLongClickListener(this);
		group1_on.setOnLongClickListener(this);
		group2_off.setOnLongClickListener(this);
		group2_on.setOnLongClickListener(this);
		group3_off.setOnLongClickListener(this);
		group3_on.setOnLongClickListener(this);
		group4_off.setOnLongClickListener(this);
		group4_on.setOnLongClickListener(this);
		on.setOnLongClickListener(this);
		off.setOnLongClickListener(this);
		flicker.setOnLongClickListener(this);
		changecolor.setOnLongClickListener(this);
		
		color_ring.setOnTouchListener(this);
	}
	
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.on_4:
			byte[] byteOn = {(byte)0xc2,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_4:
			byte[] byteOff = {(byte)0xc1,0x00};
			server.sendMessage(byteOff);
			break;
		case R.id.fourth_group1_on:
			byte[] byteGroup1on = {(byte)0xc5,0x00};
			server.sendMessage(byteGroup1on);
			break;
		case R.id.fourth_group1_off:
			byte[] byteGroup1off = {(byte)0xc6,0x00};
			server.sendMessage(byteGroup1off);
			break;
		case R.id.fourth_group2_on:
			byte[] byteGroup2on = {(byte)0xc7,0x00};
			server.sendMessage(byteGroup2on);
			break;
		case R.id.fourth_group2_off:
			byte[] byteGroup2off = {(byte)0xc8,0x00};
			server.sendMessage(byteGroup2off);
			break;
		case R.id.fourth_group3_on:
			byte[] byteGroup3on = {(byte)0xc9,0x00};
			server.sendMessage(byteGroup3on);
			break;
		case R.id.fourth_group3_off:
			byte[] byteGroup3off = {(byte)0xca,0x00};
			server.sendMessage(byteGroup3off);
			break;
		case R.id.fourth_group4_on:
			byte[] byteGroup4on = {(byte)0xcb,0x00};
			server.sendMessage(byteGroup4on);
			break;
		case R.id.fourth_group4_off:
			byte[] byteGroup4off = {(byte)0xcc,0x00};
			server.sendMessage(byteGroup4off);
			break;	
		case R.id.flicker:
			byte[] byteFlicker = {(byte)0xc3,0x00};
			server.sendMessage(byteFlicker);
			break;
		case R.id.changecolor:
			byte[] byteChangecolor = {(byte)0xc4,0x00};
			server.sendMessage(byteChangecolor);
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
		case R.id.on_4:
			byte[] byteOn = {(byte)0x42,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_4:
			byte[] byteOff = {(byte)0x41,0x00};
			server.sendMessage(byteOff);
			break;
		case R.id.fourth_group1_on:
			byte[] byteGroup1on = {(byte)0x45,0x00};
			server.sendMessage(byteGroup1on);
			break;
		case R.id.fourth_group1_off:
			byte[] byteGroup1off = {(byte)0x46,0x00};
			server.sendMessage(byteGroup1off);
			break;
		case R.id.fourth_group2_on:
			byte[] byteGroup2on = {(byte)0x47,0x00};
			server.sendMessage(byteGroup2on);
			break;
		case R.id.fourth_group2_off:
			byte[] byteGroup2off = {(byte)0x48,0x00};
			server.sendMessage(byteGroup2off);
			break;
		case R.id.fourth_group3_on:
			byte[] byteGroup3on = {(byte)0x49,0x00};
			server.sendMessage(byteGroup3on);
			break;
		case R.id.fourth_group3_off:
			byte[] byteGroup3off = {(byte)0x4a,0x00};
			server.sendMessage(byteGroup3off);
			break;
		case R.id.fourth_group4_on:
			byte[] byteGroup4on = {(byte)0x4b,0x00};
			server.sendMessage(byteGroup4on);
			break;
		case R.id.fourth_group4_off:
			byte[] byteGroup4off = {(byte)0x4c,0x00};
			server.sendMessage(byteGroup4off);
			break;	
		case R.id.flicker:
			byte[] byteFlicker = {(byte)0x43,0x00};
			server.sendMessage(byteFlicker);
			break;
		case R.id.changecolor:
			byte[] byteChangecolor = {(byte)0x44,0x00};
			server.sendMessage(byteChangecolor);
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
		 if((Math.pow(x-height, 2) + Math.pow(y-weidth,2) < r2*r2)&&(Math.pow(x-height, 2)
				 + Math.pow(y-weidth,2) > r1*r1)){
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
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	
}
