<%--
  Created by IntelliJ IDEA.
  User: ZeroDeng
  Date: 2018-12-07
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <title>模块管理</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css" media="all"/>
</head>

<body>

<!-- 加载动画，移除位置在common.js中 -->
<div class="page-loading">
    <div class="rubik-loader"></div>
</div>

<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-inline">
                <label class="layui-form-label w-auto">搜索：</label>
                <div class="layui-input-inline mr0">
                    <input id="edtSearch" class="layui-input" type="text" placeholder="输入关键字"/>
                </div>
            </div>
            <div class="layui-inline">
                <button id="btnSearch" class="layui-btn icon-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
                <button class="layui-btn layui-btn icon-btn" id="btnAdd"><i class="layui-icon layui-icon-add-circle"></i>添加</button>

                <button class="layui-btn" id="btn-expand">全部展开</button>
                <button class="layui-btn" id="btn-fold">全部折叠</button>
            </div>
            <table id="auth-table" class="layui-table" lay-filter="auth-table"></table>
        </div>
    </div>

</div>

<!-- 操作列 -->
<script type="text/html" id="tableBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<!-- js部分 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common.js"></script>

<script>
    layui.use(['layer', 'form', 'table', 'treetable'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var treetable = layui.treetable;
        var admin = layui.admin;

        form.render('select');

        // 渲染表格
        function renderTable() {

            // 渲染表格
            treetable.render({
                treeColIndex: 1,
                treeSpid: -1,
                treeIdName: 'id',
                treePidName: 'parentid',
                elem: '#auth-table',
                url: '/System/Modules/getModulesList.do',
                page: false,
                cellMinWidth: 100,
                cols: [[
                    {type: 'numbers'},
                    {field: 'authorityname', minWidth: 200, title: '系统名称'},
                    {field: 'authority', title: '权限标识'},
                    {field: 'menuurl', title: '菜单url'},
                    {field: 'ordernumber', minWidth: 80, align: 'center', title: '排序号'},
                    {
                        field: 'ismenu', minWidth: 80, align: 'center', templet: function (d) {
                            if (d.ismenu == 1) {
                                return '<span class="layui-badge layui-bg-gray">按钮</span>';
                            }
                            if (d.parentid == -1) {
                                return '<span class="layui-badge layui-bg-blue">系统</span>';
                            } else {
                                return '<span class="layui-badge-rim">菜单</span>';
                            }
                        }, title: '类型'
                    },
                    {templet: '#tableBar', minWidth: 120, align: 'center', title: '操作'}
                ]],
                done: function () {
                    layer.closeAll('loading');
                }
            });
        }


        renderTable();

        $('#btn-expand').click(function () {
            treetable.expandAll('#auth-table');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#auth-table');
        });

        // 工具条点击事件
        table.on('tool(auth-table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'edit') { // 修改
                layer.msg("修改");
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                layer.msg("删除");
                doDelete(obj.data.authorityId);
            }
        });

        // 删除
        function doDelete(authorityId) {
            top.layer.confirm('确定删除此模块吗？', function () {
                layer.load(2);
                $.post('authorities/delete', {
                    authorityId: authorityId
                }, function (data) {
                    layer.closeAll('loading');
                    if (data.code == 200) {
                        layer.msg(data.msg, {icon: 1});
                        renderTable();
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                });
            });
        }


        // 添加按钮点击事件
        $('#btnAdd').click(function () {
            showEditModel();
        });
        // 显示表单弹窗
        function showEditModel(data) {
            admin.putTempData('t_authoritie', data);
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type: 2,
                title: data ? '修改模块' : '添加模块',
                area: ['380px', '500px'],
                content: '/System/Modules/modulesAddPage.do',
                end: function () {
                    admin.getTempData('formOk') && renderTable();  // 成功刷新表格
                }
            });
        }


        // 搜索按钮点击事件
        $('#btnSearch').click(function () {
            var keyword = $('#edtSearch').val();
            var $tds = $('#auth-table').next('.treeTable').find('.layui-table-body tbody tr td');
            if (!keyword) {
                $tds.css('background-color', 'transparent');
                layer.msg("请输入关键字", {icon: 5});
                return;
            }
            var searchCount = 0;
            $tds.each(function () {
                $(this).css('background-color', 'transparent');
                if ($(this).text().indexOf(keyword) >= 0) {
                    $(this).css('background-color', 'rgba(250,230,160,0.5)');
                    if (searchCount == 0) {
                        $('body,html').stop(true);
                        $('body,html').animate({scrollTop: $(this).offset().top - 150}, 500);
                    }
                    searchCount++;
                }
            });
            if (searchCount == 0) {
                layer.msg("没有匹配结果", {icon: 5});
            } else {
                treetable.expandAll('#auth-table');
            }
        });



    });
</script>
</body>

</html>