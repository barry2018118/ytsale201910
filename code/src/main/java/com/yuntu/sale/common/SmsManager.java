package com.yuntu.sale.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.servlet.PropertiesInitServlet;

/**
 * @Description 短讯服务管理类 
 * @author snps
 * @date 2018年3月13日 上午11:06:31
 */
public class SmsManager {

	// 获取“conf/sms.properties”文件中的内容
	private static Map<String, String> s_mapPropContent = null;
	
	/**
	 * 配置信息
	 */
	private static final String URL = "sms_url";
	private static final String URL_SPARE = "sms_url_spare";
	
	private static final String USERNAME_KEY = "sms_username_key";
	private static final String USERNAME_VALUE = "sms_username_value";
	private static final String PASSWORD_KEY = "sms_password_key";
	private static final String PASSWORD_VALUE = "sms_password_value";
	
	private static final String PARAM_MOBILE = "sms_mobile_key";
	private static final String PARAM_CONTENT = "sms_mobile_content";
	
	static {
		s_mapPropContent = PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_SMS);
	}
	
	public static String sendMessage(String phone, String content) {
		BufferedReader in = null;    
        try {    
            // 定义HttpClient    
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法    
            HttpPost request = new HttpPost();    
            request.setURI(new URI(s_mapPropContent.get(URL)));  
              
            //设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            nvps.add(new BasicNameValuePair(s_mapPropContent.get(USERNAME_KEY), s_mapPropContent.get(USERNAME_VALUE)));
            nvps.add(new BasicNameValuePair(s_mapPropContent.get(PASSWORD_KEY), s_mapPropContent.get(PASSWORD_VALUE)));
            nvps.add(new BasicNameValuePair(s_mapPropContent.get(PARAM_MOBILE), phone));
            nvps.add(new BasicNameValuePair(s_mapPropContent.get(PARAM_CONTENT), content));
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
            HttpResponse response = client.execute(request);
            
            int code = response.getStatusLine().getStatusCode();  
            if(code == 200) {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));  
                StringBuffer sb = new StringBuffer("");    
                String line = "";    
                String NL = System.getProperty("line.separator");    
                while ((line = in.readLine()) != null) {    
                    sb.append(line + NL);    
                }  
                  
                in.close();    
                return sb.toString();  
            } else {
                System.out.println("状态码：" + code);  
                return null;  
            }  
        } catch(Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}