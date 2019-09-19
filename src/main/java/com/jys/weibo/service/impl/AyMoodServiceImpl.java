package com.jys.weibo.service.impl;

import com.jys.weibo.model.AyMood;
import com.jys.weibo.repository.AyMoodRespository;
import com.jys.weibo.service.AyMoodProducer;
import com.jys.weibo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 描述：微信说说服务层
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Resource
    private AyMoodRespository ayMoodRespository;

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRespository.save(ayMood);
    }
//队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");
    @Resource
    private AyMoodProducer ayMoodProducer;

    @Override
    public String asyNSave(AyMood ayMood) {
        //往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination,ayMood);
        return "success";
    }
}
