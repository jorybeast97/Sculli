package com.magnoliaory.scullicommunicationserver.handler;

import com.magnoliaory.scullicommunicationserver.utils.CommonUtils;
import com.magnoliaory.scullientityoperation.model.Monitor;
import com.magnoliaory.scullientityoperation.service.MonitorService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Modbus数据信息连接的Handler,进行数据校验,身份认证等情况
 */
@Component
@ChannelHandler.Sharable
public class MessageHandler extends ChannelInboundHandlerAdapter {


    @Autowired
    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MonitorService monitorService;
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
        Monitor monitor = monitorService.selectByHostAndPort(host, port);
        if (monitor == null) {
            ctx.channel().close();
            logger.warn("客户端:[" + host + ":" + port + "] 未在数据库中注册,禁止连接服务端");
        }
        logger.info("客户端:[" + host + ":" + port + "] 连接成功");
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
        double data = Double.parseDouble(modbusMsg);
        String host = CommonUtils.getClientHost(ctx);
        int port = CommonUtils.getClientPort(ctx);
        Monitor monitor = monitorService.selectByHostAndPort(host, port);
        if ((monitor.getAlertLowerLimit() > data && monitor.getLower() < data)
                || (monitor.getAlertUpperLimit() < data && monitor.getCeiling() > data)) {
            logger.warn("数据预警状态");
        }
        if (monitor.getCeiling() < data || monitor.getLower() > data) {
            logger.error("数据报警");
        }
        logger.info("数据正常");
        //业务代码...
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage());
        cause.printStackTrace();
    }
}
