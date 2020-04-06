package com.magnoliaory.scullicommunicationserver.initializer;

import com.magnoliaory.scullicommunicationserver.handler.ModbusMessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ModbusMessageHandler modbusMessageHandler;

    /**
     * 配置各类拦截器
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        //配置业务拦截器
        channelPipeline.addLast(modbusMessageHandler);

    }
}
