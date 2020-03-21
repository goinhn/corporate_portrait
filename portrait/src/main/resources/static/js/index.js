$(function () {
    getBusinessBackgroundChart();
    getBusinessStabilityChart();
    getBusinessManagementAbilityChart();
    getBusinessManagementRiskChart();
    getJudicialRiskChart();
    getCreditRiskChart();

    displayTable(testObjectDate);
});

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

var displayTable = function(info){
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


// var getBusinessBackgroundChart = function () {
//     $('.open-popup-link').magnificPopup({
//         type: 'inline'
//         // removalDelay: 300,
//         // mainClass: 'mfp-with-zoom',
//         // titleSrc: 'title'
//         // gallery: {
//         //     enabled: true
//         // },
//         // zoom: {
//         //     enabled: true, // By default it's false, so don't forget to enable it

//         //     duration: 600, // duration of the effect, in milliseconds
//         //     easing: 'ease-in-out', // CSS transition easing function
//         // }
//     });
// }

var getBusinessBackgroundChart = function () {
    console.log("businessBackgroundChart begin");

    let myChart = echarts.init(document.getElementById('businessBackgroundChart'))
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
                {name: '企业（机构）名称', max: 1},
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
                data: [
                    {
                        name: '企业背景分析',
                        value: [0.1333333330, 1.0000000000, 0.1111111110, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}

var getBusinessStabilityChart = function () {
    console.log("businessStabilityChart begin");

    let myChart = echarts.init(document.getElementById('businessStabilityChart'))
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
                data: [
                    {
                        name: '企业稳定性分析',
                        value: [0.0064665130]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}

var getBusinessManagementAbilityChart = function () {
    console.log("businessManagementAbilityChart begin");

    let myChart = echarts.init(document.getElementById('businessManagementAbilityChart'))
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
                data: [
                    {
                        name: '企业经营能力分析',
                        value: [0.6666666670, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.1000000000, 0.0000000000]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}

var getBusinessManagementRiskChart = function () {
    console.log("businessManagementRiskChart begin");

    let myChart = echarts.init(document.getElementById('businessManagementRiskChart'))
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
                {name: '保证的期间', max: 1},
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
                data: [
                    {
                        name: '企业经营风险分析',
                        value: [0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000, 0.0192428180, 0.0000000000, 0.0000000000, 0.0769230770, 0.0000000000, 0.0000000000, 0.0000000000]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}

var getJudicialRiskChart = function () {
    console.log("judicialRiskChart begin");

    let myChart = echarts.init(document.getElementById('judicialRiskChart'))
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
                data: [
                    {
                        name: '司法风险分析',
                        value: [0.0000000000, 0.0000000000, 0.0000000000, 0.0000000000]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}

var getCreditRiskChart = function () {
    console.log("creditRiskChart begin");

    let myChart = echarts.init(document.getElementById('creditRiskChart'))
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
                data: [
                    {
                        name: '信用风险分析',
                        value: [0.3333333330, 0.0000000000, 0.0000000000, 0.0000000000]
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);
}