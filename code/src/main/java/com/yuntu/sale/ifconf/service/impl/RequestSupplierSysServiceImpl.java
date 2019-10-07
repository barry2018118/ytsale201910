package com.yuntu.sale.ifconf.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.servlet.PropertiesInitServlet;
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import com.yuntu.sale.ifconf.service.RequestSupplierSysService;
import com.yuntu.sale.ifconf.vo.OrderResutlVo;
import com.yuntu.sale.ifconf.vo.ReqResutlVo;
import com.yuntu.sale.ifconf.vo.ThirdOrderNumVo;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.CalendarPriceService;

import net.sf.json.JSONObject;

/**
 * @Description 请求供应商数据接口 
 * @author Jack.jiang
 * @date 2018年6月5日
 */
@Service
public class RequestSupplierSysServiceImpl implements RequestSupplierSysService{

	@Autowired
	private EnterprisePlatformInterfaceService enterprisePlatformInterfaceService;
	
	@Autowired
	private CalendarPriceService calendarPriceService;
	
	private String getBaseUrl() {
		String baseUrl = PropertiesInitServlet
				.getPropContent(GrobalSystemConstant.PROP_RESOURCES)
				.get("gys.base.url");
		return StringUtils.isEmpty(baseUrl)?"http://127.0.0.1:88/gys":baseUrl;
	}
	
	@Override
	public OrderResutlVo order(SupplierProduct supplierProduct, Orders order,List<Code> codeList) {
		if(order==null||order.getApimsg()==0) {
			return null;
		}
		EnterprisePlatformInterfacePo configPo=enterprisePlatformInterfaceService.getById(order.getApimsg().intValue());
		if(configPo==null) {
			return null;
		}
		//$datac['orderPrice'] = $datac['quantity']*$sproduct['min_price'];
		//$datac['price'] = $sproduct['min_price'];
		
		Map<String,String> params=Maps.newHashMap();
		params.put("method", "order");
		//配置信息
		params.put("config", configPo.getConfig());
		params.put("sendway", configPo.getSendmes()+"");
        //订单信息
		params.put("orderno", order.getOrderno());
		params.put("linkName", order.getCustomerName());
		params.put("mobile", order.getTel());
		params.put("idcard", order.getIdcard());
		params.put("quantity", order.getNum()+"");
		String occDate=order.getConsumeTimeTime();
		params.put("occDate", occDate);
		params.put("requestTime", System.currentTimeMillis()+"");
		//产品信息
		params.put("goodsCode", supplierProduct.getThirdPlatformNo());
		params.put("sproductId", supplierProduct.getId()+"");
		params.put("goodsName", supplierProduct.getName());
		String jsonStr=calendarPriceService.productCostByDate(supplierProduct.getId(), occDate, supplierProduct.getEnterpriseId());
		if(StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		if("false".equals(jsonObject.getString("success")) || jsonObject.getString("data")==null){
			return null;
		}
		params.put("sellPrice", jsonObject.getString("data")); //结算价
		if(supplierProduct.getIsRealname()==1) {
			params.put("isRealName", "1");
			String credentials="[";
			List<String> realNameList=Lists.newArrayListWithCapacity(codeList.size());
			for (Code cc:codeList) {
				realNameList.add(cc.getRealName());
			}
			credentials+=String.join(",", realNameList);
			credentials+="]";
			params.put("credentials", credentials);
		}
		//请求下单
		String url=getBaseUrl() +"/"+configPo.getInterfaceEname();
		String orderJsonStr=post(url, params);
		JSONObject orderJsonObject=JSONObject.fromObject(orderJsonStr);
		OrderResutlVo orderResutlVo=(OrderResutlVo) JSONObject.toBean(orderJsonObject, OrderResutlVo.class);
		
		return orderResutlVo; 
	}

	@Override
	public ThirdOrderNumVo queryOrder(Orders order) {
		if(order==null||order.getApimsg()==0) {
			return null;
		}
		EnterprisePlatformInterfacePo configPo=enterprisePlatformInterfaceService.getById(order.getApimsg().intValue());
		if(configPo==null) {
			return null;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("method", "checkcode");
		//配置信息
		params.put("config", configPo.getConfig());
        //订单信息
		params.put("orderno", order.getOrderno());
		params.put("thirdno", order.getThirdno());
		//$datac['cardPwd'] = $codes['cardPwd']; 
		params.put("requestTime", System.currentTimeMillis()+"");
		
		String url=getBaseUrl() +"/"+configPo.getInterfaceEname();
		String jsonStr=post(url, params);
		if(StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		ThirdOrderNumVo thirdOrderNumVo=(ThirdOrderNumVo) JSONObject.toBean(jsonObject, ThirdOrderNumVo.class);
		return thirdOrderNumVo;
	}

	@Override
	public ReqResutlVo refund(Orders order, OrdersRefund orderRefund) {
		if(order==null||order.getApimsg()==0) {
			return null;
		}
		EnterprisePlatformInterfacePo configPo=enterprisePlatformInterfaceService.getById(order.getApimsg().intValue());
		if(configPo==null) {
			return null;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("method", "part_refund");
		//配置信息
		params.put("config", configPo.getConfig());
        //订单信息
		params.put("orderno", order.getOrderno());
		params.put("thirdno", order.getThirdno());
		params.put("orderRefundId", orderRefund.getId()+"");
		//$datac['cardPwd'] = $codes['cardPwd']; 
		params.put("returnNum",orderRefund.getNum()+"");
		params.put("serialNumber",orderRefund.getSerialNumber());
		params.put("requestTime", System.currentTimeMillis()+"");
		
		String url=getBaseUrl() +"/"+configPo.getInterfaceEname();
		String jsonStr=post(url, params);
		
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		ReqResutlVo reqResutlVo=(ReqResutlVo) JSONObject.toBean(jsonObject, ReqResutlVo.class);
		return reqResutlVo;
	}

	@Override
	public ReqResutlVo resend(Orders order,String tel) {
		if(order==null||order.getApimsg()==0) {
			return null;
		}
		EnterprisePlatformInterfacePo configPo=enterprisePlatformInterfaceService.getById(order.getApimsg().intValue());
		if(configPo==null) {
			return null;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("method", "sendsms");
		//配置信息
		params.put("config", configPo.getConfig());
		params.put("sendway", configPo.getSendmes()+"");
        //订单信息
		params.put("orderno", order.getOrderno());
		params.put("thirdno", order.getThirdno());
		params.put("tplCode", tel);
		params.put("requestTime", System.currentTimeMillis()+"");
		
		String url=getBaseUrl() +"/"+configPo.getInterfaceEname();
		String jsonStr=post(url, params);
		
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		ReqResutlVo reqResutlVo=(ReqResutlVo) JSONObject.toBean(jsonObject, ReqResutlVo.class);
		return reqResutlVo;
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
