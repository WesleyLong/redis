package com.example.redis.demo.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public enum JedisConnectionPool {
    INSTANCE;

    public Jedis getConnection() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.136.100", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            System.err.println("获取jedis连接池失败");
            e.getMessage();
        } finally {
            if (jedis != null) {
                // 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
                jedis.close();
            }
        }
        return jedis;
    }
}
