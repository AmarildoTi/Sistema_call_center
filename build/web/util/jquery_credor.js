(function($){
    $.iniciarCredor = function(theme,data){
        $("#novo").jqxButton({theme: theme,width:100});
        $("#salvar").jqxButton({theme: theme,width:100});
        $("#cancelar").jqxButton({theme: theme,width:100});
        $("#alterar").jqxButton({theme: theme,width:100});
        $("#cnpj").jqxMaskedInput({theme: theme, width:160, height: 25, mask: "##.###.###/####-##", disabled: true});
        $("#cep").jqxMaskedInput({theme: theme, width:160, height: 25, mask: "#####-###", disabled: true});
        $("#nomefantasia").jqxInput({theme: theme, width: 490, height: 25, disabled: true});
        $("#razaosocial").jqxInput({theme: theme, width: 665, height: 25, disabled: true});
        $("#endereco").jqxInput({theme: theme, width: 665, height: 25, disabled: true});
        $("#complemento").jqxInput({theme: theme, height: 25, disabled: true});
        $("#bairro").jqxInput({theme: theme, width: 497, height: 25, disabled: true});
        $("#cidade").jqxInput({theme: theme, width: 320, height: 25, disabled: true});
        $("#uf").jqxInput({theme: theme, height: 25, disabled: true});
        
        $("#form").jqxValidator({
             rules: [
                 { input: '#cnpj', message: 'Entre com um CNPJ V&aacute;lido!', action: 'keyup, blur', position: 'bottom', rule: function (input, commit) {
                         if (validaCNPJ(input.val())) {
                             return true;
                         }
                         return false;
                     }
                 },
                 { input: '#nomefantasia', message: 'Entre com o nome fantasia!', action: 'keyup, blur', rule: 'required' },
                 { input: '#nomefantasia', message: 'O nome fantasia deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', rule: 'minLength=3' },
                 { input: '#razaosocial', message: 'Entre com a raz&atilde;o social!', action: 'keyup, blur', rule: 'required' },
                 { input: '#razaosocial', message: 'A raz&atilde;o social deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', rule: 'minLength=3' },
                 { input: '#endereco', message: 'Entre com o endere&ccedil;o!', action: 'keyup, blur', rule: 'required' },
                 { input: '#endereco', message: 'O endere&ccedil;o deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', rule: 'minLength=3' },
                 { input: '#bairro', message: 'Entre com o bairro!', action: 'keyup, blur', position: 'bottom', rule: 'required' },
                 { input: '#bairro', message: 'O bairro deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', position: 'bottom', rule: 'minLength=3' },
                 { input: '#cidade', message: 'Entre com a cidade!', action: 'keyup, blur', position: 'bottom', rule: 'required' },
                 { input: '#cidade', message: 'A cidade deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', position: 'bottom', rule: 'minLength=3' },
                 { input: '#uf', message: 'Entre com a UF!', action: 'keyup, blur', rule: 'required' },
                 { input: '#uf', message: 'UF n&atilde;o pode conter n&uacute;meros!', action: 'keyup', rule: 'notNumber' },
                 { input: '#uf', message: 'UF deve conter duas letras!', action: 'keyup', rule: 'minLength=2' },
                 { input: '#cep', message: 'Entre com o CEP!', action: 'keyup, blur', position: 'bottom', rule: 'required' }
             ], theme: theme
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
                { text: "ID", dataField: "id", align: "center", width: 60 },
                { text: "CNPJ", dataField: "cnpj", align: "center", width: 150 },
                { text: "Nome Fantasia", dataField: "nomefantasia", align: "center", width: 140 },
                { text: "Raz&atilde;o Social", dataField: "razaosocial", align: "center", width: 200 },
                { text: "Endere&ccedilo", dataField: "endereco", align: "center", minwidth: 100, hidden:true },
                { text: "Complemento", dataField: "complemento", align: "center", minwidth: 100, hidden:true },
                { text: "Bairro", dataField: "bairro", align: "center", minwidth: 100, hidden:true },
                { text: "Cidade", dataField: "cidade", align: "center", minwidth: 130 },
                { text: "Estado", dataField: "estado", align: "center", minwidth: 100, hidden:true },
                { text: "CEP", dataField: "cep", align: "center", minwidth: 100, hidden:true }
            ]
        });
        
        $("#jqxgrid").on('rowselect', function (event) {
            var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', event.args.rowindex);
            $("#id").val(dataRecord.id);
            $("#cnpj").val(dataRecord.cnpj);
            $("#nomefantasia").val(dataRecord.nomefantasia);
            $("#razaosocial").val(dataRecord.razaosocial);
            $("#endereco").val(dataRecord.endereco);
            $("#complemento").val(dataRecord.complemento);
            $("#bairro").val(dataRecord.bairro);
            $("#cidade").val(dataRecord.cidade);
            $("#uf").val(dataRecord.uf);
            $("#cep").val(dataRecord.cep);
        });
        
        $("#novo").click(function(){
            $("#nomefantasia, #razaosocial, #endereco, #complemento, #bairro, #cidade, #uf").jqxInput({disabled: false});
            $("#cnpj, #cep").jqxMaskedInput({disabled: false});
            $("#cnpj, #cep").jqxMaskedInput('clear'); 
            $("#nomefantasia, #razaosocial, #endereco, #complemento, #bairro, #cidade, #uf").jqxInput('val' ,"");
            $("#salvar").val("Inserir");
            $("#id").val("");
            $('#jqxgrid').jqxGrid({ selectionmode: "none"});
            $('#jqxgrid').jqxGrid({ enablehover: false});
            $("#salvar, #cancelar").attr("type","button");
            $("#novo, #alterar").attr("type","hidden");
            $("#cnpj").focus();
            $("#cnpj").select();
        });
        
        $("#alterar").click(function(){
            if($("#id").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o usu&aacute;rio que deseja alterar");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#salvar").val("Alterar");
                $('#jqxgrid').jqxGrid({ selectionmode: "none"});
                $('#jqxgrid').jqxGrid({ enablehover: false});
                $("#salvar, #cancelar").attr("type","button");
                $("#novo, #alterar").attr("type","hidden");
                $("#nomefantasia, #razaosocial, #endereco, #complemento, #bairro, #cidade, #uf").jqxInput({disabled: false});
                $("#cep").jqxMaskedInput({disabled: false});
            }
        });
        
        $("#cancelar").click(function(){
            $("#salvar, #cancelar").attr("type","hidden");
            $("#alterar, #novo").attr("type","button");
            $('#jqxgrid').jqxGrid({ selectionmode: "singlerow"});
            $('#jqxgrid').jqxGrid({ enablehover: true});
            $("#nomefantasia, #razaosocial, #endereco, #complemento, #bairro, #cidade, #uf").jqxInput({disabled: true});
            $("#cnpj, #cep").jqxMaskedInput({disabled: true});
            $("#cnpj, #cep").jqxMaskedInput('clear');
            $("#nomefantasia, #razaosocial, #endereco, #complemento, #bairro, #cidade, #uf").jqxInput('val' ,"");
            $("#id").val("");
            $("#form").jqxValidator("hide");
        });
        
        $("#salvar").click(function(){
            $("#form").jqxValidator("validate");
        });
        
        $("#form").on("validationSuccess", function (event){
            if ($("#salvar").val() === "Inserir"){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("CredorInserir");
                $("#form").submit();
            }
            if ($("#salvar").val() === "Alterar"){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("CredorAlterar");
                $("#form").submit();
            }
        });
    };
})(jQuery);
