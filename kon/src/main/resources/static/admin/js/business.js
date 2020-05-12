$(function () {
    token();
    table();
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


function table() {
    let token = localStorage.getItem('token');
    $('#businessTable').DataTable({
        "columnDefs": [
            {
                "targets": -1,
                "render": function ( data, type, full, meta ) {
                    let id = data.eid;
                    let func = "deleteNow(" + id + ")";
                    return "<input data-toggle='modal' data-target='#logoutModal' class='form-control form-group btn btn-primary' type='button' onclick=" + func + " value='删除'>";
                }
            }
        ],
        'language': {
            'url': localUrl + '/kon/static/admin/js/language.json'
        },
        'processing': true,
        'searching': true,
        'serverSide': true,
        "deferRender": true,
        'ajax': {
            'type': 'GET',
            'url': localUrl + '/kon/manage/business' + '?token=' + token,
            'dataType': 'json'
        },
        'columns': [
            {'data': 'entName'},
            {'data': 'entStatus'},
            {'data': 'entType'},
            {'data': 'entCat'},
            {'data': 'industryPhy'},
            {'data': 'creditGrade'},
            {'data': null}
        ]
    });
}


function deleteNow(id) {
    let token = localStorage.getItem("token");
    let url = localUrl + '/kon/manage/businessdelete' + '?id=' + id + '&token=' + token;

    $('#confirm').off('click').on('click',function () {
        ajaxTool('GET', url, function (result) {
            if(result.flag){
                $('#close').click();
                $('#businessTable').DataTable().draw(false);
            }
        });
    })

}

