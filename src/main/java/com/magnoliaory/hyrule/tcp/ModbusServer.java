package com.magnoliaory.hyrule.tcp;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModbusServer implements AdaptServer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建两个NioEvent组,bossGroup负责客户端线程调度
     * workgroup负责具体行为
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    /**
     * channel初始化器
     */
    @Autowired
    private ServerChannelInitializer serverChannelInitializer;

    @Override
    public void start(String hostName, int port) {

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(serverChannelInitializer);

            //绑定服务器端口号
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            if (channelFuture.isSuccess()) {
                logger.info("服务器 : "+hostName+" 启动成功 . . .");
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("服务器 : "+hostName+" 启动失败 . . .");
        } finally {
            logger.info("Gourp关闭");
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }


}
