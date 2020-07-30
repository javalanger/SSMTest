package com.jykj.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("baidu")
public class BaiduNetDiscController {

    public static void main(String[] args) {
	String ak = "HVNaYWUIqfNsRGA7gy2yUuId";
	String sk = "ECGUkEA1qkW47IPHIHf1IDsWUGWTx3Ax";
	String data = BaiduNetDiscController.getAuth(ak, sk);
	System.out.println("data-->"+data);
    }
    
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "百度云应用的AK";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "百度云应用的SK";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key		HVNaYWUIqfNsRGA7gy2yUuId
     * @param sk - 百度云官网获取的 Securet Key	ECGUkEA1qkW47IPHIHf1IDsWUGWTx3Ax
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://openapi.baidu.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=authorization_code"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk+ "&redirect_uri=http://192.168.0.221:8080/baidunetdisc.html";
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            Integer message = connection.getResponseCode();
            System.out.println("message--->"+message);
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
    
    @RequestMapping("getMsg")
    @ResponseBody
    public String getMsg(String path) {
	String token = "error";
	try {
	    URL url = new URL(path);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoInput(true);
	    connection.setRequestMethod("GET");
	    InputStream inputStream = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	    StringBuffer sb = new StringBuffer();
	    String str = null;
	    while((str = br.readLine()) != null) {
		sb.append(str);
	    }
	    token = sb.toString();
	    System.out.println("sb-->"+token);
	    
	    br.close();
	    inputStream.close();
	    connection.disconnect();
	    return token;
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return token;
    }
    
    @RequestMapping("dosom")
    @ResponseBody
    public String dosom(String path,String data) {
	String token = "error";
	try {
	    URL url = new URL(path);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    connection.setUseCaches(false);
	    connection.setRequestMethod("POST");
	    
	    OutputStream outputStream = connection.getOutputStream();
	    outputStream.write(data.getBytes());
	    outputStream.flush();
	    outputStream.close();
	    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
	    	InputStream inputStream = connection.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		    StringBuffer sb = new StringBuffer();
		    String str = null;
		    while((str = br.readLine()) != null) {
			sb.append(str);
		    }
		    token = sb.toString();
		    System.out.println("sb-->"+token);
		    
		    br.close();
		    inputStream.close();
		    connection.disconnect();
		    return token;
	    }
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return token;
    }
}