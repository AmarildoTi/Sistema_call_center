(function($){
    $.iniciarRelatorio = function(theme, dataCredor){
        
        $("#dia").jqxRadioButton({ width: 120, height: 25, checked: true,disabled:true});
        $("#mes").jqxRadioButton({ width: 120, height: 25,disabled:true});
        $("#ano").jqxRadioButton({ width: 120, height: 25,disabled:true});
        $("#intervalo").jqxRadioButton({ width: 120, height: 25,disabled:true});
        
        $("#periodoinicial").jqxDateTimeInput({width: '135', height: '25', theme: theme, textAlign: "center", max: new Date(), formatString: "dd/MM/yyyy",readonly:true,showCalendarButton: false});
        $("#periodofinal").jqxDateTimeInput({width: '135', height: '25', theme: theme, textAlign: "center", max: new Date(), formatString: "dd/MM/yyyy",readonly:true,showCalendarButton: false});
        $("#periodoinicial").jqxDateTimeInput({disabled:true});
        $("#periodofinal").jqxDateTimeInput({disabled:true});
        
        $.getScript('/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.culture.pt-BR.js', function () {
            $("#periodoinicial").jqxDateTimeInput({ culture: 'pt-BR' });
            $("#periodofinal").jqxDateTimeInput({ culture: 'pt-BR' });
        });
        
        var opcaoRelatorio;
        
        $("#gerarrelatorio").jqxButton({theme: theme,width:220,height: '25'});

        var sourceTipo = ["Relat&oacute;rio de Usu&aacute;rios","Relat&oacute;rio de Credores","Relat&oacute;rio de Perfis de Negocia&ccedil;&atilde;o","Relat&oacute;rio D&iacute;vida por Perfil de Negocia&ccedil;&atilde;o","Relat&oacute;rio Acordos A Quebrar","Relat&oacute;rio Acordos Quebrados","Relat&oacute;rio D&iacute;vidas Quitadas","Relat&oacute;rio de Negocia&ccedil;&otilde;es Sint&eacute;tico","Relat&oacute;rio de Negocia&ccedil;&otilde;es Anal&iacute;tico","Relat&oacute;rio de Acordos Sint&eacute;tico","Relat&oacute;rio de Acordos Anal&iacute;tico","Relat&oacute;rio de Promessas Sint&eacute;tico","Relat&oacute;rio de Promessas Anal&iacute;tico"];
        
        $("#tiporelatorio").jqxDropDownList({theme: theme,height:25,width:650,source: sourceTipo, autoDropDownHeight: true, placeHolder: "Escolha um Relat&oacute;rio"});

        $('#tiporelatorio').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                $("#relatorio").val(index);
            }
        });

        var sourceCredor =
            {
            localdata: dataCredor,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(sourceCredor);
        
        $("#credor").jqxDropDownList({ source: dataAdapter, width: '650', height: '25', theme: theme, displayMember: "credor", valueMember: "id", autoDropDownHeight: true, placeHolder: "Escolha um Credor", disabled: true });

        $('#credor').on('change', function (event){
            var args = event.args;
            if (args) {
                var item = args.item;
                $("#idcredor").val(item.value);
            }
        });

        $('#tiporelatorio').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                opcaoRelatorio = index;
                if (opcaoRelatorio > 1){
                    $("#credor").jqxDropDownList({disabled: false});
                }else{
                    $("#credor").jqxDropDownList({disabled: true});
                }
                if (opcaoRelatorio > 6){
                    $("#dia").jqxRadioButton({disabled:false});
                    $("#mes").jqxRadioButton({disabled:false});
                    $("#ano").jqxRadioButton({disabled:false});
                    $("#intervalo").jqxRadioButton({disabled:false});
                    $("#periodoinicial").jqxDateTimeInput({disabled:false});
                    $("#periodofinal").jqxDateTimeInput({disabled:false});
                 }else{
                    $("#dia").jqxRadioButton({disabled:true});
                    $("#mes").jqxRadioButton({disabled:true});
                    $("#ano").jqxRadioButton({disabled:true});
                    $("#intervalo").jqxRadioButton({disabled:true});
                    $("#periodoinicial").jqxDateTimeInput({disabled:true});
                    $("#periodofinal").jqxDateTimeInput({disabled:true});
                }
            }
        });



        
        $("#dia").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#periodoinicial").jqxDateTimeInput({ value:new Date()});
                $("#periodofinal").jqxDateTimeInput({ value:new Date()});
            }
        });
        
        $("#mes").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                var Hoje = new Date();
                var Dia = 1;
                var Mes = Hoje.getMonth();
                var Ano = Hoje.getFullYear();
                $("#periodoinicial").jqxDateTimeInput({ value:new Date(Ano, Mes, Dia)});
                $("#periodofinal").jqxDateTimeInput({ value:Hoje});
            }
        });
        
        $("#ano").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                var Hoje = new Date();
                var Dia = 1;
                var Mes = 0;
                var Ano = Hoje.getFullYear();
                $("#periodoinicial").jqxDateTimeInput({ value:new Date(Ano, Mes, Dia)});
                $("#periodofinal").jqxDateTimeInput({ value:Hoje});
            }
        });
        
        $("#intervalo").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#periodoinicial").jqxDateTimeInput({readonly:false, showCalendarButton:true});
                $("#periodofinal").jqxDateTimeInput({readonly:false, showCalendarButton:true});
            }else{
                $("#periodoinicial").jqxDateTimeInput({readonly:true, showCalendarButton:false});
                $("#periodofinal").jqxDateTimeInput({readonly:true, showCalendarButton:false});
            }
        });

        $("#gerarrelatorio").click(function(){
            if ($("#tiporelatorio").jqxDropDownList('getSelectedIndex') < 0){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor escolher um tipo de relat&oacute;rio!!!");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                if (opcaoRelatorio > 1 && $("#credor").jqxDropDownList('getSelectedIndex') < 0){
                    $("#messageBoxOK #textoMessageBoxOK").html("Favor escolher um credor!!!");
                    $("#messageBoxOK").jqxWindow("open");
                }else{
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#form").attr("target","_blank");
                    $("#operacao").val("RelatorioExibir");
                    $("#form").submit();
                    $("#form").attr("target","");
                }
            }
        });
    };
})(jQuery);