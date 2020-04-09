package com.magnoliaory.scullicommunicationserver.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class CommonUtils {

    /**
     * 获取端口
     * @param ctx
     * @return
     */
    public static int getClientPort(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        int port = inetSocketAddress.getPort();
        return port;
    }

    /**
     * 获取host
     * @param ctx
     * @return
     */
    public static String getClientHost(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String host = inetSocketAddress.getAddress().getHostAddress();
        return host;
    }

    /**
     * Netty中buf和String转化
     * @param byteBuf
     * @return
     */
    public static String convertStringFromByteBuf(ByteBuf byteBuf) {
        String msg = byteBuf.toString(CharsetUtil.UTF_8);
        return msg;
    }


}
