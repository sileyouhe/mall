package com.sileyouhe.mall.service;

/**
 * Use Json to store the data
 */
public interface RedisService {

    /**
     * save data
     */
    void set(String key, String value);

    /**
     * get data
     */
    String get(String key);

    /**
     * set expire time
     */
    boolean expire(String key, long expire);

    /**
     * remove data
     */
    void remove(String key);

    /**
     * increment
     */
    Long increment(String key, long delta);

}
