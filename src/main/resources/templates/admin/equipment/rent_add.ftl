<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>系统设置-器材管理--器材租用管理---添加</title>
    <#include "../common/header.ftl">

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
        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">


            <div class="lyear-layout-sidebar-scroll">

                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <li class="nav-item active"><a href="index.html"><i class="mdi mdi-home"></i> 后台首页</a></li>
                        <li class="nav-item nav-item-has-subnav active open">
                            <a href="javascript:void(0)"><i class="mdi mdi-palette"></i>系统设置</a>
                            <ul class="nav nav-subnav" >
                                <li ><a href="/user/list">管理员用户管理</a></li>
                                <li class="nav-item nav-item-has-subnav">
                                    <a href="javascript:void(0)"><i class="mdi mdi-palette"></i>器材管理</a>
                                    <ul class="nav nav-subnav">
                                        <li class="nav-item active"><a href="/equipment/equipment_list">器材基本管理</a></li>
                                        <li><a href="/equipment/rent_list">器材租用管理</a></li>
                                        <li><a href="/equipment/repair_list">器材维修管理</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>

        </aside>
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
                        <span class="navbar-page-title"> 后台首页 </span>
                    </div>
                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <a href="javascript:void(0)" data-toggle="dropdown">
                                <img class="img-avatar img-avatar-48 m-r-10" src="/admin/images/users/avatar.jpg"
                                     alt="体育馆管理"/>
                                <span> <span class="caret"></span></span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="lyear_pages_profile.html"><i class="mdi mdi-account"></i> 个人信息</a></li>
                                <li><a href="lyear_pages_edit_pwd.html"><i class="mdi mdi-lock-outline"></i> 修改密码</a>
                                </li>
                                <li><a href="javascript:void(0)"><i class="mdi mdi-delete"></i> 清空缓存</a></li>

                                <li><a href="lyear_pages_login.html"><i class="mdi mdi-logout-variant"></i> 退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header"><h4>添加租用单</h4></div>
                            <div class="card-body">

                                <form id="rent_add_form" action="add" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label for="title">租用人</label>
                                        <input type="text" class="form-control required" id="name" name="name" value="" placeholder="请填写租用人名" tips="请填写租用人名" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="title">电话号码</label>
                                        <input type="text" class="form-control required" id="tel" name="tel" value="" placeholder="请填写电话号码" tips="请填写电话号码" />
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="title">器材名称</label>
                                        <input type="text" class="form-control required" id="equipmentname" name="equipmentname" value="" placeholder="请填写器材名称" tips="请填写用户名" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="seo_keywords">租用描述</label>
                                        <input type="text" class="form-control required" id="description" name="description" value="" placeholder="请填写器材描述" tips="请填写密码"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">确 定</button>
                                        <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
                                    </div>
                                </form>
                             </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>
<#include "../common/footer.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        //提交按钮监听事件
        $("#add-form-submit-btn").click(function () {
            if(!checkForm("rent_add_form")){
                return;
            }
            var name = $("#name").val();
            var tel = $("#tel").val();
            var equipmentname = $("#equipmentname").val();
            var description = $("#description").val();
            $.ajax({
                url: 'rent_add',
                type: 'POST',
                data: {name: name,tel: tel,equipmentname: equipmentname, description: description},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        showSuccessMsg('用户添加成功',function () {
                            window.location.href = 'rent_list';
                        })
                       // window.location.href = 'index';
                    } else {
                        showErrorMsg(data.msg);
                    }
                },
                error: function (data) {
                    alert('网络错误!');
                }
            });
        });



    });
</script>
</body>
</html>