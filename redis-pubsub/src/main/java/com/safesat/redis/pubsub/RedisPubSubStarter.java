package com.safesat.redis.pubsub;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RedisPubSubStarter {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
