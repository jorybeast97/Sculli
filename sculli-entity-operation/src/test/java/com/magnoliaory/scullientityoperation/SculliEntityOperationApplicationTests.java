package com.magnoliaory.scullientityoperation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SculliEntityOperationApplicationTests {

    @Autowired
    private ExtensionMapper extensionMapper;

    @Test
    void contextLoads() {

        Extension extension = extensionMapper.selectById(1);
        System.out.println(extension);

    }

}
