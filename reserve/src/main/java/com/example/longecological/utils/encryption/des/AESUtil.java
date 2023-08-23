package com.example.longecological.utils.encryption.des;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.utils.encryption.rsa.Base64;
import com.example.longecological.utils.encryption.rsa.RSAUtilApp;



public class AESUtil {
	
	//密钥 (需要前端和后端保持一致)
    public static final String KEY = "5b9c2ed3e19c40e6";  

    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
  
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
  
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return Base64.encode(bytes);  
    }  
  
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
    public static byte[] base64Decode(String base64Code) throws Exception{  
        return StringUtils.isEmpty(base64Code) ? null : Base64.decode(base64Code);  
    }  
  
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  
  
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
  
  
    /** 
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
  
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
        return new String(decryptBytes);  
    }  
  
    /** 
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密后的string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    }  
    
    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {  
    	JSONObject param = new JSONObject();
    	//token登录
//    	param.put("device_no", "235447dd105ef355ea098151157ced054");
//    	param.put("device_type", "samsung");
//    	param.put("login_password", "");
//    	param.put("login_type", "token");
//    	param.put("token", "632|XX8S4NEUP7DBNPFR42DRELTYYKZ9KUYP");
//    	param.put("version_no", "10");
    	
    	//用户注册
//    	param.put("user_tel", "13355555555");
//    	param.put("invite_code", "13222222222");
//    	param.put("login_password", "123456");
//    	param.put("pay_password", "123456");
//    	param.put("sms_code", "999999");
    	
    	//用户找回密码
//    	param.put("login_password", "123456");
//    	param.put("user_tel", "13971579437");
//    	param.put("sms_code", "999999");
    	
    	//用户修改交易密码
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
//    	param.put("pay_password", "123456");
//    	param.put("sms_code", "999999");
    	
    	//用户修改登录密码
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
//    	param.put("login_password", "123456");
//    	param.put("sms_code", "999999");
    	
    	//用户头像修改
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
//    	param.put("head_photo", "defaultAvatar1.png");
    	
    	//用户昵称修改
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
//    	param.put("nick_name", "111111");
    	
    	//获取七牛云
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
    	
    	//查询系统最新版本
//    	param.put("device_type", "android");
    	
    	//添加意见反馈接口
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
//    	param.put("feedback_title", "路线问题");
//    	param.put("feedback_content", "路线地点有误，规定时间无法赶到指定地点");
//    	param.put("feedback_img", "defaultAvatar.png");
//    	param.put("contact_way", "13345677654");
    	
    	//查询意见反馈列表
//    	param.put("token", "633|V95U3KBO8WNS8X53G42QVMTLU8SVKQPI");
    	
    	//查询APP图片列表
//    	param.put("token", "633|SZV3IR72P08BKQBJVQOG5EACGLW6QG87");
//        param.put("img_type", "01");
    	
    	//用户账号登录
//    	param.put("login_type", "account");
//    	param.put("login_password", "123456");
//    	param.put("user_tel", "13222222222");
    	
    	//用户修改手机号第一步
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("sms_code", "999999");
    	
    	//用户修改手机号第二步
//    	param.put("token", "");
//    	param.put("user_tel", "13222222222");
//    	param.put("valid_flag", "20200604201225385110485");
//    	param.put("sms_code", "999999");
    	
    	//查询路线列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("type", "01");
//    	param.put("seat", 0);
    	
    	//查询路线详情
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("trip_id", "10");
    	
    	//查询旅游批次
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("trip_id", "10");
    	
    	//查询出行人列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
    	
    	//添加出行人信息
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("name", "李四");
//    	param.put("id_card", "421087199505064877");
    	
    	//删除出行人信息
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("linkman_id", "1");
    	
    	//根据旅游路线查询用户旅游卡
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("trip_id", "1");
//    	param.put("last_id", "0");
    	
    	//查询推荐类型列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
    	
    	//查询推荐城市列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("recommend_id", "1");
//    	param.put("seat", "0");
    	
    	//提交定制行程订单
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("start_address", "深圳");
//    	param.put("end_address", "贵州");
//    	param.put("start_date", "20200605");
//    	param.put("end_date", "20200610");
//    	param.put("budget", "100");
//    	param.put("serve", "专车接送");
//    	param.put("trip_num1", 1);
//    	param.put("trip_num2", 1);
//    	param.put("trip_num3", 1);
//    	param.put("trip_num4", 1);
//    	param.put("trip_num5", 1);
//    	param.put("house_num1", 1);
//    	param.put("house_num2", 1);
//    	param.put("house_num3", 1);
//    	param.put("house_num4", 1);
//    	param.put("name", "张三");
//    	param.put("tel", "13623152565");
//    	param.put("email", "12654@qq.com");
    	
    	//查询路线收费标准
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("trip_id", "1");
    	
    	//提交创建行程订单
//    	param.put("token", "634|E855OB2W9Y6I45BA9BC9RNOXDRFEGQTD");
//    	param.put("name", "张三");
//    	param.put("tel", "13545677654");
//    	param.put("pay_type", "03");
//    	param.put("trip_id", "1");
//    	param.put("trip_batch_id", "17");
//    	List<Object> crew_list = new ArrayList<Object>();
//    	JSONObject crew1 = new JSONObject();
//    	crew1.put("height_type", "0");
//    	crew1.put("name", "张三");
//    	crew1.put("id_card", "421087199306052145");
//    	crew1.put("card_no", "NO1000100");
//    	JSONObject crew2 = new JSONObject();
//    	crew2.put("height_type", "1");
//    	crew2.put("name", "李四");
//    	crew_list.add(crew1);
//    	crew_list.add(crew2);
//    	param.put("crew_list", crew_list);
//    	param.put("pay_password", "123456");
//    	param.put("money", "600.00");
    	
    	//查询我的行程列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("last_id", 0);
    	
    	//查询出行信息列表
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("last_id", 0);
    	
    	//查询行程订单详情
//    	param.put("token", "633|WAQNDLKB7HTJY3XZR6DWN7HS7MQJEFNF");
//    	param.put("trip_order_id", "4");
    	
    	//查询旅游线路图
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("trip_route_id", "1");
//    	param.put("start_date", "20200605");
//    	param.put("end_date", "20200610");
    	
    	//查询定制行程列表
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("last_id", "0");
    	
    	//查询定制行程详情
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("order_id", "1");
    	
    	//取消定制行程
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("order_id", "1");
    	
    	//删除定制行程
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("order_id", "1");
    	
    	//删除旅游订单
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("order_id", "4");
    	
    	//发布攻略
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("title", "超级震撼，仰光的佛像");
//    	param.put("content", "仰光位于缅甸，是缅甸最大的城市，也是仰光省的首府，原为缅甸的首都，面积为312万平方英里，位于仰光河河岸，伊洛瓦底江三角洲。仰光是一座具有热带风光的美丽的海滨城市。城区三面环水，东面是勃固河，南面是仰光河，西有伊洛瓦底江入海汉河之一的莱河。从濒临仰光河的南部，沿东西两河之间向北扩展，是繁华的商业区，一条条笔直宽敞的大街上，花圃里鲜花盛开，人行道上绿树成荫。市内的北边有苗雅湖，南边有干基道湖，湖水清澈，波光潋滟，宛如两颗熠熠生辉的绿宝石。　\r\n" + 
//    			"　　仰光看起来像一个巨大的公园，到处是植物，花草和佛塔。裸露左肩、穿着红色袈裟的僧人赤脚在街上飘着。仰光有殖民地时代留下的英国式建筑的暗红色屋顶，印度教寺庙镶嵌着各种神灵雕像覆盖着青苔的顶、现代文明发明的四方盒子的顶、佛塔、教堂、鸽子在天空嬉戏。仰光大金塔是仰光最早的著名建筑，也是世界佛教的一大圣地。缅甸人称大金塔为“瑞大光塔”，在缅甸语里，“瑞”是“金”的意思，“大光”是仰光的古称。　\r\n" + 
//    			"　　仰光以北约11公里处，屹立着造型别致的世界和平塔。这座塔建于1952年，是缅甸最新的宝塔。塔身中空，有五个入口，每个入口处都有一尊巨大的佛祖塑像。仰光东面的群山丛中，有一座别有趣味的剑堤幽佛塔。这座高4.6米的佛塔，不是直接筑于地面或者山顶上，而是耸峙于一个活动中的巨大岩石上。　　\r\n" + 
//    			"　来到仰光，一览它的迷人风采。　　　");
//    	param.put("link", "card1.png,card2.png,card3.png");
//    	param.put("link_content", "0|688x366|png,0|688x366|png,0|688x366|png");
//    	param.put("address", "深圳");
    	
    	//查询最火排序列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//查询最新排序列表
//    	param.put("token", "633|IA29Q4BTCXASDF0VJMR0RX3C9980MWXA");
//    	param.put("last_id", "0");
    	
    	//发布评论
//    	param.put("token", "635|BEI4BUO2A3L3W22HIUE48JYVK4DO9GN0");
//    	param.put("strategy_id", "7");
//    	param.put("content", "攻略发得不错");
//    	param.put("comment_id", "");
//    	param.put("comment_user_id", "");
//    	param.put("comment_head_photo", "");
//    	param.put("comment_nick_name", "");
    	
    	//查询评论列表
//    	param.put("token", "636|4GD7VOY3R8OFC2KMDISCFK9UY4W0R8WR");
//    	param.put("strategy_id", "7");
//    	param.put("order_by", "desc");
//    	param.put("last_id", "0");
    	
    	//删除评论
//    	param.put("token", "636|HCD0LDDXM3LHTH55SR4T5996RM8NP95G");
//    	param.put("order_id", "9");
    	
    	//点赞
//    	param.put("token", "636|HCD0LDDXM3LHTH55SR4T5996RM8NP95G");
//    	param.put("strategy_id", "5");
    	
    	//查询我的旅游卡类型列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//统计当前旅游卡数量
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
    	
    	//查询用户旅游卡列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
//    	param.put("type", "01");
//    	param.put("last_id", "0");
    	
    	//查询旅游卡可使用路线列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
//    	param.put("seat", "0");
    	
    	//查询旅游卡单价
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
    	
    	//购买旅游卡
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
//    	param.put("pay_type", "03");
//    	param.put("pay_password", "123456");
//    	param.put("card_num", "1");
//    	param.put("pay_money", "1180.00");
    	
    	//查询可转出数量
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
    	
    	//查询可转出旅游卡列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
//    	param.put("card_num", "1");
//    	param.put("last_id", "0");
    	
    	//互转旅游卡
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("card_id", "1");
//    	param.put("card_num", "1");
//    	param.put("id_list", "29");
//    	param.put("in_user_tel", "13333333333");
//    	param.put("pay_password", "123456");
    	
    	//查询购买记录列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//查询购买详情
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("order_id", "4");
    	
    	//查询互转列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//查询互转详情
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("order_id", "2");
    	
    	//查询银行名称列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
    	
    	//添加收款账号
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("account", "13333333333");
//    	param.put("account_name", "张三");
//    	param.put("account_type", "01");
//    	param.put("account_img", "defaultAvatar.png");
//    	param.put("sms_code", "999999");
    	
    	//查询收款账号列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("account_type", "01");
    	
    	//删除收款账户
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("account_id", "7");
    	
    	//移入收藏夹
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//        param.put("strategy_id", "12");
    	
    	//查询收藏列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//批量删除收藏夹
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("strategy_id_list", "12");
    	
    	//删除攻略
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("id_list", "8");
    	
    	//查询我的攻略列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("status", "00");
//    	param.put("last_id", "0");
    	
    	//用户升级申请
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("new_grade", "2");
    	
    	//查询任务进度条
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
    	
    	//查询直推用户列表
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//提现
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("account_type", "01");
//    	param.put("account_id", "7");
//    	param.put("cash_money", "200");
//    	param.put("pay_password", "123456");
    	
    	//查询提现记录
//    	param.put("token", "633|6BUOB3PTBMP0BLAF2HFALB7ZG0DWWX7B");
//    	param.put("last_id", "0");
    	
    	//查询公告详情
//    	param.put("token", "633|NM3ZCHV1W8QUO3YBFOOFUE6BLDFYKJDF");
//        param.put("last_id", "0");
    	
    	//查询攻略详情
//    	param.put("token", "633|NM3ZCHV1W8QUO3YBFOOFUE6BLDFYKJDF");
//    	param.put("strategy_id", "20");
    	
    	//查询订单支付列表
//    	param.put("token", "637|L92WW16GCE2EF2WUDK5UYSR7PVSXE8CV");
//    	param.put("type", "01");
//    	param.put("last_id", "0");
    	
    	//取消支付
//    	param.put("token", "637|L92WW16GCE2EF2WUDK5UYSR7PVSXE8CV");
//    	param.put("order_id", "85");
    	
    	//继续支付
//    	param.put("token", "637|L92WW16GCE2EF2WUDK5UYSR7PVSXE8CV");
//    	param.put("order_id", "83");
//    	param.put("pay_type", "03");
//    	param.put("pay_password", MD5Utils.MD5Encode("123456"));
    	
    	param.put("token", "638|9GTJKZENVS8222XH5F2OQVXD27HAEV3H");
//    	param.put("pay_money", "99");
//    	param.put("pay_type", "01");
    	
    	
    	String key = "ajiyfew76543ytre";
        String aesEncrypt = aesEncrypt(param.toJSONString(),key);
        System.out.println("AES加密密文："+aesEncrypt);
        String aesEncrypt2 = Base64.encode(RSAUtilApp.encryptByPublicKey(key.getBytes(),SysSecurityKeyConstant.publicKey_app));
        System.out.println("RSA加密:"+aesEncrypt2);
        System.out.println("参数："+param.toJSONString());
        
    } 

}
