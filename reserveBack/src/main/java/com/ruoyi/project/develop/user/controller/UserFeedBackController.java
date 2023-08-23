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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserFeedBack;
import com.ruoyi.project.develop.user.service.UserFeedBackService;

/**
 * 用户意见反馈操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userFeedBack")
public class UserFeedBackController extends BaseController
{
    private String prefix = "develop/userFeedBack";
	
	@Autowired
	private UserFeedBackService userFeedBackService;
	
	
	/**
	 * 跳转用户意见反馈列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userFeedBack:view")
	@GetMapping()
	public String userFeedBack()
	{
	    return prefix + "/userFeedBack";
	}
	
	
	/**
	 * 查询用户意见反馈列表
	 */
	@RequiresPermissions("develop:userFeedBack:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userFeedBackService.getUserFeedBackList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户套餐列表
	 */
	@Log(title = "用户意见反馈管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userFeedBack:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserFeedBack> list = userFeedBackService.selectUserFeedBackList(params);
        ExcelUtil<UserFeedBack> util = new ExcelUtil<UserFeedBack>(UserFeedBack.class);
        return util.exportExcel(list, "用户意见反馈数据");
    }
	
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("develop:userFeedBack:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userFeedBack", userFeedBackService.getUserFeedBackById(id));
        return prefix + "/detail";
    }
    
    
    /**
	 * 跳转编辑系统回复页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("userFeedBack", userFeedBackService.getUserFeedBackById(id));
		return prefix + "/edit";
    }
	
	
	/**
     * 修改保存参数
     * @param user
     * @return
     */
    @RequiresPermissions("develop:userFeedBack:edit")
    @Log(title = "系统参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return userFeedBackService.editUserFeedBack(params);
    }

}
