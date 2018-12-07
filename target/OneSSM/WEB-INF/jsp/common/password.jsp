<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html class="bg-white">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css"/>
</head>
<body>

<!-- 加载动画，移除位置在common.js中 -->
<div class="page-loading">
    <div class="rubik-loader"></div>
</div>

<form class="layui-form model-form" id="form-psw">
    <div class="layui-form-item">
        <label class="layui-form-label">原始密码:</label>
        <div class="layui-input-block">
            <input type="password" name="oldPsw" placeholder="请输入原始密码" class="layui-input"
                   lay-verType="tips" lay-verify="required" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码:</label>
        <div class="layui-input-block">
            <input type="password" name="newPsw" placeholder="请输入新密码" class="layui-input"
                   lay-verType="tips" lay-verify="required|psw" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码:</label>
        <div class="layui-input-block">
            <input type="password" name="rePsw" placeholder="请再次输入新密码" class="layui-input"
                   lay-verType="tips" lay-verify="required|repsw" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closeDialog">取消</button>
            <button class="layui-btn" lay-filter="submit-psw" lay-submit>保存</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common.js"></script>
<script>
    layui.use(['layer', 'form', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var admin = layui.admin;

        admin.iframeAuto();  // 让当前iframe弹层高度适应

        // 监听提交
        form.on('submit(submit-psw)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        // 添加表单验证方法
        form.verify({
            psw: [/^[\S]{5,12}$/, '密码必须5到12位，且不能出现空格'],
            repsw: function (t) {
                if (t !== $('#form-psw input[name=newPsw]').val()) {
                    return '两次密码输入不一致';
                }
            }
        });

    });
</script>
</body>
</html>