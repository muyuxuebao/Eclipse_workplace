package com.yinliang.netty_3_5.ChannelBuffer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class BufferClient {
	public static void main(String[] args) {
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new BufferClientHandler());
			}
		});

		// 连接到本地的8000端口的服务端
		bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
	}
}

class BufferClientHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		this.sendMessageByFrame(e);
	}

	/*
	 * 将<b>"Hello, I'm client."</b>分成三份发送。 <br>
	 * Hello, <br>
	 * I'm<br>
	 * client.<br>
	 */
	private void sendMessageByFrame(ChannelStateEvent e) {
		String msgOne = "Hello, ";
		String msgTwo = "I'm ";
		String msgThree = "client.";
		e.getChannel().write(this.tranStr2Buffer(msgOne));
		e.getChannel().write(this.tranStr2Buffer(msgTwo));
		e.getChannel().write(this.tranStr2Buffer(msgThree));
	}

	/*
	 * 将字符串转换成{@link ChannelBuffer}，私有方法不进行字符串的非空判
	 */
	private Object tranStr2Buffer(String str) {
		ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
		buffer.writeBytes(str.getBytes());
		return buffer;
	}
}
