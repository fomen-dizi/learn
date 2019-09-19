package com.jys.weibo.service.impl;

import com.jys.weibo.dao.WbDao;
import com.jys.weibo.model.Weibo;
import com.jys.weibo.service.WbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WbServiceImpl implements WbService {
    @Resource
    private WbDao wbDao;

    @Override
    public List<Weibo> getWeiboItemListList() {
        List<Weibo> itemList = new ArrayList<Weibo>();
        Weibo item = new Weibo();
        item.setId(1);
        item.setUserId(1);
        item.setWbType(1);
        item.setWbSendTime(new Date());
        item.setWbCreateTime(new Date());
        item.setWbModifyTime(new Date());
        item.setWbImageId(1);
        item.setWbVideoId(1);
        item.setWbMusicId(1);
        item.setWbContent("【祝贺！湖北71人上榜“全国优秀教师和全国优秀教育工作者”，快来围观有没有你所熟悉的老师" +
                "\uD83D\uDC68\u200D\uD83C\uDFEB\uD83D\uDC69\u200D\uD83C\uDFEB】在今年教师节来临之际，教育部对1432名全国优秀教师和158名全国优秀教育工作者进行表彰。其中，湖北有65人获得“全国优秀教师”称号，6人获“全国优秀教育工作者”称号。具体名单如下，向这些获奖者表示祝贺，向广大教育工作者表示衷心的感谢！");
        item.setWbCommentId(1);
        item.setWbZanCnt(1);
        item.setWbZfCnt(1);
        item.setWbScCnt(1);
        item.setWbLookCnt(1);
        itemList.add(item);
        return itemList;
    }

    @Override
    public void insertWb(Weibo weibo) {
        /*Weibo weibo1= */wbDao.insertWb(weibo);
//        return weibo1;
    }

    @Override
    public List<Weibo> findAllWb() {
        List<Weibo> wblist = wbDao.findAllWb();
        return wblist;
    }

    @Override
    public String findImagePathById(int id) {
        return wbDao.findImagePathById(id);
    }

    @Override
    public String findVideoPathById(int id) {
        return wbDao.findVideoPathById(id);
    }
}
