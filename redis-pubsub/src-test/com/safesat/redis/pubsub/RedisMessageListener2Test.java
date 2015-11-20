package com.safesat.redis.pubsub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisMessageListener2Test implements CommandLineRunner{
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("localhost");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setPassword("");
		return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
	RedisMessageListenerContainer redisContainer() {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(new MessageListenerAdapter(new RedisMessageListener2()), topic());
		return container;
	}
    
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic( "pubsub:queue" );
    }
    
  @Bean
  MessageListenerAdapter messageListener() {
      return new MessageListenerAdapter( new RedisMessageListener2() );
  }
    
  public static void main(String args[]) {
	  new AnnotationConfigApplicationContext(RedisMessageListener2Test.class);
  }
  
	@Override
	public void run(String... arg0) throws Exception {
		messageListener();
	}
}
