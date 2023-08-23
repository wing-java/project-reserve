package com.ruoyi.project.system.user.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.system.post.service.IPostService;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    
    /**
     * 跳转用户列表页面
     * @return
     */
    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    
    /**
     * 查询用户列表，返回表格分页数据对象
     * @param user
     * @return
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询用户列表
        List<User> list = userService.selectUserList(user);
        //处理响应请求分页数据
        return getDataTable(list);
    }
    

    /**
     * 用户信息导出
     * @param user
     * @return
     */
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user)
    {
    	//不分页查询所有用户列表
        List<User> list = userService.selectUserList(user);
        //Excel相关处理对象
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        //对list数据源将其里面的数据导入到excel表单
        return util.exportExcel(list, "用户数据");
    }
    

    /**
     * 导入用户信息
     * @param file：用户数据文件
     * @param updateSupport：是否更新支持，如果已存在，则进行更新数据
     * @return
     * @throws Exception
     */
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        //对excel表单默认第一个索引名转换成list
        List<User> userList = util.importExcel(file.getInputStream());
        //导入用户数据
        String message = userService.importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }
    

    /**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.importTemplateExcel("用户数据");
    }
    
    
    /**
     * 跳转到新增用户页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	//查询所有角色
        mmap.put("roles", roleService.selectRoleAll());
        //查询所有岗位
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    
    /**
     * 新增保存用户
     * @param user
     * @return
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        if (StringUtil.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.insertUser(user));
    }

    
    /**
     * 跳转修改用户页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
    	//查询用户信息
        mmap.put("user", userService.selectUserById(userId));
        //查询所有角色
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        //查询所有岗位
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

   
    /**
     * 修改保存用户
     * @param user
     * @return
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
        if (StringUtil.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.updateUser(user));
    }

    
    /**
     * 跳转到重置用户密码页面
     * @param userId
     * @param mmap
     * @return
     */
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+userId+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_ResetUserPwd;
    	}else {
    		mmap.put("id", userId);
            return prefix + "/resetPwd";
    	}
    }
    
    
    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id)
    {
        return userService.selectUserById(id);
    }

    
    /**
     * 重置用户密码
     * @param user
     * @return
     */
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public R resetPwd(User user)
    {
    	return userService.resetPwd(user);
    }
    
    
    /**
     * 删除用户
     * @param ids
     * @return
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(userService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    
    /**
     * 校验用户名
     * @param user
     * @return
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    
    /**
     * 校验手机号
     * @param user
     * @return
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user)
    {
        return userService.checkPhoneUnique(user);
    }

    
    /**
     * 校验邮箱
     * @param user
     * @return
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user)
    {
        return userService.checkEmailUnique(user);
    }

   
    /**
     * 用户状态修改
     * @param user
     * @return
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(User user)
    {
        return toAjax(userService.changeStatus(user));
    }
}