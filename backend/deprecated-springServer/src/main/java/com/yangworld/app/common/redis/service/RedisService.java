package com.yangworld.app.common.redis.service;

import java.util.Optional;

public interface RedisService {
    void setData(Object object);
    Object getData(String key);
    void deleteData(String key);
}
