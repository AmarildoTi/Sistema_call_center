<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <% 
            response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
        %>
        <title>Sistema CALLCENTER</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <link rel="stylesheet" href="/CallCenter/util/estilo.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.base.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.pfc.css" type="text/css" />
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/scripts/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.selection.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.columnsresize.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.pager.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.sort.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmaskedinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxradiobutton.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxnumberinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdatetimeinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcalendar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_pagamento.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var data2 = new Array();
                var data3 = new Array();
                var i = 0;
                <c:forEach var="divida" items="${listaDivida2}">
                    var row = {};
                    var j = 0;
                    row['id']="${divida.credor.id}";
                    row['credor']="${divida.credor.razaosocial}";
                    <c:forEach var="negociacao" items="${divida.negociacao}">
                        <c:if test="${negociacao.id != 0}" >
                            var row2 = {};
                            row2['negociacao']="${negociacao.id}";
                            row2['divida']="${divida.id}";
                            row2['parcelamento']="${negociacao.parcelamento}";
                            row2['valornegociado']="${negociacao.valorNegociadoString}";
                            data2[i]=row2;
                            data3[i] = new Array();
                            <c:forEach var="parcela" items="${negociacao.parcela}">
                                var row3 = {};
                                row3['parcela']="${parcela.numero}";
                                row3['valor']="${parcela.valor}";
                                row3['vencimento']="${parcela.dataVencimento}";
                                row3['datapagamento']="${parcela.dataPagamento}";
                                row3['valorpagamento']="${parcela.valorPagamento}";
                                data3[i][j]=row3;
                                j = j + 1;
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                    data[i]=row;
                    i = i + 1;
                </c:forEach>
                $.iniciarPagamento("pfc",data,data2,data3);
                if(${mensagem != "" && mensagem != null}){
                    $("#messageBoxOK").jqxWindow("open");
                };
            });
        </script>
    </head>
    <body>
        <c:import url="/util/menu.jsp" />
        <c:import url="/util/messageBox.jsp" />
	<div id="elemento6">
            <form id="form" name="form" method="post" action="" >
                <input type="hidden" id="operacao" name="operacao" value="" />
                <input type="hidden" id="negociacao" name="negociacao" value="" />
                <input type="hidden" id="indexcredor" name="indexcredor" value="${indexcredor}"/>
                <input type="hidden" id="opcao" name="opcao" value="" />
                <input type="hidden" id="jsp" name="jsp" value="supervisor/pagamento/pagamento.jsp" />
                <input type="hidden" id="iddivida" name="iddivida" value="" />
                <input type="hidden" id="indexnegociacao" name ="indexnegociacao" value=""/>
                <div class="titulopagamento">CONFIRMAR PAGAMENTO</div>
                <table class="tabelapagamento">
                    <tr>
                        <td>CPF:</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="texto2" name="texto2" value="${texto2}"></input>
                            <input type="button" id="localizar" name="localizar" value="Localizar"></input>
                            <input type="button" id="cancelar" name="cancelar" value="Cancelar"></input>
                        </td>
                    </tr>
                </table>
                <table id="detalhes" class="tabelapagamentoi">
                    <tr>
                        <td colspan="3" class="fontebold">Devedor: ${listaDivida2[0].devedor.nome}</td>
                    </tr>
                    <tr>
                        <td colspan="3">Credor:</td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="credor"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="fontebold" align="center"><div id="descricao"></div></td>
                    </tr>
                    <tr>
                        <td>Parcela:</td>
                        <td>Vencimento:</td>
                        <td>Valor:</td>
                    </tr>
                    <tr>
                        <td>
                            <div id="parcela"></div>
                        </td>
                        <td>
                            <div id="vencimento"></div>
                        </td>
                        <td>
                            <div id="valor"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>Data Pagamento</td>
                        <td colspan="2">Valor Pagamento</td>
                    </tr>
                    <tr>
                        <td>
                            <div id="datapagamento"></div>
                        </td>
                        <td>
                            <div id="valorpagamento"></div>
                        </td>
                        <td>
                            <input type="button" id="confirmarpagamento" name="confirmarpagamento" value="Confirmar Pagamento"></input>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
