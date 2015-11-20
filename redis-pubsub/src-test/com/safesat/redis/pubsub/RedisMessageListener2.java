package com.safesat.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener2 implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener.class);
    @Override
    public void onMessage( final Message message, final byte[] pattern ) {
    	System.out.println("II Message received: " + message.toString() );
    }
}