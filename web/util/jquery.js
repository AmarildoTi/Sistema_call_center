(function($){
    $.getLocalization = function () {
        var localizationobj = {};
        localizationobj.pagergotopagestring = "Ir para:";
        localizationobj.pagershowrowsstring = "Mostrar Linha:";
        localizationobj.pagerrangestring = " de ";
        localizationobj.pagernextbuttonstring = "anterior";
        localizationobj.pagerpreviousbuttonstring = "próximo";
        localizationobj.sortascendingstring = "Ordem ascendente";
        localizationobj.sortdescendingstring = "Ordem Decrescente";
        localizationobj.sortremovestring = "Remover Ordenação";
        localizationobj.firstDay = 1;
        localizationobj.percentsymbol = "%";
        localizationobj.currencysymbol = "€";
        localizationobj.currencysymbolposition = "after";
        localizationobj.decimalseparator = ".";
        localizationobj.thousandsseparator = ",";
        var days = {
            // full day names
            names: ["Domingo", "Segunda", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"],
            namesAbbr: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
            // shortest day names
            namesShort: ["D", "S", "T", "Q", "Q", "S", "S"]
        };
        localizationobj.days = days;
        var months = {
            // full month names (13 months for lunar calendards -- 13th month should be "" if not lunar)
            names: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", ""],
            // abbreviated month names
            namesAbbr: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez", ""]
        };
        var patterns = {
            d: "dd.MM.yyyy",
            D: "dddd, d. MMMM yyyy",
            t: "HH:mm",
            T: "HH:mm:ss",
            f: "dddd, d. MMMM yyyy HH:mm",
            F: "dddd, d. MMMM yyyy HH:mm:ss",
            M: "dd MMMM",
            Y: "MMMM yyyy"
        };
        localizationobj.patterns = patterns;
        localizationobj.months = months;
        localizationobj.todaystring = "Hoje";
        localizationobj.clearstring = "Limpar";
        return localizationobj;
    };
    $.iniciar = function(theme){
        $('#messageBoxSimNao').jqxWindow({title:"Aviso", width: 450, height: 100,
            theme: theme, resizable: false, isModal: true, autoOpen: false, showCloseButton: false,
            okButton: $('#sim'), cancelButton: $('#nao'),keyboardCloseKey: 0,
            initContent: function () {
                $('#sim').jqxButton({ theme: theme, width: '65px' });
                $('#nao').jqxButton({ theme: theme, width: '65px' });
                $('#sim').jqxButton("focus");
            }
        });
        $('#messageBoxOK').jqxWindow({title:"Aviso", width: 450, height: 100,
            theme: theme, resizable: false, isModal: true, autoOpen: false, showCloseButton: false,
            okButton: $('#ok'),keyboardCloseKey: 0,
            initContent: function () {
                $('#ok').jqxButton({ theme: theme, width: '65px' });
                $('#ok').jqxButton("focus");
            }
        });
        $('#messageBox').jqxWindow({title:"Aviso", width: 450, height: 100,
            theme: theme, resizable: false, isModal: true, autoOpen: false, showCloseButton: false,
            keyboardCloseKey: 0,
            initContent: function () {
            }
        });
        $("#jqxMenu").jqxMenu({ width: '148', mode: 'vertical', theme: theme});
        $("#jqxMenu").css('visibility', 'visible');
    };
})(jQuery);