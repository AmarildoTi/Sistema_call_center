(function($){
    $.iniciarPerfil = function(theme,data,data2){
        $("#novo").jqxButton({theme: theme,width:100});
        $("#salvar").jqxButton({theme: theme,width:100});
        $("#cancelar").jqxButton({theme: theme,width:100});
        $("#alterar").jqxButton({theme: theme,width:100});
        $("#excluir").jqxButton({theme: theme,width:100});
        
        $("#atrasode").jqxNumberInput({theme: theme,height:25,width:100,min:1,max:9999,decimalDigits:0,groupSeparator:"",digits:4,disabled:true,promptChar:" ", value: 1});
        $("#atrasoate").jqxNumberInput({theme: theme,height:25,width:100,min:1,max:9999,decimalDigits:0,groupSeparator:"",digits:4,disabled:true,promptChar:" ", value: 1});
        $("#maximoparcelas").jqxNumberInput({theme: theme,height:25,width:100,min:1,max:99,decimalDigits:0,groupSeparator:"",digits:2,disabled:true,promptChar:" ", value: 1});
        $("#juros").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,max:100.00,decimalDigits:2, symbolPosition: 'right', symbol: '%',decimalSeparator:",",groupSeparator:"",digits:3,disabled:true,promptChar:" "});
        $("#multa").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,max:100.00,decimalDigits:2, symbolPosition: 'right', symbol: '%',decimalSeparator:",",groupSeparator:"",digits:3,disabled:true,promptChar:" "});
        $("#jurosporparcela").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,max:100.00,decimalDigits:2, symbolPosition: 'right', symbol: '%',decimalSeparator:",",groupSeparator:"",digits:3,disabled:true,promptChar:" "});
        $("#descontovalorprincipal").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,max:100.00,decimalDigits:2, symbolPosition: 'right', symbol: '%',decimalSeparator:",",groupSeparator:"",digits:3,disabled:true,promptChar:" "});
        $("#descontoencargos").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,max:100.00,decimalDigits:2, symbolPosition: 'right', symbol: '%',decimalSeparator:",",groupSeparator:"",digits:3,disabled:true,promptChar:" "});
        $("#valorminimoentrada").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,decimalDigits:2, symbolPosition: 'left', symbol: 'R$',decimalSeparator:",",groupSeparator:"",digits:4,disabled:true,promptChar:" "});
        $("#valorminimoparcela").jqxNumberInput({theme: theme,height:25,width:100,min:0.00,decimalDigits:2, symbolPosition: 'left', symbol: 'R$',decimalSeparator:",",groupSeparator:"",digits:4,disabled:true,promptChar:" "});
        
        $('#messageBoxOK2').jqxWindow({title:"Aviso", width: 460, height: 200,
            theme: theme, resizable: false, isModal: true, autoOpen: false, showCloseButton: false,
            okButton: $('#ok2'),
            initContent: function () {
                $('#ok2').jqxButton({ theme: theme, width: '65px' });
                $('#ok2').focus();
            }
        });       
        
        var source =
            {
            localdata: data,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#jqxgrid").jqxGrid(
        {
            width: 680,
            source: dataAdapter,
            theme: theme,
            columnsresize: true,
            autoheight: true,
            altrows: true,
            pageable: true,
            localization: $.getLocalization(),
            columns: [
                { text: "id", dataField: "id", align: "center", width: 50, hidden:true},
                { text: "De", columngroup:"diasatraso", dataField: "atrasoDe", align: "center", width: 55, cellsalign: 'right'},
                { text: "At&eacute;", columngroup:"diasatraso", dataField: "atrasoAte", align: "center", width: 55, cellsalign: 'right'},
                { text: "M&aacute;x", dataField: "maximoParcelas", align: "center", width: 40, cellsalign: 'right'},
                { text: "Juros", columngroup:"encargos", dataField: "juros", align: "center", width: 60,  cellsalign: 'right'},
                { text: "Multa", columngroup:"encargos", dataField: "multa", align: "center", width: 60, cellsalign: 'right'},
                { text: "Parcela", columngroup:"encargos", dataField: "jurosPorParcela", align: "center", width: 60,  cellsalign: 'right'},
                { text: "Principal", columngroup:"desconto", dataField: "descontoValorPrincipal", align: "center", width: 70, cellsalign: 'right'},
                { text: "Encargos", columngroup:"desconto", dataField: "descontoEncargos", align: "center", width: 70, cellsalign: 'right'},
                { text: "Entrada", columngroup:"valorminimo", dataField: "valorMinimoEntrada", align: "center", width: 70,  cellsalign: 'right'},
                { text: "Parcela", columngroup:"valorminimo", dataField: "valorMinimoParcela", align: "center", width: 70,  cellsalign: 'right'},
                { text: "Tipo", dataField: "tipo", align: "center", width: 70}
            ],
            columngroups: [
                { text: 'Dias em Atraso', align: 'center', name: 'diasatraso' },
                { text: 'Encargos %', align: 'center', name: 'encargos' },
                { text: 'Desconto %', align: 'center', name: 'desconto' },
                { text: 'Valor M&iacute;nimo R$', align: 'center', name: 'valorminimo' }
            ]
        });
        var source2 =
            {
            localdata: data2,
            datatype: "array"
        };
        
        var dataAdapter2 = new $.jqx.dataAdapter(source2);
        
        $("#credor").jqxDropDownList({ source: dataAdapter2, selectedIndex: 0, width: '660', height: '25', theme: theme, displayMember: "credor", valueMember: "id", autoDropDownHeight: true });
        
        $("#credor").jqxDropDownList('selectIndex',$("#indexcredor").val());
        
        
        var source3 = ["NORMAL","CAMPANHA"];
        
        $("#tipo").jqxDropDownList({theme: theme,height:25,width:210,source: source3,selectedIndex: 0, autoDropDownHeight: true, disabled: true});
                
        $('#credor').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                var item = args.item;
                $("#idcredor").val(item.value);
                $("#indexcredor").val(index);
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("PerfilNegociacaoListar");
                $("#form").submit();
            }
        });
        
        $("#jqxgrid").on('rowselect', function (event) {
            var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', event.args.rowindex);
            $("#id").val(dataRecord.id);
            $("#atrasode").val(dataRecord.atrasoDe);
            $("#atrasoate").val(dataRecord.atrasoAte);
            $("#maximoparcelas").val(dataRecord.maximoParcelas);
            $("#juros").val(dataRecord.juros);
            $("#multa").val(dataRecord.multa);
            $("#jurosporparcela").val(dataRecord.jurosPorParcela);
            $("#descontovalorprincipal").val(dataRecord.descontoValorPrincipal);
            $("#descontoencargos").val(dataRecord.descontoEncargos);
            $("#valorminimoentrada").val(dataRecord.valorMinimoEntrada);
            $("#valorminimoparcela").val(dataRecord.valorMinimoParcela);
            $("#tipo").val(dataRecord.tipo);
        });
        
        var desabilitarCampos = function(desabilita){
            $("#atrasode").jqxNumberInput({disabled:desabilita});
            $("#atrasoate").jqxNumberInput({disabled:desabilita});
            $("#maximoparcelas").jqxNumberInput({disabled:desabilita});
            $("#juros").jqxNumberInput({disabled:desabilita});
            $("#multa").jqxNumberInput({disabled:desabilita});
            $("#jurosporparcela").jqxNumberInput({disabled:desabilita});
            $("#descontovalorprincipal").jqxNumberInput({disabled:desabilita});
            $("#descontoencargos").jqxNumberInput({disabled:desabilita});
            $("#valorminimoentrada").jqxNumberInput({disabled:desabilita});
            $("#valorminimoparcela").jqxNumberInput({disabled:desabilita});
            $("#tipo").jqxDropDownList({disabled:desabilita}); 
        };
        
        $("#novo").click(function(){
            $("#salvar").val("Inserir");
            $("#id").val("");
            $('#jqxgrid').jqxGrid({ selectionmode: "none"});
            $('#jqxgrid').jqxGrid({ enablehover: false});
            $("#atrasoate").val("");
            $("#maximoparcelas").val("");
            $("#juros").val("");
            $("#multa").val("");
            $("#jurosporparcela").val("");
            $("#descontovalorprincipal").val("");
            $("#descontoencargos").val("");
            $("#valorminimoentrada").val("");
            $("#valorminimoparcela").val("");
            $("#atrasode").val("");
            $("#salvar, #cancelar").attr("type","button");
            $("#novo, #alterar, #excluir").attr("type","hidden");
            $("#credor").jqxDropDownList({ disabled: true });
            desabilitarCampos(false);
            $("#atrasode").jqxNumberInput('focus');
            $("#atrasode").select();
        });
        
        $("#alterar").click(function(){
            if($("#id").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o Perfil de Negocia&ccedil;&atilde;o que deseja alterar");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#salvar").val("Alterar");
                $('#jqxgrid').jqxGrid({ selectionmode: "none"});
                $('#jqxgrid').jqxGrid({ enablehover: false});
                $("#salvar, #cancelar").attr("type","button");
                $("#novo, #alterar, #excluir").attr("type","hidden");
                $("#credor").jqxDropDownList({ disabled: true });
                desabilitarCampos(false);
            }
        });
        
        $("#cancelar").click(function(){
            $("#salvar, #cancelar").attr("type","hidden");
            $("#alterar, #excluir, #novo").attr("type","button");
            $('#jqxgrid').jqxGrid({ selectionmode: "singlerow"});
            $('#jqxgrid').jqxGrid({ enablehover: true});
            $("#credor").jqxDropDownList({ disabled: false });
            $("#id").val("");
            $("#atrasode").val("");
            $("#atrasoate").val("");
            $("#maximoparcelas").val("");
            $("#juros").val("");
            $("#multa").val("");
            $("#jurosporparcela").val("");
            $("#descontovalorprincipal").val("");
            $("#descontoencargos").val("");
            $("#valorminimoentrada").val("");
            $("#valorminimoparcela").val("");
            desabilitarCampos(true);
        });
        
        var verificarCampos = function(){
            if ($("#atrasode").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Atraso de !!!");
                $("#messageBoxOK").jqxWindow("open");
                $("#atrasode").focus();
                return false;
            }
            if ($("#atrasoate").val().toString() === ""){
                $("#atrasoate").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Atraso At&eacute; !!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#maximoparcelas").val().toString() === ""){
                $("#maximoparcelas").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo M&acuteximo de parcelas!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#juros").val().toString() === ""){
                $("#juros").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Juros!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#multa").val().toString() === ""){
                $("#multa").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Multa;!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#jurosporparcela").val().toString() === ""){
                $("#jurosporparcela").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Juros por Parcela;!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#descontovalorprincipal").val().toString() === ""){
                $("#descontovalorprincipal").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Desconto do Valor Principal!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#descontoencargos").val().toString() === ""){
                $("#descontoencargos").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Desconto Encargos!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#valorminimoentrada").val().toString() === ""){
                $("#valorminimoentrada").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Valor M&iacute;nimo Entrada!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#valorminimoparcela").val().toString() === ""){
                $("#valorminimoparcela").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Valor M&iacute;nimo Parcela!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#tipo").val().toString() === ""){
                $("#tipo").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo Tipo!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            return true;
        };

        $("#salvar").click(function(){
            if ($("#salvar").val() === "Inserir"){
                if(verificarCampos()){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("PerfilNegociacaoInserir");
                    $("#form").submit();
                }
            }
            else{
                if ($("#salvar").val() === "Alterar"){
                    if(verificarCampos()){
                        $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                        $("#operacao").val("PerfilNegociacaoAlterar");
                        $("#form").submit();
                    }
                }
            }
        });
        
        $("#excluir").click(function(){
            if($("#id").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o perfil de negocia&ccedil;&atilde;o que deseja excluir");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja realmente excluir esse perfil de negocia&ccedil;&atilde;o?");
                $("#messageBoxSimNao").jqxWindow("open");
                $("#sim").click(function(){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("PerfilNegociacaoExcluir");
                    $("#form").submit();
                });
            }
        });
    };
})(jQuery);
