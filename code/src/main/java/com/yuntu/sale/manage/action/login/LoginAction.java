package com.yuntu.sale.manage.action.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuntu.sale.base.RandomStringUtils;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.common.SmsManager;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.vo.RandomMessageVo;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 登录Action
 * @author snps
 * @date 2017-4-7 下午5:07:53
 */
@Controller 
public class LoginAction extends BaseAction {

	@Autowired  
	private  InfoUserService infoUserService;
	
	@Resource
	private InfoEnterpriseService infoEnterpriseService;
	
	@RequestMapping(value="/login")
	public String login(Model model) {
		// return "/manage/login/login" ;
		
		Long enterpriseId = 1L;
		InfoEnterprise enterprise = infoEnterpriseService.getById(enterpriseId);
		model.addAttribute("enterprise", enterprise);
		return "/login/login" ;
	}
	
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request,HttpSession session,String username,String password,String vcode) throws Exception{        
    	// InfoUser user =  infoUserService.login(username,MD5Tools.MD5(password));
    	InfoUser user =  infoUserService.login(username, password) ;
    	if(user==null) {
    		return "nameOrPasswordError";
    	}
    	if(user.getIsDelete()==GrobalConstant.I_INDEX_DELETE_YES) {
    		return "userDelete";
    	}
    	if(user.getStatus()==GrobalConstant.I_INDEX_USER_STATUS_UNUSABLE) {
    		return "userUnsable";
    	}
    	String code = (String)session.getAttribute("strCode");
    	if(!code.equals(vcode)){
    		return "checkNumError";
    	}
    	
    	/*if(user.getIsMaster()==GrobalConstant.I_INDEX_MASTER_YES) {
    		return "masterUser";
    	}*/
    	
    	session.setAttribute("session_user", user);  
        return "";   
    }
    
    @RequestMapping(value="/getPhoneCode", method=RequestMethod.POST)
    @ResponseBody
    public Object getPhoneCode(HttpServletRequest request,HttpSession session, String phone) {  
    	// 生成随机Key-Value
		String sKey = RandomStringUtils.getStringRandom(100, 2) ;
		// String sValue = RandomStringUtils.getStringRandom(10000, 4) ;
		String sValue = "1111" ;
		RandomMessageVo randomVo = new RandomMessageVo() ;
		randomVo.setKey(sKey) ;
		randomVo.setValue(sValue) ;
		
		// 给手机端发送验证码短信
		// sendCaptcha(phone, randomVo);
		return JSONObject.toJSONString(randomVo);
    }
    
    @RequestMapping(value="/masteruser/login", method=RequestMethod.POST)
    @ResponseBody
    public Object masteruserLogin(HttpServletRequest request, HttpSession session, String username, String password) throws Exception{        
    	InfoUser user =  infoUserService.login(username, password) ;
    	if(user==null) {
    		return "nameOrPasswordError";
    	}
    	if(user.getIsDelete()==GrobalConstant.I_INDEX_DELETE_YES) {
    		return "userDelete";
    	}
    	if(user.getStatus()==GrobalConstant.I_INDEX_USER_STATUS_UNUSABLE) {
    		return "userUnsable";
    	}

    	session.setAttribute("session_user", user);  
        return "";
    }
    
    /**
	 * 给手机端发送验证码短信
	 */
	private void sendCaptcha(String phone, RandomMessageVo vo) {
		String content = "【云端行】短信密码：{codeVal}，密码序号：{codeNo}。您正在登录云端行分销系统，严防诈骗！不要告诉任何人！";
		content = content.replace("{codeNo}", vo.getKey());
		content = content.replace("{codeVal}", vo.getValue());
		SmsManager.sendMessage(phone, content);
	}
      
	/**
	 * 退出系统 
	 */
    @RequestMapping(value="/logout", method={RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request, HttpSession session) throws Exception{
    	session.removeAttribute("session_user") ;
    	session.invalidate() ;
    	
    	return "redirect:/login" ;
    }
    
    
    /**************************************************************************************
     * 上海登录&退出
     */
    @RequestMapping(value="/login2")
	public String login2(Model model) {
		// return "/manage/login/login" ;
		
		Long enterpriseId = 1L;
		InfoEnterprise enterprise = infoEnterpriseService.getById(enterpriseId);
		model.addAttribute("enterprise", enterprise);
		return "/login/login2" ;
	}
    
    @RequestMapping(value="/logout2", method={RequestMethod.GET, RequestMethod.POST})
    public String logout2(HttpServletRequest request, HttpSession session) throws Exception{
    	session.removeAttribute("session_user") ;
    	session.invalidate() ;
    	
    	return "redirect:/login2" ;
    }
    
}