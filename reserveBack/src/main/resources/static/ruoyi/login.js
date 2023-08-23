/*初始化执行方法*/
$(function() {
	validateKickout();
	//（1）校验表单
    validateRule();
    
    //（2）点击更换图形验证码
	$('.imgcode').click(function() {
		//Math.random()：系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double 值
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		//摄入值
		$(".imgcode").attr("src", url);
	});
});


//设置 validate 的默认值
$.validator.setDefaults({
	//submitHandler：通过验证后运行的函数,里面要加上表单提交的函数,否则表单不会提交
    submitHandler: function() {
    	//验证通过，登录
		login();
    }
});


//登录方法
function login() {
	//当我们发起ajax请求时,一般会弹出加载条,等到回调成功,关闭加载条.上面的js代码就是在发起ajax请求前,显示加载框内容;但是它是对我们js代码进行了一次封装;
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());//用户名
    var password = $.common.trim($("input[name='password']").val());//密码
    var validateCode = $("input[name='validateCode']").val();//图形验证码
    var rememberMe = $("input[name='rememberme']").is(':checked');//记住密码
    //调用ajax请求后台方法
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "validateCode" : validateCode,
            "rememberMe": rememberMe
        },
        success: function(r) {
        	//如果登录成功，访问index方法
            if (r.code == 0) {
                location.href = ctx + 'index';
            } else {
            	//否则
            	$.modal.closeLoading();//关闭加载条
            	$('.imgcode').click();//更换图形验证码
            	$(".code").val("");//验证码置空
            	$.modal.msg(r.msg);//提示错误消息
            }
        }
    });
}


//表单校验规则
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    //在键盘按下并释放及提交后验证提交表单
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}


function validateKickout() {
	if (getParam("kickout") == 1) {
		layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
			icon: 0,
		    title: "系统提示"
		},
		function(index) {
			//关闭弹窗
			layer.close(index);
	        if (top != self) {
	            top.location = self.location;
	        } else {
	            var url  =  location.search;
	            if (url) {
	                var oldUrl  = window.location.href;
	                var newUrl  = oldUrl.substring(0,  oldUrl.indexOf('?'));
	                self.location  = newUrl;
	            }
	        }
	    });
	}
}
	
function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}
