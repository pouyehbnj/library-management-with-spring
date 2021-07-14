/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication;

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 *
 * @author ASUS
 */
public class Test {

    public void testSessionIdKeys(String username) throws Exception {
//       
//        JedisPoolConfig config = new JedisPoolConfig();
//        Jedis jedis= new Jedis(config,"37,152.138.117", 6379);
        //jedisPool.setMaxTotal("300"); 
        //redis.clients.jedis.Jedis jedis = jedisPool.getResource();
        try (Jedis jedis = new Jedis("37.152.138.117", 6379,3000)) {
            jedis.connect();
            String sessionId = "spring:session:sessions:" + username;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//            if (jedisPool != null) {
//                jedisPool.close();
//            }
//        }

    }
}
