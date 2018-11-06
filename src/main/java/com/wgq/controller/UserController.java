package com.wgq.controller;

import com.wgq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/add")
    public String add(@RequestBody @Validated User user) {
        logger.info("user:{}", user);
//        redisTemplate.opsForValue().set("user" + System.currentTimeMillis(), user);

        boolean b = (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            Boolean result = connection.set("uname".getBytes(),
                    "abceddd".getBytes(),
                    Expiration.from(10, TimeUnit.SECONDS),
                    RedisStringCommands.SetOption.SET_IF_ABSENT);
            return result;
        });

        return b + "ok";
    }

}
