package com.yuntu.sale.manage.service.role;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.service.function.FunctionService;

public interface InfoRoleFunctionService extends FunctionService{

	void setFunction(Long entityId,Long enterpriseId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) ;
	
	List<FuncModule> getFuctions(Long id);
	
	/**
	 * 获取多个角色的功能列表
	 * @param roleIds
	 * @return
	 */
	List<FuncModule> getSomeRolesFunction(List<Long> roleIds);
	
}