package com.example.longecological.annotations.impl;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheVersionService;
import com.example.longecological.annotations.EnableCacheVersionService.CacheVersionOperation;

/**
 * EnableRedisService 注解切面处理
 * @author: liujingzhong
 * @date: 2018年9月29日
 */
@Aspect
@Component
@SuppressWarnings("all")
public class CacheVersionServiceAspect {
	
	private static final Logger log = LoggerFactory.getLogger(CacheVersionServiceAspect.class);
	
	@Pointcut("@annotation(com.example.longecological.annotations.EnableCacheVersionService)")
    public void dealCacheVersionServiceCut(){}
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "dealCacheVersionServiceCut()")
    @SuppressWarnings("all")
    public Object dealCacheVersionServiceCut(ProceedingJoinPoint point) throws Throwable{
    	Method method = getMethod(point);
    	// 获取注解对象
    	EnableCacheVersionService redis = method.getAnnotation(EnableCacheVersionService.class);
    	//所有参数
    	Object[] args = point.getArgs();
    	//获取缓存KEY
        String cacheKey = redis.keyPrefix()+parseKey(redis.fieldKey(), method, args);
        //获取缓存版本号
        String cacheVersionKey = redis.versionKey()+parseVersionKey(redis.fieldVersionKey(), method, args);
        log.info("{} enable cache service,cacheKey:{}",point.getSignature(),cacheKey);
        CacheVersionOperation cacheVersionOperation = redis.cacheVersionOperation();
        if(cacheVersionOperation==CacheVersionOperation.QUERY) {
        	return processQuery(point, redis, cacheKey, cacheVersionKey);
        }
        if(cacheVersionOperation==CacheVersionOperation.UPDATE||cacheVersionOperation==CacheVersionOperation.DELETE) {
        	return processUpdateAndDelete(point, cacheKey, cacheVersionKey);
        }
        return point.proceed();
    }

    /**
     * 查询处理
     */
	private Object processQuery(ProceedingJoinPoint point, EnableCacheVersionService redis, String cacheKey, String cacheVersionKey)
			throws Throwable {
		//查询当前版本号
		Object version = redisTemplate.opsForValue().get(cacheVersionKey);
		if(version == null) {
			cacheKey += "_0";
			redisTemplate.opsForValue().set(cacheVersionKey, 0);
		}else {
			cacheKey += "_" + String.valueOf(version);
		}
		//查询当前版本缓存
		if(redisTemplate.hasKey(cacheKey)) {
        	log.info("{} enable cache service,has cacheKey:{} , return",point.getSignature(),cacheKey);
			return redisTemplate.opsForValue().get(cacheKey);
		}else {
			Object result = null;
			try {
				return result = point.proceed();
			} finally {
				if(redis.expireTime()==0){
					redisTemplate.opsForValue().set(cacheKey, result, 86400);
				}else{
					redisTemplate.opsForValue().
						set(cacheKey, result, redis.expireTime(), redis.timeUnit());
				}
				
				log.info("after {} proceed,save result to cache,redisKey:{},save content:{}",point.getSignature(),cacheKey,result);
			}
		}
	}
	
	/**
	 *删除和修改处理
	 */
	private Object processUpdateAndDelete(ProceedingJoinPoint point, String cacheKey, String cacheVersionKey)
			throws Throwable {
		//通常来讲,数据库update操作后,只需删除掉原来在缓存中的数据,下次查询时就会刷新
		try {
			return point.proceed();
		} finally {
			//版本号增加1
			Long version = (Long) redisTemplate.opsForValue().get(cacheVersionKey);
			if(version == null) {
				version = 1l;
			}else {
				version += 1;
			}
			redisTemplate.opsForValue().set(cacheVersionKey, version);
		}
	}
    
    
    private Method getMethod(JoinPoint joinPoint) throws Exception {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        return method;
    }
    
    
    
    /**
     * 获取redis的key
     */
    private String parseKey(String fieldKey,Method method,Object [] args){
    	if("".equals(fieldKey)) return "";
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =   
            new LocalVariableTableParameterNameDiscoverer();  
        String [] paraNameArr=u.getParameterNames(method);
        
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser(); 
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for(int i=0;i<paraNameArr.length;i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        String suffix_key = "";
    	String[] fieldKeys = fieldKey.split(";");
    	for(String key : fieldKeys) {
    		suffix_key += "_" + parser.parseExpression(key).getValue(context,String.class);
    	}
        return suffix_key.substring(1);
    }
    
    /**
     * 获取redis的key
     */
    private String parseVersionKey(String fieldVersionKey,Method method,Object [] args){
    	if("".equals(fieldVersionKey)) return "";
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =   
            new LocalVariableTableParameterNameDiscoverer();  
        String [] paraNameArr=u.getParameterNames(method);
        
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser(); 
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for(int i=0;i<paraNameArr.length;i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        String suffix_key = "";
    	String[] fieldVersionKeys = fieldVersionKey.split(";");
    	for(String key : fieldVersionKeys) {
    		suffix_key += "_" + parser.parseExpression(key).getValue(context,String.class);
    	}
        return suffix_key.substring(1);
    }
}