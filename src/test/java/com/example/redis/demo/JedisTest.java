package com.example.redis.demo;

import redis.clients.jedis.Jedis;
//直连redis
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.136.100", 6379);
        System.out.println(jedis.get("hello"));
        jedis.close();
    }
}
