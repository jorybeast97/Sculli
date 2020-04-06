package com.magnoliaory.scullicommunicationserver.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class CommonUtils {

    public static int getClientPort(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        int port = inetSocketAddress.getPort();
        return port;
    }

    public static String getClientHost(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String host = inetSocketAddress.getAddress().getHostAddress();
        return host;
    }

    public static String convertStringFromByteBuf(ByteBuf byteBuf) {
        String msg = byteBuf.toString(CharsetUtil.UTF_8);
        return msg;
    }

}
