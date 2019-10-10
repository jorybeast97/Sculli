package com.magnoliaory.hyrule.tcp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * Channel配置器
 */

@Component
public class ServerChannelInitializer extends ChannelInitializer {

    /**
     * 各类型的TCP连接拦截器
     * InboundHandler : 数据读取拦截器
     * OutboundHandler : 数据发送拦截器
     */
    @Autowired
    private InBoundHandler inBoundHandler;

    @Autowired
    private OutBoundHandler outBoundHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline().
                addLast("数据读取拦截器", inBoundHandler).
                addLast("数据发送拦截器", outBoundHandler).
                addLast("编码器", new StringEncoder(Charset.forName("UTF-8"))).
                addLast("解码器", new StringDecoder(Charset.forName("UTF-8")));


    }


}
