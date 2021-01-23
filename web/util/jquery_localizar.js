(function($){
    $.iniciarLocalizar = function(theme,data,meuTipo){
        $("#texto").jqxInput({theme: theme, width: 665, height: 25});
        if ($("#opcao").val() === "1" || $("#opcao").val() === "0"){
            $("#porcpf").jqxRadioButton({width: 100, height: 25, checked: true, theme: theme});
        }else{
            $("#porcpf").jqxRadioButton({width: 100, height: 25, theme: theme});
        }
        if ($("#opcao").val() === "2"){
            $("#pornome").jqxRadioButton({width: 100, height: 25, checked: true, theme: theme});
        }else{
            $("#pornome").jqxRadioButton({width: 100, height: 25, theme: theme});
        }
        if ($("#opcao").val() === "3"){
            $("#porcontrato").jqxRadioButton({width: 100, height: 25, checked: true, theme: theme});
        }else{
            $("#porcontrato").jqxRadioButton({width: 100, height: 25, theme: theme});
        }
        if ($("#opcao").val() === "4"){
            $("#opcao").val(0);
            $("#porcpf").jqxRadioButton({width: 100, height: 25, checked: true, theme: theme});
        }

        $("#cancelar").jqxButton({theme: theme,width:220,height: '25'});
        $("#localizar").jqxButton({theme: theme,width:220,height: '25'});
        $("#telaatendimento").jqxButton({theme: theme,width:220,height: '25'});            
        
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
            pagesize: 5,
            pagesizeoptions: ['5'],
            columns: [
                { text: "D&iacute;vida", dataField: "divida", align: "center", width: 80, hidden:true  },
                { text: "Status", dataField: "status", align: "center", width: 80, hidden:true  },
                { text: "Credor", dataField: "credor", align: "center", width: 120 },
                { text: "CPF", dataField: "cpf", align: "center", width: 100 },
                { text: "Devedor", dataField: "devedor", align: "center", width: 180 },
                { text: "Contrato", dataField: "contrato", align: "center", width: 100 },
                { text: "Valor", dataField: "valor", align: "center", width: 90},
                { text: "Data", dataField: "data", align: "center", width: 90 }
            ]
        });
        
        $("#jqxgrid").on('rowselect', function (event) {
            var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', event.args.rowindex);
            $("#credor").html(dataRecord.credor);
            $("#devedor").html(dataRecord.devedor);
            $("#cpf").html(dataRecord.cpf);
            $("#contrato").html(dataRecord.contrato);
            $("#valor").html(dataRecord.valor);
            $("#data").html(dataRecord.data);
            $("#status").html(dataRecord.status);
            $("#dataatendimento").html(dataRecord.dataatendimento);
            $("#ultimooperador").html(dataRecord.operador);
            $("#parcelamento").html(dataRecord.parcelamento);
            $("#statusnegociacao").html(dataRecord.statusnegociacao);
            $("#valornegociado").html(dataRecord.valornegociado);
            $("#divida").val(dataRecord.divida);
            $("#id").val(dataRecord.iddivida);
            if (dataRecord.status !== ""){
                $("#detalhesatendimento").attr("class","tabelalocalizarsub");
            }else{
                $("#detalhesatendimento").attr("class","tabelalocalizarsubi");
            }
            if (dataRecord.parcelamento !== ""){
                $("#detalhesnegociacao").attr("class","tabelalocalizarsub");
                if (dataRecord.statusnegociacao === "Ativa" && dataRecord.primeirapaga === "SIM"){
                    $("#telaatendimento").attr("type","hidden");
                }else{
                    $("#telaatendimento").attr("type","button");                    
                }
            }else{
                $("#detalhesnegociacao").attr("class","tabelalocalizarsubi");
                if (meuTipo === "Operador"){
                    $("#telaatendimento").attr("type","button");                    
                }else{
                    $("#telaatendimento").attr("type","hidden");                    
                }
            }                
            $("#detalhes").attr("class","tabelalocalizar");
        });

        $("#cancelar").click(function(){
            $(location).attr("href","/CallCenter/sistema/publico/login/home.jsp");
        });
        
        $("#localizar").click(function(){
            if($("#texto").val().toString() === ""){
                $("#messageBoxOK #textoMessageBoxOK").html("Digite o que deseja localizar");
                $("#messageBoxOK").jqxWindow("open");
                $("#texto").jqxInput("focus");
                $("#texto").select();
            }
            else{
                if ($("#porcpf").val()===true){
                    $("#opcao").val(1);
                }
                if ($("#pornome").val()===true){
                    $("#opcao").val(2);
                }
                if ($("#porcontrato").val()===true){
                    $("#opcao").val(3);
                }
                $("#form").attr("action","//localhost:8084/CallCenter/mvc");
                $("#operacao").val("DividaListar");
                $("#form").submit();
            }
        });
        
        $("#telaatendimento").click(function(){
            $("#form").attr("action","//localhost:8084/CallCenter/mvc");
            $("#operacao").val("AtendimentoLocalizar");
            $("#jsp").val("operador/atendimento/atendimento.jsp");
            $("#form").submit();
        });
    };
})(jQuery);