(function($){
    $.iniciarIndex = function(theme){
        $("#usuario").jqxInput({ theme: theme, height: 25, width: 130});
        $("#senha").jqxPasswordInput({ theme: theme, height: 25, width: 130});
        $("#form").jqxValidator({
             rules: [
                 { input: '#usuario', message: 'Entre com o seu usu&aacute;rio!', action: 'keyup, blur', rule: 'required' },
                 { input: '#usuario', message: 'Usu&aacute;rio deve ter de 6 a 10 caracteres!', action: 'keyup', rule: 'length=6,10' },
                 { input: '#senha', message: 'Entre com a sua senha!', action: 'keyup, blur', rule: 'required' },
                 { input: '#senha', message: 'Sua senha deve ter no m&iacute;nimo 6 caracteres!', action: 'keyup', rule: 'minLength=6' }
             ], theme: theme
         });
        
        $('#login').jqxButton({ theme: theme,height: 25, width: 100});
        $("#login").on('click', function () {
            $("#form").jqxValidator("validate");
        });
        $("#form").on('validationSuccess', function (event) {
            $("#form").attr("action","//localhost:8084/CallCenter/mvc");
            $("#form").submit();
        });

    };
})(jQuery);