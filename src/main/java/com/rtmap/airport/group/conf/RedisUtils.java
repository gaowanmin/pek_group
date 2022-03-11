package com.rtmap.airport.group.conf;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * 提供redis操作集、锁等
 *
 * @Auther: shan
 * @Date:2022/2/16
 * @Description:
 */
@Component
public class RedisUtils {

    public static final String LOCK_PREFIX = "redis_lock";
    //加锁失效时间，毫秒
    public static final int LOCK_EXPIRE = 300; // ms

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean setWithTimeOut(final String key, String value, long timeOut) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.MINUTES);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean hset(String column, String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(column,key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String hget(String column, String key) {
        Object o = null;
        try {
            o = redisTemplate.opsForHash().get(column,key);
            if(o != null)
                return JSONObject.toJSONString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean removeKey(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            //当前锁的过期时间
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            //当锁不存在时，设置锁，key为锁名称，value为过期时间
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                //如果设置成功，则返回true
                return true;
            } else {
                //如果锁没有设置成功
                //获取已经存在的锁的value(即已经存在的锁的过期时间)
                byte[] value = connection.get(lock.getBytes());

                //当已经存在的旧锁的过期时间存在时
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    // 如果旧锁已经过期，则重新加锁
                    if (expireTime < System.currentTimeMillis()) {
                        // 重新强制加锁，防止死锁，并返回旧锁的过期时间
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        //判断：如果旧锁已经过期，则返回true，否则返回false
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 删除锁
     *
     * @param key
     */
    public Boolean unlock(String key) {
        boolean result = false;
        try {
            String lock = LOCK_PREFIX + key;
            redisTemplate.delete(lock);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
