$(function() {
	
	

	initClist();
});


function tuikuan(type) {
	 
	
	window.location.href="/userCenter/shou/exchange?type="+type+"&orderDetailId="+$("#id").val();
	
	return;
	
	
}


function doQuery(){
	 $('#c_list').bootstrapTable("refresh",{silent:true})
}

