package com.magnoliaory.scullidataprocess.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hyrule-modbus-message-queue")
public class ModbusQueueListener {

    @RabbitHandler
    public void getModbusMessage(String msg) {
        System.out.println("收到信息" + msg);
        System.out.println("解析完成");
    }

}
