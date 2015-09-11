package com.yinliang.netty_3_5.ChannelBuffer;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
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

public class BufferServer {
	public static void main(String[] args) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new BufferServerHandler());
			}
		}); // 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));
	}
}

class BufferServerHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		// 五位读取
		while (buffer.readableBytes() >= 5) {
			ChannelBuffer tempBuffer = buffer.readBytes(5);
			System.out.println(tempBuffer.toString(Charset.defaultCharset()));
		}
		// 读取剩下的信息
		System.out.println(buffer.toString(Charset.defaultCharset()));
	}
}
