package com.ITS.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import com.ITS.management.message.Message;

public class Connection implements Runnable {
	/**
	 * client establishes a connection to server to make a command. it needs server's url (or ip), port num, and the message it needs to send to server.
	 */
	private Message message;
	private String toUrl;
	private int portNum;

	public Connection(String toUrl, int portNum) {
		this.toUrl = toUrl;
		this.portNum = portNum;
	}

	public void setMessage(Message msg) {
		this.message = msg;
	}

	public Message getMessage() {
		return this.message;
	}

	@Override
	public void run() {
		// build a socket channel
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress(this.toUrl, this.portNum);
			socketChannel.connect(socketAddress);

			// send message to socket channel
			this.sendRequest(socketChannel, this.message.wrapMessage());

			switch (this.message.expectDataType) {
			case SystemConstant.TYPE_BOOLEAN: {
				this.message.expectData = receiveBoolean(socketChannel);
				break;
			}

			case SystemConstant.TYPE_STRING: {
				this.message.expectData = receiveString(socketChannel);
				break;
			}

			case SystemConstant.TYPE_FILE: {
				this.message.expectData = receiveFile(socketChannel, "newcert.pfx");
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socketChannel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void sendRequest(SocketChannel socketChannel, String requestStr) throws IOException {
		ByteBuffer writeBuffer = ByteBuffer.wrap(requestStr.getBytes("UTF-8"));
		socketChannel.write(writeBuffer);
	}

	private static boolean receiveBoolean(SocketChannel socketChannel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		socketChannel.read(buffer);
		buffer.flip();

		String receivedStr = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();

		return receivedStr.equals("true");
	}

	private static String receiveString(SocketChannel socketChannel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		socketChannel.read(buffer);
		buffer.flip();

		String receivedStr = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();

		return receivedStr;
	}

	private static File receiveFile(SocketChannel socketChannel, String fileName) throws IOException {
		File file = new File(fileName);

		FileOutputStream fos = null;
		FileChannel channel = null;

		try {
			fos = new FileOutputStream(file);
			channel = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

			int size = 0;
			while ((size = socketChannel.read(buffer)) != -1) {
				buffer.flip();
				if (size > 0) {
					buffer.limit(size);
					channel.write(buffer);
					buffer.clear();
				}
			}

			return file;
		} finally {
			try {
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
