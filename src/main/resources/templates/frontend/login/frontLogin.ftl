<!DOCTYPE html>
<!-- saved from url=(0077)https://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/login.html -->
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>中通国脉Mall管理平台</title>
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/vendor/login/css/login.css" rel="stylesheet">
</head>

<body>

<div class="logo"><img src="/img/logo3.png"></div>
<div class="bjtu"></div>
<div class="row denglu">
    <div class="col-md-5"></div>
    <div class="col-md-5">

    <#if error??>
        <div class="col-md-3 alert alert-danger alert-dismissable"
             style="float: left;position: absolute;margin: 0 0 0 235px;width: 448px;">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        ${error!""}
        </div>
    </#if>

        <div class="col-md-3" style="margin: 80px 0 0 220px;float: left">
            <div class="login">
                <div class="login-head">
                    <h3 class="head-title">用户登录</h3>
                    <img href="#" src="/img/zhuce.png">
                </div>
                <div class="login-body content" style="margin-left: 45px;">

                    <form role="form" id="login_form" action="/front/login.action" method="POST">
                        <input type="hidden" name="userType" value="backend">
                        <fieldset>
                            <div class="form-group gt">
                                <img src="/img/user.png" style="margin-left: -200px;"/>
                                <input class="form-control" placeholder="请输入手机号" name="phone" type="text"
                                       value="" autofocus>
                            </div>
                            <div class="form-group gt">
                                <img src="/img/password.png" style="margin-left: -200px;"/>
                                <input class="form-control" placeholder="请输入密码" name="pass" type="password"
                                       value="">
                            </div>

                        <#--<div class="form-group ">
                            <div class="input-group">
                                <input class="form-control" placeholder="输入右侧验证码" name="captchaCode"
                                       id="captcha_code" type="text"
                                       value="">
                                <span class="input-group-addon" id="captcha"><img
                                        src="/validateCode.action" height="20"
                                        id="captcha_image"></span>
                            </div>
                        </div>-->

                            <input type="hidden" name="redirect_uri" value="">
                            <button type="submit"
                                    class="btn btn-primary btn-block btn-color <#if warning??>disable</#if>">
                                登&nbsp;&nbsp;录
                            </button>
                        </fieldset>


                    </form>

                </div>
                <div class="row " style="margin-top:30px;margin-left: 45px;">
                    <div class="col-md-3">
                        <div id="qrcode"></div>
                    </div>
                    <div class="col-md-7" style="padding-top:50px">
                        &nbsp;&nbsp;<a href="/interface/downloadapp">扫二维码或点击下载APP</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="footer"><p>Copyright © 2018 中通国脉物联科技南京有限公司版权所有.</p></div>

<script type="text/javascript">

    //alert(22);
    //    $('#login_form input').attr('readonly', 'readonly');
    //$('#login_form').find('button').attr('disabled', 'disabled');
</script>

<script src="/vendor/password/js/password.js" type="text/javascript"></script>
<script src="/vendor/metisMenu/metisMenu.min.js"></script>
<script src="/vendor/login/js/qrcode.js"></script>
<script src="/vendor/login/js/login.js"></script>
</body>
</html>