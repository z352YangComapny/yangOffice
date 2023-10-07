package com.ssoystory.memberservice.common.redis.service;

public interface RedisService {
    void setData(Object object);
    Object getData(String key);
    void deleteData(String key);
}
