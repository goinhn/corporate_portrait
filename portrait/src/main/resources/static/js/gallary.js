$(function() {
	getNewInfoBusinessBackground();
	getNewInfoBusinessStability();
	getNewInfoBusinessManagementAbility();
	getNewInfoBusinessManagementRisk();
	getNewInfoJudicialRisk();
	getNewInfoCreditRisk();
});

var getNewInfoBusinessBackground = function () {
    $('#newInfoBusinessBackground').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoBusinessBackgroundInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};

var getNewInfoBusinessStability = function () {
    $('#newInfoBusinessStability').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoBusinessStabilityInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};

var getNewInfoBusinessManagementAbility = function () {
    $('#newInfoBusinessManagementAbility').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoBusinessManagementAbilityInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};

var getNewInfoBusinessManagementRisk = function () {
    $('#newInfoBusinessManagementRisk').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoBusinessManagementRiskInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};

var getNewInfoJudicialRisk = function () {
    $('#newInfoJudicialRisk').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoJudicialRiskInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};

var getNewInfoCreditRisk = function () {
    $('#newInfoCreditRisk').click(function() {
        let items = [];
        $($(this).attr('href')).find('#newInfoCreditRiskInput').each(function() {
            items.push({
                src: $(this) 
            });
        });

        $.magnificPopup.open({
            items:items,
            gallery: {
                enabled: true 
            }
        });
    });
};