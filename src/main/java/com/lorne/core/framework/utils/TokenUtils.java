package com.lorne.core.framework.utils;

import com.alibaba.fastjson.JSONObject;
import com.lorne.core.framework.utils.encode.MD5Util;
import com.lorne.core.framework.utils.redis.RedisUtil;

import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by yuliang on 2016/7/1.
 */
public class TokenUtils {

    public static String putValue(Map<String,Object> sessionUser, int minutes) {
        Jedis jedis = RedisUtil.getJedis();
        String key = getKey();
        jedis.set(key, JSONObject.toJSONString(sessionUser));
        jedis.expire(key, minutes * 60);
        RedisUtil.returnResource(jedis);
        return key;
    }


    public static Map<String,Object> getUserByKey(String key) {
        Map<String,Object> sessionUser = null;
        Jedis jedis = RedisUtil.getJedis();
        String json = jedis.get(key);
        try {
            sessionUser = json==null?null:JSONObject.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionUser != null) {
                jedis.expire(key, 2 * 60 * 60);
            }
            RedisUtil.returnResource(jedis);
        }
        return sessionUser;
    }

    private static String getKey() {
        return MD5Util.md5(KidUtils.getUUID());
    }


}
