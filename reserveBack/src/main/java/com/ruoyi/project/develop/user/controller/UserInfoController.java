package com.ruoyi.project.develop.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.benefit.domain.Benefit;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.VerifyRecordService;
import com.ruoyi.project.develop.user.domain.UserInfo;
import com.ruoyi.project.develop.user.service.UserInfoService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userInfo")
public class UserInfoController extends BaseController
{
    private String prefix = "develop/userInfo";
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private VerifyRecordService verifyRecordService;
	
	
	/**
	 * 跳转用户信息列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:view")
	@GetMapping()
	public String userInfo()
	{
	    return prefix + "/userInfo";
	}
	
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("develop:userInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userInfoService.getUserInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:list")
	@PostMapping("/summaryUserInfoList")
	@ResponseBody
	public Map<String, Object> summaryUserInfoList(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return userInfoService.summaryUserInfoList(params);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "用户信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserInfo> list = userInfoService.selectUserInfoList(params);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        return util.exportExcel(list, "用户信息数据");
    }
	
	
	
	/**
	 * 冻结解冻用户账号
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysFreeze/{id}")
	@RequiresPermissions("develop:userInfo:sysFreeze")
    public String sysFreeze(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_SysFreezeUserInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysFreeze";
    	}
    }
		
		
	/**
	 * 批量冻结和解冻用户账号
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:sysFreeze")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysFreeze")
    @ResponseBody
    public R sysFreeze(@RequestParam Map<String, Object> params)
    {
        return userInfoService.batchSysFreezeUser(params);
    }
	
	
	/**
	 * 跳转编辑用户信息页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/develop/userInfo/toIsAuth/?id="+id+"&bus_type="+VerifyConstant.AuthVerfiy_EditUserInfo;
    	}else {
    		//根据id查询用户详情
    		UserInfo userInfo = userInfoService.getUserInfoById(id);
    		mmap.put("userInfo", userInfo);
            return prefix + "/edit";
    	}
    }
	
	
	 /**
     * 跳转到身份信息认证页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/toIsAuth")
    public String toIsAuth(String id, String bus_type, ModelMap mmap)
    {
		mmap.put("id", id);
		mmap.put("bus_type", bus_type);
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
    	//短信验证码校验不通过
		if(!Type.SUCCESS.value.equals(checkResult.get("code").toString())) {
			mmap.addAttribute("error_msg", "身份信息认证失败，原因："+checkResult.get("msg").toString());
			return prefix + "/authError";
		}else {
			//否则通过，开始授权，并且跳转到相关操作页面
			User user = ShiroUtils.getSysUser();
			user.setAuth(true);
			ShiroUtils.setSysUser(user);
			return "redirect:/develop/userInfo/edit/"+map.get("id").toString();
		}
    }
    
    
    /**
     * 修改保存用户信息
     * @param user
     * @return
     */
    @RequiresPermissions("develop:userInfo:edit")
    @Log(title = "用户列表管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return userInfoService.editUserInfo(params);
    }
    
    
	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userInfo", userInfoService.getUserInfoById(id));
        return prefix + "/detail";
    }
	
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:detail")
	@PostMapping("/getUserInfoById")
	@ResponseBody
	public UserInfo getUserInfoById(String id)
	{
		return userInfoService.getUserInfoById(id);
	}
	
	
	/**
     * 跳转查询流水详情页面
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("develop:userInfo:userBenefitList")
    @GetMapping("/userBenefit/{id}")
    public String userBenefit(@PathVariable("id") String id, String purse_type, ModelMap mmap)
    {
        mmap.put("user_id", id);
        mmap.put("purse_type", purse_type);
        return prefix + "/userBenefit";
    }
    
    
    /**
     * 查询用户流水详情列表
     * @param params
     * @return
     */
	@RequiresPermissions("develop:userInfo:userBenefitList")
	@PostMapping("/userBenefitList")
	@ResponseBody
	public TableDataInfo userBenefitList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userInfoService.getBenefitRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流水详情信息
	 */
	@Log(title = "用户流水详情管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userInfo:userBenefitList")
    @PostMapping("/userBenefitExport")
    @ResponseBody
    public AjaxResult userBenefitExport(@RequestParam Map<String, Object> params)
    {
		List<Benefit> list = userInfoService.selectBenefitRecordBalanceList(params);
        ExcelUtil<Benefit> util = new ExcelUtil<Benefit>(Benefit.class);
        return util.exportExcel(list, "用户流水详情信息数据");
    }
	
	
	/**
	 * 跳转黑名单列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:viewFreeze")
	@GetMapping("/userInfoFreeze")
	public String userInfoFreeze()
	{
	    return prefix + "/userInfoFreeze";
	}
	
	
	/**
     * 查询父级团队列表
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("develop:userInfo:parentList")
    @GetMapping("/parentUser/{id}")
    public String parentUser(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("user_id", id);
        return prefix + "/parentUser";
    }
    
    
    /**
	 * 查询父级团队成员列表
	 */
	@RequiresPermissions("develop:userInfo:parentList")
	@PostMapping("/parentList")
	@ResponseBody
	public TableDataInfo parentList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userInfoService.getParentUserInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计父级成员数据信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:parentList")
	@PostMapping("/summaryParentUserInfoList")
	@ResponseBody
	public Map<String, Object> summaryParentUserInfoList(@RequestParam Map<String, Object> params)
	{
		return userInfoService.summaryParentUserInfoList(params);
	}
	
	
	/**
	 * 导出父级成员列表
	 */
	@Log(title = "用户父级信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userInfo:export")
    @PostMapping("/parentExport")
    @ResponseBody
    public AjaxResult parentExport(@RequestParam Map<String, Object> params)
    {
    	List<UserInfo> list = userInfoService.selectParentUserInfoList(params);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        return util.exportExcel(list, "用户父级成员信息数据");
    }
	
	
	/**
     * 查询子级团队列表
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("develop:userInfo:childrenList")
    @GetMapping("/childrenUser/{id}")
    public String childrenUser(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("user_id", id);
        return prefix + "/childrenUser";
    }
	
	
	/**
	 * 查询子级（伞下）团队成员列表
	 */
	@RequiresPermissions("develop:userInfo:childrenList")
	@PostMapping("/childrenList")
	@ResponseBody
	public TableDataInfo childrenList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userInfoService.getChildrenUserList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计子级成员数据信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userInfo:childrenList")
	@PostMapping("/summaryChildrenUserInfoList")
	@ResponseBody
	public Map<String, Object> summaryChildrenUserInfoList(@RequestParam Map<String, Object> params)
	{
		return userInfoService.summaryChildrenUserInfoList(params);
	}
	
	
	/**
	 * 导出子级成员列表
	 */
	@Log(title = "用户子级信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userInfo:export")
    @PostMapping("/childrenExport")
    @ResponseBody
    public AjaxResult childrenExport(@RequestParam Map<String, Object> params)
    {
    	List<UserInfo> list = userInfoService.selectChildrenUserInfoList(params);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        return util.exportExcel(list, "用户伞下成员信息数据");
    }
	
	/**
     * 跳转平移用户页面
     * @param oldPassword
     * @param newPassword
     * @param smsCode
     * @return
     */
    @Log(title = "平移用户", businessType = BusinessType.OTHER)
    @GetMapping("/toTransUser")
    public String toTransUser(@RequestParam Map<String, Object> params, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		params.put("bus_type", VerifyConstant.AuthVerfiy_TransUser);
    		return "redirect:/system/user/profile/toIsAuth2?"+StringUtil.formatUrlMap(params);
    	}else {
    		UserInfo userInfo = userInfoService.getUserInfoById(StringUtil.getMapValue(params, "id"));
    		mmap.put("userInfo", userInfo);
            return prefix + "/transUser";
    	}
    }
	
	 /**
	  * 平移用户
	  * @param params
	  * @return
	  */
	@RequiresPermissions("develop:userInfo:transUser")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/transUser")
    @ResponseBody
    public R transUser(@RequestParam Map<String, Object> params)
    {
        return userInfoService.transUser(params);
    }
	
	/**
     * 跳转新增业绩页面
     * @param oldPassword
     * @param newPassword
     * @param smsCode
     * @return
     */
    @Log(title = "新增业绩", businessType = BusinessType.OTHER)
    @GetMapping("/toAddPerformance")
    public String toAddPerformance(@RequestParam Map<String, Object> params, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		params.put("bus_type", VerifyConstant.AuthVerfiy_AddPerformance);
    		return "redirect:/system/user/profile/toIsAuth2?"+StringUtil.formatUrlMap(params);
    	}else {
    		UserInfo userInfo = userInfoService.getUserInfoById(StringUtil.getMapValue(params, "id"));
    		mmap.put("userInfo", userInfo);
            return prefix + "/addPerformance";
    	}
    }
	
	 /**
	  * 平移用户
	  * @param params
	  * @return
	  */
	@RequiresPermissions("develop:userInfo:addPerformance")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/addPerformance")
    @ResponseBody
    public R addPerformance(@RequestParam Map<String, Object> params)
    {
        return userInfoService.addPerformance(params);
    }
	
}
