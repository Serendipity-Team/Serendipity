package com.serendipity.service;

import org.springframework.stereotype.Service;

/**
 * @author MySelf
 * @date 2019/4/11
 */
@Service
public interface IRedis {
    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @throws Exception e
     */
    void expire(String key, long time) throws Exception;

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     * @throws Exception e
     */
    long getExpire(String key) throws Exception;

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     * @throws Exception e
     */
    boolean hasKey(String key) throws Exception;

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     * @throws Exception e
     */
    void del(String... key) throws Exception;


    //============================String=============================

//    String setMsg(String key, String msg);
//
//    String getMsg(String key);

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     * @throws Exception e
     */
    Object get(String key) throws Exception;

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     * @throws Exception e
     */
    boolean set(String key, Object value) throws Exception;

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     * @throws Exception e
     */
    boolean set(String key, Object value, long time) throws Exception;

}