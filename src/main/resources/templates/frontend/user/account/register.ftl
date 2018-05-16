<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>注册用户</title>
<#--密码强度样式-->
    <link rel="stylesheet" href="/vendor/password/css/pwdStrength.css">
    <link href="/vendor/fileinput/css/fileinput.min.css" rel="stylesheet">

    <script src="/vendor/util/js/emailHint.js" type="text/javascript"></script>
    <script src="/vendor/fileinput/js/fileinput.min.js" type="text/javascript"></script>
    <script src="/vendor/fileinput/js/locales/zh.js" type="text/javascript"></script>
<#--显示密码-->
    <script src="/vendor/password/js/password.js" type="text/javascript"></script>
<#--密码强度js-->
    <script src="/vendor/password/js/pwdStrength.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">注册用户</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                &nbsp;
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="register_form" action="/user/register" class="form-horizontal"
                              enctype="multipart/form-data"
                              role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">用户名</label>

                                <div class="col-lg-6">
                                    <input type="text" name="account" class="form-control" id="account"
                                           placeholder="您的账户名和登录名">
                                    <p class="help-block">请输入您的用户名，不超过16个汉字或32个字符</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-3 control-label">设置密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="您的登录密码">
                                    <p class="help-block">建议使用大、小字母加数字的组合</p>
                                    <div id="strong">
                                        <div class="progress-bar" role="progressbar"></div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="confirm_password" class="col-lg-3 control-label">确认密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="confirm_password" class="form-control"
                                           id="confirm_password"
                                           placeholder="请再次输入密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-3 control-label">电子邮箱</label>

                                <div class="col-lg-6">
                                    <input type="text" name="email" class="form-control" id="email"
                                           placeholder="电子邮箱" list="input_list">
                                    <datalist id="input_list"></datalist>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="cellphone" class="col-lg-3 control-label">手机号码</label>

                                <div class="col-lg-4">
                                    <input type="text" name="cellphone" class="form-control" id="cellphone"
                                           placeholder="手机号码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nick_name" class="col-lg-3 control-label">昵称/机构全称</label>

                                <div class="col-lg-6">
                                    <input type="text" name="nickName" class="form-control" id="nick_name"
                                           placeholder="昵称/机构全称">
                                </div>
                            </div>
                            <div class="form-group" id="stamp_group">
                                <label for="app_logo" class="col-lg-3 control-label">请上传附件图片</label>

                                <div class="col-lg-6">
                                    <input type="file" name="identityImage" id="app_logo" class="file-loading"
                                           value="">
                                    <script type="text/javascript">
                                        $("#app_logo").fileinput({
                                            language: 'zh',
                                            allowedFileTypes: ['image'],
                                            allowedFileExtensions: ["jpg", "gif", "png", "jpeg"],
                                            showUpload: false,
                                            maxFileCount: 1,
                                            maxFileSize: 1024,
                                            initialCaption: '仅支持 gif,png,jpg,jpeg 格式的图片',
                                            'previewFileType': 'image'
                                        });

                                    </script>
                                    <p class="help-block">请上传机构三证合一统一代码证件资料图片,图片大小不能超过1M</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <button type="submit" class="btn btn-primary btn-lg">立即注册</button>
                                    <div class="help-block">
                                        点击"立即注册"，即表示您同意并愿意遵守&nbsp;<a
                                            href="/info/user/agreement">用户协议</a>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //显示隐藏help-block
        $("input").each(function (i) {
            $(this).focus(function () {
                $("input").eq(i).parent().find('p').css('display', 'none');
            }).blur(function () {
                $("input").eq(i).parent().find('p').css('display', 'block');
            });
        });
        $("#password").focus(function () {
            $(this).parent().parent().find('p').css('display', 'none');
        }).blur(function () {
            $(this).parent().parent().find('p').css('display', 'block');
        });
        //表单验证
        $("#register_form").bootstrapValidator({
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
                            message: '用户名不能为空'
                        },
                        stringLength: {//长度
                            min: 1,
                            max: 16,
                            message: '用户名长度不能超过16位'
                        },
                        regexp: {//字符正则匹配
                            regexp: /^[a-zA-Z0-9_.\u4e00-\u9fa5]+$/i,
                            message: '用户名由汉字、数字、字母、下划线或者小数点组成'
                        },
                        remote: {//后台验证是否存在，返回json数据，正确：{"valid",true}
                            url: '/user/account/validate',//请求地址
                            delay: 1000,//每隔1秒发送ajax请求，减轻服务器压力
                            type: 'POST',//请求方式，默认get
                            message: '用户名已存在'//若json返回false则提示该信息
                        }
                    }
                },
                nickName: {
                    validators: {
                        notEmpty: {//非空
                            message: '昵称不能为空'
                        },
                        stringLength: {//长度
                            min: 1,
                            max: 16,
                            message: '昵称长度不能超过16位'
                        },
                        regexp: {//字符正则匹配
                            regexp: /^[a-zA-Z0-9_.\u4e00-\u9fa5]+$/i,
                            message: '昵称由汉字、数字、字母、下划线或者小数点组成'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度须在6到18之间'
                        },
                        different: {//不能和用户名相同
                            field: 'account',//需要进行比较的input name值
                            message: '不能和用户名相同'
                        },
                        callback: {
                            message: '密码强度太低',
                            callback: function () {
                                var $power = $(".progress-bar").text();
                                console.log($power);
                                if ($power == "" || $power == "极弱") {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    }
                },
                confirm_password: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {//相同
                            field: 'password',
                            message: '两次密码不一致'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮件地址不能为空'
                        },
                        emailAddress: {
                            message: '请输入正确的邮件地址,如：xx@xx.com'
                        }
                    }
                },
                cellphone: {
                    validators: {
                        notEmpty: {
                            message: '手机号码不能为空'
                        },
                        regexp: {
                            regexp: /^1[34578][0-9]{9}$/,
                            message: '请输入正确的11位手机号码，例如13823456789'
                        }
                    }
                },
                identityImage: {
                    validators: {
                        notEmpty: {//非空
                            message: '附件图片不能为空'
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