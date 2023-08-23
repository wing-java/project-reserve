package com.ruoyi.project.develop.product.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.product.domain.UserProductOrder;
import com.ruoyi.project.develop.product.service.UserProductOrderService;

/**
 * 订单信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userProductOrder")
public class UserProductOrderController extends BaseController
{
    private String prefix = "develop/userProductOrder";
	
	@Autowired
	private UserProductOrderService userProductOrderService;
	
	
	/**
	 * 跳转订单列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userProductOrder:view")
	@GetMapping()
	public String userProductOrder()
	{
	    return prefix + "/userProductOrder";
	}
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("develop:userProductOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userProductOrderService.getUserProductOrderList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 导出订单列表
	 */
	@Log(title = "订单信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userProductOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserProductOrder> list = userProductOrderService.selectUserProductOrderList(params);
        ExcelUtil<UserProductOrder> util = new ExcelUtil<UserProductOrder>(UserProductOrder.class);
        return util.exportExcel(list, "系统订单信息数据");
    }
	
	
    /**
     * 根据订单id查询订单信息
     * @param id
     * @return
     */
    @RequiresPermissions("develop:userProductOrder:list")
    @PostMapping("/getUserProductOrderById")
    @ResponseBody
    public Map<String, Object> getUserProductOrderById(String id)
    {
        return userProductOrderService.getUserProductOrderById(id);
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
    	Map<String, Object> userProductOrder = userProductOrderService.getUserProductOrderByProductOrderId(id);
		mmap.put("userProductOrder", userProductOrder);
		return prefix + "/detail";
    }
    
}
