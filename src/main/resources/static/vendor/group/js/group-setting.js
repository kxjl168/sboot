$(function () {

    flushGroupRegion();
    flushGroupDevice();

    //每次关闭弹窗，清空选择
    $('#deviceModel').on('hidden.bs.modal', function (e) {
        $("input[name=devIdList]:checked").prop("checked",false);
    });

    $('#regionModel').on('hidden.bs.modal', function (e) {
        $("input[name=regionIdList]:checked").prop("checked",false);
    });
});

function flushGroupRegion() {
    //获取组 区域
    $.ajax({
        type: "post",
        url: '/manager/group/groupRegions.action',
        data: {groupId: $("#_groupId").val()},
        dataType: "json",
        success: function (data) {
            $("#showregion").empty();
            for(var i=0;i<data.length;i++){
                var d=data[i];
                var str="<div class=\"col-sm-2 mokuai \">"+
                    "<span style=\"line-height: 42px;\">"+
                    d.name+"</span>"+
                    "<input type='hidden' name='showRegionId' value='"+d.id+"'>"+
                    "<img class=\"delOneRegion\" src=\"/img/sc.png\" onclick=\"delOneRegion($(this))\">"+
                    "</div>";
                $("#showregion").append(str);
            }

        }
    });
}
function delOneRegion(e) {
    $(e).parent().remove();
}

function flushGroupDevice() {
    //获取组设备
    $.ajax({
        type: "post",
        url: '/manager/group/groupDevices.action',
        data: {groupId: $("#_groupId").val()},
        dataType: "json",
        success: function (data) {
            $("#showdev").empty();
            for(var i=0;i<data.length;i++){
                var d=data[i];
                var str="<div class=\"col-sm-2 mokuai\">"+
                    "<span style=\"line-height: 42px;\">"+
                    d.name+"</span>"+
                    "<input type='hidden' name='showDeviceId' value='"+d.id+"'>"+
                    "<img class=\"delOneDev\" src=\"/img/sc.png\" onclick=\"delOneDevice($(this))\">"+
                    "</div>";
                $("#showdev").append(str);
            }

        }
    });
}
function delOneDevice(e) {
    $(e).parent().remove();
}

//打开 获取 区域
function openRegionDialog() {

    $.ajax({
        type: "post",
        url: '/manager/group/userAllRegionsExGroup.action',
        data: {groupId: $("#_groupId").val()},
        dataType: "json",
        success: function (data) {
            $("#selRegion").empty();
            for(var i=0;i<data.length;i=i+2){
                var d=data[i];
                var str1='<div class="checkbox col-sm-1" style="margin-left:15px;padding-top:10px;width: 15px;"><input type="checkbox" name="regionIdList" value="'+d.id+'" ></div>'+
                        '<div class="col-sm-4 mokuai2" onclick="change($(this))">'+
                        '<span style="line-height: 42px;">'+d.name+'</span>'+
                        '</div>';
                var str='';
                if(i+1<data.length){
                    d=data[i+1];
                    var str2='<div class="checkbox col-sm-1" style="margin-left:15px;padding-top:10px;width: 15px;"><input type="checkbox" name="regionIdList" value="'+d.id+'" ></div>'+
                        '<div class="col-sm-4 mokuai2" onclick="change($(this))">'+
                        '<span style="line-height: 42px;">'+d.name+'</span>'+
                        '</div>';
                     str='<div class="row">'+str1+str2+'</div>';
                }else{
                    str='<div class="row">'+str1+'</div>';
                }
                /* 格式：
        <div class="row" >
        <div class="col-sm-5 mokuai2" onclick="change($(this))">
        <span style="line-height: 42px;">区域名称1</span>
        </div>
        <div class="col-sm-5 mokuai2" onclick="change($(this))">
        <span style="line-height: 42px;">区域名称1</span>
        </div>
        </div>
*/
                $("#selRegion").append(str);

            }
            $("#selRegion")
            $('#regionModel').modal('show');

        }
    });

}

