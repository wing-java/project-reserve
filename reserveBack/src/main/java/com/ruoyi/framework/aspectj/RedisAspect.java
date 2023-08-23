package com.ruoyi.framework.aspectj;

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

import com.ruoyi.framework.aspectj.lang.annotation.Redis;
import com.ruoyi.framework.aspectj.lang.annotation.Redis.CacheOperation;

/**
 * EnableRedisService 注解切面处理
 * @author: liujingzhong
 * @date: 2018年9月29日
 */
@Aspect
@Component
@SuppressWarnings("all")
public class RedisAspect {
	
	private static final Logger log = LoggerFactory.getLogger(RedisAspect.class);
	
    @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.Redis)")
    public void dealCacheServiceCut(){}
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "dealCacheServiceCut()")
    @SuppressWarnings("all")
    public Object dealCacheService(ProceedingJoinPoint point) throws Throwable{
    	Method method = getMethod(point);
    	// 获取注解对象
    	Redis redis = method.getAnnotation(Redis.class);
    	//所有参数
    	Object[] args = point.getArgs();
        String cacheKey = redis.keyPrefix()+parseKey(redis.fieldKey(), method, args);
        log.info("{} enable cache service,cacheKey:{}",point.getSignature(),cacheKey);
        CacheOperation cacheOperation = redis.cacheOperation();
        if(cacheOperation==CacheOperation.QUERY) {
        	return processQuery(point, redis, cacheKey);
        }
        if(cacheOperation==CacheOperation.UPDATE||cacheOperation==CacheOperation.DELETE) {
        	return processUpdateAndDelete(point, cacheKey);
        }
        return point.proceed();
    }

    /**
     * 查询处理
     */
	private Object processQuery(ProceedingJoinPoint point, Redis redis, String cacheKey)
			throws Throwable {
		if(redisTemplate.hasKey(cacheKey)) {
        	log.info("{} enable cache service,has cacheKey:{} , return",point.getSignature(),cacheKey);
			return redisTemplate.opsForValue().get(cacheKey);
		}else {
			Object result = null;
			try {
				return result = point.proceed();
			} finally {
				if(redis.expireTime()==0){
					redisTemplate.opsForValue().set(cacheKey, result);
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
	private Object processUpdateAndDelete(ProceedingJoinPoint point, String cacheKey)
			throws Throwable {
		//通常来讲,数据库update操作后,只需删除掉原来在缓存中的数据,下次查询时就会刷新
		try {
			return point.proceed();
		} finally {
			redisTemplate.delete(cacheKey);
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
        return parser.parseExpression(fieldKey).getValue(context,String.class);
    }
}