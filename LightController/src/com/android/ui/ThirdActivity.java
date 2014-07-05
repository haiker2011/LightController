package com.android.ui;

import com.android.server.UDPServer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;


public class ThirdActivity extends Activity 
		implements OnClickListener,OnLongClickListener 
{
	
	
	private ImageButton group1_on,group1_off;
	private ImageButton group2_on,group2_off;
	private ImageButton group3_on,group3_off;
	private ImageButton group4_on,group4_off;
	private ImageButton add,remove;
	private ImageButton before,next;
	private ImageButton on,off;	
	
	
	UDPServer server = new UDPServer();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	
		group1_on = (ImageButton) findViewById(R.id.third_group1_on);
		group1_off = (ImageButton) findViewById(R.id.third_group1_off);
		group2_on = (ImageButton) findViewById(R.id.third_group2_on);
		group2_off = (ImageButton) findViewById(R.id.third_group2_off);
		group3_on = (ImageButton) findViewById(R.id.third_group3_on);
		group3_off = (ImageButton) findViewById(R.id.third_group3_off);
		group4_on = (ImageButton) findViewById(R.id.third_group4_on);
		group4_off = (ImageButton) findViewById(R.id.third_group4_off);
		add = (ImageButton) findViewById(R.id.third_add);
		remove = (ImageButton) findViewById(R.id.third_remove);
		before = (ImageButton) findViewById(R.id.third_before);
		next = (ImageButton) findViewById(R.id.third_next);
		on = (ImageButton) findViewById(R.id.on_3);
		off = (ImageButton) findViewById(R.id.off_3);
		
		
		group1_on.setOnClickListener(this);
		group1_off.setOnClickListener(this);
		group2_on.setOnClickListener(this);
		group2_off.setOnClickListener(this);
		group3_on.setOnClickListener(this);
		group3_off.setOnClickListener(this);
		group4_on.setOnClickListener(this);
		group4_off.setOnClickListener(this);
		add.setOnClickListener(this);
		remove.setOnClickListener(this);
		before.setOnClickListener(this);
		next.setOnClickListener(this);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		
		
		group1_on.setOnLongClickListener(this);
		group1_off.setOnLongClickListener(this);
		group2_on.setOnLongClickListener(this);
		group2_off.setOnLongClickListener(this);
		group3_off.setOnLongClickListener(this);
		group3_on.setOnLongClickListener(this);
		group4_off.setOnLongClickListener(this);
		group4_on.setOnLongClickListener(this);
		add.setOnLongClickListener(this);
		remove.setOnLongClickListener(this);
		before.setOnLongClickListener(this);
		next.setOnLongClickListener(this);
		on.setOnLongClickListener(this);
		off.setOnLongClickListener(this);
		
	}
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.third_group1_on:
			
			byte[] byteGroup1on = {(byte)0xb7,0x00};
			server.sendMessage(byteGroup1on);//使用sendMessage()函数进行重构减少重复代码，其他条件相同代码删除
			break;
		case R.id.third_group1_off:
			
			byte[] byteGroup1off = {(byte)0xba,0x00};
			server.sendMessage(byteGroup1off);
		
			break;
		case R.id.third_group2_on:
			byte[] byteGroup2on = {(byte)0xbc,0x00};
			server.sendMessage(byteGroup2on);
			
			break;
		case R.id.third_group2_off:
			byte[] byteGroup2off = {(byte)0xb2,0x00};
			server.sendMessage(byteGroup2off);
			
			break;
		case R.id.third_group3_on:
			byte[] byteGroup3on = {(byte)0xb6,0x00};
			server.sendMessage(byteGroup3on);
			break;
		case R.id.third_group3_off:
			byte[] byteGroup3off = {(byte)0xb9,0x00};
			server.sendMessage(byteGroup3off);
			break;
		case R.id.third_group4_on:
			byte[] byteGroup4on = {(byte)0xb1,0x00};
			server.sendMessage(byteGroup4on);
			break;
		case R.id.third_group4_off:
			byte[] byteGroup4off = {(byte)0xb5,0x00};
			server.sendMessage(byteGroup4off);
			break;
		case R.id.third_add:
			byte[] byteAdd = {(byte)0xbe,0x00};
			server.sendMessage(byteAdd);
			break;
		case R.id.third_remove:
			byte[] byteRemove = {(byte)0xbd,0x00};
			server.sendMessage(byteRemove);
			break;
		case R.id.third_before:
			byte[] byteBefore = {(byte)0xbb,0x00};
			server.sendMessage(byteBefore);
			break;
		case R.id.third_next:
			byte[] byteNext = {(byte)0xb3,0x00};
			server.sendMessage(byteNext);
			break;
		case R.id.on_3:
			byte[] byteOn = {(byte)0xb4,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_3:
			byte[] byteOff = {(byte)0xb8,0x00};
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
		case R.id.third_group1_on:
			
			byte[] byteGroup1on = {(byte)0x37,0x00};
			server.sendMessage(byteGroup1on);//使用sendMessage()函数进行重构减少重复代码，其他条件相同代码删除
			break;
		case R.id.third_group1_off:
			
			byte[] byteGroup1off = {(byte)0x3a,0x00};
			server.sendMessage(byteGroup1off);
		
			break;
		case R.id.third_group2_on:
			byte[] byteGroup2on = {(byte)0x3c,0x00};
			server.sendMessage(byteGroup2on);
			
			break;
		case R.id.third_group2_off:
			byte[] byteGroup2off = {(byte)0x32,0x00};
			server.sendMessage(byteGroup2off);
			
			break;
		case R.id.third_group3_on:
			byte[] byteGroup3on = {(byte)0x36,0x00};
			server.sendMessage(byteGroup3on);
			break;
		case R.id.third_group3_off:
			byte[] byteGroup3off = {(byte)0x39,0x00};
			server.sendMessage(byteGroup3off);
			break;
		case R.id.third_group4_on:
			byte[] byteGroup4on = {(byte)0x31,0x00};
			server.sendMessage(byteGroup4on);
			break;
		case R.id.third_group4_off:
			byte[] byteGroup4off = {(byte)0x35,0x00};
			server.sendMessage(byteGroup4off);
			break;
		case R.id.third_add:
			byte[] byteAdd = {(byte)0x3e,0x00};
			server.sendMessage(byteAdd);
			break;
		case R.id.third_remove:
			byte[] byteRemove = {(byte)0x3d,0x00};
			server.sendMessage(byteRemove);
			break;
		case R.id.third_before:
			byte[] byteBefore = {(byte)0x3b,0x00};
			server.sendMessage(byteBefore);
			break;
		case R.id.third_next:
			byte[] byteNext = {0x33,0x00};
			server.sendMessage(byteNext);
			break;
		case R.id.on_3:
			byte[] byteOn = {(byte)0x34,0x00};
			server.sendMessage(byteOn);
			break;
		case R.id.off_3:
			byte[] byteOff = {(byte)0x38,0x00};
			server.sendMessage(byteOff);
			break;

		default:
			break;
		}
		
	}
	
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}	
	

}
