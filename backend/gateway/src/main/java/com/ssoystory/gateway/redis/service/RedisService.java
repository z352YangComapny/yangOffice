package com.ssoystory.gateway.redis.service;

public interface RedisService {
    void setData(Object object);
    Object getData(String key);
    void deleteData(String key);
}
