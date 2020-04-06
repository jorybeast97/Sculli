package com.magnoliaory.scullicommunicationserver.server;

import com.magnoliaory.scullicommunicationserver.initializer.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class ModbusServer {

    /**
     * 创建boss和work两个group,一个负责接收TCP连接,一个负责处理IO相关操作
     */
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private final NioEventLoopGroup workGroup = new NioEventLoopGroup();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Channel channel;

    @Value("${netty.host}")
    private String host;

    @Value("${netty.port}")
    private int port;

    @Autowired
    private ServerInitializer serverInitializer;

    @PostConstruct
    public ChannelFuture start() {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(host, port))
                    .childHandler(serverInitializer);
            channelFuture = bootstrap.bind().sync();
            channel = channelFuture.channel();
            logger.info("===========Netty Start Successful===========");
        } catch (InterruptedException e) {
            logger.error("==============Netty Start Failed============");
            e.printStackTrace();
        } finally {

        }
        return channelFuture;
    }

    /**
     * 停止服务
     */
    @PreDestroy
    public void destory() {
        logger.info("==========Shut Down Netty Server===========");
        if (channel != null) {
            channel.close();
        }
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        logger.info("==========Shut Down Netty Server Successfully===========");
    }

}
