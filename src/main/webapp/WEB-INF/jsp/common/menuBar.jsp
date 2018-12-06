<%--
  Created by IntelliJ IDEA.
  User: ZeroDeng
  Date: 2018-12-04
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- 风格切换 -->
<div class="tpl-skiner">
    <div class="tpl-skiner-toggle am-icon-cog">
    </div>
    <div class="tpl-skiner-content">
        <div class="tpl-skiner-content-title">
            选择主题
        </div>
        <div class="tpl-skiner-content-bar">
            <span class="skiner-color skiner-white" data-color="theme-white"></span>
            <span class="skiner-color skiner-black" data-color="theme-black"></span>
        </div>
    </div>
</div>
<!-- 侧边导航栏 -->
<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">
            <div class="tpl-user-panel-profile-picture">
                <img src="${pageContext.request.contextPath}/assets/img/user04.png" alt="">
            </div>
            <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              ${UserName}
          </span>
            <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>

    <!-- 菜单 -->
    <ul class="sidebar-nav">
        <!--系统功能菜单-->
        <li class="sidebar-nav-heading">主页 <span class="sidebar-nav-heading-info"> Home</span></li>
        <li class="sidebar-nav-link">
            <a href="/index.do" class="${indexActive}">
                <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
            </a>
        </li>
        <!--系统管理子系统-->
        <li class="sidebar-nav-heading">系统管理<span class="sidebar-nav-heading-info"> System</span></li>
        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title ${ModulesModulesActive}">
                <i class="am-icon-list sidebar-nav-link-logo"></i> 模块管理
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub" style="${ModulesModulesOpen}">
                <li class="sidebar-nav-link">
                    <a href="/System/Modules/ModulesList.do?pageNo=1" class="${ModulesActive}">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 模块管理
                    </a>
                </li>
            </ul>
        </li>

        <!--A子系统-->
        <li class="sidebar-nav-heading">Ax子系统<span class="sidebar-nav-heading-info"> Business</span></li>
        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title">
                <i class="am-icon-table sidebar-nav-link-logo"></i> 数据列表
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub">
                <li class="sidebar-nav-link">
                    <a href="table-list.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字列表
                    </a>
                </li>

                <li class="sidebar-nav-link">
                    <a href="table-list-img.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 图文列表
                    </a>
                </li>
            </ul>
        </li>

        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title">
                <i class="am-icon-table sidebar-nav-link-logo"></i> 数据列表
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub">
                <li class="sidebar-nav-link">
                    <a href="table-list.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字列表
                    </a>
                </li>

                <li class="sidebar-nav-link">
                    <a href="table-list-img.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 图文列表
                    </a>
                </li>
            </ul>
        </li>
        <!--B子系统-->
        <li class="sidebar-nav-heading">Bx子系统<span class="sidebar-nav-heading-info"> Business</span></li>
        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title">
                <i class="am-icon-table sidebar-nav-link-logo"></i> 数据列表
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub">
                <li class="sidebar-nav-link">
                    <a href="table-list.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字列表
                    </a>
                </li>

                <li class="sidebar-nav-link">
                    <a href="table-list-img.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 图文列表
                    </a>
                </li>
            </ul>
        </li>

        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title">
                <i class="am-icon-table sidebar-nav-link-logo"></i> 数据列表
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub">
                <li class="sidebar-nav-link">
                    <a href="table-list.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字列表
                    </a>
                </li>

                <li class="sidebar-nav-link">
                    <a href="table-list-img.html">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 图文列表
                    </a>
                </li>
            </ul>
        </li>



    </ul>
</div>
