package com.magnoliaory.scullientityoperation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据预热模块,将安环,网关等模块预热给服务器,直接缓存查询
 */
@Component
public class PreLoad implements ApplicationRunner {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${sculli.preload-state}")
    private boolean openPreLoad;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (openPreLoad) {
            List<Extension> list = extensionService.selectAll();
            list.forEach(extension -> {
                String key = "extension:" + extension.getId();
                redisTemplate.opsForValue().set(key, extension);
            });
            System.out.println("数据预加载完成");
        }
    }
}
