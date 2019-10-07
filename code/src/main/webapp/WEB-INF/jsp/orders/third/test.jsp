<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>测试第三方下单(非实名制)</title>
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
</head>

<body>
        测试第三方下单(非实名制)
        {"enterpriseId":"4",
        "orderNum":1,
        "tel":"18643214321",
        "buyUser":"aaa",
        "idcard":"",
        "sendtype":2,
        "price":19,
        "unitPrice":19,
        "sproductId":3,
        "consumeTim":1527609600,
        "note":"",
        "payid":"160536111342000007",
        "payApiId": 1,
        "tuanname":"qunar",
        "idcards":[]}
</body>

<script type="text/javascript">
    $(function() {
        test() ;
    });

    function test() {
        $.ajax({
            //下单测试URL：/orders/third/buy
            //支付测试URL：/orders/third/11/pay"
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
        //下单测试
        var json = {
            "enterpriseId":"4",
            "orderNum":1,
            "tel":"18643214321",
            "buyUser":"aaa",
            "idcard":"",
            "sendtype":2,
            "price":19,
            "unitPrice":19,
            "sproductId":3,
            "consumeTim":1527609600,
            "note":"",
            "payid":"160536111342000007",
            "payApiId": 1,
            "tuanname":"qunar",
            "idcards":[]};


        /* var json = {
             "enterpriseId": 4, //下单企业ID
             "sproductId": 4, //购买商品ID
             "orderNum": 2, //下单购买数量
             "unitPrice": 3.33, //售票单价
             "price": 6.66, //销售总价
             "consumeTim": 1526971625,//预计游玩时间
             "tuanname" : "PC", //来源
             "payid" : "152670607579174360987", //订单号
             //"consumeTim" : "1527177600000",//预计游玩时间
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
         };*/

       //支付测试
        /*var json = {
            "enterpriseId": 4, //下单企业ID
            "sproductId": 4, //购买商品ID
            "orderNum": 2, //下单购买数量
            "unitPrice": 3.33, //售票单价
            "price": 6.66, //销售总价
            "consumeTim": 1526971625,//预计游玩时间
            "tuanname" : "PC", //来源
            "payid" : "152670607579174360987", //订单号
            //"consumeTim" : "1527177600000",//预计游玩时间
            "buyUser" : "zhangyong", //取票人姓名
            "tel" : "13966666666", //取票人电话
            "idcard" : "370202000000000000",//取票人身份证
            "note" : "nothing", //订单备注
            "sendtype" : 2,//是否发送短信
            "idcards": [{
                "name": 'zhang2',
                "card": '370202222222222222',
            }, {
                "name": 'zhang3',
                "card": '370202333333333333',
            }
            ]
        };*/
	    return json;
    }
    
</script>
</html>