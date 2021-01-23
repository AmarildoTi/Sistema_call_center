(function($){
    $.iniciarAlterarSenha = function(theme, data, tipo){
        
        $("#senhaatual").jqxInput({ theme: theme, height: 25, width: 130});
        $("#senhanova").jqxInput({ theme: theme, height: 25, width: 130});
        $("#senha").jqxInput({ theme: theme, height: 25, width: 130});

        $("#form").jqxValidator({
             rules: [
                 { input: '#senhaatual', message: 'Entre com a sua senha atual!', action: 'keyup, blur', rule: 'required' },
                 { input: '#senhaatual', message: 'Sua senha atual tem no m&iacute;nimo 6 caracteres!', action: 'keyup', rule: 'minLength=6' },
                 { input: '#senhanova', message: 'Entre com a sua nova senha!', action: 'keyup, blur', rule: 'required' },
                 { input: '#senhanova', message: 'Sua nova senha deve ter no m&iacute;nimo 6 caracteres!', action: 'keyup', rule: 'minLength=6' },
                 { input: '#senha', message: 'Confirme sua nova senha!', action: 'keyup, blur', rule: 'required' },
                 { input: '#senha', message: 'Confirma&ccedil;&atilde;o diferente da nova senha!', action: 'keyup', rule: function (input, commit) {
                         if (input.val() === $("#senhanova").val()) {
                             return true;
                         }
                         return false;
                     }
                 }
             ], theme: theme
         });
        
        var source = {
            localdata: data,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#usuario").jqxDropDownList({ source: dataAdapter, selectedIndex: 0, width: '180', height: '25', displayMember: "usuario", valueMember: "usuario", theme: theme, autoDropDownHeight: true});
        
        $("#alterar").jqxButton({theme: theme,width:150, height: 25});
        $("#cancelar").jqxButton({theme: theme,width:150, height: 25});

        $("#cancelar").click(function(){
            $(location).attr("href","/CallCenter/sistema/publico/login/home.jsp");
        });
        
        $("#alterar").click(function(){
            $("#form").jqxValidator("validate");
        });
        
        $("#form").on("validationSuccess", function(event){
            $("#form").attr("action","//localhost:8084/CallCenter/mvc");
            $("#form").submit();
        });
    };
})(jQuery);