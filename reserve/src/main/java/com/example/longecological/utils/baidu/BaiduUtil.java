package com.example.longecological.utils.baidu;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;

public class BaiduUtil {

	public static void main(String[] args) {
        String path = "https://lhyhksysyz.api.bdymkt.com/bankcard3/check";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials("f6d525f5336b48df8d659d2b0e308a29", "dad415a2d4124c08969b41bb7fbd8cf0");
        request.addHeaderParameter("Content-Type", "application/x-www-form-urlencoded");
        request.addQueryParameter("name", "李小雨");
        request.addQueryParameter("idcard", "412722198706028437");
        request.addQueryParameter("bankcard", "6222621310024640647");
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

        try {
          ApiExplorerResponse response = client.sendRequest(request);
          // 返回结果格式为Json字符串
          System.out.println("-----------"+response.getResult());
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
