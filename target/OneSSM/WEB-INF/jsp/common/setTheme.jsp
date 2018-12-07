<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh" class="bg-white">
<head>
    <title>消息</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css"/>
    <style>
        .layui-card-body {
            padding: 0;
        }

        .theme-div {
            padding-left: 15px;
            padding-top: 20px;
            margin-bottom: 10px;
        }

        .btnTheme {
            display: inline-block;
            margin: 0 6px 15px 0;
            padding: 4px;
            border: 1px solid #fff;
        }

        .btnTheme img {
            width: 80px;
            height: 50px;
            border: 1px solid #f2f2f2;
            background: #F2F2F2;
            cursor: pointer;
        }

        .btnTheme:hover, .btnTheme.active {
            border-color: #5FB878;
        }

        .more-menu-item {
            display: block;
            height: 50px;
            line-height: 50px;
            font-size: 16px;
            border-bottom: 1px solid #e8e8e8;
            color: #333;
            padding: 0px 25px;
            font-style: normal;
        }

        .more-menu-item:first-child {
            border-top: 1px solid #e8e8e8;
        }

        .more-menu-item:hover {
            background: #F2F2F2;
            color: #333;
        }

        .more-menu-item .layui-icon {
            padding-right: 10px;
            font-size: 18px;
        }

        .more-menu-item:after {
            content: "\e602";
            font-family: layui-icon !important;
            position: absolute;
            right: 16px;
        }

        .more-menu-item.no-icon:after {
            content: "";
        }

        /** 设置表单样式 */
        .set-item-label {
            display: inline-block;
            height: 38px;
            line-height: 38px;
            padding-left: 20px;
            color: #333333;
        }

        .set-item-ctrl {
            display: inline-block;
            height: 38px;
            line-height: 38px;
        }

        .set-item-ctrl > * {
            margin: 0;
        }
    </style>
</head>
<body>

<div class="layui-card-header">设置主题</div>
<div class="layui-card-body">
    <div class="theme-div">
        <div class="btnTheme">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_1.png" title="黑白主题">
        </div>
        <div class="btnTheme" theme="theme-black" title="黑色主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_2.png">
        </div>
        <div class="btnTheme" theme="theme-cyan" title="藏青主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_7.png">
        </div>
        <div class="btnTheme" theme="theme-blue-white" title="蓝白主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_3.png">
        </div>
        <div class="btnTheme" theme="theme-blue" title="蓝黑主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_4.png">
        </div>
        <div class="btnTheme" theme="theme-blue-side" title="蓝色主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_5.png">
        </div>
        <div class="btnTheme" theme="theme-white" title="白色主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_6.png" style="border-color: #cccccc;">
        </div>
        <div class="btnTheme" theme="theme-green-dark" title="暗绿主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_9.png">
        </div>
        <div class="btnTheme" theme="theme-green" title="绿色主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_8.png">
        </div>
        <div class="btnTheme" theme="theme-red-white" title="红白主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_12.png">
        </div>
        <div class="btnTheme" theme="theme-red-dark" title="暗红主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_11.png">
        </div>
        <div class="btnTheme" theme="theme-red" title="红色主题">
            <img src="${pageContext.request.contextPath}/assets/images/img_theme_10.png">
        </div>
    </div>


    <div class="layui-form" style="margin: 25px 0;">
        <div class="layui-form-item">
            <label class="set-item-label">页脚：</label>
            <div class="set-item-ctrl" style="margin-right: 15px;">
                <input id="setFooter" lay-filter="setFooter" type="checkbox" lay-skin="switch" lay-text="开启|关闭">
            </div>
            <label class="set-item-label" style="width: auto;">Tab记忆：</label>
            <div class="set-item-ctrl">
                <input id="setTab" lay-filter="setTab" type="checkbox" lay-skin="switch" lay-text="开启|关闭">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="set-item-label">切换Tab自动刷新：</label>
            <div class="set-item-ctrl">
                <input id="setRefresh" lay-filter="setRefresh" type="checkbox" lay-skin="switch" lay-text="开启|关闭">
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common.js"></script>
<script>
    layui.use(['layer', 'form', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var admin = layui.admin;

        // 切换主题
        function doChangeTheme(theme) {
            $('.btnTheme').removeClass('active');
            if (theme) {
                $('.btnTheme[theme=' + theme + ']').addClass('active');
                layui.data(admin.tableName, {key: 'theme', value: theme});
            } else {
                $('.btnTheme').eq(0).addClass('active');
                layui.data(admin.tableName, {key: 'theme', remove: true});
            }
            try {
                top.layui.admin.changeTheme(theme ? getThemeDir() + theme + '.css' : theme);
            } catch (e) {
                console.warn(e);
            }
        }

        doChangeTheme(layui.data(admin.tableName).theme);

        // 切换主题
        $('.btnTheme').click(function () {
            var theme = $(this).attr('theme');
            doChangeTheme(theme);
        });

        // 关闭/开启页脚
        var openFooter = layui.data(admin.tableName).openFooter;
        $('#setFooter').prop('checked', openFooter == undefined ? true : openFooter);
        form.render('checkbox');
        form.on('switch(setFooter)', function (data) {
            var checked = data.elem.checked;
            layui.data(admin.tableName, {key: 'openFooter', value: checked});
            if (checked) {
                top.layui.jquery('body.layui-layout-body').removeClass('close-footer');
            } else {
                top.layui.jquery('body.layui-layout-body').addClass('close-footer');
            }
        });

        // 关闭/开启Tab记忆功能
        var cacheTab = layui.data(admin.tableName).cacheTab;
        $('#setTab').prop('checked', cacheTab == undefined ? true : cacheTab);
        form.render('checkbox');
        form.on('switch(setTab)', function (data) {
            var checked = data.elem.checked;
            layui.data(admin.tableName, {key: 'cacheTab', value: checked});
            top.layui.index.cacheTab = checked;
            if (checked) {
                var tabList = [];
                top.layui.jquery('.layui-body .layui-tab-content .layui-tab-item iframe').each(function (index) {
                    var menuPath = $(this).attr('src');
                    var $title = top.layui.jquery('.layui-body .layui-tab-title li').eq(index);
                    var menuName = $title.html();
                    menuName = menuName.replace('<i class="layui-icon layui-unselect layui-tab-close">ဆ</i>', '');
                    tabList.push({
                        menuPath: menuPath,
                        menuName: menuName
                    });
                });
                admin.putTempData('indexTabs', tabList);
                admin.putTempData('tabPosition', top.layui.jquery('.layui-body .layui-tab-content .layui-tab-item.layui-show iframe').attr('src'));
            } else {
                admin.putTempData('indexTabs', []);
                admin.putTempData('tabPosition', undefined);
            }
        });

        // 切换Tab自动刷新
        var tabAutoRefresh = layui.data(admin.tableName).tabAutoRefresh;
        $('#setRefresh').prop('checked', tabAutoRefresh == undefined ? false : tabAutoRefresh);
        form.render('checkbox');
        form.on('switch(setRefresh)', function (data) {
            var checked = data.elem.checked;
            layui.data(admin.tableName, {key: 'tabAutoRefresh', value: checked});
            if (checked) {
                top.layui.jquery('.layui-body>.layui-tab[lay-filter="admin-pagetabs"]').attr('lay-autoRefresh', 'true');
            } else {
                top.layui.jquery('.layui-body>.layui-tab[lay-filter="admin-pagetabs"]').removeAttr('lay-autoRefresh');
            }
        });

    });
</script>
</body>
</html>