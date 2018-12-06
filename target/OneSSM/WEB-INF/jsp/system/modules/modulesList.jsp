<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeroDeng
  Date: 2018-12-06
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>模块管理</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="${pageContext.request.contextPath}/assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>

</head>

<body data-type="widgets">
<script src="${pageContext.request.contextPath}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <%@ include file="/WEB-INF/jsp/common/header.jsp"%>
    <!--侧边菜单-->
    <%@ include file="/WEB-INF/jsp/common/menuBar.jsp"%>


    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title  am-cf">文章列表</div>


                        </div>
                        <div class="widget-body  am-fr">

                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                <div class="am-form-group">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button type="button" class="am-btn am-btn-default am-btn-success"><span class="am-icon-plus"></span> 新增</button>
                                            <button type="button" class="am-btn am-btn-default am-btn-danger"><span class="am-icon-trash-o"></span> 删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                                <div class="am-form-group tpl-table-list-select">
                                    <select data-am-selected="{btnSize: 'sm'}">
                                        <option value="option1">所有类别</option>
                                        <option value="option2">IT业界</option>
                                        <option value="option3">数码产品</option>
                                        <option value="option3">笔记本电脑</option>
                                        <option value="option3">平板电脑</option>
                                        <option value="option3">只能手机</option>
                                        <option value="option3">超极本</option>
                                    </select>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                    <input type="text" class="am-form-field ">
                                    <span class="am-input-group-btn">
                                    <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button"></button>
                                  </span>
                                </div>
                            </div>



                            <!--分页-->
                            <div class="am-u-lg-12 am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <c:if test="${Page.prePage!=0}">
                                            <li class=""><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${Page.prePage}">«</a></li>
                                        </c:if>
                                        <c:forEach var="No" begin="1" end="${Page.pages}" step="1">
                                            <c:if test="${Page.pageNum==No}">
                                                <li class="am-active"><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${No}">${No}</a></li>
                                            </c:if>
                                            <c:if test="${Page.pageNum!=No}">
                                                <li class=""><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${No}">${No}</a></li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${Page.nextPage!=0}">
                                            <li><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${Page.nextPage}">»</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>


                            <div class="am-u-sm-12">
                                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black ">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>名称</th>
                                        <th>等级</th>
                                        <th>URL</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach items="${List}" var="item">
                                        <tr class="gradeX">

                                            <td class="am-text-middle">${item.id}</td>
                                            <td class="am-text-middle">${item.name}</td>
                                            <td class="am-text-middle">${item.menuType}</td>
                                            <td class="am-text-middle">${item.menuUrl}</td>
                                            <td class="am-text-middle">
                                                <div class="tpl-table-black-operation">
                                                    <a href="javascript:;">
                                                        <i class="am-icon-pencil"></i> 编辑
                                                    </a>
                                                    <a href="javascript:;" class="tpl-table-black-operation-del">
                                                        <i class="am-icon-trash"></i> 删除
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <!-- more data -->
                                    </tbody>
                                </table>
                            </div>
                            <!--分页-->
                            <div class="am-u-lg-12 am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <c:if test="${Page.prePage!=0}">
                                            <li class=""><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${Page.prePage}">«</a></li>
                                        </c:if>
                                        <c:forEach var="No" begin="1" end="${Page.pages}" step="1">
                                            <c:if test="${Page.pageNum==No}">
                                                <li class="am-active"><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${No}">${No}</a></li>
                                            </c:if>
                                            <c:if test="${Page.pageNum!=No}">
                                                <li class=""><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${No}">${No}</a></li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${Page.nextPage!=0}">
                                            <li><a href="${pageContext.request.contextPath}/System/Modules/ModulesList.do?pageNo=${Page.nextPage}">»</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>


</body>

</html>
