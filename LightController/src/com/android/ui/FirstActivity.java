package com.android.ui;

import com.android.server.RGB2HSV;
import com.android.server.UDPServer;
import com.android.ui.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;


public class FirstActivity extends Activity implements 
		OnClickListener,OnLongClickListener,OnTouchListener {
	private static final String TAG = "FirstActivity";
	static final boolean DEBUG = false;
	
	private ImageButton lightup,lightdown;
	private ImageButton saturationup,saturationdown;
	private ImageButton color_ring;	
	private Bitmap bitmap;		
	//Button ID 
	//According the ID to select the OnClickListener
	//选择ID作为匹配Button不好，每次添加控件时，R.java中的ID会发生变化，还有别的方法吗？？？
	//在case中直接使用R.id.可以解决这个问题
//	private static final int LIGHTUP_ID = 0x7f080000;
//	private static final int LIGHTDOWN_ID = 0x7f080002;
//	private static final int SATURATIONUP_ID = 0x7f080003;
//	private static final int SATURATIONDOWN_ID = 0x7f080004;
	
	UDPServer server = new UDPServer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	
		lightup = (ImageButton) findViewById(R.id.lightup);
		lightdown = (ImageButton) findViewById(R.id.lightdown);
		saturationup = (ImageButton) findViewById(R.id.saturationup);
		saturationdown = (ImageButton) findViewById(R.id.saturationdown);
		
		color_ring = (ImageButton) findViewById(R.id.color_ring);
		
		
		lightup.setOnClickListener(this);
		lightdown.setOnClickListener(this);
		saturationup.setOnClickListener(this);
		saturationdown.setOnClickListener(this);

		
		
		color_ring.setOnTouchListener(this);
		
		
		lightup.setOnLongClickListener(this);
		lightdown.setOnLongClickListener(this);
		saturationup.setOnLongClickListener(this);
		saturationdown.setOnLongClickListener(this);
		
	}
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lightup:
			
			byte[] byteLightUp = {(byte)0x93,0x00};
			server.sendMessage(byteLightUp);//使用sendMessage()函数进行重构减少重复代码，其他条件相同代码删除
			break;
		case R.id.lightdown:
			
			byte[] byteLightDown = {(byte)0x91,0x00};
			server.sendMessage(byteLightDown);
		
			break;
		case R.id.saturationup:
			byte[] byteSaturationUp = {(byte)0x92,0x00};
			server.sendMessage(byteSaturationUp);
			
			break;
		case R.id.saturationdown:
			byte[] byteSaturationDown = {(byte)0x95,0x00};
			server.sendMessage(byteSaturationDown);
			
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
		case R.id.lightup:
			byte[] byteLightUp = {0x13,0x00};
			server.sendMessage(byteLightUp);
				
			break;
		case R.id.lightdown:
			byte[] byteLightDown = {0x11,0x00};
			server.sendMessage(byteLightDown);
					
			break;
		case R.id.saturationup:
			byte[] byteSaturationUp = {0x12,0x00};
			server.sendMessage(byteSaturationUp);
			
			break;
		case R.id.saturationdown:
			byte[] byteSaturationDown = {0x15,0x00};
			server.sendMessage(byteSaturationDown);
			
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
		
		//event.getDownTime() > 200  可以获取触控按下去的时间可以用来模拟长按
		
		switch (eventaction) {
		case MotionEvent.ACTION_UP:				
			float x = event.getX();
			float y = event.getY();	
			if(DEBUG){
			Log.d(TAG, "x: " + x);
			Log.d(TAG, "y: " + y);
			}
			//获取屏幕的长和宽
//			DisplayMetrics dm = new DisplayMetrics();
//			getWindowManager().getDefaultDisplay().getMetrics(dm);
//			int screenWidth = dm.widthPixels;
//			int screenHight = dm.heightPixels;
//			Log.i("Values", "width: " + screenWidth);
//			Log.i("Values", "hight: " + screenHight);
			//计算彩环的面积，在彩环中执行改变颜色的命令，在彩环内部执行开关控件的命令
			//通过实际点击得出的坐标不太靠谱，应该寻找更好的解决方案？？？
			//解决如何计算Imagebutton的长宽位置等属性？？？
			if(Math.pow(x-270, 2) + Math.pow(y-260,2) < 120*120){
				byte[] byteOnOff = {0x14,0x00};
				server.sendMessage(byteOnOff);
			}else if(Math.pow(x-270, 2) + Math.pow(y-260,2) < 240*240){	
				//重构放入RGBtransfrom中更好
				
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

	//进行重构，提取到RGB2HSV类中
//	private int RGB2HSV(double r, double g, double b) {
//		// TODO Auto-generated method stub
//		//h=[0,360],s=[0,1],v=[0,1]
//		double min,max,delta,tmp;
//		double h = 0;
//		tmp = r>g?g:r;
//		min = tmp>b?b:tmp;
//		tmp = r>g?r:g;
//		max = tmp>b?tmp:b;
//		delta = max - min;
//		if(max == 0)
//			h = 0;
//		if(delta == 0)
//			h = 0;
//		if(r == max){			
//				h = (g-b)/delta;								
//			}else if(g == max){
//				h = 2+(b-r)/delta;
//		}else if(b == max){
//			h = 4+(r-b)/delta;
//		}
//		h *= 60;
//		if(h < 0)
//			h += 360;
//		return (int)(h*255/360);
//	}
	
//	//提出来作为一个独立类的方法更好，主要高内聚原则
//	private void sendMessage(final byte[] buffer) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override	
//			public void run() {
//				// TODO Auto-generated method stub
//				 try {  
//		                Thread.sleep(500);  
//		            } catch (InterruptedException e) {  
//		                // TODO Auto-generated catch block  
//		                e.printStackTrace();  
//		            }  
//		            try {  
//		            	//1.InetAddress
//		            	//2.DatagramSocket
//		            	//3.buf=get input content
//		            	//4.DatagramPacket
//		            	//5.send()
//		            	//6.close Socket
//		                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//		                DatagramSocket socket = new DatagramSocket();  
//		                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,  
//		                        serverAddr, SERVERPORT);
//		                socket.send(packet);  
//		                socket.close();
//		            } catch (Exception e) {  
//		                e.printStackTrace();
//		            }  
//			}
//		});
//		thread.start();
//	}

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}	
	

}

