$(document).ready(function(){

    $('#loginButton').click(function () {
        login();
    });

    function login() {
        var username = $('#username');
        var password = $('#password');
        var errMessage = $('#errMessage');

        errMessage.text('');

        if (username.val() === '') {
            errMessage.text('账号不能为空！')
        } else if (password.val() === '') {
            errMessage.text('密码不能为空！')
        } else {

            // 请求 /rbac0/login
            var url = window.location.pathname;
            url = url.substring(0, url.lastIndexOf("/")+1);
            var requestUrl = url + "login";

            var usernameVal = username.val();
            var passwordVal = password.val();
            var remembermeVal = $("#rememberme").prop("checked");


            var formData = new FormData();
            formData.append("login_username", usernameVal);
            formData.append("login_password", passwordVal);
            formData.append("login_remember_me", remembermeVal);
            $.ajax({
                url: requestUrl,
                data: formData,
                type: "POST",
                resetForm : false,
                dataType : "json",
                contentType: false,
                processData: false,
                success: function (res) {
                    console.log('login_data', res);
                    if(0 === res.code){
                        window.location.href = res.data;
                    }else{
                        errMessage.text(res.msg);
                    }
                },
                error: function() {
                    errMessage.text('网络异常，请稍后重试！');
                }
            });
        }
    }
});