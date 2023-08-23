package com.example.longecological.controller.payreturn;

import com.example.longecological.service.payreturn.DementedGodReturnRechargeService;
import com.example.longecological.utils.string.RequestMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 狂神回调：用户充值
 */
@Controller
@RequestMapping("/return/api/dementedGod/recharge")
public class DementedGodReturnRechargeController {
    private static Logger LOGGER = LoggerFactory.getLogger(DementedGodReturnRechargeController.class);
    @Autowired
    private DementedGodReturnRechargeService dementedGodReturnRechargeService;

    @RequestMapping(value = "/returnPayAsynchronousRecharge", method = RequestMethod.POST)
    public void returnPayAsynchronousRecharge(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        LOGGER.info("****************************************余额充值：狂神的异步回调函数被调用******************************");
        Map<String, Object> paramMap = RequestMap.getParameterMap(request);
        dementedGodReturnRechargeService.dementedGodAsynchronousResultRecharge(paramMap);
        response.getWriter().write("SUCCESS");
        response.getWriter().close();
    }
}
