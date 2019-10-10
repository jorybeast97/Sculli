package com.magnoliaory.hyrule.tcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerStart implements ApplicationRunner {

    @Autowired
    private ModbusServer modbusServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        modbusServer.start("测试服务器" , 13549);
    }

}
