$(function(){

});

$('#returnBackButton').on({
    click: ()=>{
        window.location.href = './index.html';
    }
});


// ajax封装
function ajaxMain(type, url, data, su = () => { }, er = () => { }, be = () => { }, co = () => { }) {
    $.ajax({
      type: type, //请求方式
      url: url, //请求的url地址
      dataType: "json", //返回格式为json
      async: true,//请求是否异步，默认为异步，这也是ajax重要特性
      data: data, //参数值
      success: (req) => {
        if (req.status == 'ok') {
          su(req);
        } else {
          console.log(req.status);
          console.log(req.responseText);
        }
      },
      error: (e) => {
        console.log(e.status);
        console.log(e.responseText);
        er();
      },
      beforeSend: () => {
        //请求前的处理
        be();
      },
      complete: () => {
        //请求完成的处理
        co();
      }
    });
  }