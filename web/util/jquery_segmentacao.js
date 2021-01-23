(function($){
    $.iniciarSegmentacao = function(theme,dataSeg,dataCredor,dataUsuario){
        $("#novo").jqxButton({theme: theme,width:100});
        $("#salvar").jqxButton({theme: theme,width:100});
        $("#cancelar").jqxButton({theme: theme,width:100});
        $("#alterar").jqxButton({theme: theme,width:100});
        $("#excluir").jqxButton({theme: theme,width:100});
        
        $("#atrasoate").jqxNumberInput({theme: theme,height:25,width:100,min:1,max:9999,decimalDigits:0,groupSeparator:"",digits:4,disabled:true,promptChar:" ", value: 1, autoValidate: false});
        $("#atrasode").jqxNumberInput({theme: theme,height:25,width:100,min:1,max:9999,decimalDigits:0,groupSeparator:"",digits:4,disabled:true,promptChar:" ", value: 1, autoValidate: false});
        $("#valordividaate").jqxNumberInput({theme: theme,height:25,width:130,min:0,max:999999.99,decimalDigits:2, symbol: 'R$',decimalSeparator:",",groupSeparator:"",digits:6,disabled:true,promptChar:" ", autoValidate: false});
        $("#valordividade").jqxNumberInput({theme: theme,height:25,width:130,min:0,max:999999.99,decimalDigits:2, symbol: 'R$',decimalSeparator:",",groupSeparator:"", digits:6,disabled:true,promptChar:" ", autoValidate: false});

        $("#form").jqxValidator({
             rules: [
                 { input: '#atrasoate', message: '"Dias em atraso at&eacute;" n&atilde;o pode ser menor que "Dias em atraso de"!', action: 'change', rule:  function () {
                         if($('#atrasode').val() <= $('#atrasoate').val()){
                             return true;
                         }
                         return false;
                     }
                 },
                 { input: '#atrasoate', message: '"Dias em atraso at&eacute;" n&atilde;o pode ser menor que 1!', action: 'change', rule:  function () {
                         if($('#atrasoate').val() >= 1){
                             return true;
                         }
                         return false;
                     }
                 },
                 { input: '#atrasode', message: '"Dias em atraso de" n&atilde;o pode ser maior que "Dias em atraso at&eacute;"!', action: 'change', rule:  function () {
                         if($('#atrasode').val() <= $('#atrasoate').val() || $('#atrasode').val() < 1){
                             return true;
                         }
                         return false;
                     }
                 },
                 { input: '#atrasode', message: '"Dias em atraso de" n&atilde;o pode ser menor que 1"!', action: 'change', rule:  function () {
                         if($('#atrasode').val() >= 1){
                             return true;
                         }
                         return false;
                     }
                 }
             ], theme: theme
         });

        
        var source =
            {
            localdata: dataSeg,
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
                { text: "idcredor", dataField: "credorid", align: "center", width: 50, hidden:true},
                { text: "Credor", dataField: "credor", align: "center", width: 200},
                { text: "idooperador", dataField: "operadorid", align: "center", width: 50, hidden:true},
                { text: "Operador", dataField: "operador", align: "center", width: 210},
                { text: "De", columngroup:"diasatraso", dataField: "atrasode", align: "center", width: 55, cellsalign: 'right'},
                { text: "At&eacute;", columngroup:"diasatraso", dataField: "atrasoate", align: "center", width: 55, cellsalign: 'right'},
                { text: "De", columngroup:"valordivida", dataField: "valordividade", align: "center", width: 80, cellsalign: 'right'},
                { text: "At&eacute;", columngroup:"valordivida", dataField: "valordividaate", align: "center", width: 80, cellsalign: 'right'}
            ],
            columngroups: [
                { text: 'Dias em Atraso', align: 'center', name: 'diasatraso' },
                { text: 'Valor da D&iacute;vida', align: 'center', name: 'valordivida' }
            ]
        });
        var source2 =
            {
            localdata: dataCredor,
            datatype: "array"
        };
        
        var dataAdapter2 = new $.jqx.dataAdapter(source2);
        
        $("#credor2").jqxDropDownList({ source: dataAdapter2, selectedIndex: 0, width: '660', height: '25', theme: theme, displayMember: "credor", valueMember: "id", autoDropDownHeight: true });
        
        $("#credor2").jqxDropDownList('selectIndex',$("#indexcredor2").val());
        
        var source3 =
            {
            localdata: dataUsuario,
            datatype: "array"
        };
        
        var dataAdapter3 = new $.jqx.dataAdapter(source3);
        
        $("#operador2").jqxDropDownList({ source: dataAdapter3, selectedIndex: 0, width: '660', height: '25', theme: theme, displayMember: "operador", valueMember: "id", autoDropDownHeight: true });
        
        $("#operador2").jqxDropDownList('selectIndex',$("#indexoperador2").val());
        
        $('#credor2').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                var item = args.item;
                $("#idcredor2").val(item.value);
                $("#indexcredor2").val(index);
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("SegmentacaoListar");
                $("#form").submit();
            }
        });

        $('#operador2').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                var item = args.item;
                $("#idoperador2").val(item.value);
                $("#indexoperador2").val(index);
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("SegmentacaoListar");
                $("#form").submit();
            }
        });

        $("#jqxgrid").on('rowselect', function (event) {
            if ($("#indexcredor2").val().toString() === "0"){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione um Credor!!!");
                $("#messageBoxOK").jqxWindow("open");
            }
            else{
                if ($("#indexoperador2").val().toString() === "0"){
                    $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione um Operador!!!");
                    $("#messageBoxOK").jqxWindow("open");
                }
                else{
                    var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', event.args.rowindex);
                    $("#id2").val(dataRecord.id);
                    $("#idcredor2").val(dataRecord.credorid);
                    $("#idoperador2").val(dataRecord.operadorid);
                    $("#atrasode").val(dataRecord.atrasode);
                    $("#atrasoate").val(dataRecord.atrasoate);
                    $("#valordividade").val(dataRecord.valordividade);
                    $("#valordividaate").val(dataRecord.valordividaate);
                }
            }
        });
        
        var desabilitarCampos = function(desabilita){
            $("#atrasode").jqxNumberInput({disabled:desabilita});
            $("#atrasoate").jqxNumberInput({disabled:desabilita});
            $("#valordividade").jqxNumberInput({disabled:desabilita});
            $("#valordividaate").jqxNumberInput({disabled:desabilita});
        };
        
        $("#novo").click(function(){
            if ($("#indexcredor2").val().toString() === "0"){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o Credor!!!");
                $("#messageBoxOK").jqxWindow("open");
            }
            else{
                if ($("#indexoperador2").val().toString() === "0"){
                    $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o Operador!!!");
                    $("#messageBoxOK").jqxWindow("open");
                }
                else{
                    $("#salvar").val("Inserir");
                    $("#id2").val("");
                    $('#jqxgrid').jqxGrid({ selectionmode: "none"});
                    $('#jqxgrid').jqxGrid({ enablehover: false});
                    $("#atrasoate").val("1");
                    $("#atrasode").val("1");
                    $("#valordividade").val("");
                    $("#valordividaate").val("");
                    $("#atrasode").focus();
                    $("#salvar, #cancelar").attr("type","button");
                    $("#novo, #alterar, #excluir").attr("type","hidden");
                    $("#credor2").jqxDropDownList({ disabled: true });
                    $("#operador2").jqxDropDownList({ disabled: true });
                    desabilitarCampos(false);
                }
            }
        });
        
        $("#alterar").click(function(){
            if($("#id2").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione a Segmenta&ccedil;&atilde;o que deseja alterar");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#salvar").val("Alterar");
                $('#jqxgrid').jqxGrid({ selectionmode: "none"});
                $('#jqxgrid').jqxGrid({ enablehover: false});
                $("#salvar, #cancelar").attr("type","button");
                $("#novo, #alterar, #excluir").attr("type","hidden");
                $("#credor2").jqxDropDownList({ disabled: true });
                $("#operador2").jqxDropDownList({ disabled: true });
                desabilitarCampos(false);
            }
        });
        
        $("#cancelar").click(function(){
            $("#salvar, #cancelar").attr("type","hidden");
            $("#alterar, #excluir, #novo").attr("type","button");
            $('#jqxgrid').jqxGrid({ selectionmode: "singlerow"});
            $('#jqxgrid').jqxGrid({ enablehover: true});
            $("#credor2").jqxDropDownList({ disabled: false });
            $("#operador2").jqxDropDownList({ disabled: false });
            $("#id2").val("");
            $("#atrasode").val("");
            $("#atrasoate").val("");
            $("#valordividade").val("");
            $("#valordividaate").val("");
            desabilitarCampos(true);
        });
        
        var verificarCampos = function(){
            if ($("#atrasode").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo ''Atraso De'' !!!");
                $("#messageBoxOK").jqxWindow("open");
                $("#atrasode").focus();
                return false;
            }
            if ($("#atrasoate").val().toString() === ""){
                $("#atrasoate").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo ''Atraso At&eacute;'' !!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if (parseInt($("#atrasode").val().toString()) > parseInt($("#atrasoate").val().toString())){
                $("#messageBoxOK #textoMessageBoxOK").html("O campo ''Atraso De'' n&aatilde;o pode ser maior que o campo ''Atraso At&eacute;'' !!!");
                $("#messageBoxOK").jqxWindow("open");
                $("#atrasode").focus();
                return false;
            }
            if ($("#valordividade").val().toString() === ""){
                $("#valordividade").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o campo ''Valor D&iacute;vida De''!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#valordividaate").val().toString() === ""){
                $("#valordividaate").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("Favor preencher o ''Valor D&iacute;vida At&eacute;''!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if (parseFloat($("#valordividade").val().toString()) > parseFloat($("#valordividaate").val().toString())){
                $("#valordividade").focus();
                $("#messageBoxOK #textoMessageBoxOK").html("O campo ''Valor D&iacute;vida De'' n&aatilde;o pode ser maior que o campo ''Valor D&iacute;vida At&eacute;''!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            return true;
        };

        $("#salvar").click(function(){
            $("#form").jqxValidator("validate");
        });
        
        $("#form").on("validationSuccess", function(){
            if ($("#salvar").val() === "Inserir"){
                if(verificarCampos()){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("SegmentacaoInserir");
                    $("#form").submit();
                }
            }
            else{
                if ($("#salvar").val() === "Alterar"){
                    if(verificarCampos()){
                        $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                        $("#operacao").val("SegmentacaoAlterar");
                        $("#form").submit();
                    }
                }
            }            
        });
        
        $("#excluir").click(function(){
            if($("#id2").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione a segmenta&ccedil;&atilde;o que deseja excluir");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja realmente excluir essa segmenta&ccedil;&atilde;o?");
                $("#messageBoxSimNao").jqxWindow("open");
                $("#sim").click(function(){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("SegmentacaoExcluir");
                    $("#form").submit();
                });
            }
        });
    };
})(jQuery);
