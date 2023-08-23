package com.ruoyi.project.develop.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.test.service.TestService;


/**
 * 用户信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/backTest")
public class BackTestController extends BaseController
{
    private String prefix = "develop/backTest";
	
    @Autowired
	private TestService testService;
	
	
	/**
	 * 选择系统日期
	 * @return
	 */
    @GetMapping("/toCeshi1")
    public String toCeshi1()
    {
		return prefix + "/ceshi1";
    }
	
	
	/**
	 * 修改系统日期
	 * @param params
	 * @return
	 */
	@PostMapping("/ceshi1")
    @ResponseBody
    public R ceshi1(@RequestParam Map<String, Object> params)
    {
//		String date = StringUtil.getMapValue(params, "date");
//		date = date.replaceAll("-", "").replaceAll("\\:", "").replaceAll(" ", "");
//		testService.test(date);
		
		
//		String date = StringUtil.getMapValue(params, "date");
//		System.out.println(date);
//		if("01".equals(StringUtil.getMapValue(params, "op_type"))) {
//			bonusBenefitDealService.dealPerformanceSummary(date);
//		}else if("02".equals(StringUtil.getMapValue(params, "op_type"))) {
//			bonusBenefitDealService.dealCitySpokespersonBonus(date);
//		}else if("03".equals(StringUtil.getMapValue(params, "op_type"))) {
//			bonusBenefitDealService.dealCountrySpokespersonBonus(date);
//		}else if("04".equals(StringUtil.getMapValue(params, "op_type"))) {
//			bonusBenefitDealService.dealCliqueBonus(date);
//		}else if("05".equals(StringUtil.getMapValue(params, "op_type"))) {
//			bonusBenefitDealService.dealCliqueWeightBonus(date);
//		}
        return R.ok("操作成功");
    }
    
    
    /**
     * 查询支付宝支付结果
     * @param params
     * @return
     */
//    @PostMapping("/queryAliOrder")
//    @ResponseBody
//    public R queryAliOrder(@RequestParam Map<String, Object> params)
//    {
//    	WeChatPayUtil.orderQuery(params.get("out_trade_no").toString());
//        return R.ok("操作成功");
//    }
	
	public static void main(String[] args) {
		
		String date = "2023-07-04 17:41:30";
		date = date.replaceAll("-", "").replaceAll("\\:", "").replaceAll(" ", "");
		System.out.println(date);
	}
}
