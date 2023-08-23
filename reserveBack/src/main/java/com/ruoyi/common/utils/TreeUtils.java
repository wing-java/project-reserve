package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 权限数据处理
 * 
 * @author ruoyi
 */
public class TreeUtils
{
    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<Menu> getChildPerms(List<Menu> list, int parentId)
    {
    	//返回菜单列表
        List<Menu> returnList = new ArrayList<Menu>();
        //循环处理菜单
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
        	//菜单对象
            Menu t = (Menu) iterator.next();
            // 如果当前菜单是等级菜单，即parentId=0，则递归查找所有子菜单（只处理顶级菜单，找所有顶级菜单的子菜单）
            if (t.getParentId() == parentId)
            {
            	//查找所有子节点（无限极），并设置每个父菜单的子菜单对象
                recursionFn(list, t);
                //设置好子菜单的节点对象加入集合返回
                returnList.add(t);
            }
        }
        return returnList;
    }

    
   /**
    * 递归查找顶级菜单的所有子菜单，并设置每个父菜单的子菜单对象
    * @param list：所有节点列表
    * @param t：需要找的父节点对象
    */
    private static void recursionFn(List<Menu> list, Menu t)
    {
        // 得到该父菜单的直属子节点列表
        List<Menu> childList = getChildList(list, t);
        //设置子菜单列表
        t.setChildren(childList);
        
        //再依次查找每一个子菜单的子节点
        for (Menu tChild : childList)
        {
        	// 判断是否有子节点，有子节点，则再查询该节点的子节点
            if (hasChild(list, tChild))
            {
            	//该节点对象
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext())
                {
                    Menu n = (Menu) it.next();
                    //递归查询
                    recursionFn(list, n);
                }
            }
        }
    }

   
    /**
     * 得到某一个节点的子节点列表（直属）
     * @param list：所有菜单列表
     * @param t：父菜单对象（某一个节点）
     * @return
     */
    private static List<Menu> getChildList(List<Menu> list, Menu t)
    {
    	//返回的子节点列表
        List<Menu> tlist = new ArrayList<Menu>();
        //循环遍历找父菜单的子节点列表
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
        	//查找中的列表中的某一个菜单对象
            Menu n = (Menu) it.next();
            //该菜单对象的父节点id=查找的父菜单的菜单id，即是父菜单的子节点
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
            	//添加进子节点列表
                tlist.add(n);
            }
        }
        //返回子节点列表
        return tlist;
    }
    

    List<Menu> returnList = new ArrayList<Menu>();

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Menu> getChildPerms(List<Menu> list, int typeId, String prefix)
    {
        if (list == null)
        {
            return null;
        }
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu node = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<Menu> list, Menu node, String p)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, node);
        // 判断是否有子节点
        if (hasChild(list, node))
        {
            returnList.add(node);
            Iterator<Menu> it = childList.iterator();
            while (it.hasNext())
            {
                Menu n = (Menu) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    
    /**
     * 判断是否有子节点
     * @param list：所有节点列表
     * @param t：查找的节点对象
     * @return
     */
    private static boolean hasChild(List<Menu> list, Menu t)
    {
    	//如果他的直属子节点列表大于0，则说明有子节点
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
