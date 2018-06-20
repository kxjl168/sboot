<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Dashboard</title>

</head>
<body>


<div class="">
    <!-- logo+搜索 -->

    <!-- 未登录模块 -->
    <div class="w1190 loginps">
        <div class="login-bg">
            <div class="zhuce a-box">
                <div class="logintext">
                    <a class="tx"><img src="/img/tx.png"></a>
                    <dl>
                        <dt>HI,上午好！</dt>
                        <dd><span>累计注册会员</span><strong>15640</strong>个</dd>
                    </dl>
                </div>
                <div class="an">
                    <button type="button" class="btnys btn btn-info loginancolor">登录</button>
                    <button type="button" class="btnys btn btn-default">注册</button>
                </div>
                <div class="zytext">
                    <dl>
                        <dd><img src="/img/zhuan.png"></dd>
                        <dd><img src="/img/wen.png"></dd>
                        <dd><img src="/img/hao.png"></dd>
                        <dd><img src="/img/sheng.png"></dd>
                    </dl>
                </div>
                <div class="zytext">
                    <dl>
                        <dd><img src="/img/you.png"></dd>
                        <dd><img src="/img/hui.png"></dd>
                        <dd><img src="/img/duo01.png"></dd>
                        <dd><img src="/img/duo02.png"></dd>
                    </dl>
                </div>
            </div>
            <!-- 已登录模块 -->
            <div class="loginxx a-box">
                <div class="logintext">
                    <a class="tx"><img src="/img/tx01.png"></a>
                    <dl>
                        <dt>HI,黑白兔</dt>
                        <dd><span>请完善您的个人资料》</span></dd>
                    </dl>
                </div>
                <div class="ddzx">
                    <div class="ddzt-text textcolor">
                        <dl>
                            <dt><span>1</span></dt>
                            <dd>待付款</dd>
                        </dl>
                        <dl>
                            <dt><span>3</span></dt>
                            <dd>待发货</dd>
                        </dl>
                        <dl>
                            <dt><span>2</span></dt>
                            <dd>待收货</dd>
                        </dl>
                    </div>
                    <div class="ddzt-text">
                        <dl>
                            <dt><span>1</span></dt>
                            <dd>已完成</dd>
                        </dl>
                        <dl>
                            <dt><span>0</span></dt>
                            <dd>已取消</dd>
                        </dl>
                    </div>

                </div>
            </div>
            <div class="guanggao">
                <div class="rmhdtu"><img src="/img/rmhd.png"></div>
                <div class="ggtext">
                    <dl>
                        <dd><em></em><a href="#">新注册即可领99元优惠卷</a></dd>
                        <dd><em></em><a href="#">千款爆品，1元起秒</a></dd>
                        <dd><em></em><a href="#">锁量保价 工程无忧</a></dd>
                        <dd><em></em><a href="#">团购惠有时，团出最低价</a></dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
    <!-- 轮播海报 -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="/img/hb01.png" style="width: 1902px; height: 328px;" alt="First slide">
            </div>
            <div class="item">
                <img src="/img/hb.png" style="width: 1902px; height: 328px;" alt="Second slide">
            </div>
            <div class="item">
                <img src="/img/hb01.png" style="width: 1902px; height: 328px;" alt="Third slide">
            </div>
            <div class="item">
                <img src="/img/hb.png" style="width: 1902px; height: 328px;" alt="Fourth slide">
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <div>
            <a class="carousel-control left pre-left" href="#myCarousel"
               data-slide="prev">&lsaquo;</a>
            <a class="carousel-control right next-right" href="#myCarousel"
               data-slide="next">&rsaquo;</a>
        </div>
    </div>
    <!-- 热门商品 -->
    <div class="dhrk">
        <div class="w1190 ">
            <div class="re-biaoti">
                <img src="/img/re.png">
                <span>热门商品</span>
            </div>
            <div>
                <ul class="hdlist textstyle">
					   <#if list?size gte 4>
							<#list list as item>
                                <#if item_index gt 3>
                                    <#break >
                                </#if>
								<li class="chumo">
                                    <div style="float: left;">
                                        <dl>
                                            <dt>${item.name}</dt>
                                            <dd>
                                                <span>${item.description!}</span>
                                            </dd>
                                        </dl>
                                        <div class="jinru"><a href="/public/classify/1/1/1/0/0/${item.id}.html">进入</a></div>
                                    </div>
                                    <div class="chanpintu"><img src="/img/cpt.png"></div>
                                </li>
                            </#list>
                       <#else >
                           <#list list as item>
						   		<li class="chumo">
                                    <div style="float: left;">
                                        <dl>
                                            <dt>${item.name}</dt>
                                            <dd>
                                                <span>${item.description!}</span>
                                            </dd>
                                        </dl>
                                        <div class="jinru"><a href="/public/classify/1/1/1/0/0/${item.id}.html">进入</a></div>
                                    </div>
                                    <div class="chanpintu"><img src="/img/cpt.png"></div>
                                </li>
                           </#list>
                           <#list list?size..3 as t>
								<li class="chumo">
                                    <div style="float: left;">
                                        <dl>
                                            <dt></dt>
                                            <dd>
                                                <span></span>
                                            </dd>
                                        </dl>
                                        <div class="jinru"><a>进入</a></div>
                                    </div>
                                    <div class="chanpintu"><img src=""></div>
                                </li>
                           </#list>
                       </#if>
                </ul>
					<#if list?size gte 5>
					   <ul class="gglist textstyle">
							<#if list?size gte 8>
								<#list 4..list?size-1 as it>
                                    <#assign  item=list[it]>
									<li class="chumo">
                                        <div style="float: left;">
                                            <dl>
                                                <dt>${item.name}</dt>
                                                <dd>
                                                    <span>${item.description!}</span>
                                                </dd>
                                            </dl>
                                            <div class="jinru"><a href="/public/classify/1/1/1/0/0/${item.id}.html">进入</a>
                                            </div>
                                        </div>
                                        <div class="chanpintu"><img src="/img/cpt.png"></div>
                                    </li>
                                </#list>
                            <#else>
                                <#list 4..list?size-1 as it>
                                    <#assign  item=list[it]>
									<li class="chumo">
                                        <div style="float: left;">
                                            <dl>
                                                <dt>${item.name}</dt>
                                                <dd>
                                                    <span>${item.description!}</span>
                                                </dd>
                                            </dl>
                                            <div class="jinru"><a href="/public/classify/1/1/1/0/0/${item.id}.html">进入</a>
                                            </div>
                                        </div>
                                        <div class="chanpintu"><img src="/img/cpt.png"></div>
                                    </li>
                                </#list>
                                <#list list?size..7 as it>
								    <li class="chumo">
                                        <div style="float: left;">
                                            <dl>
                                                <dt></dt>
                                                <dd>
                                                    <span></span>
                                                </dd>
                                            </dl>
                                            <div class="jinru"><a>进入</a></div>
                                        </div>
                                        <div class="chanpintu"><img src=""></div>
                                    </li>
                                </#list>
                            </#if>
                       </ul>
                    </#if>


            </div>

        </div>

    </div>
    <!-- 商品展示 -->
    <div class="cpzs">
			<#list list as item>
			    <#if item_index gt 7>
                    <#break >
                </#if>

                <div class="w1190 juli">
                    <div class="cp-biaoti cp-biaoti-color0${item_index+1}">
                        <h2>
                            <strong>${item_index+1}F</strong>
                            ${item.name}
                        </h2>
                        <div class="cp-right cp-right-hover0${item_index+1}">
                            <#if item.commoditySecondClassifyList??>
                                <#list item.commoditySecondClassifyList as scItem>
                                    <#if scItem_index gt 1>
                                        <#break >
                                    </#if>
                                	<a href="/public/classify/2/1/1/0/0/${scItem.id}.html">${scItem.name}</a>
                                </#list>
                            </#if>

                            <span>|</span>
                            <a href="/public/classify/1/1/1/0/0/${item.id}.html">更多分类》</a>
                        </div>
                    </div>
                    <div class="cp-list cp-list-color0${item_index+1}">
                        <div class="cp-list-left cp-${item_index+1}F">
                            <div class="cp-top">
                                <dl>
                                    <dt>${item.name}</dt>
                                    <dd>
                                        <span>${item.description!}</span>
                                    </dd>
                                </dl>
                            </div>
                            <div class="cp-middle">
								<#assign pictureFile=item.pictureFile />
                                <img src="/file/${pictureFile.http_relative_path!}">
                            </div>
                            <div class="cp-bottom">
								<#if item.commoditySecondClassifyList??>
									<#list item.commoditySecondClassifyList as scItem>
                                        <#if scItem_index gt 1>
                                            <#break >
                                        </#if>
                                        <#if scItem_index == 0>
                                            <#assign class="fist" />
                                        <#else>
                                            <#assign class="second" />
                                        </#if>
										<div class="button-${class} button-color0${item_index+1}"><a
                                                href="/public/classify/2/1/1/0/0/${scItem.id}.html">${scItem.name}</a></div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                        <div class="cp-list-right">
                            <ul>
                                <#if item.commodityInstanceList??>
									<#assign ciList=item.commodityInstanceList />
									<#if ciList?size gte 4>
                                        <#list ciList as ciItem>
                                            <#if ciItem_index gt 3>
                                                <#break >
                                            </#if>
                                            <#assign commodity=ciItem.commodity />
                                            <#assign commodityImages=commodity.commondityImages />
                                            <#if commodityImages?? && commodityImages?size gte 1>
                                                <#assign commodityImage=commodityImages[0]/>
                                                <#assign commodityImageFile=commodityImage.fileinfo/>
                                            </#if>
											<li class="cpgm">
                                                <a href="/public/commodity/${ciItem.id}.html">
                                                    <div class="cpgmtu">
                                                    <#if commodityImageFile??>
                                                        <img src="/file/${commodityImageFile.http_relative_path}">

                                                    <#else >
														<img src="">
                                                    </#if>

                                                    </div>
                                                    <div class="cptext">
                                                        <span>${commodity.name}</span>
                                                        <span>¥${ciItem.price}</span>
                                                    </div>
                                                </a>

                                            </li>
                                        </#list>
                                    <#else>
                                        <#list ciList as ciItem>
                                            <#assign commodity=ciItem.commodity />
                                            <#assign commodityImages=commodity.commondityImages />
                                            <#if commodityImages?? && commodityImages?size gte 1>
                                                <#assign commodityImage=commodityImages[0]/>
                                                <#assign commodityImageFile=commodityImage.fileinfo/>
                                            </#if>
											<li class="cpgm">
                                                <a href="/public/commodity/${ciItem.id}.html">
                                                    <div class="cpgmtu">
														<#if commodityImageFile??>
                                                            <img src="/file/${commodityImageFile.http_relative_path}">
                                                        <#else >
															<img src="">
                                                        </#if>
                                                    </div>
                                                    <div class="cptext">
                                                        <span>${commodity.name}</span>
                                                        <span>¥${ciItem.price}</span>
                                                    </div>
                                                </a>
                                            </li>
                                        </#list>
                                        <#list ciList?size..3 as it>
										    <li class="cpgm">
                                                <div class="cpgmtu"><img src=""></div>
                                                <div class="cptext">
                                                    <span></span>
                                                    <span>¥0.00</span>
                                                </div>
                                            </li>
                                        </#list>
                                    </#if>
                                <#else>
                                    <#list 0..3 as it>
									    <li class="cpgm">
                                            <div class="cpgmtu"><img src=""></div>
                                            <div class="cptext">
                                                <span></span>
                                                <span>¥0.00</span>
                                            </div>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>
                            <ul>
								<#assign ciList=item.commodityInstanceList />
								<#if ciList?? && ciList?size gt 4>
                                    <#if ciList?size gte 8>
                                        <#list 4..ciList?size-1 as it>
                                            <#assign ciItem=ciList[it]/>
                                            <#assign commodity=ciItem.commodity />
                                            <#assign commodityImages=commodity.commondityImages />
                                            <#if commodityImages?? && commodityImages?size gte 1>
                                                <#assign commodityImage=commodityImages[0]/>
                                                <#assign commodityImageFile=commodityImage.fileinfo/>
                                            </#if>
											<li class="cpgm">
                                                <a href="/public/commodity/${ciItem.id}.html">
                                                    <div class="cpgmtu">
													<#if commodityImageFile??>
                                                        <img src="/file/${commodityImageFile.http_relative_path}">
                                                    <#else >
															<img src="">
                                                    </#if>
                                                    </div>
                                                    <div class="cptext">
                                                        <span>${commodity.name}</span>
                                                        <span>¥${ciItem.price}</span>
                                                    </div>
                                                </a>
                                            </li>
                                        </#list>
                                    <#else >
                                        <#list 4..ciList?size-1 as it>
                                            <#assign ciItem=ciList[it]/>
                                            <#assign commodity=ciItem.commodity/>
                                            <#assign commodityImages=commodity.commondityImages />
                                            <#if commodityImages?? && commodityImages?size gte 1>
                                                <#assign commodityImage=commodityImages[0]/>
                                                <#assign commodityImageFile=commodityImage.fileinfo/>
                                            </#if>
												<li class="cpgm">
                                                    <a href="/public/commodity/${ciItem.id}.html">
                                                        <div class="cpgmtu">
															<#if commodityImageFile??>
                                                                <img src="/file/${commodityImageFile.http_relative_path}">
                                                            <#else >
																<img src="">
                                                            </#if>
                                                        </div>
                                                        <div class="cptext">
                                                            <span>${commodity.name}</span>
                                                            <span>¥${ciItem.price}</span>
                                                        </div>
                                                    </a>
                                                </li>
                                        </#list>

                                        <#list ciList?size..7 as it>
											<li class="cpgm">
                                                <div class="cpgmtu"><img src=""></div>
                                                <div class="cptext">
                                                    <span></span>
                                                    <span>¥0.00</span>
                                                </div>

                                            </li>
                                        </#list>
                                    </#if>
                                <#else >
                                    <#list 0..3 as it>
									    <li class="cpgm">
                                            <div class="cpgmtu"><img src=""></div>
                                            <div class="cptext">
                                                <span></span>
                                                <span>¥0.00</span>
                                            </div>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </#list>
    </div>


    <script type="text/javascript">
        //$(".text dd").css("display","block");
        //左侧导航js
        $(".text").mouseover(function () {
            $(this).find("dd").css("display", "block");
        });
        $(".text").mouseout(function () {
            $(this).find("dd").css("display", "none");
        });

        function search() {
            window.location.href = "/public/search";
        }

    </script>

</body>
</html>