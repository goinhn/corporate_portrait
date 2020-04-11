

$(function () {
    lookAll();
});


function submitNow() {
    let companyBaseinfoNewInfo = getBackOneInfo($('#companyBaseinfoNewInfoGallary'));
    let entName = companyBaseinfoNewInfo.entname;
    if (typeof (entName) == "undefined") {
        alert("输入的企业名称不能为空！");
        return;
    } else {
        if (entName.toString().trim().split(0, 255) == '') {
            alert("输入的企业名称不能为空！");
            return;
        }
    }

    let url = localUrl + '/por/save/searchEntName/' + entName;
    ajaxTool("GET", url, judgeEntName);
}

function judgeEntName(data) {
    if (data.flag == true) {
        getFormInfo();
    } else {
        alert(data.errorMsg);
    }
}


function getFormInfo() {
    let companyBaseinfoNewInfo = getModeOneInfo($('#companyBaseinfoNewInfoGallary'));
    let changeInfoNewInfo = getModeOneInfoArray($('#changeInfoNewInfoGallary'));
    let entContributionNewInfo = getModeOneInfoArray($('#entContributionNewInfoGallary'));
    let entContributionYearNewInfo = getModeOneInfoArray($('#entContributionYearNewInfoGallary'));
    let enterpriseInsuranceNewInfo = getModeOneInfoArray($('#enterpriseInsuranceNewInfoGallary'));
    let entGuaranteeNewInfo = getModeOneInfoArray($('#entGuaranteeNewInfoGallary'));
    let entSocialSecurityNewInfo = getModeOneInfoArray($('#entSocialSecurityNewInfoGallary'));
    let jnCreditInfoNewInfo = getModeOneInfo($('#jnCreditInfoNewInfoGallary'));
    let justiceDeclareNewInfo = getModeOneInfoArray($('#justiceDeclareNewInfoGallary'));
    let justiceEnforcedNewInfo = getModeOneInfoArray($('#justiceEnforcedNewInfoGallary'));
    let justiceJudgeNewNewInfo = getModeOneInfoArray($('#justiceJudgeNewNewInfoGallary'));
    let oneDataNewInfo = getModeOneInfo($('#oneDataNewInfoGallary'));

    if (changeInfoNewInfo.remark instanceof Array) {
        changeInfoNewInfo.remark = changeInfoNewInfo.remark[0];
    }
    if (changeInfoNewInfo.dataflag instanceof Array) {
        changeInfoNewInfo.dataflag = changeInfoNewInfo.dataflag[0];
    }
    if (changeInfoNewInfo.alttime instanceof Array) {
        changeInfoNewInfo.alttime = changeInfoNewInfo.alttime[0];
    }
    if (changeInfoNewInfo.cxstatus instanceof Array) {
        changeInfoNewInfo.cxstatus = changeInfoNewInfo.cxstatus[0];
    }

    let coptra = {};
    $.extend(coptra, companyBaseinfoNewInfo);
    $.extend(coptra, changeInfoNewInfo);
    $.extend(coptra, entContributionNewInfo);
    $.extend(coptra, entContributionYearNewInfo);
    $.extend(coptra, enterpriseInsuranceNewInfo);
    $.extend(coptra, entGuaranteeNewInfo);
    $.extend(coptra, entSocialSecurityNewInfo);
    $.extend(coptra, jnCreditInfoNewInfo);
    $.extend(coptra, justiceDeclareNewInfo);
    $.extend(coptra, justiceEnforcedNewInfo);
    $.extend(coptra, justiceJudgeNewNewInfo);
    $.extend(coptra, oneDataNewInfo);

    let send = JSON.stringify({"coptra": coptra});
    ajaxTool("POST", remoteUrl, getMode, send);
}

function getMode(data) {
    // console.log(data);
    if (data.flag == 0) {
        if (sendBackFormInfo()) {
            if (sendBackAnalysisLabel(data.data)) {
                alert("提交成功")
            } else {
                alert("提交失败");
            }
        } else {
            alert("提交失败");
        }
    } else if (data.flag == 1) {
        alert(data.errorMsg);
    } else {
        alert("模型计算发生错误");
    }
}

