package com.yinliang.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	static Socket s = null;
	static DataOutputStream dos = null;
	static DataInputStream dis = null;
	private static boolean bConnected = false;

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		chatClient.connect();
		//chatClient.start();
	}

	private void start() {

		try {
			dos.writeUTF("aaa");
			while (bConnected) {
				String string = dis.readUTF();
				System.out.println("client receive:" + string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			s = new Socket(InetAddress.getLocalHost(), 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
			System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("服务器未启动,请先启动服务器");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
