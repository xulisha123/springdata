package com.tuling.msglistenner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 配置在订阅者
 */
@Component
public class Subscriber implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //todo  消息处理   日志记录   积分  邮件发送..

        //Message 封装消息管道和消息内容


        // 管道
        byte[] channel = message.getChannel();     // key
        byte[] body = message.getBody();           // value


        Object channelInfo = redisTemplate.getKeySerializer().deserialize(channel);
        Object bodyInfo = redisTemplate.getValueSerializer().deserialize(body);

        // 消息体
        System.out.println("管道："+channelInfo+"消息内容："+bodyInfo);
    }
}