function openDeviceDialog() {

    //获取 设备
    $.ajax({
        type: "post",
        url: '/manager/group/userAllDevicesExGroup.action',
        data: {groupId: $("#_groupId").val()},
        dataType: "json",
        success: function (data) {
            $("#selDevice").empty();
            for(var i=0;i<data.length;i=i+2){
                var d=data[i];
                var str1='<div class="checkbox col-sm-1" style="margin-left:15px;padding-top:10px;width: 15px;"><input type="checkbox" name="devIdList" value="'+d.id+'" ></div>'+
                    '<div class="col-sm-5 mokuai2" onclick="change($(this))">'+
                    '<span style="line-height: 42px;">'+d.name+'</span>'+
                    '</div>';
                var str='';
                if(i+1<data.length){
                    d=data[i+1];
                    var str2='<div class="checkbox col-sm-1" style="margin-left:15px;padding-top:10px;width: 15px;"><input type="checkbox" name="devIdList" value="'+d.id+'" ></div>'+
                        '<div class="col-sm-5 mokuai2" onclick="change($(this))">'+
                        '<span style="line-height: 42px;">'+d.name+'</span>'+
                        '</div>';
                    str='<div class="row">'+str1+str2+'</div>';
                }else{
                    str='<div class="row">'+str1+'</div>';
                }
                /* 格式：
        <div class="row" >
        <div class="col-sm-5 mokuai2" onclick="change($(this))">
        <span style="line-height: 42px;">区域名称1</span>
        </div>
        <div class="col-sm-5 mokuai2" onclick="change($(this))">
        <span style="line-height: 42px;">区域名称1</span>
        </div>
        </div>
        */
                $("#selDevice").append(str);

            }
            $('#deviceModel').modal('show');

        }
    });

}
//新增组区域。删除组区域也调用该方法
function saveGroupRegion(isDelReg) {

    var _reginIdList="";
    $("input[name=regionIdList]:checked").each(function (e) {
        _reginIdList+='"'+$(this).val()+'",';
    });
    if(_reginIdList==""&&isDelReg==undefined){ //没有选择区域，如果是新增，则返回。如果删除，放行
        return;
    }
    //显示div中的 区域
    $("input[name=showRegionId]:hidden").each(function (e) {
        _reginIdList+='"'+$(this).val()+'",';
    });
    _reginIdList="["+_reginIdList.substring(0,_reginIdList.length-1)+"]";

    //设置组 区域
    $.ajax({
        type: "post",
        url: '/manager/group/saveGroupRegion',
        data: {groupId: $("#_groupId").val(),regionIdList:_reginIdList},
        success: function (data) {
            $('#regionModel').modal('hide');
            flushGroupRegion();
            //如果是删除区域
            if(isDelReg!=undefined){
                $("#delregbtn").removeClass("delregbtncls");
                $("#delregbtn").text("删除区域");
                $("#saveregbtn").prop("disabled",false);
                $(".delOneRegion").css("display","none");
                $("#delRegSure").css("display","none");
            }
        }
    });

}
//新增组设备。删除组设备也调用该方法
function saveGroupDevice(isDelDev) {

    var _devIdList="";
    $("input[name=devIdList]:checked").each(function (e) {
        _devIdList+='"'+$(this).val()+'",';
    });
    if(_devIdList==""&&isDelDev==undefined){ //没有选择设备，如果是新增，则返回。如果删除，放行
        return;
    }
    //显示div中的设备
    $("input[name=showDeviceId]:hidden").each(function (e) {
        _devIdList+='"'+$(this).val()+'",';
    });
    _devIdList="["+_devIdList.substring(0,_devIdList.length-1)+"]";

    //设置组 设备
    $.ajax({
        type: "post",
        url: '/manager/group/saveGroupDevice',
        data: {groupId: $("#_groupId").val(),devIdList:_devIdList},
        success: function (data) {
            $('#deviceModel').modal('hide');
            flushGroupDevice();
            //如果是删除设备
            if(isDelDev!=undefined){
                $("#deldevbtn").removeClass("deldevbtncls");
                $("#deldevbtn").text("删除设备");
                $("#savedevbtn").prop("disabled",false);
                $(".delOneDev").css("display","none");
                $("#delDevSure").css("display","none");
            }
        }
    });

}

//点击删除区域按钮
function showDelRegion(){

    if(!$("#delregbtn").hasClass("delregbtncls")){
        $("#delregbtn").addClass("delregbtncls");
        $("#delregbtn").text("返回");
        $("#saveregbtn").prop("disabled",true);
        $(".delOneRegion").css("display","block");
        $("#delRegSure").css("display","block");
    }else{
        $("#delregbtn").removeClass("delregbtncls");
        $("#delregbtn").text("删除区域");
        $("#saveregbtn").prop("disabled",false);
        $(".delOneRegion").css("display","none");
        $("#delRegSure").css("display","none");
        flushGroupRegion();
    }

}
//点击删除设备按钮
function showDelDev(){

    if(!$("#deldevbtn").hasClass("deldevbtncls")){
        $("#deldevbtn").addClass("deldevbtncls");
        $("#deldevbtn").text("返回");
        $("#savedevbtn").prop("disabled",true);
        $(".delOneDev").css("display","block");
        $("#delDevSure").css("display","block");
    }else{
        $("#deldevbtn").removeClass("deldevbtncls");
        $("#deldevbtn").text("删除设备");
        $("#savedevbtn").prop("disabled",false);
        $(".delOneDev").css("display","none");
        $("#delDevSure").css("display","none");
        flushGroupDevice();
    }

}

function changeSrc(){
    if($("#quanxuan").attr("src")=="/img/xz.png"){
        $("#selRegion :checkbox").prop("checked",false);
        $("#quanxuan").attr("src","/img/yuan.png");
    }else{
        $("#quanxuan").attr("src","/img/xz.png");
        $("#selRegion :checkbox").prop("checked",true);
    }

}
function changeSrc2(){

    if($("#quanxuan2").attr("src")=="/img/xz.png"){
        $("#selDevice :checkbox").prop("checked",false);
        $("#quanxuan2").attr("src","/img/yuan.png");
    }else{
        $("#quanxuan2").attr("src","/img/xz.png");
        $("#selDevice :checkbox").prop("checked",true);
    }
}