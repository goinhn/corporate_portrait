let choice = false;

$(function () {
    admin();
    user();
    login();
})

function login() {
    let $login = $('#login');
    $login.on({
        click: function () {
            let $username = $('#username').val().trim();
            let $password = $('#password').val().trim();
            $password = $.md5($password);

            let url = '';
            if (choice) {
                url = localUrl + '/kon/verify/adminlogin'
            } else {
                url = localUrl + '/kon/verify/userlogin'
            }
            ajaxTool("POST", url, function (result) {
                if (result.flag) {
                    let token = result.data.token;
                    let username = result.data.username;
                    let loginTime = result.data.loginTime;
                    let expireTime = result.data.expireTime;

                    localStorage.clear();
                    localStorage.setItem("token", token);
                    localStorage.setItem("username", username);

                    if (choice) {
                        window.location.href = localUrl + '/kon/admin/admin.html' + '?token=' + token;
                    } else {
                        window.location.href = localUrl + '/kon/user/user.html' + '?token=' + token;
                    }
                } else {
                    alert(result.errorMsg);
                }
            }, JSON.stringify({
                "username": $username,
                "password": $password
            }));
        }
    });
}

function admin() {
    let $admin = $('#admin');
    let $loginChoice = $('#loginChoice');
    $admin.on({
        click: function () {
            $loginChoice.html("管理员登录");
            choice = true;
        }
    });
}

function user() {
    let $user = $('#user');
    let $loginChoice = $('#loginChoice');
    $user.on({
        click: function () {
            $loginChoice.html("用户登录");
            choice = false;
        }
    })
}

