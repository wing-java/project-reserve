package com.ruoyi.project.system.user.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ruoyi.framework.web.controller.BaseController;

/**
 * 图片验证码（支持算术形式）
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 生成图形验证码
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            //String type = request.getParameter("type");//验证码类型
            String type = "math";
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            
            //验证码类型 math
            if ("math".equals(type))
            {
            	//生成图形验证码文本内容
                String capText = captchaProducerMath.createText();
                //截取图形验证码的内容（@之前的）
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                //截取图形验证码的答案（@之后的）
                code = capText.substring(capText.lastIndexOf("@") + 1);
                //生成验证码图片
                bi = captchaProducerMath.createImage(capStr);
            }
            
            //验证码类型 char
            else if ("char".equals(type))
            {
            	//生成图形验证码文本内容
                capStr = code = captchaProducer.createText();
                //生成验证码图片
                bi = captchaProducer.createImage(capStr);
            }
            
            //存储图形验证码session值
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            
            //将图形验证码以io流的形式输出
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //关闭流操作
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}