function sendBackFormInfo() {
    let companyBaseinfoNewInfo = getBackOneInfo($('#companyBaseinfoNewInfoGallary'));
    let entName = companyBaseinfoNewInfo.entname;
    let changeInfoNewInfo = getBackOneInfoArray($('#changeInfoNewInfoGallary'), entName);
    let entContributionNewInfo = getBackOneInfoArray($('#entContributionNewInfoGallary'), entName);
    let entContributionYearNewInfo = getBackOneInfoArray($('#entContributionYearNewInfoGallary'), entName);
    let enterpriseInsuranceNewInfo = getBackOneInfoArray($('#enterpriseInsuranceNewInfoGallary'), entName);
    let entGuaranteeNewInfo = getBackOneInfoArray($('#entGuaranteeNewInfoGallary'), entName);
    let entSocialSecurityNewInfo = getBackOneInfoArray($('#entSocialSecurityNewInfoGallary'), entName);
    let jnCreditInfoNewInfo = getBackOneInfo($('#jnCreditInfoNewInfoGallary'));
    let justiceDeclareNewInfo = getBackOneInfoArray($('#justiceDeclareNewInfoGallary'), entName);
    let justiceEnforcedNewInfo = getBackOneInfoArray($('#justiceEnforcedNewInfoGallary'), entName);
    let justiceJudgeNewNewInfo = getBackOneInfoArray($('#justiceJudgeNewNewInfoGallary'), entName);
    let oneDataNewInfo = getBackOneInfo($('#oneDataNewInfoGallary'));

    jnCreditInfoNewInfo.entname = entName;
    oneDataNewInfo.entname = entName;

    let send = {
        "companyBaseinfoNewInfo": companyBaseinfoNewInfo,
        "changeInfoNewInfos": changeInfoNewInfo,
        "entContributionNewInfos": entContributionNewInfo,
        "entContributionYearNewInfos": entContributionYearNewInfo,
        "enterpriseInsuranceNewInfos": enterpriseInsuranceNewInfo,
        "entGuaranteeNewInfos": entGuaranteeNewInfo,
        "entSocialSecurityNewInfos": entSocialSecurityNewInfo,
        "jnCreditInfoNewInfo": jnCreditInfoNewInfo,
        "justiceDeclareNewInfos": justiceDeclareNewInfo,
        "justiceEnforcedNewInfos": justiceEnforcedNewInfo,
        "justiceJudgeNewNewInfos": justiceJudgeNewNewInfo,
        "oneDataNewInfo": oneDataNewInfo
    };

    // console.log(JSON.stringify(send));

    let url = localUrl + '/por/save/saveNewInfo';

    return ajaxTool("POST", url, getBackInfoLook, JSON.stringify(send));
}

function getBackInfoLook(data) {
    if (!data.flag) {
        alert(data.errorMsg);
    }
}


function sendBackAnalysisLabel(data) {
    let entName = data.entname;
    let mms_data = data.mms_data;
    let model_pred = data.model_pred;

    let min = mms_data["bidnum"];
    let max = mms_data["bidnum"];
    for (let each in mms_data) {
        if (min > mms_data[each]) {
            min = mms_data[each];
        }
        if (max < mms_data[each]) {
            max = mms_data[each];
        }
    }

    let bei = max - min;

    for (let each in mms_data) {
        let abs = Math.abs(mms_data[each]);
        let result = abs / bei;
        mms_data[each] = result.toFixed(10);
    }

    let send = {
        "businessBackgroundAnalysis": {
            "entName": entName,
            "empNum": mms_data.empnum,
            "encodeEntStatus": mms_data.encode_entstatus,
            "shopNum": mms_data.shopnum,
            "branchNum": mms_data.branchnum,
            "isInfoA": mms_data.is_infoa,
            "isInfoB": mms_data.is_infob,
            "levelRank": mms_data.level_rank
        },
        "businessManagementAbilityAnalysis": {
            "entName": entName,
            "evaluation": mms_data.evaluation,
            "investNum": mms_data.investnum,
            "bidNum": mms_data.bidnum,
            "cbzt": mms_data.cbzt,
            "ibrandNum": mms_data.ibrand_num,
            "icopyNum": mms_data.icopy_num,
            "ipatNum": mms_data.ipat_num,
            "idomNum": mms_data.idom_num,
            "passPercent": mms_data.passpercent
        },
        "businessManagementRiskAnalysis": {
            "entName": entName,
            "priclasecam": mms_data.priclasecam,
            "encodeGuaranperiod": mms_data.encode_guaranperiod,
            "encodeGatype": mms_data.encode_gatype,
            "isRage": mms_data.is_rage,
            "subPefperfromto": mms_data.sub_pefperfromto,
            "unpaidsocialins": mms_data.unpaidsocialins,
            "isBra": mms_data.is_bra,
            "isBrap": mms_data.is_brap,
            "pledgeNum": mms_data.pledgenum,
            "taxunpaidNum": mms_data.taxunpaidnum,
            "isExcept": mms_data.is_except
        },
        "businessStabilityAnalysis": {
            "entName": entName,
            "altTime": mms_data.alttime
        },
        "creditRiskAnalysis": {
            "entName": entName,
            "isPunish": mms_data.is_punish,
            "isKcont": mms_data.is_kcont,
            "creditGrade": mms_data.credit_grade,
            "isJusticeCreditaic": mms_data.is_justice_creditaic
        },
        "judicialRiskAnalysis": {
            "entName": entName,
            "lawSum": mms_data.law_sum,
            "defendant": mms_data.defendant,
            "enforceAmount": mms_data.enforce_amount,
            "isJusticeCredit": mms_data.is_justice_credit

        },
        "businessBackgroundLabel": {
            "entName": entName,
            "label": model_pred.df_bj
        },
        "businessManagementAbilityLabel": {
            "entName": entName,
            "label": model_pred.df_jn
        },
        "businessManagementRiskLabel": {
            "entName": entName,
            "label": model_pred.df_jf
        },
        "businessStabilityLabel": {
            "entName": entName,
            "label": model_pred.df_wd
        },
        "creditRiskLabel": {
            "entName": entName,
            "label": model_pred.df_xf
        },
        "judicialRiskLabel": {
            "entName": entName,
            "label": model_pred.df_sf
        }
    };
    let url = localUrl + '/por/save/saveNewAnalysisLabel';

    return ajaxTool("POST", url, getBackAnalysisLabelLook, JSON.stringify(send))

}


