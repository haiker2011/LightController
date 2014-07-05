package com.android.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
//	private static final String SERVERIP = "10.10.100.254";//����IP��ַ
	private static final int SERVERPORT = 8899;//����ָ���Ķ˿�
	private static final String SERVERIP = "192.168.11.169";//����IP��ַ
	
	public void sendMessage(final byte[] buffer) {
		// TODO Auto-generated method stub
		
		//ʹ�ö��̱߳�̣���ֹFC
		Thread thread = new Thread(new Runnable() {			
			@Override	
			public void run() {
				// TODO Auto-generated method stub  
		            try {  
		            	//1.InetAddress
		            	//2.DatagramSocket
		            	//3.buf=get input content
		            	//4.DatagramPacket
		            	//5.send()
		            	//6.close Socket
		                InetAddress serverAddr = InetAddress.getByName(SERVERIP);  
		                DatagramSocket socket = new DatagramSocket();  
		                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,  
		                        serverAddr, SERVERPORT);
		                socket.send(packet);  
		                socket.close();
		            } catch (Exception e) {  
		                e.printStackTrace();
		            }  
			}
		});
		thread.start();
	}


}
