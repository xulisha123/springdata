package com.example.springbootredis07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    RedisMessageListenerContainer container(
            JedisConnectionFactory redisConnectionFactory,
            MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 设置连接工厂
        container.setConnectionFactory(redisConnectionFactory);
        // 设置监听者绑定的管道
        List<Topic> topicList = new ArrayList<>();
        topicList.add(new PatternTopic("log"));

        // 第一个参数： 监听者     , 第二个参数  ：绑定管道
        container.addMessageListener(messageListenerAdapter, topicList);
        return container;
    }


    /**
     * 消息侦听器适配器,能将消息委托给目标侦听器方法(多余）
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(MessageListener listener) {
        return new MessageListenerAdapter(listener);
    }
}
