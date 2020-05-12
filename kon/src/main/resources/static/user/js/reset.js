$(function () {
    reset();
});


function reset() {
    let $resetPassword = $('#resetPassword');

    $resetPassword.on({
        click: function () {
            let $password = $('#password').val().trim();
            let $repeatPassword = $('#repeatPassword').val().trim();
            if(checkPassword($password, $repeatPassword)) {
                let activecode = localStorage.getItem('activecode');
                $password = $.md5($password);
                let url = localUrl + '/kon/verify/userresetpassword' + '?activecode=' + activecode + '&password=' + $password;
                ajaxTool("GET", url, function (result) {
                    if(result.flag) {
                        alert(result.data);
                        localStorage.clear();
                        window.location.href = localUrl + '/kon/verification/login.html';
                    } else {
                        alert(result.errorMsg);
                    }
                });
            }
        }
    })
}

function checkPassword(one, two) {
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