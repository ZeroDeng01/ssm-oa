<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html class="bg-white">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改模块</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css"/>
</head>
<body>

<!-- 加载动画，移除位置在common.js中 -->
<div class="page-loading">
    <div class="rubik-loader"></div>
</div>

<form id="authForm" lay-filter="authForm"  class="layui-form model-form">
    <input name="id" type="hidden" value="${id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">权限名称</label>
        <div class="layui-input-block">
            <input name="authorityName" placeholder="请输入权限名称" type="text" class="layui-input" maxlength="50"
                   lay-verify="required" value="${authorityName}" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限标识</label>
        <div class="layui-input-block">
            <input name="authority" placeholder="请输入权限标识" type="text" class="layui-input" value="${authority}"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单url</label>
        <div class="layui-input-block">
            <input name="menuUrl" placeholder="请输入菜单url" type="text" class="layui-input" value="${menuUrl}"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block">
            <input name="menuIcon" placeholder="请输入菜单图标" type="text" class="layui-input" value="${menuIcon}"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否为菜单</label>
        <div class="layui-input-block">

            <input type="radio" name="isMenu" value="0" title="是" <c:if test="${isMenu==0}">checked</c:if>/>
            <input type="radio" name="isMenu" value="1" title="否" <c:if test="${isMenu==1}">checked</c:if>/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序号</label>
        <div class="layui-input-block">
            <input name="orderNumber" placeholder="请输入排序号" type="number" class="layui-input" lay-verify="required" value="${orderNumber}" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上级菜单</label>
        <div class="layui-input-block">
            <select name="parentId">
                <option value="-1">--请选择--</option>
                <c:forEach items="${list}" var="v">
                    <c:if test="${id!=parentId}">
                        <option value="${v.id}" <c:if test="${v.id==parentId}">selected</c:if> >${v.authorityname}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item text-right">
        <button class="layui-btn layui-btn-primary" type="button" ew-event="closeDialog">取消</button>
        <button class="layui-btn" lay-filter="formSubmit" lay-submit>保存</button>
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

        var url = '/System/Modules/modulesMod.do';

        // 回显数据
        var authoritie = admin.getTempData('t_authoritie');
        if (authoritie) {
            url = '/System/Modules/getModulesList.do';
            form.val('authForm', authoritie);
        }


        // 表单提交事件
        form.on('submit(formSubmit)', function (data) {
            layer.load(2);
            $.post(url, data.field, function (data) {
                layer.closeAll('loading');
                if (data.code == 200) {
                    top.layer.msg(data.msg, {icon: 1});
                    admin.putTempData('formOk', true);  // 操作成功刷新表格
                    // 关闭当前iframe弹出层
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                } else {
                    top.layer.msg(data.msg, {icon: 2});
                }
            });
            return false;
        });




    });
</script>

</body>
</html>