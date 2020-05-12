$(function () {
    reset();
});


function reset() {
    $('#resetPassword').on({
        click: function () {
            let $username = $('#username').val().trim();
            let url = localUrl + '/kon/verify/userforgot' + '?username=' + $username;
            ajaxTool("GET", url, function (result) {
                if (result.flag) {
                    alert(result.data);
                } else {
                    alert(result.errorMsg);
                }
            });
        }
    });
}