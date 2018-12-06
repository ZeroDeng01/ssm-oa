<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统登陆</title>
    <meta name="description" content="系统登陆">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layer/theme/default/layer.css">
    <script src="${pageContext.request.contextPath}/assets/layer/layer.js"></script>

    <script>
        window.onload = function()
        {
            var msg = "${Msg}";
            if(msg!=""){
                layer.msg(msg);
            }
        }
        function doSave()
        {
            if( validateData() )
            {
                document.forms[0].submit();
            }
        }
        function validateData()
        {
            if( document.forms[0].UserName.value == "" )
            {
                layer.msg("用户名不能为空");
                return false;
            }
            if( document.forms[0].Password.value == "" )
            {
                layer.msg("密码不能为空");
                return false;
            }
            return true;
        }
        function signup() {
            location.href="/signup/index.do";
        }
    </script>

</head>

<body data-type="login">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js"></script>
    <div class="am-g tpl-g">
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
        <div class="tpl-login">
            <div class="tpl-login-content">
                <div class="tpl-login-logo">

                </div>


                <form class="am-form tpl-form-line-form" action="/UserLogin.do" method="post">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" id="UserName" name="UserName" placeholder="请输入账号" value="${username}">
                    </div>

                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" id="Password" name="Password" placeholder="请输入密码" value="${password}">

                    </div>
                    <div class="am-form-group tpl-login-remember-me">
                        <input id="remember-me" name="remember-me" type="checkbox" checked />
                        <label for="remember-me">
                        记住密码
                         </label>

                    </div>

                    <div class="am-form-group">
                        <button type="button" onclick="doSave()" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登陆</button>
                    </div>
                    <div class="am-form-group">
                        <button type="button" onclick="signup()" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

</body>

</html>