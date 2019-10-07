<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>测试第三方下单</title>
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
</head>

<body>
        测试第三方下单
</body>

<script type="text/javascript">
    $(function() {
        test() ;
    });

    function test() {
        $.ajax({
	        url : "${ctx}/orders/third/buy",
	        type : "post",
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(getJsonData()),
            dataType: "json",
	        beforeSend : function() {
	           alert(JSON.stringify(getJsonData())) ;
	        },
	        success : function(data) {
	            alert(data.code) ;
	            alert(data.message) ;
	        }
	    });
    }
    
    function getJsonData() {
        var json =
        {"enterpriseId":"4","orderNum":1,"tel":"18643214321","buyUser":"\u5f20\u4e09","idcard":"","sendtype":2,"price":19,"unitPrice":19,"sproductId":4,"consumeTim":1527696000,"note":"","payid":"160536111353000007","payApiId":"1","tuanname":"qunar","idcards":[{"name":"zhangsan","card":"370202222222222222"}]}
        /*
         {
	        "enterpriseId": 4, //下单企业ID
	        "sproductId": 3, //购买商品ID
	        "orderNum": 1, //下单购买数量
	        "unitPrice": 3.33, //售票单价
	        "price": 6.66, //销售总价
	        "consumeTim": 1526971625,
            "tuanname" : "PC", //来源
            "payid" : "152670607579174360986", //订单号
            "consumeTim" : "1527177600000",//预计游玩时间
            "buyUser" : "zhangyong", //取票人姓名
            "tel" : "13966666666", //取票人电话
            "idcard" : "370202000000000000",//取票人身份证
            "note" : "nothing", //订单备注
	        "idcards": [{
                "name": 'zhang2',
                "card": '370202222222222222',
	           }, {
                "name": 'zhang3',
                "card": '370202333333333333',
               }
            ]
	    }
        */
        ;
	    return json;
    }
    
</script>
</html>