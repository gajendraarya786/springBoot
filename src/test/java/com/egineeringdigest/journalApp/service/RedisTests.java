package com.egineeringdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void testSendMail() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("email", "gajendra.work538@gmail.com");

        String salary = ops.get("salary");
        String email = ops.get("email");
        System.out.println(email);
    }
}
