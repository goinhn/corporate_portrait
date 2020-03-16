$(function () {
  // 搜索事件绑定
  $('#searchButton').on({
    click: () => {
      let searchContent = $('#searchText').val();
      let url = '/PostSearchCompany';
      let postData = {
        'searchContent': searchContent
      }
      console.log('searchContent:' + searchContent + ',url:' + url + ',postData:' + postData);
      ajaxMain('POST', url, postData, dispalyTable);
      buttonClickBind();
    }
  });
});


// 测试数据
var testObjectDate = [
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  },
  {
    "entname": "8f0c15a6c0d2c09a157feef19ddb8783",
    "entcat": "有限责任公司(自然人投资或控股)",
    "esldate": "1205942400000",
  }
];
dispalyTable(testObjectDate);

// 企业画像按钮事件绑定
function buttonClickBind(){
  $('tbody tr').each(function(index, value){
    $(this).children('td:eq(3)').children('button').on({
      click: ()=>{
        let entname = $(this).children('td:eq(3)').children('button').attr('name');
        console.log('entname: '+entname);
        displayButton(entname);
      }
    });
  });
}

// 前端接收数据展示
function dispalyTable(info) {
  let table = $('#tableDisplay').DataTable({
    data: info,
    columns: [
      { data: 'entname' },
      { data: 'entcat' },
      { data: 'esldate' },
      {
        data: 'entname',
        render: function (data, type, row) {
          return '<button name="' + data + '" class="btn btn-secondary btn-sm btn-icon-split"><span class="text">企业画像</span></button>';
        }
      }
    ]
  });
}

// 企业画像按钮跳转
function displayButton(info){
  let searchButton = info;
  let url = '/PostSearchModel';
  let postData = {
    'entname': info
  }
  // ajaxMain('POST', url, postData, );
  window.location.href='./display.html'
}

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

