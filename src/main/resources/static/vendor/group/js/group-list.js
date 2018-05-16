
$(function(){

});

function searchGroup() {
    var key=$("#searchKey").val();
    if(key=="") return;
    $("#pageForm").find("pn").val(1); // pageNo
    $("#pageForm").append("<input type='hiden' name='groupName' value='"+key+"'>");
    $("#pageForm").attr("action","/manager/group/etrGroupList");
    $("#pageForm").submit();
}

function openNewDialog() {
    $("#modeltitle").text("新建权限组名称");
    $("#modeltitle2").text("新建组名");
    $("#groupId").val("");
    $("#groupName").val("");
    $('#myModal').modal('show');
}
function openDialog(gname,gid) {

    $("#modeltitle").text("修改权限组名称");
    $("#modeltitle2").text("修改组名");
    $("#groupName").val(gname);
    $("#groupId").val(gid);

    $('#myModal').modal('show');

}

function subOper() {
    if($("#groupId").val()==""){
        subAdd();
    }else{
        updateGroupName($("#groupId").val());
    }

}

function subAdd(){

    var groupName = $.trim($("#groupName").val());
    if ("" == groupName) {
        showPopover($("#groupName"), "请输入组名称");
        return;
    }

    $.ajax({
        type: "post",
        url: '/manager/group/addGroup.action',
        data: {groupName: $("#groupName").val()},
        success: function (data) {
            if (data=='success') {
                showPopover($("#showmessage"), "新增成功！");
                location.reload();
                //location.href="/manager/group/etrGroupList";
            } else {

            }
        }
    });

}

function updateGroupName(groupId) {
    var groupName = $.trim($("#groupName").val());
    if ("" == groupName) {
        showPopover($("#groupName"), "请输入组名称");
        return;
    }

    $.ajax({
        type: "post",
        url: '/manager/group/updateGroupName.action',
        data: {"groupId":groupId,"groupName": $("#groupName").val()},
        success: function (data) {

            if (data=='success') {
                showPopover($("#showmessage"),"修改成功！");
                //location.href="/manager/group/etrGroupList";
                location.reload();
            } else {

            }
        }
    });
}


function delGroup(groupId) {
    if(!confirm("确定要删除该权限组吗?")){
        return;
    }
    $.ajax({
        type: "post",
        url: '/manager/group/delGroup.action',
        data: {"groupId":groupId},
        success: function (data) {

            if (data=='success') {
                location.reload();
                showPopover($("#showmessage"),"删除成功！");
                //location.href="/manager/group/etrGroupList";

            } else {

            }
        }
    });
}

function etrGroupSetting(groupId) {
    $("#_groupId").val(groupId);
    $("#etrSettingFm").trigger("submit");

}