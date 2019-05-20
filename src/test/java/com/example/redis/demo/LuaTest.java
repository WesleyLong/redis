package com.example.redis.demo;

import com.example.redis.demo.util.JedisConnectionPool;
import redis.clients.jedis.Jedis;

public class LuaTest {
    public static void main(String[] args) {
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Jedis jedis = JedisConnectionPool.INSTANCE.getConnection();
        //直接执行script
        Object result = jedis.eval(script, 1, key);
        System.out.println(result);

        //先把script上传到redis，然后再执行，好处是可以重复执行，redis重启后会消失，需要重新上传
        String scriptSha = jedis.scriptLoad(script);
        Object result1 = jedis.evalsha(scriptSha, 1, key);
        System.out.println(result1);
    }
}
