<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>体育馆系统</title>

    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/admin/css/style.min.css" rel="stylesheet">
    <script type="text/javascript" src="/admin/js/jquery.min.js"></script>
    <script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
    <script type="text/javascript" src="/admin/js/main.min.js"></script>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">

        <!--End 左侧导航-->

        <!--头部信息-->
        <header class="lyear-layout-header">
            <nav class="navbar navbar-default">
                <div class="topbar">
                    <div class="topbar-left">
                        <div class="lyear-aside-toggler">
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                        </div>
                    </div>
                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                        <#if home_user??>
                        <li><a href="/home/logout"><i class="mdi mdi-mdi-logout-variant"></i> 退出登录</a></li>
                        <li><span class="show">欢迎你!${home_user.username} </span></li>

                        <#else>
<#--                        <li><a href="lyear_pages_profile.html"><i class="mdi mdi-lock-outline"></i> 修改密码</a></li>-->
<#--                        <li><a href="lyear_pages_profile.html"><i class="mdi mdi-account"></i> 个人信息</a></li>-->
                            <li><a href="/home/login"><i class="mdi mdi-login"></i> 登陆</a></li>
                            <li><a href="/home/register"><i class="mdi mdi-regex"></i> 注册</a></li>
                        </#if>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!--End 头部信息-->


        <!--End 页面主要内容-->
    </div>
</div>
<#include "../common/footer.ftl">
<script type="text/javascript">

</script>
</body>
</html>