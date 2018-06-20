<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Dashboard</title>
    <link href="/css/common-front.css" rel="stylesheet">
    <link href="/css/search.css" rel="stylesheet">
    <link href="/css/modal.css" rel="stylesheet">
</head>
<body>

<div class="w1190 bgcolor">
    <div class="dbleft">
        <div class="spjx"></div>
        <div class="dbsp">
            <ul>
                <li>
                    <div class="dbsptu"><img src="/img/cpt2.png"></div>
                    <div class="dbtext">
                        <p>普天视 10A集中式室内电源</p>
                        <span class="jiage">¥540.00</span>
                        <div class="fuanniu">
                            <button type="button" class="btnys btn btn-default">立即购买</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dbsptu"><img src="/img/cpt2.png"></div>
                    <div class="dbtext">
                        <p>普天视 10A集中式室内电源</p>
                        <span class="jiage">¥540.00</span>
                        <div class="fuanniu">
                            <button type="button" class="btnys btn btn-default">立即购买</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dbsptu"><img src="/img/cpt2.png"></div>
                    <div class="dbtext">
                        <p>普天视 10A集中式室内电源</p>
                        <span class="jiage">¥540.00</span>
                        <div class="fuanniu">
                            <button type="button" class="btnys btn btn-default">立即购买</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="guanggao1">
        <div class="rmhdtu"><img src="/img/rmhd01.png"></div>
        <div class="ggtext">
            <dl>
                <dd><em></em><a href="#">新注册即可领99元优惠卷</a></dd>
                <dd><em></em><a href="#">千款爆品，1元起秒</a></dd>
                <dd><em></em><a href="#">锁量保价 工程无忧</a></dd>
                <dd><em></em><a href="#">团购惠有时，团出最低价</a></dd>
                <dd><em></em><a href="#">团购惠有时，团出最低价</a></dd>
            </dl>
        </div>
    </div>
</div>
<div class="w1190 zj-box">
    <div class="w1190 biaoti">
        <div class="btleft">
            <a class="#">分类</a>
            <span>></span>
					<#list navs as nav>
					    <a class="#">${nav.name!}</a>
                        <#if nav_has_next>
						    <span>></span>
                        </#if>
                    </#list>
        <#--<a class="#">智能家居</a>
        <span>></span>
        <a class="#">家居安全</a>
        <span>></span>
        <a class="#">智能门锁</a>-->
        </div>
        <div class="btright">
            共找到
            <span>${total?default(0)}</span>
            条相关产品
        </div>
    </div>
    <div class="zstext">
        <table class="table">
            <tbody>
            <tr>
                <td>品牌：</td>
                <td class="value" colspan="2">
                    <div class="wzxx-box">
                        <div class="more">
                            <a>更多</a>
                            <span><img src="/img/gd.png"></span>
                        </div>
                        <div class="wzxx gdnr">
                            <ul>
	        								<#list brands as brand>
                                                <li><a href="/public/classify/${level}/${sort}/${page}/${brand.id}/${option}/${id}.html">${brand.brandName!}</a></li>
                                            </#list>
                            </ul>
                        </div>

                    </div>
                </td>
            </tr>

						<#list scdClassifies as scdClassifie>
							<tr>
                                <td>${scdClassifie.name}：
                                </td>
                                <td class="value" colspan="2">
                                    <div class="wzxx-box">
                                        <div class="wzxx">
                                            <ul>
												<#list scdClassifie.thdClassifies as thdClassifie>
                                                    <li><a href="/public/classify/3/${sort}/${page}/${brand}/${option}/${thdClassifie.id}.html">${thdClassifie.name}</a></li>
                                                </#list>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                <#if level gte 2>
                    <#list options as option>
                    <tr>
                        <td>${option.name}：
                        </td>
                        <td class="value" colspan="2">
                            <div class="wzxx-box">
                                <div class="wzxx">
                                    <ul>
                                        <#list option.optVals as optVal>

                                            <li><a href="/public/classify/${level}/${sort}/${page}/${brand}/${optVal.optValMd5?default(0)}/${id}.html">${optVal.value}</a></li>
                                        </#list>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </#list>
                </#if>

            </tbody>
        </table>
        <div class="more02">
            <a>更多选项</a>
            <span><img src="/img/gd.png"></span>
        </div>

    </div>
