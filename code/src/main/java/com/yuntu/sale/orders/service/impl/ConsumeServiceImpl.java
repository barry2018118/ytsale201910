package com.yuntu.sale.orders.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.servlet.PropertiesInitServlet;
import com.yuntu.sale.orders.service.ConsumeService;

/**
 * 码核销
 * @author Jack.jiang
 * @version 2018-05-16
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {
	
	private String getConsumeUrl() {
		String baseUrl = PropertiesInitServlet
				.getPropContent(GrobalSystemConstant.PROP_RESOURCES)
				.get("consume.url");
		return StringUtils.isEmpty(baseUrl)?"http://127.0.0.1:88/pos/index.php":baseUrl;
	}

	@Override
	public String consumeCode(String code,Integer num,Long shopId,Long userId) {
		Map<String,String> params=Maps.newHashMap();
		params.put("num", num+"");
		params.put("code", code);
		params.put("shop_id", shopId+"");
		params.put("user_id", userId+"");
		params.put("do", "sign");
		params.put("mod", "pc");
		params.put("isprint", "0");
		params.put("request_id", UUID.randomUUID().toString());
		String url=getConsumeUrl();
		return post(url, params);
	}

	 /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交&lt;参数，值&gt;Map
     * @return 提交响应
     */
    public String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
        			e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
            		e.printStackTrace();
            }
        }
        return responseText;
    }
}