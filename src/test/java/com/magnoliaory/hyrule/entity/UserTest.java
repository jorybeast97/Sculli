package com.magnoliaory.hyrule.entity;

import com.magnoliaory.hyrule.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void cascadeSelectUser() {
        User user = userRepository.findById(1).get();
        System.out.println(user);
        System.out.println(user.getRoles().get(0));
        System.out.println(user.getRoles().get(0).getPermissions().get(0));
    }


}
