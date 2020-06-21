<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>体育馆管理系统--注册页面</title>
    <link rel="icon" href="favicon.ico" type="image/ico">
    <style>
        .background {
            position: absolute;
            top: 0;
            right: 0;
            left: 0;
            bottom: 0;
           /*background-image: url(../../../assets/login/background.png);*/
            background-position: center;
            z-index: -1;
            opacity: 0.5;
        }
        .mask {
            position: absolute;
            top: 0;
            right: 0;
            left: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0);
        }
        .setting {
            position: absolute;
            z-index: 1;
            top: 1rem;
            left: 1rem;
            width: 3rem;
            height: 3rem;
            text-align: center;
            border-radius: 50%;
            background: #f7f7f7;
            cursor: pointer;
            transition: 0.5s;
        }
        .el-icon-setting {
            font-size: 1.5rem;
            line-height: 3rem;
        }
        .setting:hover {
            transform: rotate(360deg) scale(1.1);
            color: #fefefe;
            background: rgb(19, 180, 255);
        }
        .code-box {
            display: flex;
        }
        .code-image {
            width: 200px;
            margin-left: 1rem;
        }
        .lowin {
            position: absolute;
            left: 50%;
            top: 45%;
            transform: translate(-50%, -50%);
            text-align: center;
            font-family: 'Segoe UI';
            font-size: 14px;
        }

        .lowin .lowin-wrapper {
            -webkit-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s;
            -webkit-perspective: 1000px;
            perspective: 1000px;
            position: relative;
            height: 100%;
            width: 360px;
            margin: 0 auto;
        }

        .lowin a {
            color: #0081C6;
            text-decoration: none;
            border-bottom: 1px dashed rgba(0, 129, 198, .45);
            margin-top: -3px;
            padding-bottom: 2px;
        }

        .lowin * {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        .lowin .lowin-brand {
            overflow: hidden;
            width: 100px;
            height: 100px;
            margin: 0 auto -50px auto;
            border-radius: 50%;
            -webkit-box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
            box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
            padding: 10px;
            background-color: #fff;
            z-index: 1;
            position: relative;
        }

        .lowin .lowin-brand img {
            width: 100%;
            transition: .5s;
        }

        .lowin .lowin-box {
            width: 100%;
            left: 0;
            transition: 0.5s;
        }

        .lowin:hover  {
        .lowin-box {
            transform: translateY(-0.5%) scale(1.01);
        }
        .lowin-brand img {
            transform: rotate(360deg);
        }
        .lowin-brand {
            transform: translateY(-1%);
        }
        }

        .login {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            animation: show 1s ease;
        }

        @keyframes show {
            0% {
                opacity: 0.5;
                transform: scale(0.95);
            }
            100% {
                opacity: 1;
                transform: scale(1);
            }
        }

        .lowin .lowin-box-inner {
            background-color: #fff;
            -webkit-box-shadow: 0 7px 25px rgba(0, 0, 0, .08);
            box-shadow: 0 7px 25px rgba(0, 0, 0, .08);
            padding: 60px 25px 25px 25px;
            text-align: left;
            border-radius: 3px;
        }

        .lowin .lowin-box::after {
            content: ' ';
            -webkit-box-shadow: 0 0 25px rgba(0, 0, 0, .1);
            box-shadow: 0 0 25px rgba(0, 0, 0, .1);
            -webkit-transform: translate(0, -92.6%) scale(.88);
            -ms-transform: translate(0, -92.6%) scale(.88);
            transform: translate(0, -92.6%) scale(.88);
            border-radius: 3px;
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #fff;
            z-index: -1;
        }

        .lowin .lowin-box.lowin-flip {
            -webkit-transform: rotate3d(0, 1, 0, -180deg);
            transform: rotate3d(0, 1, 0, -180deg);
            display: none;
            opacity: 0;
        }

        .lowin .lowin-box p {
            color: rgba(0, 129, 198, .45);
            font-weight: 700;
            margin-bottom: 20px;
            text-align: center;
        }

        .lowin .lowin-box .lowin-group {
            margin-bottom: 30px;
        }

        .lowin .lowin-box label {
            margin-bottom: 5px;
            display: inline-block;
            width: 100%;
            color: rgba(0, 129, 198, .45);
            font-weight: 700;
        }

        .lowin .lowin-box label a {
            float: right;
        }

        .lowin .lowin-box .lowin-input {
            background-color: rgba(0, 129, 198, .05);
            color: rgba(0, 129, 198, .7);
            border: none;
            border-radius: 3px;
            padding: 15px 20px;
            width: 100%;
            outline: 0;
        }

        .lowin .lowin-box .lowin-btn {
            display: inline-block;
            text-align: center;
            width: 100%;
            border: none;
            color: #fff;
            padding: 15px;
            border-radius: 3px;
            background-color: #0081C6;
            -webkit-box-shadow: 0 2px 7px rgba(0, 129, 198, .45);
            box-shadow: 0 2px 7px rgba(0, 129, 198, .45);
            font-weight: 700;
            outline: 0;
            cursor: pointer;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s;
        }

        .lowin .lowin-box .lowin-btn:active {
            -webkit-box-shadow: none;
            box-shadow: none;
        }

        .lowin .lowin-box .lowin-btn:hover {
            opacity: .9;
        }

        .lowin .text-foot {
            text-align: center;
            padding: 10px;
            font-weight: 700;
            margin-top: 20px;
            color: rgba(0, 129, 198, .45);
        }

        .lowin .lowin-footer {
            text-align: center;
            margin: 30px 0;
            font-size: 12px;
            color: rgba(0, 129, 198, .45);
            font-weight: 700;
        }

        .lowin .login-back-link {
            -webkit-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s;
            display: none;
            opacity: 0;
        }

        @media screen and (max-width: 650px) {
            .lowin-box {
                width: 22.5rem!important;
            }
        }

        @media screen and (max-width: 400px) {
            .lowin-box {
                width: 100%!important;
            }
        }
    </style>
</head>

<body>
<div class="login">
    <div class="background" ref="background">
        <div class="mask" ref="mask"></div>
    </div>
    <div class="lowin lowin-blue">
        <div class="lowin-brand"><img src="https://myinterface.xuanzai.top/getPicture?type=%E5%A4%B4%E5%83%8F&id=1" alt="logo" /></div>
        <div class="lowin-wrapper">
            <div class="lowin-box lowin-login">
                <div class="lowin-box-inner">
                    <form id="register-form" method="post">
                        <p>注册</p>
                        <div class="lowin-group">
                            <label>
                                用户名
                            </label>
                            <input  name="username"  id="username" type="text" class="lowin-input" />
                        </div>
                        <div class="lowin-group password-group">
                            <label>
                                密码
                            </label>
                            <input type="password" name="password" id="password" autocomplete="current-password" class="lowin-input" />
                        </div>
                        <div class="lowin-group">
                            <label>
                                手机号
                            </label>
                            <input  type="text" name="phone" id="phone" class="lowin-input" />
                        </div>
                        <div class="lowin-group">
                            <label>
                                邮箱
                            </label>
                            <input type="text" name="email" id="email" class="lowin-input" />
                        </div>

                        <div class="lowin-group">
                            <!--    						<label>
                                                            验证码
                                                            <a href="#" class="login-back-link">Sign in?</a>
                                                        </label> -->
                            <!--    						<div class="code-box">
                                                            <input v-model="vcode" type="text" class="lowin-input" @keyup.enter="submit" />
                                                            <el-image class="code-image" :src="codeUrl" fit="fill" @click="getCode">
                                                              <div slot="error" class="image-slot">
                                                                <i class="el-icon-picture-outline"></i>
                                                              </div>
                                                            </el-image>
                                                        </div> -->
                        </div>
                        <div class="form-group">

                        <button class="lowin-btn login-btn"  type="button" id="submit-btn">注 册</button>
                        </div>
                        <!-- 							<div class="text-foot">
                                                        下次自动登录
                                                        <el-checkbox v-model="ruleForm.checked" @change="autoLogin">自动登录</el-checkbox>
                                                    </div> -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<#include "../common/footer.ftl">
<#include "../common/header.ftl">
<script type="text/javascript">

    /*
        表单校验：
            1.用户名：单词字符，长度8到20位
            2.密码：单词字符，长度8到20位
            3.email：邮件格式
            4.手机号：手机号格式

     */

    //校验用户名
    //单词字符，长度8到20位
    function checkUsername() {
        //1.获取用户名值
        var username = $("#username").val();
        //2.定义正则
        var reg_username = /^\w{8,20}$/;

        //3.判断，给出提示信息
        var flag = reg_username.test(username);
        if(flag){
            //用户名合法
            $("#username").css("border","");
        }else{
            //用户名非法,加一个红色边框
            $("#username").css("border","1px solid red");
        }

        return flag;
    }

    //校验密码
    function checkPassword() {
        //1.获取密码值
        var password = $("#password").val();
        //2.定义正则
        var reg_password = /^\w{8,20}$/;

        //3.判断，给出提示信息
        var flag = reg_password.test(password);
        if(flag){
            //密码合法
            $("#password").css("border","");
        }else{
            //密码非法,加一个红色边框
            $("#password").css("border","1px solid red");

        }

        return flag;
    }
    //校验手机号
    function checkPhone() {
        //1.获取密码值
        var phone = $("#phone").val();
        //2.定义正则
        var reg_phone = /^1[3456789]\d{9}$/;

        //3.判断，给出提示信息
        var flag = reg_phone.test(phone);
        if(flag){
            //手机合法
            $("#phone").css("border","");
        }else{
            //手机非法,加一个红色边框
            $("#phone").css("border","1px solid red");

        }

        return flag;
    }

    //校验邮箱
    function checkEmail(){
        //1.获取邮箱
        var email = $("#email").val();
        //2.定义正则		itcast@163.com
        var reg_email = /^\w+@\w+\.\w+$/;

        //3.判断
        var flag = reg_email.test(email);
        if(flag){
            $("#email").css("border","");
        }else{
            $("#email").css("border","1px solid red");

        }

        return flag;
    }

    $(document).ready(function () {
        //当表单提交时，调用所有的校验方法
        $("#submit-btn").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            var email = $("#email").val();
            var phone = $("#phone").val();
            //1.发送数据到服务器
            if(checkUsername() && checkPassword() && checkEmail() && checkPhone()){
                $.ajax({
                    url: '/home/register',
                    type: 'POST',
                    data: {username: username, password: password, phone: phone,email: email},
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 0) {
                            window.location.href = 'login';
                        } else {
                            showErrorMsg(data.msg);
                        }
                    },
                    error: function (data) {
                        alert('网络错误!');
                    }
                });

            }
        });
        //当某一个组件失去焦点是，调用对应的校验方法
        $("#username").blur(checkUsername);
        $("#password").blur(checkPassword);
        $("#email").blur(checkEmail);
        $("#phone").blur(checkPhone);


    });

</script>
</body>
</html>