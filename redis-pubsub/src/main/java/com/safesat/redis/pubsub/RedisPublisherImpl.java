package com.safesat.redis.pubsub;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;

public class RedisPublisherImpl implements IRedisPublisher {
    private final RedisTemplate< String, Object > template;
    private final ChannelTopic topic; 
    private final AtomicLong counter = new AtomicLong( 0 );

    public RedisPublisherImpl( final RedisTemplate< String, Object > template, 
            final ChannelTopic topic ) {
        this.template = template;
        this.topic = topic;
    }

    @Scheduled( fixedDelay = 5000 )
    public void publish() {
        template.convertAndSend( topic.getTopic(), "Message[Helloe kate -> " + counter.incrementAndGet() + 
            "] " + RandomStringUtils.randomAlphanumeric(6));
 }
}