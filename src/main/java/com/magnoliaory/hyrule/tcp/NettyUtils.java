package com.magnoliaory.hyrule.tcp;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * 封装了部分Netty常用的操作
 */
public class NettyUtils {

    /**
     * 根据Context获取客户端连接的地址
     * @param ctx
     * @return
     */
    public static String getChannelHost(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String host = inetSocketAddress.getAddress().getHostAddress();
        return host;
    }

    /**
     * 根据客户端信息获取连接的端口
     * @param ctx
     * @return
     */
    public static Integer getChannelPort(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        Integer port = inetSocketAddress.getPort();
        return port;
    }

    /**
     * 读取byteBuf中的信息
     * @param byteBuf
     * @return
     */
    public static String getMessage(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
