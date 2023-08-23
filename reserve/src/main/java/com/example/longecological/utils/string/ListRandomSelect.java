package com.example.longecological.utils.string;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;  
  
public class ListRandomSelect {  
	

    /**
     * 从list中随机抽取若干不重复元素
     * @param paramList：被抽取list
     * @param count：抽取元素的个数
     * @param newList：组成的新的list
     */
    public static void getRandomList(List paramList,List newList,int count){
        if(paramList.size()<count){
            return;
        }
        Random random=new Random();
        List<Integer> tempList=new ArrayList<Integer>();
        int temp=0;
        for(int i=0;i<count;i++){
            temp=random.nextInt(paramList.size());//将产生的随机数作为被抽list的索引
            if(!tempList.contains(temp)){
                tempList.add(temp);
                newList.add(paramList.get(temp));
                paramList.remove(temp);
            }
            else{
                i--;
            }   
        }
        return;
    }
    
    
    /**
     * 从一个集合里面根据key的value值获取对象
     * @param selectList
     * @param selectKey
     * @param selectValue
     * @return
     */
    public static Map<String, Object> getObjectFromListByValue(List<Map<String, Object>> selectList, String selectKey, String selectValue){
    	Map<String, Object> selectObject = null;
    	for(int i=0; i<selectList.size(); i++) {
    		if(selectValue.equals(selectList.get(i).get(selectKey).toString())) {
    			selectObject = selectList.get(i);
    			return selectObject;
    		}
    	}
    	return selectObject;
    }

}  