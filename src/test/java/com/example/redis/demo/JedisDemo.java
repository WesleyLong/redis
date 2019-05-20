package com.example.redis.demo;

import com.example.redis.demo.util.JedisConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisDemo {
    private Jedis jedis;

    @Test
    public void stringTest() {
        String res = jedis.set("jedis", "你好");
        System.out.println(res);
        res = jedis.get("jedis");
        System.out.println(res);
    }

    @Test
    public void hashTest() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("city", "hefei");
        jedis.hmset("mkey", map);
//      直接输出
        System.out.println(jedis.hgetAll("mkey"));

//      遍历返回的map并输出
        Map<String, String> map1 = jedis.hgetAll("mkey");
        for (Map.Entry entry : map1.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
        }
    }

    @Test
    public void getKeys() {
//        Set<String> keys = jedis.keys("*"); //列出所有的key
//        Set<String> keys = jedis.keys("key"); //查找特定的key
        ScanResult<String> res = jedis.scan(ScanParams.SCAN_POINTER_START);
        List<String> list = res.getResult();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Before
    public void getJedis() {
        jedis = JedisConnectionPool.INSTANCE.getConnection();
    }

    @After
    public void closeJedis() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
