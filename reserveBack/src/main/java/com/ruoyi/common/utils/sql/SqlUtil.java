package com.ruoyi.common.utils.sql;

import com.ruoyi.common.utils.string.StringUtil;

/**
 * sql操作工具类
 * 
 * @author ruoyi
 */
public class SqlUtil
{
    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    
    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
    	//字符非空，并且不是有效的sql语句符号，说明sql非法，返回空
        if (StringUtil.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            return StringUtil.EMPTY;
        }
        //否则有效继续
        return value;
    }

    
    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
