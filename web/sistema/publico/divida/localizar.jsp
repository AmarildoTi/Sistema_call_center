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
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_localizar.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="divida" items="${listaDivida}">
                    var row = {};
                    row['statusnegociacao']="";
                    row['parcelamento']="";
                    row['valornegociado']="";
                    row['primeirapaga']="NAO";
                    <c:if test="${divida.negociacao[0].id != 0}" >
                        row['statusnegociacao']="${divida.negociacao[0].status}";
                        row['parcelamento']="${divida.negociacao[0].parcelamento}";
                        row['valornegociado']="${divida.negociacao[0].valorNegociadoString}";
                        <c:forEach var="parcela" items="${divida.negociacao[0].parcela}">
                            <c:if test="${parcela.numero == 1}">
                                <c:if test="${parcela.dataPagamento != null}" >
                                    row['primeirapaga']="SIM";
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    row['divida']="${divida}";
                    row['iddivida']="${divida.id}";
                    row['credor']="${divida.credor.nomefantasia}";
                    row['cpf']="${divida.devedor.cpf}";
                    row['devedor']="${divida.devedor.nome}";
                    row['contrato']="${divida.contrato}";
                    row['valor']="${divida.valorString}";
                    row['data']="${divida.dataString}";
                    row['status']="";
                    row['dataatendimento']="";
                    row['operador']="";
                    <c:if test="${divida.status.id != 0}" >
                        row['status']="${divida.status.descricao}";
                        row['dataatendimento']="${divida.dataUltimoAtendimentoString}";
                        row['operador']="${divida.ultimoOperador.nome}";
                    </c:if>
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>
                $.iniciarLocalizar("pfc",data,"${login.tipo}");
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
                <input type="hidden" id="operacao" name="operacao" value="UsuarioAlterarSenha" />
                <input type="hidden" id="jsp" name="jsp" value="publico/divida/localizar.jsp" />
                <input type="hidden" id="opcao" name="opcao" value="${opcao}" />
                <input type="hidden" id="id" name="id" value="" />
                <input type="hidden" id="divida" name="divida" value="" />
                <input type="hidden" id="indexnegociacao" name ="indexnegociacao" value=""/>
                <div class="titulolocalizar">LOCALIZAR D&Iacute;VIDA POR:</div>
                <table class="tabelalocalizar">
                    <tr>
                        <td>
                            <div id="porcpf">CPF</div>
                        </td>
                        <td>
                            <div id="pornome">Nome</div>
                        </td>
                        <td>
                            <div id="porcontrato">Contrato</div>                            
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="text" id="texto" name="texto" class="jqxmaiuscula" value="${texto}"></input>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <input type="button" id="localizar" name="localizar" value="Localizar"></input>
                            <input type="button" id="cancelar" name="cancelar" value="Cancelar"></input>
                        </td>
                    </tr>
                </table>
                <div id="jqxgrid" class="jqxgriddivida"></div>
                <div class="titulolocalizar">DETALHES DA D&Iacute;VIDA SELECIONADA:</div>
                <table id="detalhes" class="tabelalocalizari">
                    <tr class="fontemenor">
                        <td width="100"><b>Credor:</b></td>
                        <td colspan="3"><div id="credor"/></td>
                    </tr>
                    <tr class="fontemenor">
                        <td><b>Devedor:</b></td>
                        <td colspan="3"><div id="devedor"/></td>
                    </tr>
                    <tr class="fontemenor">
                        <td><b>CPF:</b></td>
                        <td><div id="cpf"/></td>
                        <td width="100"><b>Contrato:</b></td>
                        <td><div id="contrato"/></td>
                    </tr>
                    <tr class="fontemenor">
                        <td><b>Valor:</b></td>
                        <td><div id="valor"/></td>
                        <td><b>Data:</b></td>
                        <td width="100"><div id="data"/></td>
                        
                    </tr>
                    <tr class="fontemenor">
                        <td colspan="4" align="center">
                            <table id="detalhesatendimento" class="tabelalocalizarsubi"> 
                                <tr>
                                    <td colspan="4" align="center"><b>&Uacute;LTIMO ATENDIMENTO:</b></td>
                                </tr>
                                <tr>
                                    <td width="100"><b>Status:</b></td>
                                    <td><div id="status"></div></td>
                                    <td width="100"><b>Data:</b></td>
                                    <td width="100"><div id="dataatendimento"></div></td>
                                </tr>
                                <tr>
                                    <td width="100"><b>Operador:</b></td>
                                    <td colspan="3"><div id="ultimooperador"></div></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr class="fontemenor">
                        <td colspan="4" align="center">
                            <table id="detalhesnegociacao" class="tabelalocalizarsubi">
                                <tr>
                                    <td colspan="6" align="center"><b>NEGOCIA&Ccedil;&Atilde;O:</b></td>
                                </tr>
                                <tr>
                                    <td width="100"><b>Status:</b></td>
                                    <td><div id="statusnegociacao"></div></td>
                                    <td width="100"><b>Parcelamento:</b></td>
                                    <td><div id="parcelamento"></div></td>
                                    <td width="100"><b>Valor Total:</b></td>
                                    <td width="150"><div id="valornegociado"></div></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <input type="button" id="telaatendimento" name="telaatendimento" value="Iniciar Atendimento"></input>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