function getBackAnalysisLabelLook(data) {
    if (!data.flag) {
        alert(data.errorMsg);
    }
}


function getModeOneInfo(formOut) {
    let resultJson = {};

    let one = formOut.find('form').serializeArray();
    for (let key in one) {
        let value = one[key]['value'].toString().trim().slice(0, 255);
        if (value == '') {
            resultJson[one[key]['name']] = 0;
        } else {
            let reg = /^[0-9]*$/;
            let pattern = new RegExp(reg);
            if (pattern.test(value)) {
                resultJson[one[key]['name']] = parseInt(value);
            } else {
                let time = value;
                time.replace(new RegExp("-", "gm"), "/");
                let timeNumber = (new Date(time)).getTime();
                if (isNaN(timeNumber)) {
                    resultJson[one[key]['name']] = value;
                } else {
                    resultJson[one[key]['name']] = timeNumber;
                }
            }
        }
    }
    return resultJson;
}

function getModeOneInfoArray(formOut) {
    let resultJson = {};

    formOut.find('form').each(function () {
        let one = $(this).serializeArray();
        for (let key in one) {
            resultJson[one[key]['name']] = [];
        }
        return false;
    });

    formOut.find('form').each(function () {
        let one = $(this).serializeArray();
        for (let key in one) {
            let value = one[key]['value'].toString().trim().slice(0, 255);
            if (value == '') {
                resultJson[one[key]['name']].push(0);
            } else {
                let reg = /^[0-9]*$/;
                let pattern = new RegExp(reg);
                if (pattern.test(value)) {
                    resultJson[one[key]['name']].push(parseInt(value));
                } else {
                    let time = value;
                    time.replace(new RegExp("-", "gm"), "/");
                    let timeNumber = (new Date(time)).getTime();
                    if (isNaN(timeNumber)) {
                        resultJson[one[key]['name']].push(value);
                    } else {
                        resultJson[one[key]['name']].push(timeNumber);
                    }
                }
            }
        }
    });

    return resultJson;
}


function getBackOneInfo(formOut) {
    let resultJson = {};

    let one = formOut.find('form').serializeArray();
    for (let key in one) {
        let value = one[key]['value'].toString().trim().slice(0, 255);
        resultJson[one[key]['name']] = value;
    }
    return resultJson;
}

function getBackOneInfoArray(formOut, entName) {
    let resultList = [];

    formOut.find('form').each(function () {
        let resultJson = {};
        let one = $(this).serializeArray();
        for (let key in one) {
            let value = one[key]['value'].toString().trim().slice(0, 255);
            resultJson[one[key]['name']] = value;
        }
        resultJson["entname"] = entName;
        resultList.push(resultJson);
    });

    return resultList;
}


function setNext(now) {
    let length = $(now).parent().parent().find('form').length;

    if (length < 10) {
        let $newNode = $(now).parent().parent().find('form:last');
        let number = parseInt($newNode.find("div:first").html());
        let $newForm = $newNode.clone();
        $newForm.find("input").each(function () {
            $(this).val('');
        });
        $newForm.find("textarea").each(function () {
            $(this).val('');
        });
        $newForm.find("div:first").html(number + 1);
        $newNode.after($newForm);
    } else {
        alert("不能创建更多表单");
    }
}


function setClear(now) {
    let length = $(now).parent().parent().find('form').length;

    if (length > 1) {
        let $newNode = $(now).parent().parent().find('form:last');
        $newNode.remove();
    } else {
        alert("不能撤销更多表单");
    }
}


function lookAll() {
    hiddenLookAll($('#companyBaseinfoNew'));
    hiddenLookAll($('#changeInfoNew'));
    hiddenLookAll($('#entContributionNew'));
    hiddenLookAll($('#entContributionYearNew'));
    hiddenLookAll($('#enterpriseInsuranceNew'));
    hiddenLookAll($('#entGuaranteeNew'));
    hiddenLookAll($('#entSocialSecurityNew'));
    hiddenLookAll($('#jnCreditInfoNew'));
    hiddenLookAll($('#justiceDeclareNew'));
    hiddenLookAll($('#justiceEnforcedNew'));
    hiddenLookAll($('#justiceJudgeNewNew'));
    hiddenLookAll($('#oneDataNew'));
}


function hiddenLookAll(location, input) {
    location.click(function () {
        let items = [];
        $($(this).attr('href')).find("> div").each(function () {
            items.push({
                src: $(this)
            });
        });

        $.magnificPopup.open({
            items: items,
            type: 'inline',
            gallery: {
                enabled: true
            }
        });
    });
}


