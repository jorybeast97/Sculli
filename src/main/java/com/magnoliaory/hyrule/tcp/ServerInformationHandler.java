package com.magnoliaory.hyrule.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 该类提供了远程获取Netty服务器的部分信息
 *
 */

@Component
@ChannelHandler.Sharable
public class ServerInformationHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }


    /**
     * 命令窗口工具
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取命令
        String res = NettyUtils.getMessage((ByteBuf) msg);

    }
}
