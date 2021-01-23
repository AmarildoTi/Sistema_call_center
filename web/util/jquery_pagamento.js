(function($){
    $.iniciarPagamento = function(theme, dataCredor,dataNegociacao,dataParcela){
        $("#texto2").jqxMaskedInput({theme: theme,height:'25',width:130, mask: "###.###.###-##"});

        $("#parcela").jqxNumberInput({theme: theme,width:60,height: '25', spinButtons: true, digits:3, decimalDigits:0, min:1, promptChar:" "});
        $("#valor").jqxNumberInput({theme: theme,width:125,height: '25', readOnly: true, digits:8, decimalDigits:2, promptChar:" ", symbol: "R$", decimalSeparator:",", groupSeparator:""});
        $("#vencimento").jqxDateTimeInput({width: '135', height: '25', theme: theme, readOnly:true, textAlign:"center",showCalendarButton:false, formatString: "dd/MM/yyyy"});

        $("#valorpagamento").jqxNumberInput({theme: theme,width:125,height: '25', digits:8, decimalDigits:2, promptChar:" ", symbol: "R$", decimalSeparator:",", groupSeparator:""});
        $("#datapagamento").jqxDateTimeInput({width: '135', height: '25', theme: theme, textAlign: "center", max: new Date(), formatString: "dd/MM/yyyy"});
        
        $("#cancelar").jqxButton({theme: theme,width:150,height: '25'});
        $("#localizar").jqxButton({theme: theme,width:150,height: '25'});
        $("#confirmarpagamento").jqxButton({theme: theme,width:160,height: '25'});
        
        $("#texto2").jqxMaskedInput("focus");
        $("#texto2").select();
                    
        var arrumaData = function(data){
            var hoursInMillis = 60 * 60 * 1000;
            var dataOld = new Date(data);
            var dataNew = new Date(dataOld.getTime() + (dataOld.getTimezoneOffset()/60) * hoursInMillis);
            return dataNew;
        };
        
        var source =
            {
            localdata: dataCredor,
            datatype: "array"
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
        
        $("#credor").jqxDropDownList({ source: dataAdapter, width: '520', height: '25', theme: theme, displayMember: "credor", valueMember: "id", autoDropDownHeight: true});

        $("#credor").jqxDropDownList('selectIndex',$("#indexcredor").val());
        
         $("#cancelar").click(function(){
            $(location).attr("href","/CallCenter/sistema/publico/login/home.jsp");
        });

        function setParcela(){
            $("#parcela").jqxNumberInput({max:dataParcela[$("#indexcredor").val()].length});
            for (var i = dataParcela[$("#indexcredor").val()].length -1; i >= 0; i--){
                if (dataParcela[$("#indexcredor").val()][i].datapagamento === ""){
                    $("#parcela").val(dataParcela[$("#indexcredor").val()][i].parcela);                    
                }
            }
            if ($("#parcela").val()=== 0){
                $("#parcela").val(1);
            }
        }

        $.getScript('/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.culture.pt-BR.js', function () {
            $("#datapagamento").jqxDateTimeInput({ culture: 'pt-BR' });
        });                

        function mudarParcela(){
            if (dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].datapagamento.trim() === ""){
                $("#confirmarpagamento").jqxButton({disabled: false});
                if(new Date(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].vencimento) >  new Date()){
                    $("#datapagamento").val(new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate());
                }else{
                    $("#datapagamento").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].vencimento.replace(/\-/g, '/'));
                }
                $("#valorpagamento").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].valor);
            }else{
                $("#confirmarpagamento").jqxButton({disabled: true});
                $("#datapagamento").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].vencimento.replace(/\-/g, '/'));
                $("#valorpagamento").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].valorpagamento);
            }
            $("#descricao").html("Negocia&ccedil;&atilde;o em "+dataNegociacao[$("#indexcredor").val()].parcelamento+" parcela(s), valor total de "+dataNegociacao[$("#indexcredor").val()].valornegociado);
            $("#valor").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].valor);
            $("#vencimento").val(dataParcela[$("#indexcredor").val()][$("#parcela").val()-1].vencimento.replace(/\-/g, '/'));
            $("#negociacao").val(dataNegociacao[$("#indexcredor").val()].negociacao);
            $("#iddivida").val(dataNegociacao[$("#indexcredor").val()].divida);
        }

        if (dataCredor.length === 0) {
            $("#detalhes").attr("class","tabelapagamentoi");
        }else{
            $("#detalhes").attr("class","tabelapagamento");
            setParcela();
            mudarParcela();
        }

        $('#credor').on('change', function (event){
            var args = event.args;
            if (args) {
                var index = args.index;
                $("#indexcredor").val(index);
                setParcela();
                mudarParcela();
            }
        });
        
        $('#parcela').on('valuechanged', function (event){
            mudarParcela();
        });
        
        $("#localizar").click(function(){
            if($("#texto2").val().toString() === "" || $("#texto2").val().toString() === "___.___.___-__"){
                $("#messageBoxOK #textoMessageBoxOK").html("Digite o que deseja localizar");
                $("#messageBoxOK").jqxWindow("open");
                $("#texto2").focus();
            }
            else{
                $("#opcao").val(4);
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("DividaListar");
                $("#form").submit();
            }
        });
        
        $("#confirmarpagamento").click(function(){
            $("#opcao").val(4);
            $("#form").attr("action","//localhost:8084/CallCenter/mvc");
            $("#operacao").val("ParcelaPagar");
            $("#form").submit();
        });
    };
})(jQuery);