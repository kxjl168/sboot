function subAddMember(){

    var telephone = $.trim($("#phone").val());
    if ("" == telephone) {
        showPopover($("#phone"), "请输入手机号");
        return;
    }

    $.ajax({
        type: "post",
        url: '/manager/group/addMember.action',
        data: {phone: $("#phone").val()},
        async: false,
        dataType: "json",
        success: function (data) {

            if (data.bol) {
                window.location.href = "/manager/group/etrgroupList.action";
            } else {
                showPopover($("#showmessage"), data.message);
            }
        }
    });

}

function delMember(userId){

    $.ajax({
        type: "post",
        url: '/manager/group/delMember.action',
        data: {userId: userId},
        async: false,
        dataType: "json",
        success: function (data) {

            if (data.bol) {
                window.location.href = "/manager/group/etrgroupList.action";
            } else {
                showPopover($("#showmessage"), data.message);
            }
        }
    });
}

function showPopover(target, msg) {
    target.attr("data-original-title", msg);
    $('[data-toggle="tooltip"]').tooltip();
    target.tooltip('show');
    target.focus();

    //2秒后消失提示框
    var id = setTimeout(
        function () {
            target.attr("data-original-title", "");
            target.tooltip('hide');
        }, 2000
    );
}