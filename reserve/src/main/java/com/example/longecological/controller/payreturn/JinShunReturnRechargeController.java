package com.example.longecological.controller.payreturn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.longecological.service.payreturn.JinShunReturnRechargeService;

/**
 * 金顺回调：用户余额充值
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/return/api/jinshun/recharge")
public class JinShunReturnRechargeController {

	private static Logger LOGGER = LoggerFactory.getLogger(JinShunReturnRechargeController.class);

	@Autowired
	JinShunReturnRechargeService jinShunReturnRechargeService;
	
	/**
     * 余额充值：金顺回调-服务器异步通知页面路径
     * @param response
     * @param returnPay
     * @param req
     * @throws IOException
     */
    @RequestMapping(value = "/returnPayAsynchronousRecharge", method = RequestMethod.POST)
    public void returnPayAsynchronousRecharge(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        LOGGER.info("****************************************余额充值：金顺的异步回调函数被调用******************************");
        jinShunReturnRechargeService.jinshunAsynchronousResultRecharge(request);
        response.getWriter().write("success");
        response.getWriter().close();
    }
}
