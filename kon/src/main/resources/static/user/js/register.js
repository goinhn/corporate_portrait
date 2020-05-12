$(function () {
    register();
});

function register() {
    $('#register').on({
        click: function () {
            let $username = $('#username').val();
            let $email = $('#email').val();
            let $password = $('#password').val();
            let $repeatPassword = $('#repeatPassword').val();

            if (checkUsername($username) && checkEmail($email) && checkPassword($password, $repeatPassword)) {
                let url = localUrl + '/kon/verify/userregister';
                let data = JSON.stringify({
                    "username": $username,
                    "email": $email,
                    "password": $.md5($password)
                });
                $.ajax({
                    type: 'POST',
                    contentType: "application/json;charset=utf-8",
                    data: data,
                    url: url,
                    success: function (result) {
                        if (result.flag) {
                            alert(result.data);
                            window.location.href = localUrl + '/kon/verification/login.html';
                        } else {
                            alert(result.errorMsg);
                        }
                    },
                    error: function (e) {
                        console.log("errorStatus:" + e.status);
                        console.log("statusText:" + e.responseText);
                        // window.location.href = localUrl + "/kon/error/500.html";
                    }
                })
            }
        }
    })
}

function checkEmail(email) {
    email.toString().trim();
    let patternEmail = /^[-_A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/g;
    let resultEmail = patternEmail.test(email);

    if (resultEmail) {
        return true;
    } else {
        alert("输入邮箱格式有误，请重新输入");
        return false;
    }
}

function checkUsername(username) {
    username.toString().trim();
    console.log(username);
    let patternName = /^[_a-zA-Z0-9]{4,20}$/g;
    let resultName = patternName.test(username);

    if (resultName) {
        return true;
    } else {
        alert("输入的用户名有误，名称长度为4-20位，且仅使用数字、字母和下划线组成");
        return false;
    }
}

function checkPassword(one, two) {
    one.toString().trim();
    two.toString().trim();
    let patternPassword = /^[_a-zA-Z0-9]{3,20}$/g;
    let resultPassword = patternPassword.test(one);

    if (resultPassword) {
        if (one == two) {
            return true;
        } else {
            alert("两次输入密码不一致");
            return false;
        }
    } else {
        alert("输入的密码有误，名称长度为3-20位，且仅使用数字、字母和下划线组成");
        return false;
    }
}