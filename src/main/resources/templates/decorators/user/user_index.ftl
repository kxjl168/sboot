<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>中通国脉Mall管理平台</title>
    <link rel="icon" href="/img/tsmp.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/img/tsmp.ico" type="image/x-icon"/>

    <link href="/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/vendor/sbadmin/css/sb-admin-2.css" rel="stylesheet">
    <!--bootstrapValidator的验证css-->
    <link href="/vendor/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

   <script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/jquery/jquery-migrate-3.0.1.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--这里需要注意，在IE8中，不能直接使用本地下面的两个js，必须使用CDN的或者代理的，否则IE8下样式会报错-->
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    
    
  
		<link href="/css/bootstrap.min.css" rel="stylesheet">
	    <link href="/css/header.css" rel="stylesheet">
	    <link href="/css/sy.css" rel="stylesheet">
	    <link href="/css/haibao.css" rel="stylesheet">

    
    
    <![endif]-->
    <sitemesh:write property="head"/>
</head>
<body>

<div class="container">

    <!-- Navigation -->
   <div id="header">
			<div class="w1190">
				<dl class="fl left">
					<dd class="fl">
						<a href="#">返回首页</a>
					    <span>|</span>
					</dd>
					<dd class="fl login">
						<a class="dlu"  href="#">请登录</a>
						<a href="#">免费注册</a>
					</dd>
				</dl>
				<ul class="fl right">
					<li class="fl">
						<a href="#">我的订单</a>
					    <span>|</span>
					</li>
					<li class="fl sc">
						<a class="scfw"  href="#">商城服务<img src="img/xiala.png"></a>
					    <span>|</span>
					    <p>
					    	<a href="#">帮助中心</a>
					    	<a href="#">售后服务</a>
					    	<a href="#">在线客服</a>
					    	<a href="#">在线投诉</a>
					    </p>
					</li>
					<li class="fl sjxd">
						<a class="sj"  href="#"><img src="img/sj.png">手机下单更优惠</a>
					    <span>|</span>
					    <p>
					    	<img src="img/ewm.png" /><br />
					    	<strong>扫一扫有惊喜</strong>
					    </p>
					</li>
					<li class="fl">
						<a>咨询热线：025-58850477</a>
					</li>
				</ul>
			</div>			
		</div>
        <!-- /.navbar-top-links -->
    </nav>
    <div id="container">
        <sitemesh:write property="body"/>
    </div>
    
    
     <div class='miaov_head' style="margin-top: 30px;">
		   <iframe MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src="footer.html" width="100%"  height="502px">
		  </iframe>
		</div>
		<div class='miaov_head' style="position: fixed;right: 0;top:0;">
		   <iframe MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src="side-banner.html" width="100%"  height="1374px">
		  </iframe>
		</div>
    
</div>


<script type="text/javascript" src="/vendor/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/vendor/sbadmin/js/sb-admin-2.js"></script>
<!--bootstrapValidator的验证js-->
<script type="text/javascript" src="/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>


	 <script src="/js/slides.jquery.js"></script>
	 <!-- <script src="/js/bootstrap.min.js"></script> -->
	 <script src="/js/header.js"></script>

</body>
</html>