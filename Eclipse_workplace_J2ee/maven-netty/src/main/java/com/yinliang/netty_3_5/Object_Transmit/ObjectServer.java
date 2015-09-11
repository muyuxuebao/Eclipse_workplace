package com.yinliang.netty_3_5.Object_Transmit;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;

public class ObjectServer {
	public static void main(String[] args) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {

				return Channels.pipeline(
						new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())),
						new ObjectServerHandler());
			}
		}); // 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));
	}
}

class ObjectServerHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Command command = (Command) e.getMessage();
		// 打印看看是不是我们刚才传过来的那个
		System.out.println(command.getActionName());
	}
}