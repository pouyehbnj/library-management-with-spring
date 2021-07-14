/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication.service;

import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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

        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // } finally {
        // if (jedis != null) {
        // jedis.close();
        // }
        // if (jedisPool != null) {
        // jedisPool.close();
        // }
        // }

    }

    // @Bean
    // public JedisPool generateJedisPoolFactory() {
    // // @Value("${spring.redis.host}")
    // // private String host;
    // //
    // // @Value("${spring.redis.port}")
    // // private int port;
    // JedisPoolConfig poolConfig = new JedisPoolConfig();
    // // poolConfig.setMaxTotal(maxActive);
    // // poolConfig.setMaxIdle(maxIdle);
    // // poolConfig.setMinIdle(minIdle);
    // // poolConfig.setMaxWaitMillis(maxWaitMillis);
    // // Whether to block when the connection is exhausted, false will report an
    // exception, true will block until the timeout, and the default is true
    // try{
    // poolConfig.setBlockWhenExhausted(Boolean.TRUE);
    // JedisPool jedisPool = new JedisPool(poolConfig, "37.152.138.117", 6379);
    // System.out.println("connected");
    // return jedisPool;
    // }catch(Exception e){
    // System.out.println("error"+e);
    // return null;
    // }

    // // If the Redis password is set, please call the following constructor
    // // JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout,
    // password);

    // }
}
