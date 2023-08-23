package com.example.longecological.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableCacheVersionService {

	 /**
     * key前缀 
     */
	String keyPrefix();
    /**
     * key主体，spel表示，例：#id（取形参中id的值）
     */
    String fieldKey() default "";
    /**
     * 过期时间 (默认不设置)
     */
    int expireTime() default 86400;
    /**
     * 版本号前缀
     */
    String versionKey();
    /**
     * 版本号附带后缀
     */
    String fieldVersionKey() default "";
    
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    
    CacheVersionOperation cacheVersionOperation();
    
    /**
    * 缓存操作类型
    */
    enum CacheVersionOperation {
        QUERY, // 查询
        UPDATE, // 修改
        DELETE;  // 删除
    }
}
