package com.yuntu.sale.info.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.info.po.NoticePo;
import com.yuntu.sale.info.po.NoticeVo;
import com.yuntu.sale.info.service.NoticeService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 公告Action
 * @author snps
 * @date 2018年2月27日 下午2:24:53
 */
@Controller
@RequestMapping(value = {"/info/notice"})
public class NoticeAction extends BaseAction {

	@Resource
	private NoticeService noticeService;
	
	
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/info/notice/main" ;
	}
	
	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/info/notice/list" ;
	}
	
	/**
	 * 通过公告标题（模糊）查询
	 */
	@RequestMapping(value="/getByTitle", method=RequestMethod.GET)
	@ResponseBody
	public String getByTitle(HttpServletRequest request, String title) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<NoticeVo> pager = new Page<NoticeVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<NoticeVo> page = noticeService.getByTitle(pager, title) ;

		
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}
	
	/**
	 * 进入新建页
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/info/notice/add";
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, NoticePo entity) {

		InfoUser currentUser = super.getCurrentUser();
		entity.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
		entity.setCreateId(currentUser.getId());
		entity.setUpdateId(currentUser.getId());
		noticeService.save(entity);
		OperatorSuccessVo message = new OperatorSuccessVo("新建公告成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		//获取公告信息
		NoticePo  entity = noticeService.getById(id) ;
		model.addAttribute("entity", entity) ;


		// 获取用户公告信息
		InfoUser user = super.getCurrentUser();
		model.addAttribute("user", user);


		return "/info/notice/edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id,NoticePo entity) {

		/*//
		String name=entity.getName();
		//
		InfoScenicPo scenic= scenicService.getByUniqueName(name);*/

		//获取公告信息
		NoticePo  po=noticeService.getById(id);

		// 获取用户公告信息
		InfoUser user = super.getCurrentUser();

		po.setNoticeType(entity.getNoticeType());//公告类型
		po.setTitle(entity.getTitle());//公告标题

		po.setUpdateId(user.getUpdateId());//更新的id
		po.setUpdateTime(entity.getUpdateTime());//时间
		po.setContent(entity.getContent());//更新公告内容
		po.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
		noticeService.update(po);


		OperatorSuccessVo message = new OperatorSuccessVo("编辑公告信息成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		noticeService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除公告成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
}