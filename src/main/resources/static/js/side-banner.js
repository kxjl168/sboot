  //侧边固定导航js
		  $(".return").mouseover(function(){
			    $(".top").css("display","none");
			    $(".fhdb").css("display","block");
			  });
		      $(".return").mouseout(function(){
		      	$(".top").css("display","block");
			    $(".fhdb").css("display","none");
		    });
		  $(".kf-top img").click(function(){
			    $(".kfbk").css("display","none");
		    });
		    
		  $(".kf").mouseover(function(){
			    $(".top").css("display","none");
			    $(".kfbk").css("display","block");
			});
		  $(".gb").click(function(){
			    $(".zhankai").css("display","block");
			    $(".gb").css("display","none");
			    $(".zkyc").css("display","none");
			    $(".fixbanner").css("background","none");
		    });
		  $(".zhankai").click(function(){
			    $(".zhankai").css("display","none");
			    $(".gb").css("display","block");
			    $(".zkyc").css("display","block");
			    $(".fixbanner").css("background","#222222");
		   });