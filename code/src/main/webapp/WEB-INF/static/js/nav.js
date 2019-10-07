$(document).ready(function(){
    /*$(".left-nav ul li").click(function(){
        $(this).addClass("on").siblings("li").removeClass("on");
        $(this).addClass("on").siblings("li").find(".title").removeClass("on").next("ul").hide();
        $(this).addClass("on").siblings("li").find("li").removeClass("on");
        var $href= $(this).addClass("on").children("a").attr("href");
        $(".J_iframe").attr("src",$href);
        $(".J_iframe").attr("data-id",$href);
    });

    $(".left-nav li .title").click(function(){
        $(this).next("ul").toggle();
        $(this).toggleClass("on");
        $(this).addClass("on").parents("li").siblings("li").find(".title").removeClass("on").next("ul").hide();
        $(this).find(".fr-icon").toggleClass("icon-angle-up");
        $(this).parents("li").siblings("li").find(".title .fr-icon").removeClass("icon-angle-up");
    });*/

    /*菜单收缩展开*/
   /* $(".all .expecial .title1").click(function(){
        $(this).next("ul").toggle();
    })*/
	
	/*颜色切换-强制刷新iframe*/
    /*$(".settings .dropdown-menu li").click(function(){
        $('.J_iframe').attr('src', $('.J_iframe').attr('src'));
    })*/

	/*过滤条件*/
    /*$(".filter1 .zk").click(function(){
      $(this).parents(".form-group").siblings(".yc").toggle(1000);
        $(this).find("i").toggleClass("icon-double-angle-down").toggleClass("icon-double-angle-up");
    });*/

	/*弹出*/
    /*$(".modal a[data-toggle='modal']").click(function(){
        $(this).parents(".modal").css("z-index","1030");
      });
    $(".modal button[data-dismiss='modal']").click(function(){
        $(this).parents(".modal").prev(".modal").css("z-index","1040");
    });
    $(".modal").bind("click",function(evt){
        if($(evt.target).parents(".modal").length==0)
        {$(this).prev(".modal").css("z-index","1040");
        }
    });*/

    /*产品数量排序*/
    $('.icon-angle-down').click(function(){
        var i=$(this).parent(".px").siblings('.form-control').val();
        if(i>1){
            i--;
        }
        $(this).parent(".px").siblings('.form-control').val(i);
    });

    $('.icon-angle-up').click(function(){
        var i=$(this).parent(".px").siblings('.form-control').val();
        if(i<9999){
            i++;
        }else{
            i=9999;
        }
        $(this).parent(".px").siblings('.form-control').val(i);
    });
    
    /* 父级窗口加遮罩*/
    $("a[data-toggle='modal']").click(function(){
        $('#objid', window.parent.document).html("<div class='modal-backdrop fade in'></div>");
        $(".top", window.parent.document).css("z-index","10");

    });
    $(".modal a[data-toggle='modal'],.modal button[data-dismiss='modal']").click(function(){
                $('#objid', window.parent.document).empty();
        $(".top", window.parent.document).css("z-index","1032");
    });

});