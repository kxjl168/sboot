$(function() {
	
	

	initClist();
});

function initRate(pre,id,s){
$("#"+pre+id).raty(
		{

			score : function() {

				return $(this).attr('data-score');

			},
			iconRange : [

			{
				range : 1,
				on : '/vendor/rate/star-on-big-gray.png',
				off : '/vendor/rate/star-off-big.png',
			},

			{
				range : 2,
				on : '/vendor/rate/star-on-big-gray.png',
				off : '/vendor/rate/star-off-big.png',
			},

			{
				range : 3,
				on : '/vendor/rate/star-on-big-o.png',
				off : '/vendor/rate/star-off-big.png',
			},

			{
				range : 4,
				on : '/vendor/rate/star-on-big-o.png',
				off : '/vendor/rate/star-off-big.png',
			},

			{
				range : 5,
				on : '/vendor/rate/star-on-big.png',
				off : '/vendor/rate/star-off-big.png',
			}

			],

			starHalf : '/vendor/rate/star-half-big.png',
			starOff : '/vendor/rate/star-off-big.png',
			starOn : '/vendor/rate/star-on-big.png',
			size : 24,
			hints : [ '[1分 失望]', '[2分 不满]', '[3分 一般]', '[4分 满意]', '[5分 惊喜]' ],
			target : $("#rtip"+id),
			mouseover : function(score, evt) {
				setrtip(id,score)
				
			},
			mouseout : function(score, evt) {
				setrtip(id,score);
			},
			 targetKeep : true,
		
			click:function(s){
				setrtip(id,s);
			}
			
		});

}

function setrtip(id,score){
	$("#rtip2"+id).html($("#rtip"+id).html());
	
	$("#rtip2"+id).removeClass().addClass("rtip"+score);
	
	$("#rtip2"+id).show();
}

function commet() {
	 
	var cmts=[];
	
	$.each($(".commetdiv"),function(index,item){
		var cmt={};
		var gid=$(item).find(".gid").val();
		var iid=$(item).find(".iid").val();
		var oid=$(item).find(".oid").val();
		var odid=$(item).find(".odid").val();
		var value=encodeURIComponent( $(item).find(".svalue").val());
		var star= $(item).find(".start").attr("data-score");
		var nm= $("#hidename").is(':checked');
	
		
		
			cmt={gid:gid,
				iid:iid,
				oid:oid,
				odid:odid,
				value:value,
				star:star,
				isanony:nm?"1":"0"
				}
			cmts.push(cmt);
	});
	
	
	
		$.ajax({
			type : "post",
			url : '/userCenter/commet/save',
			data : {data:JSON.stringify( cmts)},
			dataType : "json",
			success : function(response) {
				// debugger;
				if (response.result) {
					// $('#myModal').modal("hide");
					success("操作成功！");
					
					
					$(".cmet").hide();
					
					doQuery();

				} else {
					error(response.message);
				}
			}
		});
	
}

function initClist(){
	 // 初始化Table
    $('#c_list').bootstrapTable({
        url: '/manager/estimate/showEstimate', // 请求后台的URL（*）
        method: 'post', // 请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        toolbar: '#toolbar', // 工具按钮用哪个容器
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',

        searchOnEnterKey: true,
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

        // showColumns: true, //是否显示所有的列
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        // queryParamsType : "limit",
        queryParams: function queryParams(params) { // 设置查询参数
            var param = {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                sort: params.sort, // 要排序的字段
                userId: $("#cuserId").val(),
              
            };
            return param;
        },
        columns: [ {
            field: 'userName',
            title: '用户名称',
            align: 'center',
            valign: 'middle'
        
        }, {
            field: 'value',
            title: '评价内容',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'createTime',
            title: '评价时间',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return row.createTime.split(".")[0]
            }
        }, {
            field: 'stars',
            title: '评分',
            align: 'center',
            valign: 'middle'
        }
        ]
    });
}

function doQuery(){
	 $('#c_list').bootstrapTable("refresh",{silent:true})
}

