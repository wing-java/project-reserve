package com.ruoyi.project.develop.common.domain;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public static final String SUCCESS_TAG = "success";

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";
    
    
    public static final String MSG_SUCCESS = "操作成功";
    
    public static final String MSG_ERROR = "操作失败";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS("0"),
        /** 警告 */
        WARN("301"),
        /** 错误 */
        ERROR("500"),
        /** 该账号已被冻结，暂不能登录 */
        FREEZE("101"),
    	/** 登录已失效，请重新登录 */
    	LOGINVALID("999");
    	
        public final String value;

        Type(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return this.value;
        }
    }

    
	public R() {
		put(CODE_TAG, Type.SUCCESS);
		put(MSG_TAG, MSG_SUCCESS);
	}

	public static R error() {
		return error(Type.WARN, MSG_ERROR);
	}

	public static R error(String msg) {
		return error(Type.ERROR, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put(CODE_TAG, code);
		r.put(MSG_TAG, msg);
		return r;
	}
	
	
	public static R error(Type type, String msg) {
		R r = new R();
		r.put(CODE_TAG, type.value);
		r.put(MSG_TAG, msg);
		return r;
	}
	
	
	public static R ok(String msg) {
		R r = new R();
		r.put(CODE_TAG, Type.SUCCESS.value);
		r.put(MSG_TAG, msg);
		return r;
	}
	
	
	public static R ok(String msg, Object data) {
		R r = new R();
		r.put(CODE_TAG, Type.SUCCESS.value);
		r.put(MSG_TAG, msg);
		r.put(DATA_TAG, data);
		return r;
	}

	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	
	
	public static void main(String[] args) {
		R r = R.error(Type.ERROR, "111111111111");
	}
}
