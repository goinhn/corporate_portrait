import $ from 'jquery';
import * as echarts from 'echarts';

const _echcard1 = echcard1;
export { _echcard1 };


function echcard1() {
  let myChart = display();
  myChart.hideLoading();

  let url = '';
  let data = {};
  let option = ajaxMain('POST', url, data, dataPopulation);
  myChart.setOption(option);
}

// echarts数据结构
function display() {
  let myChart = echarts.init(document.getElementById('echarts1'));


 let option = {
    backgroundColor: "#FFFFFF",
    grid: {
      left: '12%',
      top: '5%',
      bottom: '12%',
      right: '8%'
    },
    xAxis: {
      data: ['驯鹿', '火箭', '飞机', '高铁', '轮船', '汽车', '跑步', '步行',],
      axisTick: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 129, 109, 0.1)',
          width: 1 //这里是为了突出显示加上的
        }
      },
      axisLabel: {
        textStyle: {
          color: '#999',
          fontSize: 12
        }
      }
    },
    yAxis: [{
      splitNumber: 2,
      axisTick: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 129, 109, 0.1)',
          width: 1 //这里是为了突出显示加上的
        }
      },
      axisLabel: {
        textStyle: {
          color: '#999'
        }
      },
      splitArea: {
        areaStyle: {
          color: 'rgba(255,255,255,.5)'
        }
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: 'rgba(255, 129, 109, 0.1)',
          width: 0.5,
          type: 'dashed'
        }
      }
    }
    ],
    series: [{
      name: 'hill',
      type: 'pictorialBar',
      barCategoryGap: '0%',
      symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
      label: {
        show: true,
        position: 'top',
        distance: 15,
        color: '#DB5E6A',
        fontWeight: 'bolder',
        fontSize: 20,
      },
      itemStyle: {
        normal: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
              offset: 0,
              color: 'rgba(232, 94, 106, .8)' //  0%  处的颜色
            },
            {
              offset: 1,
              color: 'rgba(232, 94, 106, .1)' //  100%  处的颜色
            }
            ],
            global: false //  缺省为  false
          }
        },
        emphasis: {
          opacity: 1
        }
      },
      data: [123, 60, 25, 18, 12, 9, 2, 1],
      z: 10
    }]
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

