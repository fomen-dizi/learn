package com.jys.weibo.service.impl;

import com.jys.weibo.model.AyUserAttachmentRel;
import com.jys.weibo.repository.AyUserAttachmentRelRepository;
import com.jys.weibo.service.AyUserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户附件实现层
 */
@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {
    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
