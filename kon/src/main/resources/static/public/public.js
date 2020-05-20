


//ajax封转函数
function ajaxTool(type, url, successFunc, data) {
    let pass = false;
    $.ajax({
        async: false,
        type: type,
        url: url,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: data,
        timeout: 10000,
        success: function (result) {
            pass = result.flag;
            console.log("url:" + url + " -- " + "result:" + result);
            successFunc(result);
        },
        error: function (e) {
            console.log("errorStatus:" + e.status);
            console.log("statusText:" + e.responseText);
            window.location.href = localUrl + "/kon/error/500.html";
        }
    });

    return pass;
}