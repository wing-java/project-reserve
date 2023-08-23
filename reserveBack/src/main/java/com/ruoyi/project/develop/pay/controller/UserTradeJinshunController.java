package com.ruoyi.project.develop.pay.controller;

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
import com.ruoyi.project.develop.pay.domain.UserTradeJinshun;
import com.ruoyi.project.develop.pay.service.UserTradeJinshunService;

/**
 * 线上支付宝充值操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userTradeJinshun")
public class UserTradeJinshunController extends BaseController
{
    private String prefix = "develop/userTradeJinshun";
	
	@Autowired
	private UserTradeJinshunService userTradeJinshunService;
	
	
	/**
	 * 跳转线上支付宝充值列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userTradeJinshun:view")
	@GetMapping()
	public String userTradeJinshun()
	{
	    return prefix + "/userTradeJinshun";
	}
	
	
	/**
	 * 查询线上支付宝充值列表
	 */
	@RequiresPermissions("develop:userTradeJinshun:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userTradeJinshunService.getUserTradeJinshunList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出线上支付宝充值列表
	 */
	@Log(title = "线上支付宝充值管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userTradeJinshun:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserTradeJinshun> list = userTradeJinshunService.selectUserTradeJinshunList(params);
        ExcelUtil<UserTradeJinshun> util = new ExcelUtil<UserTradeJinshun>(UserTradeJinshun.class);
        return util.exportExcel(list, "线上支付宝充值数据");
    }

	
	 /**
     * 跳转到详情页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
    	Map<String, Object> userTradeJinshun = userTradeJinshunService.getUserTradeJinshunById(id);
		mmap.put("userTradeJinshun", userTradeJinshun);
		return prefix + "/detail";
    }
	

}
