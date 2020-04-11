const localUrl = "http://localhost:8889";
const searchHtml = "<div name=\"block\" class=\"col-md-12\">\n" +
    "                    <div class=\"form-group col-md-2\">\n" +
    "                        <div class=\"col-md-8 col-md-offset-2 btn btn-primary btn-lg\" onclick=\"delInput(this)\">-</div>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-8\">\n" +
    "                        <input class=\"form-control\" placeholder=\"公司名 / 公司序号\" type=\"text\">\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-2\">\n" +
    "                        <div class=\"col-md-8 col-md-offset-2 btn btn-primary btn-lg\" onclick=\"addInput(this)\">+</div>\n" +
    "                    </div>\n" +
    "                </div>";

const testData = [
    {
        "entName": "8f0c15a6c0d2c09a157feef19ddb8783",
        "entCat": "有限责任公司(自然人投资或控股)"
    },
    {
        "entName": "8f0c15a6c0d2c09a157feef19ddb8783",
        "entCat": "有限责任公司(自然人投资或控股)"
    },
    {
        "entName": "8f0c15a6c0d2c09a157feef19ddb8783",
        "entCat": "有限责任公司(自然人投资或控股)"
    }
];

const data1 = [
    {
        name: '企业背景分析',
        value: [0.1333333330, 1.0000000000, 0.1111111110, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000]
    }
];

const data2 = [
    {
        name: '企业稳定性分析',
        value: [0.0064665130]
    }
];

const data3 = [
    {
        name: '企业经营能力分析',
        value: [0.6666666670, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.1000000000, 0.0000000000]
    }
];

const data4 = [
    {
        name: '企业经营风险分析',
        value: [0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0192428180, 0.0000000000, 0.0000000000, 0.0769230770, 0.0000000000, 0.0000000000, 0.0000000000]
    }
];

const data5 = [
    {
        name: '司法风险分析',
        value: [0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000]
    }
];

const data6 = [
    {
        name: '信用风险分析',
        value: [0.3333333330, 0.0000000000, 0.0000000000, 0.0000000000]
    }
];

$(function () {
    searchSubmit();
});


function searchSubmit() {
    $("#searchButton").on({
        click: () => {
            let $searchInput = $("#searchBlock input");
            let entNameArray = [];
            $searchInput.each(function (index, element) {
                let tempElem = $(element).val().toString().trim();
                if (tempElem != "" && element != null) {
                    entNameArray.push(tempElem);
                }
            });

            //去重
            let newEntNameArray = [];
            let flag = false;
            for (let i = 0; i < entNameArray.length; i++) {
                flag = true;
                for (let j = 0; j < newEntNameArray.length; j++) {
                    if (newEntNameArray[j] == entNameArray[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    newEntNameArray.push(entNameArray[i]);
                }
            }

            let newEntNameStr = newEntNameArray.toString();
            if (newEntNameStr == "" || newEntNameStr == null) {
                alert("输入值不能为空！");
            } else {
                let url = localUrl + "/por/search/searchEntName/" + newEntNameStr;
                ajaxTool("GET", url, searchTable);
            }
        }
    });
}


function searchTable(data) {
    if (data.flag == true) {
        displayTable(data.data);
        let location = $("#searchButton").offset().top;
        $("html,body").animate({scrollTop: location}, 500);
    } else {
        alert(data.errorMsg);
    }
}


function displayTable(data) {
    let $tableDisplay = $('#tableDisplay').bootstrapTable('destroy').bootstrapTable({
        data: data,
        striped: true,                      //是否显示行间隔色
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 1,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "entName",                //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                     //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: false,
            visible: false
        }, {
            field: 'entName',
            title: '企业名称',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'entCat',
            title: '企业类别',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'entPortrait',
            title: '企业画像',
            align: 'center',
            valign: 'middle',
            formatter: operation,
        }],
        onLoadSuccess: function () {

        },
        onLoadError: function () {
            console.log("display table error")
        },
        onDblClickRow: function (row, $element) {

        },
    });
}


function operation(value, row, index) {
    return '<button name=' + row['entName'] + ' onclick="getInfo(this)" >企业画像</button>';
}


function getInfo(now) {
    let entName = $(now).attr('name');
    console.log(entName);
    let url = localUrl + "/por/search/searchEntInfo/" + entName.toString();
    ajaxTool("GET", url, showInfo);

}


function showInfo(data) {
    if (data.flag) {
        lookEntInfo(data.data.info);

        lookBusinessBackgroundChart(data.data.chart.businessBackground);
        lookBusinessManagementAbilityChart(data.data.chart.businessManagementAbility);
        lookBusinessManagementRiskChart(data.data.chart.businessManagementRisk);
        lookBusinessStabilityChart(data.data.chart.businessStability);
        lookCreditRiskChart(data.data.chart.creditRisk);
        lookJudicialRiskChart(data.data.chart.judicialRisk);

        lookLabel(data.data.label);
        let location = $("#fh5co-work").offset().top;
        $("html,body").animate({scrollTop: location}, 800);
    } else {
        alert(data.errorMsg);
    }
}


