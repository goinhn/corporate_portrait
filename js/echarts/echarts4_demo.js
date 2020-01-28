import $ from 'jquery';
import * as echarts from 'echarts';

const _echcard4 = echcard4;
export { _echcard4 };

function echcard4() {
  let myChart = display();
  myChart.hideLoading();

  let url = '';
  let data = {};
  let option = ajaxMain('POST', url, data, dataPopulation);
  myChart.setOption(option);
}

// echarts数据结构
function display() {
  let myChart = echarts.init(document.getElementById('echarts4'));

  let option = {
    tooltip: {
      trigger: 'item',
      formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
      orient: 'vertical',
      x: 'left',
      data: ['直达', '营销广告', '搜索引擎', '邮件营销', '联盟广告', '视频广告', '百度', '谷歌', '必应', '其他']
    },
    series: [
      {
        name: '访问来源',
        type: 'pie',
        selectedMode: 'single',
        radius: [0, '30%'],

        label: {
          normal: {
            position: 'inner'
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          { value: 335, name: '直达', selected: true },
          { value: 679, name: '营销广告' },
          { value: 1548, name: '搜索引擎' }
        ]
      },
      {
        name: '访问来源',
        type: 'pie',
        radius: ['40%', '55%'],

        data: [
          { value: 335, name: '直达' },
          { value: 310, name: '邮件营销' },
          { value: 234, name: '联盟广告' },
          { value: 135, name: '视频广告' },
          { value: 1048, name: '百度' },
          { value: 251, name: '谷歌' },
          { value: 147, name: '必应' },
          { value: 102, name: '其他' }
        ]
      }
    ]
  };
  myChart.setOption(option);
  myChart.showLoading();
  return myChart;
}


// ajax数据填充
function dataPopulation(result) {
  let names = [];
  let nums = [];
  if (result) {
    for (var i = 0; i < result.length; i++) {
      names.push(result[i].name);
    }
    for (var i = 0; i < result.length; i++) {
      nums.push(result[i].num);
    }
    let option = {
      xAxis: {
        data: names
      },
      series: [{
        name: '销量',
        data: nums
      }]
    };
    return option;
  }
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