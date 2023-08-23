package com.example.longecological.utils;

import java.util.HashMap;
import java.util.Map;


public class MqLevelUtil {

	MqLevelUtil() {
    }

    private static MqLevelUtil mqLevelUtil = null;
    private static Map<Integer,Integer> map = null;

    public synchronized static MqLevelUtil getInstance() {
        if (null == mqLevelUtil) {
        	mqLevelUtil = new MqLevelUtil();
        	if(map == null){
            	map = new HashMap<Integer,Integer>();
            	map.put(1,1);
            	map.put(5,2);
            	map.put(10,3);
            	map.put(30,4);
            	map.put(60,5);
            	map.put(120,6);
            	map.put(180,7);
            	map.put(240,8);
            	map.put(300,9);
            	map.put(360,10);
            	map.put(420,11);
            	map.put(480,12);
            	map.put(540,13);
            	map.put(600,14);
            	map.put(1200,15);
            	map.put(1800,16);
            	map.put(3600,17);
            	map.put(7200,18);
            }
        }
        return mqLevelUtil;
    }
    
    public Map<Integer,Integer> getLevelMap() {
        if(map == null){
        	map = new HashMap<Integer,Integer>();
        	map.put(1,1);
        	map.put(5,2);
        	map.put(10,3);
        	map.put(30,4);
        	map.put(60,5);
        	map.put(120,6);
        	map.put(180,7);
        	map.put(240,8);
        	map.put(300,9);
        	map.put(360,10);
        	map.put(420,11);
        	map.put(480,12);
        	map.put(540,13);
        	map.put(600,14);
        	map.put(1200,15);
        	map.put(1800,16);
        	map.put(3600,17);
        	map.put(7200,18);
        }
        return map;
    }
    
    public int getNearLevel(int second){
    	if(second < 5){
    		return 1;
    	}if(second < 10){
    		return 5;
    	}else if(second < 30){
    		return 10;
    	}else if(second < 60){
    		return 30;
    	}else if(second < 120){
    		return 60;
    	}else if(second < 180){
    		return 120;
    	}else if(second < 240){
    		return 180;
    	}else if(second < 300){
    		return 240;
    	}else if(second < 360){
    		return 300;
    	}else if(second < 420){
    		return 360;
    	}else if(second < 480){
    		return 420;
    	}else if(second < 540){
    		return 480;
    	}else if(second < 600){
    		return 540;
    	}else if(second < 1200){
    		return 600;
    	}else if(second < 1800){
    		return 1200;
    	}else if(second < 3600){
    		return 1800;
    	}else if(second < 7200){
    		return 3600;
    	}else{
    		return 7200;
    	}
    }
}
