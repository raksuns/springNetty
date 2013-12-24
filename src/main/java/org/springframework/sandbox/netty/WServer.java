package org.springframework.sandbox.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class WServer {

	private final int port;
	
	public WServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap sb = new ServerBootstrap();
			
			sb.group(bossGroup, workerGroup)
			  .channel(NioServerSocketChannel.class)
			  .localAddress(port)
			  .handler(new LoggingHandler(LogLevel.INFO))
			  .childHandler(new DispatcherServletChannelInitializer());
			
			sb.bind().sync().channel().closeFuture().sync();
		}
		finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		int port;
		
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		else {
			port = 8090;
		}
		
		new WServer(port).run();
	}
}
