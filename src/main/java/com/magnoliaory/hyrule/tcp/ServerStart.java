package com.magnoliaory.hyrule.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerStart implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ModbusServer modbusServer;

    @Value("${netty.server.serverName}")
    private String serverName;

    @Value("${netty.server.serverPort}")
    private Integer serverPort;

    @Value("${netty.server.auto-start}")
    private Integer autoStartStatus;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        modbusServer.start("测试服务器" , 25555);
//        if (autoStartStatus == 1) {
//            modbusServer.start(serverName, serverPort);
//        } else {
//            logger.info("提示信息 : application.yml文件中服务器自启动未开启,如需开启请更改auto-start字段数值为1");
//        }
    }

}
