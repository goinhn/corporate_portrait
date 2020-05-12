$(function () {
    token();
    count();
});

function token() {
    let $admin = $('#admin');
    let $people = $('#people');
    let $business = $('#business');
    let $exit = $('#exit');
    let $username = $('#username');

    let token = localStorage.getItem('token');
    let username = localStorage.getItem('username');

    $username.html(username);

    $admin.attr('href', $admin.attr('href') + '?token=' + token);
    $people.attr('href', $people.attr('href') + '?token=' + token);
    $business.attr('href', $business.attr('href') + '?token=' + token);

    $exit.on({
        click: function () {
            localStorage.clear();
            $.ajax({
                type: 'GET',
                contentType: "application/json;charset=utf-8",
                url: localUrl + '/kon/verify/adminloginout',
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (result) {
                    window.location.href = localUrl + '/kon/verification/login.html';
                },
                error: function (e) {
                    console.log("errorStatus:" + e.status);
                    console.log("statusText:" + e.responseText);
                    window.location.href = localUrl + "/kon/error/500.html";
                }
            });
        }
    });

}


function count() {
    let $businessCount = $('#businessCount');
    let $userCount = $('#userCount');
    let $adminCount = $('#adminCount');

    let token = localStorage.getItem('token');

    let url = localUrl + '/kon/manage/count' + '?token=' + token;

    ajaxTool("GET", url, function (result) {
        $businessCount.html(result.data.businessCount);
        $userCount.html(result.data.userCount);
        $adminCount.html(result.data.adminCount);
    });
}