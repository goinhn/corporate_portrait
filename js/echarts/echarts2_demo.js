import $ from 'jquery';
import * as echarts from 'echarts';

const _echcard2 = echcard2;
export { _echcard2 };

function echcard2() {
  let myChart = display();
  myChart.hideLoading();

  let url = '';
  let data = {};
  let option = ajaxMain('POST', url, data, dataPopulation);
  myChart.setOption(option);
}

// echarts数据结构
function display() {
  let myChart = echarts.init(document.getElementById('echarts2'));

  let option = {
    title: {
      text: '分类雷达图',
    },
    radar: {
      indicator: [{
        name: '责任心',
        max: 100
      },
      {
        name: '执行力',
        max: 100
      },
      {
        name: '情商',
        max: 100
      },
      {
        name: '沟通能力',
        max: 100
      },
      {
        name: '合作性',
        max: 100
      },
      {
        name: '影响力',
        max: 100
      },
      {
        name: '学习能力',
        max: 100
      },
      {
        name: '洞察力',
        max: 100
      },
      {
        name: '开放性',
        max: 100
      },
      {
        name: '成就导向',
        max: 100
      },
      {
        name: '坚韧性',
        max: 100
      },
      ],
      center: ['50%', '50%'],
      radius: '66%'
    },

    series: [{
      type: 'radar',
      zlevel: 2,
      tooltip: {
        show: true
      },
      lineStyle: {
        width: 1,
        opacity: 1
      },
      areaStyle: {
        normal: {}
      },
      data: [{
        value: [80, 80, 80, 80, 0, 0, 0, 0, 0, 0, 0],
      },
      {
        value: [0, 0, 0, 80, 80, 80, 80, 0, 0, 0, 0],
      },
      {
        value: [0, 0, 0, 0, 0, 0, 80, 80, 80, 80, 0],
      },
      {
        value: [80, 0, 0, 0, 0, 0, 0, 0, 0, 80, 80],
      },

      ]
    }, {
      type: 'sunburst',
      center: ['50%', '50%'],
      nodeClick: false,
      levels: [{},
      {
        r0: '80%',
        r: '90%',
      }
      ],
      data: [{
        name: '职场渴望（愿不愿意好好干）',
        value: 4,
        label: {
          position: 'inside',
          rotate: 'tangential'
        }
      },
      {
        name: '职场潜力（能干多好）',
        value: 4,
        label: {
          position: 'inside',
          rotate: 'tangential'
        }
      },
      {
        name: '职场协作（能不能持续地干好）',
        value: 4,
        label: {
          position: 'inside',
          rotate: 'tangential'
        }
      },
      {
        name: '职场基础（能不能干好）',
        value: 4,
        label: {
          position: 'inside',
          rotate: 'tangential'
        }
      },
      ],

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