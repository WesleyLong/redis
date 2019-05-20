package com.example.redis.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//通过连接池获取jedis
public class JedisPoolTest {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.136.100", 6379);
        Jedis jedis = null;
        try {
            // 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
            // 2. 执行操作
            System.out.println(jedis.get("hello"));
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (jedis != null) {
            // 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
                jedis.close();
            }
        }

    }
}
