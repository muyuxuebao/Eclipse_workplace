package com.yinliang.netty_3_5.File_Transmit1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class FileServer {
	public static void main(String[] args) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new FileServerHandler());
			}
		}); // 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));
	}
}

class FileServerHandler extends SimpleChannelHandler {
	private File file = new File("to/2.txt");
	private FileOutputStream fos;

	public FileServerHandler() {
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			} else {
				this.file.delete();
				this.file.createNewFile();
			}
			this.fos = new FileOutputStream(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		int length = buffer.readableBytes();
		buffer.readBytes(this.fos, length);
		this.fos.flush();
		buffer.clear();
	}
}
