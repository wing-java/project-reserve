package com.example.longecological.controller.payreturn;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.longecological.service.payreturn.CikeReturnRechargeService;

/**
 * 刺客回调：用户余额充值
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/return/api/cike/recharge")
public class CikeReturnRechargeController {

	private static Logger LOGGER = LoggerFactory.getLogger(CikeReturnRechargeController.class);

	@Autowired
	CikeReturnRechargeService cikeReturnRechargeService;
	
	/**
     * 余额充值：金顺回调-服务器异步通知页面路径
     * @param response
     * @param returnPay
     * @param req
     * @throws IOException
     */
    @RequestMapping(value = "/returnPayAsynchronousRecharge", method = RequestMethod.POST)
    public void returnPayAsynchronousRecharge(@RequestBody Map<String, Object> map, HttpServletResponse response)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        LOGGER.info("****************************************余额充值：刺客的异步回调函数被调用******************************");
        cikeReturnRechargeService.cikeAsynchronousResultRecharge(map);
        response.getWriter().write("SUCCESS");
        response.getWriter().close();
    }
}
