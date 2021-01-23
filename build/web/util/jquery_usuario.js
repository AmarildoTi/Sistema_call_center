(function($){
    $.iniciarUsuario = function(theme,data,meuID,meuTipo){
        $("#novo").jqxButton({theme: theme,width:100, height: 25});
        $("#salvar").jqxButton({theme: theme,width:100, height: 25});
        $("#cancelar").jqxButton({theme: theme,width:100, height: 25});
        $("#alterar").jqxButton({theme: theme,width:100, height: 25});
        $("#cpf").jqxMaskedInput({theme: theme, width:130, height: 25, mask: "###.###.###-##", disabled: true});
        $("#nome").jqxInput({theme: theme, width: 310, height: 25, disabled: true});
        $("#usuario").jqxInput({theme: theme, width: 110, height: 25, disabled: true});
        $("#senha").jqxInput({theme: theme, width: 110, height: 25, disabled: true});

        $("#form").jqxValidator({
             rules: [
                 { input: '#cpf', message: 'Entre com um CPF V&aacute;lido!', action: 'keyup, blur', position: 'bottom', rule: function (input, commit) {
                         if (validaCPF(input.val())) {
                             return true;
                         }
                         return false;
                     }
                 },
                 { input: '#nome', message: 'Entre com o nome!', action: 'keyup, blur', rule: 'required' },
                 { input: '#nome', message: 'O nome deve ter conter somente letras!', action: 'keyup', rule: 'notNumber' },
                 { input: '#nome', message: 'O nome deve ter no m&iacute;nimo 3 caracteres!', action: 'keyup', rule: 'minLength=3' },
                 { input: '#usuario', message: 'Entre com o seu usu&aacute;rio!', action: 'keyup, blur', position: 'bottom', rule: 'required' },
                 { input: '#usuario', message: 'Usu&aacute;rio deve ter de 6 a 10 caracteres!', action: 'keyup', position: 'bottom', rule: 'length=6,10' },
                 { input: '#senha', message: 'Entre com a sua senha!', action: 'keyup, blur', rule: 'required' },
                 { input: '#senha', message: 'Sua senha deve ter no m&iacute.nimo 6 caracteres!', action: 'keyup',  rule: 'minLength=6' }
             ], theme: theme
         });

               
        var sourceTipo = ["Operador","Supervisor","Gerente"];
        
        $("#tipo").jqxDropDownList({theme: theme,height:25,width:130,source: sourceTipo,selectedIndex: 0, autoDropDownHeight: true, disabled: true});

        var sourceStatus = ["Ativo","Inativo"];
        
        $("#status").jqxDropDownList({theme: theme,height:25,width:130,source: sourceStatus,selectedIndex: 0, autoDropDownHeight: true, disabled: true});
        
        
        $(':input').autotab_magic();
        
        var source =
            {
            localdata: data,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#jqxgrid").jqxGrid(
        {
            width: 730,
            source: dataAdapter,
            theme: theme,
            columnsresize: true,
            autoheight: true,
            altrows: true,
            pageable: true,
            localization: $.getLocalization(),
            columns: [
                { text: "ID", dataField: "id", align: "center", width: 60 },
                { text: "CPF", dataField: "cpf", align: "center", width: 125 },
                { text: "Nome", dataField: "nome", align: "center", width: 330 },
                { text: "Usu&aacute;rio", dataField: "usuario", align: "center", width: 80 },
                { text: "Tipo", dataField: "tipo", align: "center", minwidth: 80 },
                { text: "Status", dataField: "status", align: "center", minwidth: 40 }
            ]
        });
        $("#jqxgrid").on('rowselect', function (event) {
            var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', event.args.rowindex);
            $("#id").val(dataRecord.id);
            $("#cpf").val(dataRecord.cpf);
            $("#nome").val(dataRecord.nome);
            $("#usuario").val(dataRecord.usuario);
            $("#senha").val(dataRecord.senha);
            $("#tipo").val(dataRecord.tipo);
            $("#status").val(dataRecord.status);
        });
        
        $("#novo").click(function(){
            $("#nome, #senha, #usuario, #id").val("");
            $("#salvar").val("Inserir");
            $("#tipo").val("Operador");
            $("status").val("Ativo");
            $('#jqxgrid').jqxGrid({ selectionmode: "none"});
            $('#jqxgrid').jqxGrid({ enablehover: false});
            $("#tipo").jqxDropDownList({disabled: false});
            $("#nome, #usuario, #senha").jqxInput({disabled: false});
            $("#cpf").jqxMaskedInput({disabled: false, value: ""});
            $("#salvar, #cancelar").attr("type","button");
            $("#novo, #alterar").attr("type","hidden");
            $("#cpf").jqxMaskedInput("focus");
            $("#cpf").select();
        });
        
        $("#alterar").click(function(){
            if($("#id").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o usu&aacute;rio que deseja alterar");
                $("#messageBoxOK").jqxWindow("open");
            }else{
                $("#jqxgrid").jqxGrid({ selectionmode: "none"});
                $("#jqxgrid").jqxGrid({ enablehover: false});
                $("#salvar, #cancelar").attr("type","button");
                $("#novo, #alterar").attr("type","hidden");
                if (($("#tipo").val() == "Gerente" && meuTipo == "Supervisor") || ($("#id").val() == meuID)){
                    $("#tipo").jqxDropDownList({disabled: true});           
                    $("#status").jqxDropDownList({disabled: true});           
                }else{
                    $("#tipo").jqxDropDownList({disabled: false});
                    $("#status").jqxDropDownList({disabled: false});
                }
                $("#nome").jqxInput({disabled: false});
                $("#salvar").val("Alterar");
                $("#tipo").prop('disabled', false);                
            }
        });
        
        $("#cancelar").click(function(){
            $("#salvar, #cancelar").attr("type","hidden");
            $("#alterar, #novo").attr("type","button");
            $('#jqxgrid').jqxGrid({ selectionmode: "singlerow"});
            $('#jqxgrid').jqxGrid({ enablehover: true});
            $("#nome, #senha, #usuario, #id").val("");
            $("#tipo").jqxDropDownList({disabled: true});
            $("#status").jqxDropDownList({disabled: true});
            $("#nome, #usuario, #senha").jqxInput({disabled: true});
            $("#cpf").jqxMaskedInput({disabled: true, value: ""});
            $("#form").jqxValidator('hide');
        });

        $("#salvar").click(function(){
            $("#form").jqxValidator("validate");
        });
        
        $("#form").on('validationSuccess', function (event) {
            if ($("#salvar").val() === "Inserir"){
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("UsuarioInserir");
                $("#form").submit();
            }
            if ($("#salvar").val() === "Alterar"){
                $("#cpf").jqxMaskedInput({disabled: false});
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("UsuarioAlterar");
                $("#form").submit();
            }
        });
        
    };
})(jQuery);
