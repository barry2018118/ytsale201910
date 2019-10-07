package com.yuntu.sale.manage.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coolshow.util.BaseUtil;

@Controller
public class IndexAction extends BaseAction {
	
	@RequestMapping(value ={"/index"}, method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		/*String url = request.getRequestURL().toString() ;
    	String [] req = url.split("\\/") ;
    	String [] doMain = req[2].split("\\.") ;
    	String domain = doMain[0] ;*/
		
		Object currentUser = request.getSession().getAttribute("session_user") ;
		if(BaseUtil.isEmpty(currentUser)) {
			return "redirect:/login" ;
		} else {
			return "redirect:/manage" ;
		}
	}
	
	/*protected String getLoginUrl() {
		Map<String, String> mapProperties = PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_SYS_URL) ;
		return mapProperties.get("loginUrl") ;
	}
	
	protected String getManageUrl() {
		Map<String, String> mapProperties = PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_SYS_URL) ;
		return mapProperties.get("manageUrl") ;
	}*/
}