package com.serendipity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;


@Service
public class RedisImpl implements IRedis {

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public void expire(String key, long time) throws Exception {
        if (time > 0) {
            template.expire(key, time, TimeUnit.SECONDS);
        }
    }

    @Override
    public long getExpire(String key) throws Exception {
        return template.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) throws Exception {
        return template.hasKey(key);
    }

    @Override
    public void del(String... key) throws Exception {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                template.delete(key[0]);
            } else {
                template.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public Object get(String key) throws Exception {
        return key == null ? null : template.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) throws Exception {
        template.opsForValue().set(key, String.valueOf(value));
        return true;
    }

    @Override
    public boolean set(String key, Object value, long time) throws Exception {
        if (time > 0) {
            template.opsForValue().set(key, String.valueOf(value), time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
        return true;
    }

//    @Override
//    public String setMsg(String key,String msg) {
//        redisTemplate.opsForValue().set(key,msg);
//        return "success";
//    }
//
//    @Override
//    public String getMsg(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
}