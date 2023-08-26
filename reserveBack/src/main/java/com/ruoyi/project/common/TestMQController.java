package com.ruoyi.project.common;

import com.ruoyi.common.constant.RocketMqConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/api/test/goods")
public class TestMQController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/testMq")
    @ResponseBody
	public Object testMq(String text){
        if (StringUtils.isBlank(text)){
            text = "123";
        }
        rocketMQTemplate.syncSend(RocketMqConstants.test_topic, text);
        return null;
    }
}
