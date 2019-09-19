package com.jys.weibo.quartz;

import com.jys.weibo.mail.SendJunkMailService;
import com.jys.weibo.model.AyUser;
import com.jys.weibo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Configurable
@Component
@EnableScheduling
public class SendMailQuartz {
    //日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private AyUserService ayUserService;
    @Resource
    private SendJunkMailService sendJunkMailService;
    //每5秒执行一次
    @Scheduled(cron = "*/5 * * * * * ")
    public void reportCurrentByCron(){
        List<AyUser> userList = ayUserService.findAll();
        if(userList == null || userList.size() <= 0 ) return;
        //发送邮件
        sendJunkMailService.sendJunkMail(userList);
        logger.info("定时器运行！！！");
    }
}
