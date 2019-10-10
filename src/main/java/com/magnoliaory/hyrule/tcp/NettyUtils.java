package com.magnoliaory.hyrule.tcp;


import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

/**
 * 封装了部分Netty常用的操作
 */
public class NettyUtils {

    public static String getChannelHost(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String host = inetSocketAddress.getAddress().getHostAddress();
        return host;
    }

    public static Integer getChannelPort(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        Integer port = inetSocketAddress.getPort();
        return port;
    }


}
