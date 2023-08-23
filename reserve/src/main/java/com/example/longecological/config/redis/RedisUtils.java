package com.example.longecological.config.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    
    
    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    
    
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    
    
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }
    

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }
    

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }
    

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }
    

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }
    

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    
    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }
    

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
    
    
    /**
     * 将排行榜集合数据存储到redis缓存中
     * @param rankList：排行榜的集合数据
     * @param prefixKey：redis缓存key前缀
     * @param majorIndex：map的主键
     */
    public void addRedisList(List<Map<String, Object>> rankList,String prefixKey) {
    	//删除记录的索引
    	remove(prefixKey);
		set(prefixKey, JSONObject.toJSONString(rankList).toString());
    }
    
    
    /**
     * 分页查询redis缓存
     * @param prefixKey：redis缓存key
     * @param pageNum：当前页码数量
     * @param pageSize：每页大小
     * @param type：分页类型（1：下一页，2：上一页）
     * @return
     */
    public List<Map<String, Object>> getRedisLis(String prefixKey,String pageNum,int pageSize,String type){
    	int endCount = 0;
    	List<Map<String, Object>> rankList = (List<Map<String, Object>>) JSONObject.parse(get(prefixKey).toString());
    	if(rankList==null) {
    		return null;
    	}
    	//如果pageNum为空，则默认查询第一页
    	if(StringUtils.isEmpty(pageNum)||"0".equals(pageNum)) {
    		pageNum="1";
    	}
    	int startCount=(Integer.parseInt(pageNum)-1)*pageSize;
    	if(startCount > rankList.size()){
    		return null;
    	}
    	if(startCount+pageSize > rankList.size()){
    		endCount = rankList.size();
    	}else{
    		endCount = startCount+pageSize;
    	}
    	return rankList.subList(startCount, endCount);
    }
    
    /**
     * 从队列头加入
     * @param key
     * @param json
     * @return
     */
    public long lpush(String key, String str){
    	ListOperations operation = redisTemplate.opsForList();
    	return operation.leftPush(key, str);
    }
    
    /**
     * 从队列尾加入
     * @param key
     * @param json
     * @return
     */
    public long rpush(String key, String str){
    	ListOperations operation = redisTemplate.opsForList();
    	return operation.rightPush(key, str);
    }
    
    /**
     * 获取指定下标队列的对象
     * @param key
     * @param num
     * @return
     */
    public String lindex(String key, long num){
    	ListOperations operation = redisTemplate.opsForList();
    	return (String) operation.index(key, num);
    }
    
    /**
     * 移出队列中的第一个头部对象
     * @param key
     * @return
     */
    public String lpop(String key){
    	ListOperations operation = redisTemplate.opsForList();
    	return (String) operation.leftPop(key);
    }
    
    /**
     * 移出队列中的最后一个对象
     * @param key
     * @return
     */
    public String rpop(String key){
    	ListOperations operation = redisTemplate.opsForList();
    	return (String) operation.rightPop(key);
    }
    
    /**
     * 查看队列长度
     * @param key
     * @return
     */
    public long llen(String key){
    	ListOperations operation = redisTemplate.opsForList();
    	return operation.size(key);
    }
    
    /**
     * 计数器-自增
     * @param key
     * @return
     */
    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        
        return increment;
    }
    
    /**
     * 计数器-自减
     * @param key
     * @param liveTime
     * @return
     */
    public Long decr(String key, long liveTime) {
    	RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
    	Long increment = entityIdCounter.getAndDecrement();
    	if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
    	return increment;
    }
    
    /**
     * 获取计数器
     * @param key
     * @return
     */
    public Long getConter(String key) {
    	RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
    	return entityIdCounter.get();
    }
    
    /**
     * 批量更新版本号
     * @param key
     * @return
     */
    public void updateVersion(String ...keys) {
		for(String key : keys) {
			updateVersion(key);
    	}
    }
    
    /**
     * 更新版本号
     * @param key
     */
    public void updateVersion(String key) {
    	//获取当前版本号
		Object version = redisTemplate.opsForValue().get(key);
		if(version == null) {
			version = 1l;
		}else {
			version = Long.valueOf(version.toString()) + 1;
		}
		redisTemplate.opsForValue().set(key, version);
    }
    
    /**
     * 获得锁
     * @param lockId
     * @param millisecond 锁持续时间  毫秒
     * @return
     */
    public boolean getLock(String lockId, long millisecond) {
       Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock", millisecond, TimeUnit.MILLISECONDS);
       return success != null && success;
    }

    /**
     * 解锁
     * @param lockId
     */
    public void releaseLock(String lockId) {
       redisTemplate.delete(lockId);
    }
    
}