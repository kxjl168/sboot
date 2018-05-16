<#macro page action pageNumParaName="pageNum" pageSizeParaName="pageSize" pageSize="3" qryItems=[]>
    <form id="pageForm" action="${action}" method="post">
        <input type="hidden" name="${pageNumParaName}" id="${pageNumParaName}">
        <input type="hidden" name="${pageSizeParaName}" id="${pageSizeParaName}">
        <#if qryItems??>
            <#list qryItems as qryItem>
                <input type="hidden" name="${qryItem}" id="${qryItem}">
            </#list>
        </#if>
    </form>

    <script type="text/javascript">
        function gotopage(value) {
            $("#pageForm").find("#${pageNumParaName}").val(value);
            $("#pageForm").submit();
        }
    </script>

    <#if pager?? && pager.total gt 0>
        <div class="pager">
            <ul>
            <#if pager.pageNum==1>
                <li class="disabled"><a href="javascript:void(0)">|&lt;</a></li>
                <li class="disabled"><a href="javascript:void(0)">&lt;</a></li>
            <#else>
                <li><a href="javascript:void(0)" onclick="gotopage(1)">|&lt;</a></li>
                <li><a href="javascript:void(0)" onclick="gotopage(${pager.pageNum-1})">&lt;</a></li>
            </#if>
            <#if 10<pager.pages >
                <#list 5..1 as i>
                    <#if  0< (pager.pageNum-i)  >
                        <li><a href="javascript:void(0)" onclick="gotopage(${pager.pageNum-i})">  ${pager.pageNum-i}</a></li>
                    </#if>
                </#list>
                <li class="active"><a href="javascript:void(0)" onclick="gotopage(${pager.pageNum})">${pager.pageNum}</a></li>
                <#list 1..5 as i>
                    <#if  (pager.pageNum+i)<=pager.pages  >
                        <li><a href="javascript:void(0)" onclick="gotopage(${pager.pageNum+i})">  ${pager.pageNum+i}</a></li>
                    </#if>
                </#list>


            <#else>
                <#list 1..pager.pages as i>
                    <#if pager.pageNum==i>
                        <li class="active"><a href="javascript:void(0)" onclick="gotopage(${i})">${i}</a></li>
                    <#else>
                        <li><a href="javascript:void(0)" onclick="gotopage(${i})">  ${i}</a></li>
                    </#if>
                </#list>
            </#if>


            <#if pager.pages==pager.pageNum>
                <li class="disabled"><a href="javascript:void(0)">&gt;</a></li>
                <li class="disabled"><a href="javascript:void(0)">&gt;|</a></li>
            <#else>
                <li><a href="javascript:void(0)" onclick="gotopage(${pager.pageNum+1})">&gt;</a></li>
                <li><a href="javascript:void(0)" onclick="gotopage(${pager.pages})">&gt;|</a></li>
            </#if>
            </ul>
        </div>
    </#if>
</#macro>


