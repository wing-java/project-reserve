package com.ruoyi.project.system.user.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.constant.BackUrlConstant;
import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.VerifyRecordService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private VerifyRecordService verifyRecordService;
    
    
    /**
     * 查看个人资料信息
     * @param mmap
     * @return
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        User user = getSysUser();
        mmap.put("user", user);//当前用户身份信息
        //根据用户ID查询用户所属角色组
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getUserId()));
        //根据用户ID查询用户所属岗位组
        mmap.put("postGroup", userService.selectUserPostGroup(user.getUserId()));
        //业务类型
        mmap.put("bus_type", VerifyConstant.BackModifyPass);
        return prefix + "/profile";
    }

    
    /**
     * 校验旧密码是否正确
     * @param password
     * @return
     */
    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
    	//当前身份信息
        User user = getSysUser();
        if (passwordService.matches(user, password))
        {
            return true;
        }
        return false;
    }

    
    /**
     * 跳转重置密码页面
     * @param mmap
     * @return
     */
    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap)
    {
    	//身份信息
        User user = getSysUser();
        mmap.put("user", userService.selectUserById(user.getUserId()));
        mmap.put("bus_type", VerifyConstant.BackModifyPass);
        return prefix + "/resetPwd";
    }

    
    /**
     * post请求重置用户密码
     * @param oldPassword：旧密码
     * @param newPassword：新密码
     * @return
     */
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public R resetPwd(String oldPassword, String newPassword, String smsCode)
    {
    	return userService.resetPwd(oldPassword,newPassword,smsCode);
    }
    
    
    /**
     * 跳转到身份信息认证页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/toIsAuth")
    public String toIsAuth(String id, String bus_type,String operParam, ModelMap mmap)
    {
		mmap.put("id", id);
		mmap.put("bus_type", bus_type);
		mmap.put("operParam", operParam);
        return prefix + "/isAuth";
    }
    
    
    /**
     * 身份认证
     * @param oldPassword
     * @param newPassword
     * @param smsCode
     * @return
     */
    @Log(title = "身份认证", businessType = BusinessType.OTHER)
    @GetMapping("/isAuth")
    public String isAuth(@RequestParam Map<String, Object> map, ModelMap mmap)
    {
    	//校验短信验证码
    	R checkResult = verifyRecordService.compare(ShiroUtils.getUserId().toString(), map.get("bus_type").toString(),VerifyConstant.MobileAccType, ShiroUtils.getSysUser().getPhonenumber(), map.get("sms_code").toString(), VerifyConstant.SystemBack);
    	System.out.println(checkResult);
    	//短信验证码校验不通过
		if(!Type.SUCCESS.value.equals(checkResult.get("code").toString())) {
			System.out.println("错误");
			mmap.addAttribute("error_msg", "身份信息认证失败，原因："+checkResult.get("msg").toString());
			System.out.println("跳转页面");
			return prefix + "/authError";
		}else {
			//否则通过，开始授权，并且跳转到相关操作页面
			User user = ShiroUtils.getSysUser();
			user.setAuth(true);
			ShiroUtils.setSysUser(user);
			mmap.addAttribute("id", map.get("id"));
			mmap.addAttribute("operParam", map.get("operParam"));
			return BackUrlConstant.BackUrlMap.get(map.get("bus_type").toString());
		}
    }
    
    /**
     * 跳转到身份信息认证页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/toIsAuth2")
    public String toIsAuth2(@RequestParam Map<String, Object> param, ModelMap mmap)
    {
		mmap.put("param", param);
        return prefix + "/isAuth2";
    }
    
    /**
     * 身份认证--重定向方式
     * @return
     */
    @Log(title = "身份认证", businessType = BusinessType.OTHER)
    @GetMapping("/isAuth2")
    public String isAuth2(@RequestParam Map<String, Object> map, ModelMap mmap)
    {
    	//校验短信验证码
    	R checkResult = verifyRecordService.compare(ShiroUtils.getUserId().toString(), map.get("bus_type").toString(),VerifyConstant.MobileAccType, ShiroUtils.getSysUser().getPhonenumber(), map.get("sms_code").toString(), VerifyConstant.SystemBack);
    	System.out.println(checkResult);
    	//短信验证码校验不通过
		if(!Type.SUCCESS.value.equals(checkResult.get("code").toString())) {
			System.out.println("错误");
			mmap.addAttribute("error_msg", "身份信息认证失败，原因："+checkResult.get("msg").toString());
			System.out.println("跳转页面");
			return prefix + "/authError";
		}else {
			//否则通过，开始授权，并且跳转到相关操作页面
			User user = ShiroUtils.getSysUser();
			user.setAuth(true);
			ShiroUtils.setSysUser(user);
			return BackUrlConstant.BackUrlMap.get(map.get("bus_type").toString())+"?"+StringUtil.formatUrlMap(map);
		}
    }
    
    
    /**
     * 跳转修改用户信息页面
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
    	//用户身份信息
        User user = getSysUser();
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/edit";
    }
    

    /**
     * 修改用户资料
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(User user)
    {
        User currentUser = getSysUser();//当前用户身份对象
        currentUser.setUserName(user.getUserName());//用户名
        currentUser.setEmail(user.getEmail());//邮箱
        currentUser.setPhonenumber(user.getPhonenumber());//手机号码
        currentUser.setSex(user.getSex());//性别
        if (userService.updateUserInfo(currentUser) > 0)
        {
        	//更新shiro对象信息
            setSysUser(userService.selectUserById(currentUser.getUserId()));
            return success();
        }
        return error();
    }
    
    
    /**
     * 跳转修改个人头像页面
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap)
    {
        User user = getSysUser();//身份信息
        //根据用户ID查询用户信息
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/avatar";
    }

    
    /**
     * 修改用户头像信息
     * @param file
     * @return
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file)
    {
        User currentUser = getSysUser();//用户身份信息
        try
        {
        	//文件对象不为空
            if (!file.isEmpty())
            {
            	//上传文件并得到返回路径
                String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath()+"back/", file);
                currentUser.setAvatar(avatar);//用户头像上传结果
                //修改用户信息
                if (userService.updateUserInfo(currentUser) > 0)
                {
                	//更新shiro身份信息
                    setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}