function lookEntInfo(data) {
    for (let key in data) {
        if (data[key] == '' || data[key] == null) {
            data[key] = '未知';
        }
    }
    $("#entNameInfo").html(data.entName);
    $('#entTypeInfo').html(data.entType);
    $('#entCatInfo').html(data.entCat);
    $('#industryphyInfo').html(data.industryPhy);
    $('#entStatusInfo').html(data.entStatus);
    $('#bidNumInfo').html(data.bidNum);
    $('#branchNumInfo').html(data.branchNum);
    $('#investNumInfo').html(data.investNum);
    $('#shopNumInfo').html(data.shopNum);
    $('#creditGradeInfo').html(data.creditGrade);
}


function lookBusinessBackgroundChart(data) {
    let myChart = echarts.init(document.getElementById('businessBackgroundChartInfo'));
    let option = {
        title: {
            show: true,
            text: '企业背景',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            indicator: [
                {name: '从业人数', max: 1},
                {name: '企业状态', max: 1},
                {name: '网店个数', max: 1},
                {name: '分支机构数', max: 1},
                {name: '是否列入驰名商标', max: 1},
                {name: '是否列入著名商标', max: 1},
                {name: '级别', max: 1}
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '企业背景数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}

function lookBusinessStabilityChart(data) {

    let myChart = echarts.init(document.getElementById('businessStabilityChartInfo'));
    let option = {
        title: {
            show: true,
            text: '企业稳定性',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            shape: 'circle',
            indicator: [
                {name: '变更次数', max: 1}
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '企业稳定性数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}

function lookBusinessManagementAbilityChart(data) {

    let myChart = echarts.init(document.getElementById('businessManagementAbilityChartInfo'));
    let option = {
        title: {
            show: true,
            text: '企业经营能力',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            indicator: [
                {name: '是否按时足额缴纳股本', max: 1},
                {name: '对外投资次数', max: 1},
                {name: '中标次数', max: 1},
                {name: '参保状态', max: 1},
                {name: '商标数据', max: 1},
                {name: '软件著作权', max: 1},
                {name: '专利数据', max: 1},
                {name: '域名数据', max: 1},
                {name: '产品被抽查合格率', max: 1}
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '企业经营能力数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}

function lookBusinessManagementRiskChart(data) {

    let myChart = echarts.init(document.getElementById('businessManagementRiskChartInfo'));
    let option = {
        title: {
            show: true,
            text: '企业经营风险',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            indicator: [
                {name: '主债权数额', max: 1},
                {name: '保证的期限', max: 1},
                {name: '保证的方式', max: 1},
                {name: '保证担保的范围', max: 1},
                {name: '履行债务间隔', max: 1},
                {name: '单位参加的保险累计欠缴额标', max: 1},
                {name: '是否列入经营异常', max: 1},
                {name: '企业行政处罚记录', max: 1},
                {name: '出质股权次数', max: 1},
                {name: '企业累计欠税额', max: 1},
                {name: '是否被列为异常', max: 1},
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '企业经营风险数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}

function lookJudicialRiskChart(data) {

    let myChart = echarts.init(document.getElementById('judicialRiskChartInfo'));
    let option = {
        title: {
            show: true,
            text: '司法风险',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            indicator: [
                {name: '司法次数', max: 1},
                {name: '被诉方', max: 1},
                {name: '执行标的', max: 1},
                {name: '是否列入\n失信黑名单', max: 1}
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '司法风险数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}

function lookCreditRiskChart(data) {

    let myChart = echarts.init(document.getElementById('creditRiskChartInfo'));
    let option = {
        title: {
            show: true,
            text: '信用风险',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 20
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        radar: {
            center: ['50%', '50%'],
            radius: '120',
            indicator: [
                {name: '公司是否有过行政处罚', max: 1},
                {name: '是否列为守合\n同重信用企业', max: 1},
                {name: '信用等级 N+、B-、A、C、N、A-', max: 1},
                {name: '是否列入失信\n企业(工商部)', max: 1}
            ],
            name: {
                formatter: '【{value}】',
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                        'rgba(114, 172, 209, 0.4)',
                        'rgba(114, 172, 209, 0.6)',
                        'rgba(114, 172, 209, 0.8)',
                        'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        },
        series: [
            {
                name: '信用风险数据',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: '#CC3333',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        color: '#000',
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                data: data
            }
        ]
    };
    myChart.setOption(option);
}


function lookLabel(data) {
    $('#businessBackgroundLabelInfo').html(data.businessBackgroundLabel);
    $('#businessManagementAbilityLabelInfo').html(data.businessManagementAbilityLabel);
    $('#businessManagementRiskLabelInfo').html(data.businessManagementRiskLabel);
    $('#businessStabilityLabelInfo').html(data.businessStabilityLabel);
    $('#creditRiskLabelInfo').html(data.creditRiskLabel);
    $('#judicialRiskLabelInfo').html(data.judicialRiskLabel);
}


//删除输入函数
function delInput(now) {
    let searchBlock = $("#searchBlock > div[name='block']");
    if (searchBlock.length == 1) {
        return;
    }
    $(now).parent().parent().remove();
}


//添加输入函数
function addInput(now) {
    let searchBlock = $("#searchBlock > div[name='block']");
    if (searchBlock.length == 10) {
        return;
    }
    $(now).parent().parent().after(searchHtml);
}


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
            window.location.href = localUrl + "/por/error/404";
        }
    });

    if (pass) {
        return true;
    } else {
        return false;
    }
}