<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath}/assets/images/favicon.ico" rel="icon">
    <title>ZeroDeng综合管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css"/>
</head>

<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <!-- 头部 -->
    <div class="layui-header">

        <div class="layui-logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png"/>
            <cite>&nbsp;ZeroDeng&emsp;</cite>
        </div>

        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="flexible" title="侧边伸缩"><i class="layui-icon layui-icon-shrink-right"></i></a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="refresh" title="刷新"><i class="layui-icon layui-icon-refresh-3"></i></a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" lay-unselect>
                <a id="btnMessage" title="消息"><i class="layui-icon layui-icon-notice"></i></a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
                <a ew-event="fullScreen" title="全屏"><i class="layui-icon layui-icon-screen-full"></i></a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a>
                    <img src="${pageContext.request.contextPath}/assets/images/head.png" class="layui-nav-img">
                    <cite>${UserName}</cite>
                </a>
                <dl class="layui-nav-child">
                    <dd lay-unselect>
                        <a id="setInfo">个人信息</a>
                    </dd>
                    <dd lay-unselect>
                        <a id="setPsw">修改密码</a>
                    </dd>
                    <hr>
                    <dd lay-unselect>
                        <a id="btnLogout">退出</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="theme"  title="主题"><i class="layui-icon layui-icon-more-vertical"></i></a>
            </li>
        </ul>

    </div>

    <!-- 侧边栏 -->
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree arrow2" lay-filter="admin-side-nav" lay-accordion="true"
                style="margin: 15px 0;">
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-home"></i>&emsp;<cite>系统主页</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="page/console/console2.html">主页</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-unlink"></i>&emsp;<cite>系统设置</cite></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a>模块配置</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a lay-href="/System/Modules/ModulesList.do">模块管理</a>
                                </dd>
                            </dl>
                        </dd>
                        <dd>
                            <a>用户管理</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a lay-href="/System/Users/UsersList.do">用户信息</a>
                                </dd>
                            </dl>
                        </dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <!-- 主体部分 -->
    <div class="layui-body">
        <div class="layui-tab" lay-allowClose="true" lay-filter="admin-pagetabs">
            <ul class="layui-tab-title">
            </ul>
            <div class="layui-tab-content">
            </div>
        </div>
        <div class="layui-icon admin-tabs-control layui-icon-prev" ew-event="leftPage"></div>
        <div class="layui-icon admin-tabs-control layui-icon-next" ew-event="rightPage"></div>
        <div class="layui-icon admin-tabs-control layui-icon-down">
            <ul class="layui-nav admin-tabs-select" lay-filter="admin-pagetabs-nav">
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;"></a>
                    <dl class="layui-nav-child layui-anim-fadein">
                        <dd ew-event="closeThisTabs" lay-unselect><a href="javascript:;">关闭当前标签页</a></dd>
                        <dd ew-event="closeOtherTabs" lay-unselect><a href="javascript:;">关闭其它标签页</a></dd>
                        <dd ew-event="closeAllTabs" lay-unselect><a href="javascript:;">关闭全部标签页</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!-- 底部 -->
    <div class="layui-footer">
        Copyright © 2018 <a href="#" target="_blank">ZeroDeng</a> All rights reserved.
        <span class="pull-right">Version 3.0.6</span>
    </div>

    <!-- 手机屏幕遮罩层 -->
    <div class="site-mobile-shade"></div>

</div>

<!-- 加载动画，移除位置在common.js中 -->
<div class="page-loading">
    <div class="rubik-loader"></div>
</div>

<!-- js部分 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common.js"></script>
<script>
    layui.use(['layer', 'element', 'admin', 'index'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var admin = layui.admin;
        var index = layui.index;

        index.loadSetting();  // 加载本地缓存的设置属性

        // 默认加载主页
        index.loadView({
            menuPath: 'page/console/console.html',
            menuName: '<i class="layui-icon layui-icon-home"></i>'
        });

        // 消息点击事件
        $('#btnMessage').click(function () {
            admin.popupRight({
                type: 2,
                content: '/messagePage.do'
            });
        });

        // 修改密码点击事件
        $('#setPsw').click(function () {
            admin.open({
                type: 2,
                title: '修改密码',
                shade: 0,
                content: '/setPasswordPage.do'
            });
        });

        // 退出登录点击事件
        $('#btnLogout').click(function () {
            layer.confirm('确定退出登录？', {
                skin: 'layui-layer-admin'
            }, function () {
                location.replace('/UserLoginOut.do');
            });
        });

        // 个人信息点击事件
        $('#setInfo').click(function () {

        });

    });
</script>
</body>

</html>