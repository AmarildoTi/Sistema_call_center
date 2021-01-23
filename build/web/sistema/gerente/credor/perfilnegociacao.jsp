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
	<link href="/CallCenter/util/estilo.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.base.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.pfc.css" type="text/css" />
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/scripts/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.sort.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.pager.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.selection.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.edit.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.columnsresize.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxnumberinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_perfilnegociacao.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.autotab-1.1b.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.maskedinput.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="perfil" items="${credor.perfilNegociacao}">
                    var row = {};
                    row['id']="${perfil.id}";
                    row['atrasoDe']="${perfil.atrasoDe}";
                    row['atrasoAte']="${perfil.atrasoAte}";
                    row['maximoParcelas']="${perfil.maximoParcelas}";
                    row['juros']="${perfil.juros}";
                    row['multa']="${perfil.multa}";
                    row['jurosPorParcela']="${perfil.jurosPorParcela}";
                    row['descontoValorPrincipal']="${perfil.descontoValorPrincipal}";
                    row['descontoEncargos']="${perfil.descontoEncargos}";
                    row['valorMinimoEntrada']="${perfil.valorMinimoEntrada}";
                    row['valorMinimoParcela']="${perfil.valorMinimoParcela}";
                    row['tipo']="${perfil.tipo}";
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>
                var data2 = new Array();
                var i = 0;
                <c:forEach var="credor" items="${listaCredor}">
                    var row = {};
                    row['id']="${credor.id}";
                    row['credor']="${credor.razaosocial}";
                    data2[i]=row;
                    i = i + 1;    
                </c:forEach>                    
                $.iniciarPerfil("pfc",data,data2);
                if(${mensagem2 != null && mensagem2 != ""}){
                    $("#messageBoxOK2").jqxWindow("open");
                };
                if(${mensagem != null && mensagem != ""}){
                    $("#messageBoxOK").jqxWindow("open");
                };
            });
        </script>
    </head>
    <body>
        <c:import url="/util/menu.jsp" />
        <c:import url="/util/messageBox.jsp" />
        <div id="elemento6">
            <form id="form" name="form" method="post" action="">
                <input type="hidden" name="id" id="id" value="" />
                <input type="hidden" name="indexcredor" id="indexcredor" value="${indexcredor}" />
                <input type="hidden" name="idcredor" id="idcredor" value="${idcredor}" />
                <input type="hidden" name="operacao" id="operacao" value="" />
                <input type="hidden" name="jsp" id="jsp" value="" />
                <div class="tituloperfil">CADASTRO DE PERFIS DE NEGOCIA&Ccedil;&Atilde;O</div>
                <table class="tabelaperfil">
                    <tr>
                        <td colspan="6"><label>Credor:</label></td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <div id="credor"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><label>Dias em Atraso</label></td>
                        <td align="center"><label>M&aacute;ximo de</label></td>
                        <td colspan="3" align="center"><label>Encargos</label></td>
                    </tr>
                    <tr>
                        <td><label>De:</label></td>
                        <td><label>At&eacute;:</label></td>
                        <td><label>Parcelas:</label></td>
                        <td><label>Juros:</label></td>
                        <td><label>Multa:</label></td>
                        <td><label>Por Parcela:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <div id="atrasode"></div>
                        </td>
                        <td>
                            <div id="atrasoate"></div>
                        </td>
                        <td>
                            <div id="maximoparcelas"></div>
                        </td>
                        <td>
                            <div id="juros"></div>
                        </td>
                        <td>
                            <div id="multa"></div>
                        </td>
                        <td>
                            <div id="jurosporparcela"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><label>Desconto no Valor</label></td>
                        <td colspan="2" align="center"><label>Valor M&iacute;nimo</label></td>
                        <td colspan="2" rowspan="2" align="center"><label>Tipo:</label></td>
                    </tr>
                    <tr>
                        <td><label>Principal:</label></td>
                        <td><label>Encargos:</label></td>
                        <td><label>Entrada:</label></td>
                        <td><label>Parcela:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <div id="descontovalorprincipal"></div>
                        </td>
                        <td>
                            <div id="descontoencargos"></div>
                        </td>
                        <td >
                            <div id="valorminimoentrada"></div>
                        </td>
                        <td>
                            <div id="valorminimoparcela"></div>
                        </td>
                        <td colspan="2">
                            <div id="tipo"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center">
                            <input type="button" id="novo" name="novo" value="Novo" />
                            <input type="hidden" id="salvar" name="salvar" value="Salvar" />
                            <input type="hidden" id="cancelar" name="cancelar" value="Cancelar" />
                            <input type="button" id="alterar" name="alterar" value="Alterar" />
                            <input type="button" id="excluir" name="excluir" value="Excluir"  />
                        </td>
                    </tr>
                </table>
                <div id="jqxgrid" class="jqxgridperfil"></div>
            </form>
        </div>
        <div id="messageBoxOK2">
            <div></div>
            <div>
                <center>
                    <div id="textoMessageBoxOK2">
                        ${mensagem2}
                    </div>
                </center>
                <div>
                    <div>&nbsp;</div>
                    <div style="float: right;">
                        <input type="button" id="ok2" name="ok2" value="OK" />
                    </div>
                </div>
            </div>
        </div>                
    </body>
</html>