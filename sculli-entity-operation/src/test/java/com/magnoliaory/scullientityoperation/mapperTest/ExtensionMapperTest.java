package com.magnoliaory.scullientityoperation.mapperTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.magnoliaory.scullientityoperation.mapper.ExtensionMapper;
import com.magnoliaory.scullientityoperation.model.Extension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtensionMapperTest {

    @Autowired
    private ExtensionMapper extensionMapper;

    @Test
    public void selectExtension() {
        QueryWrapper<Extension> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("extension_host", "127.0.0.1");
        Extension extension = extensionMapper.selectOne(queryWrapper);
        System.out.println(extension);
    }
}
