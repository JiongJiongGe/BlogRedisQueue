package com.mybatis.service.redis;

/**
 * Created by yunkai on 2017/11/27.
 */
public interface JedisServiceI {

    /**
     * 设置key，value
     *
     * @param key
     * @param value
     */
    public void setKeyValue(String key, String value);

    /**
     * nx 设置key，value
     *
     * @param key
     * @param value
     */
    public void setNxKeyValue(String key, String value);

    /**
     * 根据key值获取value
     *
     * @param key
     * @return
     */
    public String getValue(String key);

    /**
     * 递减key对应的value
     *
     * @param key
     */
    public void decrKey(String key);

    /**
     * sadd  SET集合
     *
     * @param key
     * @param value
     * @return
     */
    public Long sadd(String key, String value);

    /**
     * lpush List集合
     * @param key
     * @param value
     * @return
     */
    public Long lpush(String key, String value);

    /**
     * lpop 移除List第一个元素并获取该值
     * @param key
     */
    public String lpop(String key);

    /**
     * sismember
     *
     * @param key
     * @param member
     */
    public void sismember(String key, String member);

}
