package com.yinliang.netty_3_5.File_Transmit2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;

public class FileServer {
	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new HttpRequestDecoder());
				pipeline.addLast("aggregator", new HttpChunkAggregator(65536));
				pipeline.addLast("encoder", new HttpResponseEncoder());
				pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
				pipeline.addLast("handler", new FileServerHandler());
				return pipeline;
			}
		});

		bootstrap.bind(new InetSocketAddress(8080));
	}
}

class FileServerHandler extends SimpleChannelUpstreamHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		HttpRequest request = (HttpRequest) e.getMessage();
		if (request.getMethod() != HttpMethod.GET) {
			this.sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
			return;
		}

		String path = this.sanitizeUri(request.getUri());
		if (path == null) {
			this.sendError(ctx, HttpResponseStatus.FORBIDDEN);
			return;
		}

		File file = new File(path);
		if (file.isHidden() || !file.exists()) {
			this.sendError(ctx, HttpResponseStatus.NOT_FOUND);
			return;
		}

		if (!file.isFile()) {
			this.sendError(ctx, HttpResponseStatus.FORBIDDEN);
			return;
		}

		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException fnfe) {
			this.sendError(ctx, HttpResponseStatus.NOT_FOUND);
			return;
		}

		long fileLength = raf.length();

		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

		/* 
		 * 由于是异步传输，所以不得已加入了一些属性，用来进行文件识别 
		 */
		response.addHeader("fileName", request.getUri());

		HttpHeaders.setContentLength(response, fileLength);

		Channel ch = e.getChannel();
	}

	private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {

	}

	private String sanitizeUri(String uri) {
		return uri;
	}
}
