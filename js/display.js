import $ from 'jquery';
import * as echcard1 from './echarts/echarts1_demo';
import * as echcard2 from './echarts/echarts2_demo';
import * as echcard3 from './echarts/echarts3_demo';
import * as echcard4 from './echarts/echarts4_demo';
import * as echcard5 from './echarts/echarts5_demo';
import * as echcard6 from './echarts/echarts6_demo';
import * as echcard7 from './echarts/echarts7_demo';

$(function () {
  let url = '';
  let data = {};
  ajaxMain('POST', url, data, entinfo);

  echartsBlocks();
});


$('#returnBackButton').on({
  click: () => {
    window.location.href = './index.html';
  }
});

function echartsBlocks(){
  try{
    echcard1._echcard1();
  }catch(error){
    console.log('1' + error);
  }
  try{
    echcard2._echcard2();
  }catch(error){
    console.log('2' + error)
  }
  try{
    echcard3._echcard3();
  }catch(error){
    console.log('3' + error)
  }
  try{
    echcard4._echcard4();
  }catch(error){
    console.log('4' + error)
  }
  try{
    echcard5._echcard5();
  }catch(error){
    console.log('5' + error)
  }
  try{
    echcard6._echcard6();
  }catch(error){
    console.log('6' + error)
  }
  try{
    echcard7._echcard7();
  }catch(error){
    console.log('7' + error)
  }
}

// 获取企业的基本信息
function entinfo(result){
  if(result){
    $('#navbarEntname').html(result.entname);

    $('#entBackground').html(result.entBackground);
    $('#entStable').html(result.entStable);
    $('#entAbility').html(result.entAbility);
    $('#entManage').html(result.entManage);
    $('#entLaw').html(result.entLaw);
    $('#entCredit').html(result.entCredit);
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