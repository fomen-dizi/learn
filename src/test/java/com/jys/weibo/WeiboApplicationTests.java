package com.jys.weibo;

import com.jys.weibo.model.AyMood;
import com.jys.weibo.model.AyUser;
import com.jys.weibo.model.AyUserAttachmentRel;
import com.jys.weibo.service.AyMoodProducer;
import com.jys.weibo.service.AyMoodService;
import com.jys.weibo.service.AyUserAttachmentRelService;
import com.jys.weibo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootTest
class WeiboApplicationTests {

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void contextLoads() {
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void mySqlTest(){
        String sql = "select id,name,password from ay_user";
        List<AyUser> userList = (List<AyUser>) jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Override
            public AyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AyUser user = new AyUser();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for(AyUser user:userList){
            System.out.println("【id】:" + user.getId() + "【name】:" + user.getName());
        }
    }
    @Resource
    private AyUserService ayUserService;

    @Test
    public void testRepository(){
        //查询所有数据
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll():"+ userList.size());
        //通过name查询数据
        List<AyUser> userList2 = ayUserService.findByName("阿一");
        System.out.println("findByName():"+ userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("阿一"),"data error!");
        //通过name模糊查询数据
        List<AyUser> userList3 = ayUserService.findByNameLike("%一%");
        System.out.println("findByNameLike():"+ userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("阿一"),"data error!");
        //通过id列表查询数据
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        List<AyUser> userList4 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn():"+ userList4.size());
        //分页查询数据
        PageRequest pageRequest =PageRequest.of(0,10);
        Page<AyUser> userList5 = ayUserService.findAll(pageRequest);
        System.out.println("findAll():"+ userList5.getTotalPages() + "/" + userList5.getSize());
        //新增数据
        AyUser ayUser = new AyUser();
        ayUser.setId("3");
        ayUser.setName("test");
        ayUserService.save(ayUser);
        //删除数据
        ayUserService.delete("3");
    }

    @Test
    public void testTransaction(){
        AyUser ayUser = new AyUser();
        ayUser.setId("3");
        ayUser.setName("阿华");
        ayUser.setPassword("134");
        ayUserService.save(ayUser);
    }

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis(){
        //增key：name ,value:ay
        redisTemplate.opsForValue().set("name","姜雨生");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        //删除
        redisTemplate.delete(name);
        //更新
        redisTemplate.opsForValue().set("name","姜雨生");
        //查询
        redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    public void testLog4j(){
        ayUserService.delete("3");
        logger.info("delete success!!!");
    }

    @Test
    public void testMybatis(){
        AyUser ayUser = ayUserService.findByNameAndPassword("阿一","123456");
        logger.info(ayUser.getId() + ayUser.getName());
        System.out.println(ayUser.getId() + ayUser.getName());
    }

    @Resource
    private AyMoodService ayMoodService;

    @Test
    public void testAyMood(){
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是我的第一条微信说说！！！");
        ayMood.setPublishTime(new Date());
        AyMood mood = ayMoodService.save(ayMood);
    }
    @Resource
    private AyMoodProducer ayMoodProducer;

    @Test
    public void testActiveMQ(){
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination,"hello,mq!!!");
    }

    @Test
    public void testActiveMQAsynSave(){
        AyMood ayMood = new AyMood();
        ayMood.setId("2");
        ayMood.setUserId("2");
        ayMood.setPublishTime(new Date());
        ayMood.setContent("这是我的第一条微信说说！！！");
        ayMood.setPraiseNum(0);
        String msg = ayMoodService.asyNSave(ayMood);
        System.out.println("异步发表说说：" + msg);
    }

    @Test
    public void testAsync(){
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        List<AyUser> ayUserList = ayUserService.findAll();

        System.out.println("第二次查询所有用户！");
        List<AyUser> ayUserList2 = ayUserService.findAll();

        System.out.println("第三次查询所有用户！");
        List<AyUser> ayUserList3 = ayUserService.findAll();

        long endTime = System.currentTimeMillis();

        System.out.println("总共消耗 " +(endTime-startTime) + "毫秒");
    }

    @Test
    public void testAsync2() throws Exception{
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        Future<List<AyUser>> ayUserList = ayUserService.findAsynAll();

        System.out.println("第二次查询所有用户！");
        Future<List<AyUser>> ayUserList2 = ayUserService.findAsynAll();

        System.out.println("第三次查询所有用户！");
        Future<List<AyUser>> ayUserList3 = ayUserService.findAsynAll();

        while (true){
            if(ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
                break;
            }else{
                Thread.sleep(10);
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("总共消耗 " +(endTime-startTime) + "毫秒");
    }
    @Resource
    private AyUserAttachmentRelService ayUserAttachmentRelService;
    @Test
    public void testMongoDB(){
        AyUserAttachmentRel ayUserAttachmentRel = new AyUserAttachmentRel();
        ayUserAttachmentRel.setId("1");
        ayUserAttachmentRel.setUserId("1");
        ayUserAttachmentRel.setFileName("个人简历.doc");
        ayUserAttachmentRelService.save(ayUserAttachmentRel);
    }
}
