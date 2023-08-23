package com.example.longecological.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 直播获取url
 * @date 2018-06-26 17:09.
 */
public class LiveUtils {

	/**
	 * 推流域名
	 */
	public static String push_domain = "tl.tpshwl.com";
	
	/**
	 * 播放域名
	 */
	public static String play_domain = "bf.tpshwl.com";
	
	/**
	 * 鉴权KEY值
	 */
	public static String app_key = "cc1926552a60c1057f8cbe5be59f0841";
	
    /**
     * 输出Url
     * @param room_no 房间号
     * @param expirationTime 过期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getPushUrl(String room_no, String expirationTime){
        //Unix时间戳
        Long unixTime = getUnixTime(expirationTime);
        //时间戳16进制
        String txTime = Integer.toHexString(unixTime.intValue()).toUpperCase();

        //获取md5 txSecret
        String txSecret = getMd5(app_key+room_no+txTime);

        //视频推送url
        String pushUrl = "rtmp://"+push_domain+"/live/"+room_no+"?txSecret="+txSecret+"&txTime="+txTime;
        
        return pushUrl;

        //视频播放url rtmp
//        String playUrlRtmp = "rtmp://bf.tpshwl.com/live/"+streamId;

        //视频播放url flv
//        String playUrlFlv = "http://bf.tpshwl.com/live/"+streamId+".flv";

        //视频播放url hls
//        String playUrlHls = "http://bf.tpshwl.com/live/"+streamId+".m3u8";

//        System.out.println("pushUrl="+pushUrl);
//        System.out.println("playUrlRtmp="+playUrlRtmp);
//        System.out.println("playUrlFlv="+playUrlFlv);
//        System.out.println("playUrlHls="+playUrlHls);
    }
    /**
     * 获取unix时间戳
     * @return
     * @throws Exception
     */
    public static Long getUnixTime (String dateStr) {

        try {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            long epoch = df.parse(dateStr).getTime();

            return epoch/1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    /**
     * 获取md5字符串
     * @param str
     * @return
     */
    public static String getMd5(String str) {

        MessageDigest md5 = null;
        try {

            md5 = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
    /**
     * 获取播放地址
     * @param room_no
     * @return
     */
    public static String getPlayUrl(String room_no) {
    	//获取播放地址
    	String playUrlRtmp = "rtmp://"+play_domain+"/live/"+room_no;
    	return playUrlRtmp;
    }

}
