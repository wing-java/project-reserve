package com.example.longecological.config.delayed;
 
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
 
/**
 * @author LiJing
 * @ClassName: ItemDelayed
 * @Description: 数据延迟实现实例 用以包装具体的实例转型
 * @date 2019/9/16 15:53
 */
 
@Setter
@Getter
public class ItemDelayed<T> implements Delayed {
 
    /**默认延迟30分钟*/
    private final static long DELAY = 30 * 60 * 1000L;
    /**数据id*/
    private Long dataId;
    /**开始时间*/
    private long startTime;
    /**到期时间*/
    private long expire;
    /**创建时间*/
    private Date now;
    /**泛型data*/
    private T data;
    /**操作类型*/
    private String oper_type;
    
    /**
     * 延迟队列设置延迟的时间
     * @param dataId
     * @param startTime
     * @param secondsDelay
     */
    public ItemDelayed(Long dataId, long startTime, long secondsDelay, String oper_type) {
        super();
        this.dataId = dataId;
        this.startTime = startTime;
        this.oper_type = oper_type;
        this.expire = startTime + (secondsDelay * 1000);
        this.now = new Date();
    }
 
    /**
     * 延迟队列指定默认延迟时间
     * @param dataId
     * @param startTime
     */
    public ItemDelayed(Long dataId, long startTime) {
        super();
        this.dataId = dataId;
        this.startTime = startTime;
        this.oper_type = oper_type;
        this.expire = startTime + DELAY;
        this.now = new Date();
    }
 
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
 
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
    
}
