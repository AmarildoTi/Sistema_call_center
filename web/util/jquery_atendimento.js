(function($){
    $.iniciarAtendimento = function(theme,data,datax,valorentrada,desconto,maximoentrada,maximodesconto,datacontato,devedor,dataAtendimento,dataNegociacao){
        $("#cadastrar").jqxButton({theme: theme,width:220,height: '25'});
        $("#jqxAbas").jqxTabs({theme: theme });
        $("#NegociacaoConcluir").jqxButton({ theme: theme,height: '25', disabled:false});
        $("#valorentrada").jqxNumberInput({theme: theme,height:'25',width:115,min:0.00,max:maximoentrada,decimalDigits:2, symbolPosition: 'left', symbol: 'R$',decimalSeparator:",",groupSeparator:"",digits:6,promptChar:" "});
        $("#valorentrada").val(valorentrada);
        $("#valordivida").jqxInput({theme: theme,height:'25',width:115});
        $("#email").jqxInput({theme: theme,height:'25',width:323, disabled:true});
        $("#valorcorrigido").jqxInput({theme: theme,height:'25',width:115});
        $("#valornegociacao").jqxInput({theme: theme,height:'25',width:115});
        $("#checkemail").jqxCheckBox({theme: theme,height:'25',width:152,disabled:false, checked:false,boxSize:"14px"});
        $("#checkdesconto").jqxCheckBox({theme: theme,height:'25',width:152 , disabled:true,boxSize:"14px"});
        $("#desconto").jqxNumberInput({theme: theme,height:'25',width:115,min:0.00,max:maximodesconto,decimalDigits:2, symbolPosition: 'left', symbol: 'R$',decimalSeparator:",",groupSeparator:"",digits:6,promptChar:" ", disabled:true});
        $("#desconto").val(desconto);
        $("#maximodesconto").jqxInput({theme: theme,height:'25',width:115, disabled:true});

        $("#checkendereco").jqxCheckBox({theme: theme,height:'25',width:151,boxSize:"14px"});
        $("#endereco").jqxInput({theme: theme,height:'25',width:458, disabled:true});
        $("#complemento").jqxInput({theme: theme,height:'25',width:168, disabled:true});
        $("#bairro").jqxInput({theme: theme,height:'25',width:225, disabled:true});
        $("#cidade").jqxInput({theme: theme,height:'25',width:225, disabled:true});
        $("#uf").jqxInput({theme: theme,height:'25',width:40, disabled:true});
        $("#cep").jqxMaskedInput({theme: theme,height:'25',width:120, disabled:true, mask: "#####-###"});
        $("#cep").val(devedor.cep);
        $("#atualizarendereco").jqxButton({theme: theme,width:220,height: '25', disabled:true});
        $("#atualizarcontato").jqxButton({theme: theme,width:220,height: '25', disabled:true});

        $("#checkcontato").jqxCheckBox({theme: theme,height:'25',width:151,boxSize:"14px"});
        $("#contatonovo").jqxMaskedInput({theme: theme,height:'25',width:180, disabled:true, mask: "##-####-####"});
        
        var sourceAtendimento =
            {
            localdata: dataAtendimento,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(sourceAtendimento);
        $("#historicoatendimento").jqxGrid(
        {
            width: 730,
            source: dataAdapter,
            theme: theme,
            columnsresize: true,
            autoheight: true,
            altrows: true,
            pageable: true,
            localization: $.getLocalization(),
            enabletooltips: true,
            rendered: tooltiprenderer,
            pagesize: 5,
            columns: [
                { text: "Operador", dataField: "operador", align: "left", width: 130 },
                { text: "Data", dataField: "data", align: "left", width: 160 },
                { text: "Status", dataField: "status", align: "left", width: 150 },
                { text: "Descri&ccedil;&atilde;o", dataField: "descricao", align: "left", width: 290 }
            ]
        });
        
        var sourceNegociacao =
            {
            localdata: dataNegociacao,
            datatype: "array"
        };
        
        var tooltiprenderer = function (element) {
            $(element).jqxTooltip({position: 'mouse', content: $(element).text() });
        };
        
        var dataAdapter = new $.jqx.dataAdapter(sourceNegociacao);
        $("#historiconegociacao").jqxGrid(
        {
            width: 730,
            source: dataAdapter,
            theme: theme,
            columnsresize: true,
            autoheight: true,
            altrows: true,
            pageable: true,
            enabletooltips: true,
            localization: $.getLocalization(),
            pagesize: 5,
            rendered: tooltiprenderer,
            columns: [
                { text: "Status", dataField: "status", align: "left", width: 120 },
                { text: "Parcelamento", dataField: "parcelamento", align: "left", width: 100 },
                { text: "Data", dataField: "data", align: "left", width: 160 },
                { text: "Valor Corrigido", dataField: "valorcorrigido", align: "left", width: 175 },
                { text: "Valor Negociado", dataField: "valornegociado", align: "left", width: 175 }
            ]
        });
        
        var sourceTipo =
            {
            localdata: datacontato,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(sourceTipo);
        
        $("#tipo").jqxDropDownList({theme: theme,height:25,width:160,source: dataAdapter, autoDropDownHeight: true, disabled: true, displayMember: "tipo", valueMember: "contato", placeHolder: "Escolha um contato"});

        if( maximodesconto > 0.0){
            if (desconto > 0.0){
                $("#checkdesconto").jqxCheckBox({disabled:false, checked:true});
                $("#desconto").jqxNumberInput({disabled:false});
                $("#maximodesconto").jqxInput({disabled:false});
            }else{
                $("#checkdesconto").jqxCheckBox({disabled:false, checked:false});
            }
        }else{
            $("#checkdesconto").jqxCheckBox({disabled:true, checked:false});
        }
        
        var source2 =
            {
            localdata: data,
            datatype: "array"
        };
        
        var dataAdapter2 = new $.jqx.dataAdapter(source2);
        
        $("#statusatendimento").jqxDropDownList({ source: dataAdapter2, selectedIndex: 0, width: '220', height: '25', theme: theme, displayMember: "status", valueMember: "id", dropDownHeight: 150 });
        
        $("#statusatendimento").jqxDropDownList('selectIndex',$("#indexstatus").val());
        var item = $("#statusatendimento").jqxDropDownList('getSelectedItem'); 
        $("#idstatus").val(item.value);
        
        $('#statusatendimento').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                var item = args.item;
                $("#idstatus").val(item.value);
                $("#indexstatus").val(index);
            }
        });
        
        $('#tipo').on('select', function (event) {
            var args = event.args;
            if (args) {
                var item = args.item;
                var index = args.index;
                $("#tipocontato").val(item.label);
                if (item.label === "CELULAR"){
                    $("#contatonovo").jqxMaskedInput({mask: "##-#####-####"});
                }else{
                    $("#contatonovo").jqxMaskedInput({mask: "##-####-####"});
                }
                $("#contatonovo").val(item.value);
                $("#contato").val(index);
            }
        });
        
        var datamin = new Date();
        var datamax = new Date();
        datamax.setDate(datamax.getDate()+30);
        var dmy = datax.split(",");
        $("#datapagamento").jqxDateTimeInput({width: 115, height: '25', theme: theme, textAlign: "center", value: new Date(parseInt(dmy[0]),parseInt(dmy[1]),parseInt(dmy[2]))});
        
        $("#datapagamento").jqxDateTimeInput('setMinDate', datamin);
        $("#datapagamento").jqxDateTimeInput('setMaxDate', datamax);
        
        $('#datapagamento').change(function(){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("OpcaoNegociacaoCalcular");
                $("#form").submit();
        }); 

        $('#valorentrada').change(function(){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("OpcaoNegociacaoCalcular");
                $("#form").submit();
        }); 

        $('#desconto').change(function(){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("OpcaoNegociacaoCalcular");
                $("#form").submit();
        });
        
        $("#checkemail").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#email").jqxInput({disabled:false});
                $("#email").focus();
            }else{
                $("#email").jqxInput({disabled:true});
            }
        });
        
        $("#checkdesconto").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#desconto").jqxNumberInput({disabled:false});
                $("#maximodesconto").jqxInput({disabled:false});
                $("#desconto").focus();
            }else{
                $("#desconto").jqxNumberInput({disabled:true});
                $("#maximodesconto").jqxInput({disabled:true});
                $("#desconto").val(0);
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("OpcaoNegociacaoCalcular");
                $("#form").submit();
            }        
        });
        
        $("#checkcontato").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#contatonovo").jqxMaskedInput({disabled:false});
                $("#tipo").jqxDropDownList({disabled:false});
                $("#contatonovo").focus();
                $("#atualizarcontato").jqxButton({disabled:false});
            }else{
                $("#contatonovo").jqxMaskedInput({disabled:true});
                $("#tipo").jqxDropDownList({disabled:true});
                $("#atualizarcontato").jqxButton({disabled:true});
                $("#tipo").jqxDropDownList("clearSelection");
                $("#contatonovo").jqxMaskedInput("clear");
                $("#contato").val("");
            }
        });
        
        $("#checkendereco").on('change', function (event) {
            var checked = event.args.checked;
            if (checked) {
                $("#endereco").jqxInput({disabled:false});
                $("#complemento").jqxInput({disabled:false});
                $("#bairro").jqxInput({disabled:false});
                $("#cidade").jqxInput({disabled:false});
                $("#uf").jqxInput({disabled:false});
                $("#cep").jqxMaskedInput({disabled:false});
                $("#endereco").focus();
                $("#atualizarendereco").jqxButton({disabled:false});
            }else{
                $("#endereco").jqxInput({disabled:true});
                $("#endereco").val(devedor.endereco);
                $("#complemento").jqxInput({disabled:true});
                $("#complemento").val(devedor.complemento);
                $("#bairro").jqxInput({disabled:true});
                $("#bairro").val(devedor.bairro);
                $("#cidade").jqxInput({disabled:true});
                $("#cidade").val(devedor.cidade);
                $("#uf").jqxInput({disabled:true});
                $("#uf").val(devedor.uf);
                $("#cep").jqxMaskedInput({disabled:true});
                $("#cep").val(devedor.cep);
                $("#atualizarendereco").jqxButton({disabled:true});
            }
        });
        
        $("#cadastrar").click(function(){
            if ($("#descricao").val() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor descrever o atendimento");
                $("#messageBoxOK").jqxWindow("open");
                $("#cpf").focus();
            }else{
                $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja cadastrar essas informa&ccedil;&otilde;es no atendimento");
                $("#messageBoxSimNao").jqxWindow("open");
                $("#sim").click(function(){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("AtendimentoInserir");
                    $("#form").submit();
                });
            }
        });
                
        $('#atualizarendereco').click(function(){
            $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja realmente atualizar o endere&ccedil;o?");
            $("#messageBoxSimNao").jqxWindow("open");
            $("#sim").click(function(){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("DevedorAlterar");
                $("#form").submit();
            });
        });
        
        $('#atualizarcontato').click(function(){
            if ($("#tipo").jqxDropDownList('getSelectedIndex') > -1){
                $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja realmente atualizar o contato?");
                $("#messageBoxSimNao").jqxWindow("open");
                $("#sim").click(function(){
                    $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                    $("#operacao").val("ContatoAlterar");
                    $("#form").submit();
                });
            }
        });
        
        $.getScript('/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.culture.pt-BR.js', function () {
            $("#datapagamento").jqxDateTimeInput({ culture: 'pt-BR' });
        });                
        
        $("#NegociacaoConcluir").click(function(){
            $("#messageBoxSimNao #textoMessageBoxSimNao").html("Deseja concluir a negocia&ccedil;&atilde;o");
            $("#messageBoxSimNao").jqxWindow("open");
            $("#sim").click(function(){
                $("#messageBox #textoMessageBox").html("Concluindo a negocia&ccedil;&atilde;o...");
                $("#messageBox").jqxWindow("open");
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("NegociacaoConcluir");
                $("#form").submit();
            });
        });
    };    
})(jQuery);
