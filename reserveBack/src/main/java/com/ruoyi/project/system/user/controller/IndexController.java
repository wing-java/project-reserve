package com.ruoyi.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    
    /**
     * 登录成功之后访问系统主页
     * @param mmap
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
    	// 根据用户id取出菜单
        User user = getSysUser();
        if(user==null) {
        	return "redirect:/login"; 
        }
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        
        mmap.put("menus", menus);//菜单信息
        mmap.put("user", user);//身份信息
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());//版权年份
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());//是否开启实例演示
        return "index";
    }
    
    
    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    
    /**
     * 主页main系统介绍
     * @param mmap
     * @return
     */
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());//版本
//        return "main";
        return "summary/index";
    }
}
