(function($){
    $.iniciarImportarDivida = function(theme,data){

        var source =
            {
            localdata: data,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
        
        $("#credor3").jqxDropDownList({ source: dataAdapter, selectedIndex: 0, width: '660', height: '25', theme: theme, displayMember: "credor", valueMember: "id", autoDropDownHeight: true, placeHolder: "Escolha um Credor" });
        if ($("#id3").val() === "0" || $("#id3").val() === "" ){
            $("#credor3").jqxDropDownList('selectIndex',-1);            
        }else{
            $("#credor3").jqxDropDownList('selectIndex',$("#indexcredor3").val());            
        }
        
        $('#credor3').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                var item = args.item;
                $("#id3").val(item.value);
                $("#indexcredor3").val(index);
            }
        });

        $("#arquivo").jqxButton({theme: theme,width:660});
        $("#importar").jqxButton({theme: theme,width:200, disabled: false});
        
        
        var verificarCampos = function(){
            if ($("#id3").val() === "0" || $("#id3").val() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o Credor!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            if ($("#arquivo").val().toString().trim() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Favor selecione o Arquivo!!!");
                $("#messageBoxOK").jqxWindow("open");
                return false;
            }
            return true;
        };
        
        $("#importar").click(function(){
            if(verificarCampos()){
                $("#messageBox #textoMessageBox").html("Importando o Arquivo...");
                $("#messageBox").jqxWindow("open");
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("DividaImportar");
                $("#form").submit();
            }
        });
    };
})(jQuery);
