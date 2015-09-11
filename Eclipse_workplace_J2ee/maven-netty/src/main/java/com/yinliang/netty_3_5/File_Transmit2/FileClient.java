package com.yinliang.netty_3_5.File_Transmit2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class FileClient {
	public static void main(String[] args) {
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new FileClientHandler());
			}
		});

		// 连接到本地的8000端口的服务端
		bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
	}
}

class FileClientHandler extends SimpleChannelHandler {

	// 每次处理的字节数
	private int readLength = 10;

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// 发送文件
		this.sendFile(e.getChannel());
	}

	private void sendFile(Channel channel) throws IOException {
		File file = new File("from/1.txt");
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] bytes = new byte[this.readLength];

		int readNum = 0;
		while ((readNum = bis.read(bytes)) != -1) {
			this.sendToServer(bytes, channel, readNum);
			System.out.println("Send bytes: " + readNum);
		}

	}

	private void sendToServer(byte[] bytes, Channel channel, int readNum) {
		ChannelBuffer buffer = ChannelBuffers.copiedBuffer(bytes, 0, readNum);
		channel.write(buffer);

	}
}