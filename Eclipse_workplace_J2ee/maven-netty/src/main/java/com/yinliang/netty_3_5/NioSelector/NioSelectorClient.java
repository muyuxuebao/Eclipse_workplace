package com.yinliang.netty_3_5.NioSelector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NioSelectorClient {
	public static void main(String[] args) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", 8000));
	}
}
