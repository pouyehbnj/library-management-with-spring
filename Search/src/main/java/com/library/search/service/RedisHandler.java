/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.service;

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author Pouyeh
 */
@Configuration
public class RedisHandler {
    @SuppressWarnings("resource")
    public void findSessions(String ID) throws Exception {

        Jedis jedis = new Jedis("37.152.138.117", 6379, 180000);
        jedis.connect();
        String sessionId = "spring:session:sessions:" + ID;
        Boolean exists = jedis.exists(sessionId);
        if (!exists) {
            return;
        }
        String type = jedis.type(sessionId);
        System.out.println("The value type of sessionId is:" + type);
        Map<String, String> sessions = jedis.hgetAll(sessionId);
        System.out.println("All keys of sessionId are:" + sessions.keySet());
        Set<Map.Entry<String, String>> entrySet = sessions.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }


    }

}
