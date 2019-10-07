package com.yuntu.sale.manage.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.servlet.PropertiesInitServlet;
import com.yuntu.sale.user.po.InfoUser;

@Controller("manageBaseAction")
public class BaseAction {
	@Autowired  
	private  HttpServletRequest request; 
	
	protected final String SUCCESS = "success" ;
	public static final String SESSION_USER = "session_user" ;
	
	/**
	 * 得到当前登录用户
	 * @return InfoUser
	 */
	public InfoUser getCurrentUser() {
		/*AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal() ;
		Map<String, Object> mapAttributes = principal.getAttributes() ;*/
		InfoUser user = (InfoUser) request.getSession().getAttribute(SESSION_USER) ;
		return user ;
	}
	
	/**
	 * 获取分页数
	 * @return int
	 */
	protected int getShowDataNumber(){
		Map<String, String> map = PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_GROBAL);
		return Integer.parseInt(map.get("showDataNumber"));
	}
	
	/**
	 * 得到本月第一天
	 * @return String
	 */
	public static String getFirstDayOfMonty() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
		Calendar c = Calendar.getInstance() ;
        c.add(Calendar.MONTH, 0) ;
        // 设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH,1) ; 
        return format.format(c.getTime()) ;
	}
}