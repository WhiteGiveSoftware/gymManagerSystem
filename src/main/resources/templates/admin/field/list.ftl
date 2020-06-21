<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>系统设置-管理员用户管理</title>
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
        <!--左侧导航条-->
        <aside class="lyear-layout-sidebar">
            <div class="lyear-layout-sidebar-scroll">
                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <li class="nav-item active"><a href="index.html"><i class="mdi mdi-home"></i> 后台首页</a></li>
                        <li class="nav-item nav-item-has-subnav active open">
                            <a href="javascript:void(0)"><i class="mdi mdi-palette"></i>系统设置</a>
                            <ul class="nav nav-subnav">
                                <li class="active"><a href="/user/list">管理员用户管理</a></li>
                                <li><a href="lyear_ui_other.html">其他</a></li>
                            </ul>
                        </li>
                </nav>
            </div>
        </aside>
        <!--左侧导航结束-->
    </div>
</div>
<#include "../common/footer.ftl">
<#--<script type="text/javascript">-->
<#--    $(document).ready(function () {-->

<#--    });-->

<#--    function del() {-->
<#--        if ($("input[type='checkbox']:checked").length != 1) {-->
<#--            showWarningMsg('请选择一条数据进行删除！');-->
<#--            return;-->
<#--        }-->
<#--        var id = $("input[type='checkbox']:checked").val();-->
<#--        $.confirm({-->
<#--            title: '确定删除？',-->
<#--            content: '删除后数据不可恢复，请慎重！',-->
<#--            buttons: {-->
<#--                confirm: {-->
<#--                    text: '确认',-->
<#--                    action: function () {-->
<#--                        deleteReq(id);-->
<#--                    }-->
<#--                },-->
<#--                cancel: {-->
<#--                    text: '关闭',-->
<#--                    action: function () {-->

<#--                    }-->
<#--                }-->
<#--            }-->
<#--        });-->
<#--    }-->

<#--    //打开编辑页面-->
<#--    function edit() {-->
<#--        if ($("input[type='checkbox']:checked").length != 1) {-->
<#--            showWarningMsg('请选择一条数据进行编辑！');-->
<#--            return;-->
<#--        }-->
<#--        window.location.href = 'edit?id=' + $("input[type='checkbox']:checked").val();-->
<#--    }-->

<#--    //调用删除方法-->
<#--    function deleteReq(id) {-->
<#--        $.ajax({-->
<#--            url: 'delete',-->
<#--            type: 'POST',-->
<#--            data: {id: id},-->
<#--            dataType: 'json',-->
<#--            success: function (data) {-->
<#--                if (data.code == 0) {-->
<#--                    showSuccessMsg('用户删除成功!', function () {-->
<#--                        $("input[type='checkbox']:checked").parents("tr").remove();-->
<#--                    })-->
<#--                } else {-->
<#--                    showErrorMsg(data.msg);-->
<#--                }-->
<#--            },-->
<#--            error: function (data) {-->
<#--                alert('网络错误!');-->
<#--            }-->
<#--        });-->
<#--    }-->
<#--</script>-->
</body>
</html>