<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户注册</title>
    <meta name="description" content="这是一个 index 页面">
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
        //验证两次密码是否一样
        function checkPwd() {
            var pwd = $('#password').val();
            var pwdAgain = $('#passwordAgain').val();

            if(pwd!=pwdAgain){
                $('#password').removeClass("am-field-valid");
                $('#password').addClass("am-field-error");

                $('#passwordAgain').removeClass("am-field-valid");
                $('#passwordAgain').addClass("am-field-error");

                $('#pwd').removeClass("am-form-success");
                $('#pwd').addClass("am-form-error");

                $('#pwdAgain').removeClass("am-form-success");
                $('#pwdAgain').addClass("am-form-error");
            }else{
                $('#password').removeClass("am-field-error");
                $('#password').addClass("am-field-valid");

                $('#passwordAgain').removeClass("am-field-error");
                $('#passwordAgain').addClass("am-field-valid");

                $('#pwd').removeClass("am-form-error");
                $('#pwd').addClass("am-form-success");

                $('#pwdAgain').removeClass("am-form-error");
                $('#pwdAgain').addClass("am-form-success");
            }
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
                <div class="tpl-login-title">注册用户</div>
                <span class="tpl-login-content-info">
                  创建一个新的用户
              </span>


                <form class="am-form tpl-form-line-form" data-am-validator action="/signup/signup.do" method="post">
                    <fieldset>

                    <div class="am-form-group">
                        <input type="text" minlength="2" id="name" name="name" placeholder="姓名（至少2个字符）" value="${name}" required/>
                    </div>

                    <div class="am-form-group">
                        <input type="text" minlength="3" id="username" name="username" placeholder="用户名（至少3个字符）" value="${username}" required/>
                    </div>

                    <div class="am-form-group">
                        <input type="email"  id="email" name="email" placeholder="邮箱" required value="${email}"/>
                    </div>


                    <div id="pwd" class="am-form-group">
                        <input type="password" minlength="6" onBlur="checkPwd()" id="password" name="password" placeholder="请输入密码(至少6个字符)" value="${password}" required/>
                    </div>

                    <div id="pwdAgain" class="am-form-group">
                        <input type="password" minlength="6" onBlur="checkPwd()" id="passwordAgain" name="passwordAgain" placeholder="再次输入密码(至少6个字符)" value="${passwordAgain}" required/>
                    </div>

                    <div class="am-form-group tpl-login-remember-me">
                        <input id="remember-me" name="remember-me" minchecked="1" type="checkbox"  required>
                        <label for="remember-me">
                        我已阅读并同意 <a href="javascript:;">《用户注册协议》</a> 
                         </label>
                    </div>

                    <div class="am-form-group">
                        <button type="submit"  class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                    </div>
                    <div class="am-form-group">
                        <button type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/index.do'"  class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登陆</button>
                    </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>

</html>