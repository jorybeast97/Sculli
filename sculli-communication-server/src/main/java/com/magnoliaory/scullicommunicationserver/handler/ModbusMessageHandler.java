package com.magnoliaory.scullicommunicationserver.handler;

import com.magnoliaory.scullicommunicationserver.utils.CommonUtils;
import com.magnoliaory.scullientityoperation.model.Extension;
import com.magnoliaory.scullientityoperation.service.ExtensionService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Modbus数据信息连接的Handler,进行数据校验,身份认证等情况
 */
@Component
@ChannelHandler.Sharable
public class ModbusMessageHandler extends ChannelInboundHandlerAdapter {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ExtensionService extensionService;

    /**
     * 注册Handler,确保连接的网关已经在数据库中进行注册
     * 如果没有注册则拒绝连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        String host = CommonUtils.getClientHost(ctx);
        int port = CommonUtils.getClientPort(ctx);
        System.out.println("开始进行校验......");
        Extension extension = extensionService.selectExtensionByHost(host);
        if (extension != null){
            System.out.println("连接成‘’                                                                                                               功");
        }else {
            ctx.channel().close();
            System.out.println("验证不通过,客户端[" + host + ":" + port + "]不在数据库注册内");
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String modbusMsg = CommonUtils.convertStringFromByteBuf((ByteBuf) msg);
        rabbitTemplate.convertAndSend(modbusMsg);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