</div>
<div class="w1190 paih">
    <nav class="navbar navbar-default">

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="sortGroup">

                <#if sort==1>
                    <li id="xlSort" class="0" >
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="1">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==2>
                    <li id="xlSort" class="0">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl01.png" >
                            </span>
                            </a>
                        </li>
                    <li id="jgSort" class="0">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==3>
                    <li id="xlSort" class="0">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="0">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==4>
                    <li id="xlSort" class="0">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl01.png" >
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="1">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="0">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==5>
                    <li id="xlSort" class="1">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                    <li  id="jgSort" class="0">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==6>
                    <li id="xlSort" class="1">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="0">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl01.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==7>
                    <li id="xlSort" class="1">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl02.png" >
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="1">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl02.png">
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="0">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl01.png" >
                            </span>
                        </a>
                    </li>
                </#if>
                <#if sort==8>
                    <li id="xlSort" class="1">
                        <a href="#">
                            销量
                            <span class="xljt">
                                <img src="/img/xl02.png" >
                            </span>
                        </a>
                    </li>
                    <li id="jgSort" class="1">
                        <a href="#">
                            价格
                            <span class="xljt">
                                <img src="/img/xl02.png" >
                            </span>
                        </a>
                    </li>
                    <li id="sjSort" class="1">
                        <a href="#">
                            上架时间
                            <span class="xljt">
                                <img src="/img/xl02.png" >
                            </span>
                        </a>
                    </li>
                </#if>

            </ul>
            <#--<form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="¥最低价">
                    <span>—</span>
                    <input type="text" class="form-control" placeholder="¥最高价">
                </div>
                <button type="submit" class="btn btn-default">确定</button>
            </form>-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" class="spsl"><span>${page}</span>/${pages}</a></li>
                <li class="more03 prePage" >
                    <span class="jt02"><img src="/img/jt05.png"></span>
                </li>
                <li class="more03 nxtPage">
                    <span class="jt02"><img src="/img/jt02.png"></span>
                </li>
            </ul>
        </div>
    </nav>
    <div class="splb">
        <ul>
					<#list ciList as ciItem>

                        <li class="cpgm">
                            <a href="/public/commodity/${ciItem.id}.html">
                                <div class="cpgmtu">
                                    <img src="/file/${ciItem.http_relative_path!}">
                                </div>
                                <div class="cptext">
                                    <span>${ciItem.name}</span>
                                    <div class="jg">
                                        <div class="jiage01">
                                            <span>¥</span>
                                            <span>${ciItem.price}</span>
                                        </div>
                                        <div class="cjl">
                                            <span>成交${ciItem.cnt}件</span>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="jrgwc">
                                            <button type="button" class="btnys btn btn-default">加入进货单</button>
                                        </div>
                                        <div class="guanzhu">
                                            <button type="button" class="djgz btnys btn btn-default" data-toggle="modal"
                                                    data-target="#myModal">
                                                <span class="gz"><img class="ygz" src="/img/gz01.png"></span>
                                                <span class="gztext">关注</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </a>

                        </li>
                    </#list>

        </ul>
    </div>
    <!--页码-->
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <input type="hidden" id="level" value="${level}">
                <input type="hidden" id="sort" value="${sort}">
                <input type="hidden" id="page" value="${page}">
                <input type="hidden" id="brand" value="${brand}">
                <input type="hidden" id="option" value="${option}">
                <input type="hidden" id="total" value="${total}">
                <input type="hidden" id="pages" value="${pages}">
                <input type="hidden" id="id" value="${id}">
                <li>
                    <a id="prePage" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
                <li>
                    <select id="goPage"  class="selectpicker form-control">
								<#list 1..pages as i>
									<#if page==i>
                                        <#assign slt="selected='selected'">
                                    <#else>
                                        <#assign slt="">
                                    </#if>
                                    <option value="${i}" ${slt!}>
                                        ${i}
                                    </option>
                                </#list>
                    </select>
                    <script type="application/javascript">
                        $(function(){
                            $("#prePage").click(function () {
                                var curPage = parseInt($("#page").val());
                                var prePage;
                                if(1==curPage){
                                    prePage = curPage;
                                }else{
                                    prePage = curPage - 1;
                                }

                                window.location = "/public/classify/" + $("#level").val() + "/" + $("#sort").val() + "/" + prePage + "/" + $("#brand").val() + "/" + $("#option").val() + "/" + $("#id").val() + ".html"
                            });

                            $(".prePage").click(function(){
                                $("#prePage").click()
                            });

                            $("#nxtPage").click(function () {
                                var curPage = parseInt($("#page").val());
                                var nxtPage;
                                if(parseInt($("#pages").val())<=curPage){
                                    nxtPage = parseInt($("#pages").val());
                                }else{
                                    nxtPage = curPage + 1;
                                }
                                window.location = "/public/classify/" + $("#level").val() + "/" + $("#sort").val() + "/" + nxtPage + "/" + $("#brand").val() + "/" + $("#option").val() + "/" + $("#id").val() + ".html"
                            });

                            $(".nxtPage").click(function(){
                                $("#nxtPage").click()
                            });

                            $("#goPage").change(function () {
                                //"/classify/{level}/{page}/{id}.html"
                                window.location = "/public/classify/" + $("#level").val() + "/" + $("#sort").val() + "/" + $(this).val() + "/" + $("#brand").val() + "/" + $("#option").val() + "/" + $("#id").val() + ".html"
                            });
                        });

                    </script>
                </li>
                <li>
                    <a id="nxtPage" aria-label="Next">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div>
        <div class="re-biaoti">
            <span>热卖推荐</span>
        </div>
        <div class="splb">
            <ul>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div>
        <div class="re-biaoti">
            <span>浏览记录</span>
        </div>
        <div class="splb">
            <ul>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="gaodu">
                    <div class="cpgmtu"><img src="/img/cpt2.png"></div>
                    <div class="cptext cmxg">
                        <span>普天视 10A集中式室内电源源源源</span>
                        <div class="jg">
                            <div class="jiage01">
                                <span>¥</span>
                                <span>540.00</span>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    //左侧导航js
    $(".text").mouseover(function () {
        $(".text dd").css("display", "block");
    });
    $(".text").mouseout(function () {
        $(".text dd").css("display", "none");
    });

    $("#dropmenu").hide();

    $("#dropmenubtn").on("click", function () {
        //  $("#dropmenu").show();

    });


    $(function(){
        //排序
        $("#sortGroup li").click(function () {
            var curId = $(this).attr("id");
            var ary = new Array();

            //点击自己的排序规则 取反
            var xl = $("#xlSort").attr("class");
            if(curId=="xlSort"){
                if(0==xl){
                    xl = 1;
                }else{
                    xl = 0
                }
            }
            ary.push(xl);

            //点击自己的排序规则 取反
            var jg = $("#jgSort").attr("class");
            if(curId=="jgSort"){
                if(0==jg){
                    jg = 1;
                }else{
                    jg = 0
                }
            }
            ary.push(jg);

            //点击自己的排序规则 取反
            var sj = $("#sjSort").attr("class");
            if(curId=="sjSort"){
                if(0==sj){
                    sj = 1;
                }else{
                    sj = 0
                }
            }
            ary.push(sj);

            var cnt = 0;
            var tmpAry;
            var i=0;
            for(;i<sortAry.length;i++){
                tmpAry = sortAry[i];
                for(var j=0;j<ary.length;j++){
                    if(ary[j]==tmpAry[j]){
                        cnt++;
                    }
                }
                if(cnt==ary.length){
                    break;
                }
                cnt = 0;
            }
            if(i<=7){
                window.location = "/public/classify/" + $("#level").val() + "/" + (i + 1) + "/" + "1" + "/" + $("#brand").val() + "/" + $("#option").val() + "/" + $("#id").val() + ".html"
            }
        });
    });

    function search() {
        window.location.href = "/public/search";
    }

    /*

     * 0 :降序    1：升序
     *
     * 1：销降    价升     上升
     * 2：销降    价降     上降
     * 3：销降    价降     上升
     * 4：销降    价升     上降
     * 5：销升    价降     上降
     * 6：销升    价降     上升
     * 7：销升    价升     上降
     * 8：销升    价升     上升
    *
    * */
    var sortAry = [
                [0,1,1],
                [0,0,0],
                [0,0,1],
                [0,1,0],
                [1,0,0],
                [1,0,1],
                [1,1,0],
                [1,1,1]
        ];

</script>

</body>
</html>