////Button短按事件监听灯光变亮
//btnLightUp.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001001100000000";
//			                buf = str.getBytes();
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);  
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//
//	
//	}
//	
//});
//
////Button长按事件监听灯光变亮
//btnLightUp.setOnLongClickListener(new Button.OnLongClickListener(){
//
//	@Override
//	public boolean onLongClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				 try {  
//		                Thread.sleep(500);  
//		            } catch (InterruptedException e) {  
//		                // TODO Auto-generated catch block  
//		                e.printStackTrace();  
//		            }  
//		            try {  
//		            	//1.InetAddress
//		            	//2.DatagramSocket
//		            	//3.buf=get input content
//		            	//4.DatagramPacket
//		            	//5.send()
//		            	//6.close Socket
//		                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//		                DatagramSocket socket = new DatagramSocket();  
//		                byte[] buf;
//		                String str;
//		                str = "1001001000000000";
//		                buf = str.getBytes();
//		                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//		                        serverAddr, SERVERPORT);
//		                socket.send(packet);  
//		                socket.close();
//		            } catch (Exception e) {  
//		                e.printStackTrace();
//		            }  
//			}
//		});
//		thread.start();
//		return true;
//	}
//	
//});
//
////Button短按事件监听灯光变暗
//btnLightDown.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001000100000000";
//			                buf = str.getBytes();
////			                if (!input.getText().toString().isEmpty()) {  
////			                    buf = input.getText().toString().getBytes();  
////			                } else {  
////			                    buf = ("Default message").getBytes();  
////			                }  
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);   
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//	
//	}
//	
//});
//
////Button长按事件监听灯光变暗
//btnLightDown.setOnLongClickListener(new Button.OnLongClickListener(){
//
//	@Override
//	public boolean onLongClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				 try {  
//		                Thread.sleep(500);  
//		            } catch (InterruptedException e) {  
//		                // TODO Auto-generated catch block  
//		                e.printStackTrace();  
//		            }  
//		            try {  
//		            	//1.InetAddress
//		            	//2.DatagramSocket
//		            	//3.buf=get input content
//		            	//4.DatagramPacket
//		            	//5.send()
//		            	//6.close Socket
//		                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//		                DatagramSocket socket = new DatagramSocket();  
//		                byte[] buf;
//		                String str;
//		                str = "1001000100000000";
//		                buf = str.getBytes();
//		                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//		                        serverAddr, SERVERPORT);
//		                socket.send(packet);  
//		                socket.close();
//		            } catch (Exception e) {  
//		                e.printStackTrace();
//		            }  
//			}
//		});
//		thread.start();
//		return true;
//	}
//	
//});
//
////Button短按事件监听饱和度变亮
//btnSaturationUp.setOnClickListener(new Button.OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Thread thread = new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
////					            try {  
////					                Thread.sleep(500);  
////					            } catch (InterruptedException e) {  
////					                // TODO Auto-generated catch block  
////					                e.printStackTrace();  
////					            }  
//					            try {  
//					            	//1.InetAddress
//					            	//2.DatagramSocket
//					            	//3.buf=get input content
//					            	//4.DatagramPacket
//					            	//5.send()
//					            	//6.close Socket
//					                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//					                DatagramSocket socket = new DatagramSocket();  
//					                byte[] buf;
//					                String str;
//					                str = "0001001000000000";
//					                buf = str.getBytes();
//					                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//					                        serverAddr, SERVERPORT);
//					                socket.send(packet);  
//					                socket.close();
//					            } catch (Exception e) {  
//					                e.printStackTrace();
//					            }  
//				
//					}
//				});
//				thread.start();
//				
//	
//			
//			}
//			
//		});
//		
//
//btnSaturationUp.setOnLongClickListener(new Button.OnLongClickListener(){
//
//	@Override
//	public boolean onLongClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				 try {  
//		                Thread.sleep(500);  
//		            } catch (InterruptedException e) {  
//		                // TODO Auto-generated catch block  
//		                e.printStackTrace();  
//		            }  
//		            try {  
//		            	//1.InetAddress
//		            	//2.DatagramSocket
//		            	//3.buf=get input content
//		            	//4.DatagramPacket
//		            	//5.send()
//		            	//6.close Socket
//		                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//		                DatagramSocket socket = new DatagramSocket();  
//		                byte[] buf;
//		                String str;
//		                str = "1001001000000000";
//		                buf = str.getBytes();
//		                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//		                        serverAddr, SERVERPORT);
//		                socket.send(packet);  
//		                socket.close();
//		            } catch (Exception e) {  
//		                e.printStackTrace();
//		            }  
//			}
//		});
//		thread.start();
//		return true;
//	}
//	
//});
//		
////Button短按事件监听饱和度变暗
//btnSaturationDown.setOnClickListener(new Button.OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Thread thread = new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//					            try {  
//					                Thread.sleep(500);  
//					            } catch (InterruptedException e) {  
//					                // TODO Auto-generated catch block  
//					                e.printStackTrace();  
//					            }  
//					            try {  
//					            	//1.InetAddress
//					            	//2.DatagramSocket
//					            	//3.buf=get input content
//					            	//4.DatagramPacket
//					            	//5.send()
//					            	//6.close Socket
//					                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//					                DatagramSocket socket = new DatagramSocket();  
//					                byte[] buf;
//					                String str;
//					                str = "0001010100000000";
//					                buf = str.getBytes();
//					                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//					                        serverAddr, SERVERPORT);
//					                socket.send(packet);  
//					                socket.close();
//					            } catch (Exception e) {  
//					                e.printStackTrace();
//					            }  
//				
//					}
//				});
//				thread.start();
//				
//	
//			
//			}
//			
//		});
//		
////Button长按事件监听饱和度变暗
//btnSaturationDown.setOnLongClickListener(new Button.OnLongClickListener(){
//
//			@Override
//			public boolean onLongClick(View v) {
//				// TODO Auto-generated method stub
//				Thread thread = new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						 try {  
//				                Thread.sleep(500);  
//				            } catch (InterruptedException e) {  
//				                // TODO Auto-generated catch block  
//				                e.printStackTrace();  
//				            }  
//				            try {  
//				            	//1.InetAddress
//				            	//2.DatagramSocket
//				            	//3.buf=get input content
//				            	//4.DatagramPacket
//				            	//5.send()
//				            	//6.close Socket
//				                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//				                DatagramSocket socket = new DatagramSocket();  
//				                byte[] buf;
//				                String str;
//				                str = "1001010100000000";
//				                buf = str.getBytes();
//				                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//				                        serverAddr, SERVERPORT);
//				                socket.send(packet);  
//				                socket.close();
//				            } catch (Exception e) {  
//				                e.printStackTrace();
//				            }  
//					}
//				});
//				thread.start();
//				return true;
//			}
//			
//		});
//		
////Button短按事件监听开关
//btnOn_off.setOnClickListener(new Button.OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Thread thread = new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//					            try {  
//					                Thread.sleep(500);  
//					            } catch (InterruptedException e) {  
//					                // TODO Auto-generated catch block  
//					                e.printStackTrace();  
//					            }  
//					            try {  
//					            	//1.InetAddress
//					            	//2.DatagramSocket
//					            	//3.buf=get input content
//					            	//4.DatagramPacket
//					            	//5.send()
//					            	//6.close Socket
//					                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//					                DatagramSocket socket = new DatagramSocket();  
//					                byte[] buf;
//					                String str;
//					                str = "0001010000000000";
//					                buf = str.getBytes();
//					                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//					                        serverAddr, SERVERPORT);
//					                socket.send(packet);  
//					                socket.close();
//					            } catch (Exception e) {  
//					                e.printStackTrace();
//					            }  
//				
//					}
//				});
//				thread.start();
//				
//	
//			
//			}
//			
//		});
//
//btnColor1.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001000000000000";
//			                buf = str.getBytes();
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);  
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//
//	
//	}
//	
//});
//
//btnColor2.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001000000001111";
//			                buf = str.getBytes();
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);  
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//
//	
//	}
//	
//});
//
//btnColor3.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001000010101010";
//			                buf = str.getBytes();
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);  
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//
//	
//	}
//	
//});
//
//btnColor4.setOnClickListener(new Button.OnClickListener(){
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			            try {  
//			                Thread.sleep(500);  
//			            } catch (InterruptedException e) {  
//			                // TODO Auto-generated catch block  
//			                e.printStackTrace();  
//			            }  
//			            try {  
//			            	//1.InetAddress
//			            	//2.DatagramSocket
//			            	//3.buf=get input content
//			            	//4.DatagramPacket
//			            	//5.send()
//			            	//6.close Socket
//			                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
//			                DatagramSocket socket = new DatagramSocket();  
//			                byte[] buf;
//			                String str;
//			                str = "0001000011111111";
//			                buf = str.getBytes();
//			                DatagramPacket packet = new DatagramPacket(buf, buf.length,  
//			                        serverAddr, SERVERPORT);
//			                socket.send(packet);  
//			                socket.close();
//			            } catch (Exception e) {  
//			                e.printStackTrace();
//			            }  
//		
//			}
//		});
//		thread.start();
//		
//
//	
//	}
//	
//});
//		