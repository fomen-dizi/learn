package com.jys.weibo.mail;

import com.jys.weibo.model.AyUser;

import java.util.List;

/**
 * 描述:发送用户邮件服务
 */
public interface SendJunkMailService {
    boolean sendJunkMail(List<AyUser> ayUser);
}
