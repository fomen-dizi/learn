package com.jys.weibo.service.impl;

import com.jys.weibo.dao.AyUserDao;
import com.jys.weibo.exception.BusinessException;
import com.jys.weibo.model.AyUser;
import com.jys.weibo.repository.AyUserRepository;
import com.jys.weibo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 用户服务层实现类
 */
@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource
    public AyUserRepository ayUserRepository;

    @Resource
    public RedisTemplate redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public AyUser findById(String id) {
        //查询redis中的所有数据。
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        if(ayUserList != null && ayUserList.size()>0){
            for(AyUser user: ayUserList){
                if(user.getId().equals(id)){
                    return user;
                }
            }
        }
        //查询数据库数据
        AyUser ayUser = ayUserRepository.findById(id).orElse(null);
        if(ayUser != null){
            //将数据插入到Redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER,ayUser);
        }
        return ayUser;
    }

    @Override
    public List<AyUser> findAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时："+ (end-start)+ "毫秒");
            return ayUserList;
        } catch (Exception e){
            logger.error("method [findAll] error,e");
            return Collections.EMPTY_LIST;
        }

//        return ayUserRepository.findAll();
    }

    @Transactional
    @Override
    public AyUser save(AyUser ayUser) {
        AyUser saveUser = ayUserRepository.save(ayUser);
//        String error = null;
//        error.split("/");
        return saveUser;
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId:"+ id + "用户被删除");
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }

    @Resource
    private AyUserDao ayUserDao;

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name,password);
    }

    @Async
    @Override
    public Future<List<AyUser>> findAsynAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时："+ (end-start)+ "毫秒");
            return new AsyncResult<List<AyUser>>(ayUserList);
        } catch (Exception e){
            logger.error("method [findAll] error,e");
            return new AsyncResult<List<AyUser>>(null);
        }
    }

    @Retryable(value={BusinessException.class},maxAttempts = 5,
            backoff = @Backoff(delay = 5000,multiplier = 2))
    @Override
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        System.out.println("[findByNameAndPasswordRetry] 方法重试失败了！");
        throw new BusinessException();
    }

    @Override
    public AyUser findByUserName(String name) {
        return null;
    }


}
