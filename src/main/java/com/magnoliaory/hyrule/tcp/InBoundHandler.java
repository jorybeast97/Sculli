package com.magnoliaory.hyrule.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundInvoker;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.omg.PortableServer.POA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@ChannelHandler.Sharable
public class InBoundHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 日志记录
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String host = NettyUtils.getChannelHost(ctx);
        Integer port = NettyUtils.getChannelPort(ctx);
        logger.info("客户端 : " + host + " : " + port + " 已经连接 ... 准备开始通信");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 数据读取 , 从缓存区中读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String res = NettyUtils.getMessage((ByteBuf) msg);
        System.out.println("服务端接受信息" +
                res);

    }

    /**
     * 服务端心跳检测机制
     * 只有写入时间超时才会进行自动断开
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //获取客户端ip及端口号
        String host = NettyUtils.getChannelHost(ctx);

        //检测evt是否为心跳包
        if (evt instanceof IdleStateEvent) {
            //状态转换
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                ctx.disconnect();
                logger.info("客户端 : " + host + " 写入超时,连接已经断开。");
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("msg receive completely");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

    }

}
