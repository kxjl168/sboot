<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>设置密码找回问题</title>
    <script src="/vendor/jquery/jquery.js" type="text/javascript"></script>

</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">设置密码找回问题</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                密码找回设置
            </div>
            <div class="panel-body">
                <form id="password_safety_form" action="/backend/init/safety"
                      class="form-horizontal" role="form" method="POST">
                    <div class="form-group">
                        <label for="password_questions" class="col-lg-3 control-label">选择一个密码问题</label>

                        <div class="col-lg-6">
                            <select id="password_questions" name="question" class="form-control">
                                <option value="">------ 请选择子项目 ------</option>
                                <option value="1">你爱人的姓名是</option>
                                <option value="2">你出生在哪里</option>
                                <option value="3">你的第一辆车是</option>
                                <option value="4">你最喜欢的运动是</option>
                                <option value="5">你最喜欢电影是</option>
                                <option value="6">你上的小学是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="answer" class="col-lg-3 control-label">设置密码问题答案</label>

                        <div class="col-lg-6">
                            <input type="text" name="answer" class="form-control" id="answer"
                                   placeholder="密码问题答案" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-3 col-lg-8">
                            <button type="submit" class="btn btn-success">保存设置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#password_safety_form").bootstrapValidator({
            feedbackIcons: {
                /*input状态样式通过，刷新，非法三种图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitButtons: 'button[type="submit"]',//提交按钮
            fields: {
                question: {
                    validators: {
                        notEmpty: {
                            message: '密保问题不能为空'
                        }
                    }
                },
                answer: {
                    validators: {
                        notEmpty: {
                            message: '答案不能为空'
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>