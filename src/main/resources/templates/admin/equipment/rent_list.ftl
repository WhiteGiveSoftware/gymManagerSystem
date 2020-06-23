<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>系统设置-器材管理--器材租用管理</title>
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
                            <ul class="nav nav-subnav">
                                <li ><a href="/user/list">管理员用户管理</a></li>
                                <li class="nav-item nav-item-has-subnav">
                                    <a href="javascript:void(0)"><i class="mdi mdi-palette"></i>器材管理</a>
                                    <ul class="nav nav-subnav">
                                        <li><a href="/equipment/equipment_list">器材基本管理</a></li>
                                        <li class="nav-item active"><a href="/equipment/rent_list">器材租用管理</a></li>
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
                            <div class="card-toolbar clearfix">
                                <form class="pull-right search-bar" method="get" action="rent_list" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                租用人名称 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)"
                                                       data-field="title">租用人名称</a></li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" value="${name!""}" name="name"
                                               placeholder="请输入器材名称">
                                        <span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">搜索</button>
                    </span>
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="/equipment/rent_add"><i class="mdi mdi-plus"></i>
                                        新增</a>
                                    <a class="btn btn-primary m-r-5" href="javascript:edit()"><i
                                                class="mdi mdi-grease-pencil"></i> 编辑</a>
                                    <a class="btn btn-primary m-r-5" href="javascript:del()"><i
                                                class="mdi mdi-close"></i> 删除</a>

                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" id="check-all"><span></span>
                                                </label>
                                            </th>
                                            <th>编号</th>
                                            <th>租用人</th>
                                            <th>电话号码</th>
                                            <th>器材名</th>
                                            <th>租用描述</th>
                                            <th>创建时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list  pageBean.content as rent>
                                                <tr>
                                                    <td>
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]"
                                                                   value="${rent.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td>${rent_index+1}</td>
                                                    <td>${rent.name}</td>
                                                    <td>${rent.tel}</td>
                                                    <td><b>${rent.equipmentname}</b></td>
                                                    <td>${rent.description}</td>
                                                    <td>${rent.creaTime}</td>
                                                </tr>
                                            </#list>
                                        <#else>
                                            <tr align="center"><td colspan="9">这里空空如也！<a href="rent_list">显示全部数据</a></td> </tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>
                                <#if pageBean.total gt 0>
                                    <ul class="pagination">
                                        <#if pageBean.currentPage == 1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="rent_list?name=${name!""}&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage == showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li>
                                                    <a href="rent_list?name=${name!""}&currentPage=${showPage}">${showPage}</a>
                                                </li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage == pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="rent_list?name=${name!""}&currentPage=${pageBean.totalPage}">»</a>
                                            </li>
                                        </#if>
                                        <li><span>共${pageBean.totalPage}页,${pageBean.total}条数据</span></li>
                                    </ul>
                                </#if>
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

    });

    function del() {
        if ($("input[type='checkbox']:checked").length != 1) {
            showWarningMsg('请选择一条数据进行删除！');
            return;
        }
        var id = $("input[type='checkbox']:checked").val();
        $.confirm({
            title: '确定删除？',
            content: '删除后数据不可恢复，请慎重！',
            buttons: {
                confirm: {
                    text: '确认',
                    action: function () {
                        deleteReq(id);
                    }
                },
                cancel: {
                    text: '关闭',
                    action: function () {

                    }
                }
            }
        });
    }

    //打开编辑页面
    function edit() {
        if ($("input[type='checkbox']:checked").length != 1) {
            showWarningMsg('请选择一条数据进行编辑！');
            return;
        }
        window.location.href = 'rent_edit?id=' + $("input[type='checkbox']:checked").val();
    }

    //调用删除方法
    function deleteReq(id) {
        $.ajax({
            url: 'rent_delete',
            type: 'POST',
            data: {id: id},
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    showSuccessMsg('租用单删除成功!', function () {
                        $("input[type='checkbox']:checked").parents("tr").remove();
                    })
                } else {
                    showErrorMsg(data.msg);
                }
            },
            error: function (data) {
                alert('网络错误!');
            }
        });
    }
</script>
</body>
</html>