<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>管理员登录</title>
    <script src="/vendor/jquery/jquery.js" type="text/javascript"></script>
    <script src="/vendor/backstretch/js/jquery.backstretch.min.js"
            type="text/javascript"></script>
<#--显示密码-->
    <script src="/vendor/password/js/password.js" type="text/javascript"></script>
</head>
<body>

<script type="text/javascript">
    $.backstretch(["/img/login-bg.jpg"]);

    function switchLoginForm(formType) {
        var formAccount = $('#login_form');
        var btnAccount = $('#account_btn');
        var formCertificate = $('#certificate_login_form');
        var btnCertificate = $('#certificate_btn');

        if (formType == 0) {
            formCertificate.hide();
            btnAccount.hide();

            formAccount.removeClass();
            formAccount.show();

            btnCertificate.removeAttr("style");
            btnCertificate.addClass("btn btn-xs btn-danger pull-right");
            $('#captcha').click();
        } else {
            formAccount.hide();
            btnCertificate.hide();

            formCertificate.removeClass();
            formCertificate.show();

            btnAccount.removeAttr("style");
            btnAccount.removeClass();
            btnAccount.addClass("btn btn-xs btn-success pull-right");
        }

    }
</script>

<div class="row">
    <div class="col-lg-4 col-lg-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">
                <p>欢迎您来到&nbsp;<a href="/login/backend/index">&nbsp;${Application.config.getPlatformName()?default("时间戳服务管理平台")} <#include "../../common/version.ftl"/></a>&nbsp;,
                    <strong>请登录</strong></p>
            </div>
            <div class="panel-body">
            <#if error??>
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                ${error!}
                </div>
            </#if>
            <#if prompt??>
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                ${prompt!}
                </div>
            </#if>
                <div class="col-lg-12">
                    <form role="form" id="login_form" action="/login/backend/submit"
                          method="POST">

                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="您的用户名" name="account" type="text"
                                       value="${loginForm.account!}" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="您的密码" name="password" type="password"
                                       value="${loginForm.password!}"">
                            </div>
                            <div class="form-group ">
                                <div class="input-group">
                                    <input class="form-control" placeholder="输入右侧验证码" name="captchaCode"
                                           id="captcha_code" type="text"
                                           value="">
                                    <span class="input-group-addon" id="captcha"><img
                                            src="/captcha/get" height="20"
                                            id="captcha_image"></span>
                                </div>
                            </div>
                            <input type="hidden" name="redirect_uri" value="${(loginForm.redirect_uri)!}">
                            <button type="submit" class="btn btn-primary btn-block">
                                登&nbsp;&nbsp;录
                            </button>
                        </fieldset>
                    </form>

                    <form role="form" id="certificate_login_form" action="" method="POST" class="hidden">
                        <fieldset>
                            <div class="form-group">
                                <label>&nbsp;</label>
                                <div class="col-lg-10 col-lg-offset-1">
                                    <img src="/img/usbkey.png"
                                         class="img-responsive"/>
                                    <br>
                                    <p class="help-block"><span class="label label-danger">提示：</span>&nbsp;使用USB
                                        Key的用户,请确认Key已插入电脑
                                    </p>
                                </div>
                            </div>

                            <input type="hidden" id="id_certificate" name="certificate" value="">
                            <input type="hidden" id="id_signature" name="signature" value="">
                            <input type="hidden" name="redirect_uri" value="">
                            <!-- Change this to a button or input when using this as a form -->
                            <div class="form-group">
                                <label></label>
                                <div>
                                    <button type="button" class="btn btn-primary btn-block">
                                        数字证书登录
                                    </button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
            <div class="panel-footer">
                <a href="/backend/admin/account/password/recover">忘记密码</a>
                <button id="certificate_btn" class="btn btn-xs btn-danger pull-right" onclick="switchLoginForm(1);">
                    CA证书登录
                </button>
                <button id="account_btn" class="hidden" onclick="switchLoginForm(0);">
                    账号登录
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#captcha_code').keyup(function (e) {
        if (e.keyCode == 13) {
            $('#login_form').submit();
        }
    });

    $('#captcha').click(function () {
        document.getElementById('captcha_image').src = '/captcha/get?time=' + new Date();
    });
    //验证
    $(function () {
        $("#login_form").bootstrapValidator({
            feedbackIcons: {
                /*input状态样式通过，刷新，非法三种图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitButtons: 'button[type="submit"]',//提交按钮
            fields: {
                account: {//验证账户
                    validators: {
                        notEmpty: {//非空
                            message: '登录名不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });

        //显示密码
        $('#password').password().on('show.bs.password', function (e) {
        }).on('hide.bs.password', function (e) {
        });
    });
</script>
</body>
</html>