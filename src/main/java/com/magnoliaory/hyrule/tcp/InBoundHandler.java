package com.magnoliaory.hyrule.tcp;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundInvoker;
import io.netty.channel.SimpleChannelInboundHandler;
import org.omg.PortableServer.POA;
import org.springframework.stereotype.Component;


@Component
public class InBoundHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String host = NettyUtils.getChannelHost(ctx);
        Integer port = NettyUtils.getChannelPort(ctx);

        System.out.println("客户端 : " + host + " : " + port + " 已经连接 ... 准备开始通信");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

    }
}
