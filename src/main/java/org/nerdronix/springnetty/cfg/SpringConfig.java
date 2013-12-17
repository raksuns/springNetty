package org.nerdronix.springnetty.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.nerdronix")
@PropertySource("classpath:netty-server.properties")
public class SpringConfig {
	
	@Value("${boss.thread.count}")
	private int bossCount;
	
	@Value("${worker.thread.count}")
	private int workerCount;
	
	@Value("${tcp.port}")
	private int tcpPort;
	
	@Value("${so.keepalive}")
	private boolean keepAlive;
	
	@Value("${so.backlog}")
	private int backlog;
	
	@Value("${log4j.configuration}")
	private String log4jConfiguration;
	
	@Autowired
	@Qualifier("springProtocolInitializer")
	private StringProtocolInitailizer protocolInitializer;
	
	@SuppressWarnings("unchecked")
	@Bean(name="serverBootstrap")
	public ServerBootstrap bootstrap() {
		
	}
}
