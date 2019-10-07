<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<title>机构列表</title>


  <div class="row" >
  <!-- 表格 start -->
          <div class="widget-content">
              <table class="table table-bordered table-striped">
                  <thead>
                  <tr>
                  <th width="50" align="center">序号</th>
                  <th width="30%">机构名称</th>
                  <th align="center" width="120">联系人</th>
                  <th align="center"  width="120">联系电话</th>
                  <th class="hidden-xs" >机构地址</th>
                  <th width="150">操作</th>
                  </thead>
                  <tbody>
                  <c:forEach items="${page.result }" var="pa"  varStatus="s">
                  <tr>
                      <td align="center">${s.index +1}</td>
                      <td>${pa.name }</td>
                      <td class="hidden-xs"  align="center">${pa.contacterName}</td>
                      <td class="hidden-xs"  align="center" >${pa.contacterTel }</td>
                      <td >${pa.address }</td>
                      <td  align="center">
                          <div class="td-edit">
                              <a href="javascript:;" title="编辑" onClick="edit('${pa.id}')">编辑</a>
                              <a href="javascript:;" title="查看" style="margin:0 5px;" onClick="detail('${pa.id}')">查看</a>
                              <a href="javascript:;" title="删除" onClick="deleteEnterprise('${pa.id}')">删除</a>
                          </div>
                      </td>
                  </tr>
                  <tr>
                  </c:forEach>
                  </tbody>
              </table>
          	<fmt:formatNumber var="c" value="${(page.total/page.size+(page.total%page.size>0?1:0))-(page.total/page.size+(page.total%page.size>0?1:0))%1}" pattern="#"/>
              <div class="dataTables_info" id="dataTable1_info">显示 1 到 ${c} 共${page.total} 条信息</div>
              <div class="dataTables_paginate paging_full_numbers" id="dataTable1_paginate">
              <c:choose>
              	<c:when test="${page.start==1 }">
              		<a tabindex="0" class="first paginate_button paginate_button_disabled" id="dataTable1_first">首页</a>
              		<a tabindex="0" class="previous paginate_button paginate_button_disabled" id="dataTable1_previous">上一页</a>
              	</c:when>
              	<c:otherwise>
              		<a tabindex="0" onclick="next(1)" class="first paginate_button" id="dataTable1_first">首页</a>
              		<a tabindex="0" onclick="next(${page.start-1})" class="previous paginate_button" id="dataTable1_previous">上一页</a>
              	</c:otherwise>
              </c:choose>
              <span>
              	<c:forEach begin="1" end="${c}" step="1" varStatus="f">
              		<c:choose>
              			<c:when test="${page.start==(f.index) }">
              				<a tabindex="0" class="_paginate">${f.index} </a>
              			</c:when>
              			<c:otherwise>
              				<a tabindex="0" class="paginate_active" onclick="next(${f.index})">${f.index}</a>
              			</c:otherwise>
              		</c:choose>
              	</c:forEach>
              </span>
              <c:choose>
              	<c:when test="${page.start==c }">
              		<a tabindex="0" class="next paginate_button paginate_button_disabled" id="dataTable1_next">下一页</a>
               		<a tabindex="0" class="last paginate_button paginate_button_disabled" id="dataTable1_last">末页</a></div>
              	</c:when>
              	<c:otherwise>
              		<a tabindex="0" onclick="next(${page.start+1})" class="next paginate_button" id="dataTable1_next">下一页</a>
              		<a tabindex="0" onclick="next(${c})" class="last paginate_button" id="dataTable1_last">末页</a></div>
              	</c:otherwise>
              </c:choose>
          </div>
  <!--  表格 end -->
  </div>


<script type="text/javascript">
	$(document).ready(function() {
		
	}) ;
	function editStatus(id,status,stop){
			var s =  $(stop).prop("title");
                art.dialog({
					title: '消息',
					id: "enterpriseChange" ,
					width: 160, 
					height: 80,
				    icon: 'warning' ,
				    content: '是否'+s ,
				    cancel:function() {
				    },
				    ok:function() {
				    	$.post(
			            	"${ctx}/manage/enterprise/ajax/setStatus",
			            	{"id":id,"status":status},
			            	function (data){
			            		if(data){
			            			var requestUrl = "${ctx}/manage/enterprise/main" ;
									var requestData = {start:'${param.start}'} ;
									cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ; 
			            		}else{
			            			art.dialog({
										title: '消息',
										id: "enterpriseChangeError" ,
										width: 160, 
										height: 80,
									    time: 3 ,
									    icon: 'error' ,
									    content: '出错了....' ,
									    close:function() {
									    	var requestUrl = "${ctx }/manage/enterprise/main" ;
											var requestData = {start:'${param.start}'} ;
											cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
									    }
									 });
			            		}
			            	}
			            );
				    }
			});
	}
	function edit(id){
		var requestUrl = "${ctx}/manage/enterprise/"+id+"/edit" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ; 
	}
	function detail(id){
		var requestUrl = "${ctx}/manage/enterprise/view" ;
		var requestData = {"id":id,start:'${param.start}'};
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ; 
	}
	function toEnterpriseFunc(id){
		var requestUrl = "${ctx}/manage/enterprise/toFunction" ;
    	var requestData = {"enterpriseId":id,start:'${page.start}'} ;
    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	function deleteEnterprise(id) {
		art.dialog({
			title : '消息',
			id : "",
			width : 160,
			height : 80,
			icon : 'warning',
			content : '是否删除',
			cancel : function() {
			},
			ok : function() {
				$.get("${ctx}/manage/enterprise/" + id + "/delete", function(
						data) {
					if (data == 'true') {
						var requestUrl = "${ctx}/manage/enterprise/main";
						var requestData = {};
						cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv');
					} else{
                        art.dialog({
							title : '消息',
							id : "",
							icon : 'warning',
							width : 160,
							height : 80,
							content : '删除失败'
						});
					
					}
				});
			}
		});
	}
	function pwd(id){
		var requestUrl = "${ctx}/manage/employee/modifyPassword" ;
		var requestData = {"id":id,start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
</script>