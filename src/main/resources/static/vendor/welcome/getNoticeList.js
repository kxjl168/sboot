var noticeList = getNoticeList();

function getNoticeList() {
    $.post('/manager/user/getNoticeList').done(function (data) {
        if (data.length > 5) {
            for (var i = 0; i < 5; i++) {
                $("#noticeList").append(
                    "<tr onclick=\"updateNoticeRead(this)\" class=\"gradeA odd\" role=\"row\" id=" + data[i].id + ">\n" +
                    "     <td>" + data[i].rowNo + "</td>\n" +
                    "     <td>" + data[i].title + "</td>\n" +
                    "     <td class='td nowrap'>" + data[i].msg + "</td>" +
                    "     <td>" + data[i].createDate + "</td>\n" +
                    "</tr>"
            )
                if (data[i].status == 0) {
                    $(".nowrap").css("color", "#fb6e56")
                }
            }
        } else {
            for (var i = 0; i < data.length; i++) {
                $("#noticeList").append(
                    "<tr onclick=\"updateNoticeRead(this)\" class=\"gradeA odd\" role=\"row\" id=" + data[i].id + ">\n" +
                    "     <td>" + data[i].rowNo + "</td>\n" +
                    "     <td>" + data[i].title + "</td>\n" +
                    "     <td class='td nowrap'>" + data[i].msg + "</td>" +
                    "     <td>" + data[i].createDate + "</td>\n" +
                    "</tr>"
            )
                if (data[i].status == 0) {
                    $(".nowrap").css("color", "#fb6e56")
                }
            }
        }
    })
}

function updateNoticeRead(obj) {
    var noticeId = obj.id;
    var msgColor = $("#" + noticeId + " > .nowrap").css("color");
    var title = $("#" + noticeId + " td:nth-child(2)").text();
    var msg = $("#" + noticeId + " td:nth-child(3)").text();
    var date = $("#" + noticeId + " td:nth-child(4)").text();

    $(".modal-body div:nth-child(1) .xxnr").text(title)
    $(".modal-body div:nth-child(2) .xxnr").text(date)
    $(".modal-body div:nth-child(3) .xxnr").text(msg)
    $("#myModal").modal('show');

    if (msgColor == "rgb(251, 110, 86)") {
        $.post('/manager/user/updateNoticeRead', {"id" : noticeId}).done(function () {
            $("#noticeList").empty();
            getNoticeList ();
        })
    }

    /*    console.log("msgColor:" + msgColor);
        console.log("title:" + title);
        console.log("msg:" + msg);
        console.log("date:" + date);
        console.log(msgColor == "rgb(251, 110, 86)")*/
}