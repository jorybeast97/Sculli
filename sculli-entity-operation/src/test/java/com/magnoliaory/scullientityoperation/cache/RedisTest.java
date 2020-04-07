package com.magnoliaory.scullientityoperation.cache;

import com.magnoliaory.scullientityoperation.mapper.ExtensionMapper;
import com.magnoliaory.scullientityoperation.model.Extension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ExtensionMapper mapper;

    @Test
    public void setCache() {
        Extension extension = mapper.selectById(1);
        String key = "extension:" + extension.getId();
        redisTemplate.opsForValue().set(key, extension);
    }

    @Test
    public void SelectCache() {
        String id = "1";
        String key = "extension:" + id;
        boolean isExit = redisTemplate.hasKey(key);
        if (isExit) {
            Extension extension = (Extension) redisTemplate.opsForValue().get(key);
            System.out.println("从缓存中获取到对象" + extension);
        }else {
            mapper.selectById(id);
            System.out.println("从数据库中获取到Val");
            setCache();
        }
    }

    
}
