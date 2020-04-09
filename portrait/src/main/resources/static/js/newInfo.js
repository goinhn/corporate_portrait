const remoteUrl = 'http://47.98.249.83:8000/predict';

$(function () {
    lookAll();
});

console.log(localUrl);


function submitNow() {
    // let companyBaseinfoNewInfo = getModeOneInfo($('#companyBaseinfoNewInfoGallary'));
    // let entName = companyBaseinfoNewInfo.entName;
    // let url = localUrl + '/save/searchEntName/' + entName;
    // ajaxTool("GET", url, judgeEntName);

    getBackFormInfo();
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
    console.log(data);
    if (data.flag == 0) {
        console.log(data.data);

    } else if (data.flag == 1) {
        alert(data.errorMsg);
    } else {
        alert("模型计算发生错误");
    }
}

function getBackFormInfo() {
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

    let send = {
        "companyBaseinfoNewInfo": companyBaseinfoNewInfo,
        "changeInfoNewInfo": changeInfoNewInfo,
        "entContributionNewInfo": entContributionNewInfo,
        "entContributionYearNewInfo": entContributionYearNewInfo,
        "enterpriseInsuranceNewInfos": enterpriseInsuranceNewInfo,
        "entGuaranteeNewInfos": entGuaranteeNewInfo,
        "entSocialSecurityNewInfos": entSocialSecurityNewInfo,
        "jnCreditInfoNewInfo": jnCreditInfoNewInfo,
        "justiceDeclareNewInfo": justiceDeclareNewInfo,
        "justiceEnforcedNewInfo": justiceEnforcedNewInfo,
        "justiceJudgeNewNewInfos": justiceJudgeNewNewInfo,
        "oneDataNewInfo": oneDataNewInfo
    };

    console.log(JSON.stringify(send));

    let url = localUrl + '/por/save/saveEntName';

    ajaxTool("POST", url, getBackInfoLook, JSON.stringify(send));

}

function getBackInfoLook(data) {
    if (data.flag == true) {
        return true;
    } else {
        alert(data.errorMsg);
        return false;
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
        $newForm.find("div:first").html(number + 1);
        $newNode.after($newForm);
    } else {
        alert("不能创建更多表单");
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


