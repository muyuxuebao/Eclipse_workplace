package com.ITS.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ITS.management.bean.KeyUserMapInfo;
import com.ITS.management.bean.KeyValueCell;
import com.ITS.management.bean.UserInfo;
import com.ITS.management.message.Message;
import com.ITS.management.util.CertUtil;
import com.ITS.management.util.SqliteUtil;

public class Server {

	private final static Logger logger = Logger.getLogger(Server.class.getName());

	public static void main(String[] args) {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;

		try {
			selector = Selector.open();

			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);

			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(10000));

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (selector.select() > 0) {
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();

				// Walk through the ready keys collection and process date requests.
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();

					// The key indexes into the selector so you
					// can retrieve the socket that's ready for I/O
					doit((ServerSocketChannel) readyKey.channel());
				}
			}
		} catch (ClosedChannelException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				selector.close();
			} catch (Exception ex) {
			}
			try {
				serverSocketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private static void doit(final ServerSocketChannel serverSocketChannel) throws IOException {
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverSocketChannel.accept();
			String msgStr = receiveRequest(socketChannel);
			Message msg = Message.unwrapMessage(msgStr);

			switch (msg.action) {
			case SystemConstant.ACTION_CREATE_CERT: {
				@SuppressWarnings("unchecked")
				ArrayList<KeyValueCell> list = (ArrayList<KeyValueCell>) msg.extra;

				HashMap<String, String> map = new HashMap<String, String>();
				for (KeyValueCell cell : list) {
					map.put(cell.getInfoName(), cell.getInfoValue());
				}

				System.out.println(map.get("vadality"));
				sendResult(socketChannel, CertUtil.createCert(map.get("userName"), SystemConstant.EXPIRE_TYPE_MONTH, Integer.parseInt(map.get("vadality"))));
				break;
			}

			case SystemConstant.ACTION_IMPORT_CERT: {
				sendFile(socketChannel, new File((String) msg.extra));
				break;
			}

			case SystemConstant.ACTION_CREATE_USER: {
				@SuppressWarnings("unchecked")
				UserInfo user = new UserInfo((ArrayList<KeyValueCell>) msg.extra);

				try {
					SqliteUtil.insert("userinfo", user);
				} catch (Exception e) {
					sendResult(socketChannel, String.valueOf("false"));
				}

				sendResult(socketChannel, String.valueOf("true"));

				break;
			}

			case SystemConstant.ACTION_RECORD_CERT: {
				@SuppressWarnings("unchecked")
				KeyUserMapInfo mapInfo = new KeyUserMapInfo((ArrayList<KeyValueCell>) msg.extra);

				try {
					SqliteUtil.delete("usercert", mapInfo);
					SqliteUtil.insert("usercert", mapInfo);
				} catch (Exception e) {
					sendResult(socketChannel, String.valueOf("false"));
				}

				sendResult(socketChannel, String.valueOf("true"));

				break;
			}

			}

		} finally {
			try {
				socketChannel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static String receiveRequest(SocketChannel socketChannel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		socketChannel.read(buffer);
		buffer.flip();

		String request = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();

		return request;
	}

	private static void sendResult(SocketChannel socketChannel, String result) throws IOException {
		ByteBuffer writeBuffer = ByteBuffer.wrap(result.getBytes("UTF-8"));
		socketChannel.write(writeBuffer);
	}

	private static void sendFile(SocketChannel socketChannel, File file) throws IOException {
		FileInputStream fis = null;

		FileChannel channel = null;
		try {
			fis = new FileInputStream(file);
			channel = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			int size = 0;
			while ((size = channel.read(buffer)) != -1) {

				buffer.rewind();
				buffer.limit(size);
				socketChannel.write(buffer);
				buffer.clear();
			}
			socketChannel.socket().shutdownOutput();
		} finally {
			try {
				channel.close();
			} catch (Exception ex) {
			}
			try {
				fis.close();
			} catch (Exception ex) {
			}
		}
	}
}