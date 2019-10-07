package com.yuntu.sale.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.yuntu.sale.product.service.CalendarPriceService;

@Service
public class CalendarPriceServiceImpl implements CalendarPriceService {

	private String getBaseUrl() {
		String baseUrl = PropertiesInitServlet
				.getPropContent(GrobalSystemConstant.PROP_RESOURCES)
				.get("calendar.base.url");
		return StringUtils.isEmpty(baseUrl)?"http://127.0.0.1:88/ota":baseUrl;
	}
	
	@Override
	public String addSectionCost(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addCost";
		return post(url, params);
	}

	@Override
	public String addDayCost(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addDayCost";
		return post(url, params);
	}

	@Override
	public String addSectionProfit(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addProfit";
		return post(url, params);
	}

	@Override
	public String addDayProfit(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addDayProfit";
		return post(url, params);
	}

	@Override
	public String batchAddProfit(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/batchAddProfit";
		return post(url, params);
	}

	@Override
	public String supplierProductCostByMonth(Long sproductId, String month) {
		String url=getBaseUrl() +"/CalendarQuery/listSproductCost";
		Map<String,String> params=Maps.newHashMap();
		params.put("month",month);
		params.put("sproduct_id", sproductId+"");
		return post(url, params);
	}

	@Override
	public String productCostAndProfitByMonth(Long sproductId, Long groupId, String month,Long enterpriseId) {
		String url=getBaseUrl() +"/CalendarQuery/listUserProductCostAndProfit";
		Map<String,String> params=Maps.newHashMap();
		params.put("month",month);
		params.put("group_id",groupId+"");
		params.put("sproduct_id", sproductId+"");
		params.put("enterprise_id", enterpriseId+"");
		return post(url, params);
	}

	@Override
	public String productCostByMonth(Long sproductId, String month,Long enterpriseId) {
		String url=getBaseUrl() +"/CalendarQuery/listUserProductCost";
		Map<String,String> params=Maps.newHashMap();
		params.put("month",month);
		params.put("sproduct_id", sproductId+"");
		params.put("enterprise_id", enterpriseId+"");
		return post(url, params);
	}

	@Override
	public String productCostByDate(Long sproductId, String date,Long enterpriseId) {
		String url=getBaseUrl() +"/CalendarQuery/userProductDateCost";
		Map<String,String> params=Maps.newHashMap();
		params.put("date",date);
		params.put("sproduct_id", sproductId+"");
		params.put("enterprise_id", enterpriseId+"");
		return post(url, params);
	}
	
	@Override
	public String addSectionOta(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addOta";
		return post(url, params);
	}

	@Override
	public String addDayOta(Map<String, String> params) {
		String url=getBaseUrl() +"/CalendarSetting/addDayOta";
		return post(url, params);
	}

	@Override
	public String productOtaByMonth(Long sproductId, String month, Long enterpriseId) {
		String url=getBaseUrl() +"/CalendarQuery/listSproductOta";
		Map<String,String> params=Maps.newHashMap();
		params.put("month",month);
		params.put("sproduct_id", sproductId+"");
		params.put("enterprise_id", enterpriseId+"");
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
