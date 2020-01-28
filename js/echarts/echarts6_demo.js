import $ from 'jquery';
import * as echarts from 'echarts';

const _echcard6 = echcard6;
export { _echcard6 };

function echcard6() {
  let myChart = display();
  myChart.hideLoading();

  let url = '';
  let data = {};
  let option = ajaxMain('POST', url, data, dataPopulation);
  myChart.setOption(option);
}

// echarts数据结构
function display() {
  let myChart = echarts.init(document.getElementById('echarts6'));

  let option = {
    title: {
      text: '阶梯瀑布图',
      subtext: 'From ExcelHome',
      sublink: 'http://e.weibo.com/1341556070/Aj1J2x5a5'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      },
      formatter: function (params) {
        var tar;
        if (params[1].value != '-') {
          tar = params[1];
        }
        else {
          tar = params[0];
        }
        return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
      }
    },
    legend: {
      data: ['支出', '收入']
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      splitLine: { show: false },
      data: function () {
        var list = [];
        for (var i = 1; i <= 11; i++) {
          list.push('11月' + i + '日');
        }
        return list;
      }()
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '辅助',
        type: 'bar',
        stack: '总量',
        itemStyle: {
          normal: {
            barBorderColor: 'rgba(0,0,0,0)',
            color: 'rgba(0,0,0,0)'
          },
          emphasis: {
            barBorderColor: 'rgba(0,0,0,0)',
            color: 'rgba(0,0,0,0)'
          }
        },
        data: [0, 900, 1245, 1530, 1376, 1376, 1511, 1689, 1856, 1495, 1292]
      },
      {
        name: '收入',
        type: 'bar',
        stack: '总量',
        label: {
          normal: {
            show: true,
            position: 'top'
          }
        },
        data: [900, 345, 393, '-', '-', 135, 178, 286, '-', '-', '-']
      },
      {
        name: '支出',
        type: 'bar',
        stack: '总量',
        label: {
          normal: {
            show: true,
            position: 'bottom'
          }
        },
        data: ['-', '-', '-', 108, 154, '-', '-', '-', 119, 361, 203]
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