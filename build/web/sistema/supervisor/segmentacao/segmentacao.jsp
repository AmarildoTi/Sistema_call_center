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
	<link href="/CallCenter/util/estilo.css" rel="stylesheet" type="text/css" />
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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_segmentacao.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.autotab-1.1b.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.maskedinput.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var dataSeg = new Array();
                var i = 0;
                <c:forEach var="segmentacao" items="${listaSegmentacao}">
                    var row = {};
                    row['id']="${segmentacao.id}";
                    row['credorid']="${segmentacao.credor.id}";
                    row['credor']="${segmentacao.credor.razaosocial}";
                    row['operadorid']="${segmentacao.credencial.id}";
                    row['operador']="${segmentacao.credencial.nome}";
                    row['atrasode']="${segmentacao.atrasoDe}";
                    row['atrasoate']="${segmentacao.atrasoAte}";
                    row['valordividade']="${segmentacao.valorDividaDe}";
                    row['valordividaate']="${segmentacao.valorDividaAte}";
                    dataSeg[i]=row;
                    i = i + 1;    
                </c:forEach>
                var dataCredor = new Array();
                var primeiro = {};
                primeiro['id']=0;
                primeiro['credor']="TODOS";
                dataCredor[0]=primeiro;
                var i = 1;
                <c:forEach var="credor" items="${listaCredor}">
                    var row = {};
                    row['id']="${credor.id}";
                    row['credor']="${credor.nomefantasia}";
                    dataCredor[i]=row;
                    i = i + 1;    
                </c:forEach>                    
                var dataUsuario = new Array();
                primeiro['id']=0;
                primeiro['operador']="TODOS";
                dataUsuario[0]=primeiro;
                var i = 1;
                <c:forEach var="credencial" items="${listaCredencial}">
                    var row = {};
                    row['id']="${credencial.id}";
                    row['operador']="${credencial.nome}";
                    if("${credencial.tipo}" === "Operador"){
                        dataUsuario[i]=row;                     
                        i = i + 1;    
                    }                    
                </c:forEach>                    
                $.iniciarSegmentacao("pfc",dataSeg,dataCredor,dataUsuario);
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
                <input type="hidden" name="id2" id="id2" value="" />
                <input type="hidden" name="indexcredor2" id="indexcredor2" value="${indexcredor2}" />
                <input type="hidden" name="indexoperador2" id="indexoperador2" value="${indexoperador2}" />
                <input type="hidden" name="idcredor2" id="idcredor2" value="${idcredor2}" />
                <input type="hidden" name="idoperador2" id="idoperador2" value="${idoperador2}" />
                <input type="hidden" name="operacao" id="operacao" value="" />
                <input type="hidden" name="jsp" id="jsp" value="" />
                <div class="titulosegmentacao">CADASTRO DE SEGMENTA&Ccedil;&Atilde;O DE ATENDIMENTO</div>
                <table class="tabelasegmentacao">
                    <tr>
                        <td colspan="4"><label>Credor:</label></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <div id="credor2"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4"><label>Operador:</label></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <div id="operador2"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><label>Dias em Atraso</label></td>
                        <td colspan="2" align="center"><label>Valor D&iacute;vida</label></td>
                    </tr>
                    <tr>
                        <td><label>De:</label></td>
                        <td><label>At&eacute;:</label></td>
                        <td><label>De:</label></td>
                        <td><label>At&eacute;:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <div id="atrasode"></div>
                        </td>
                        <td>
                            <div id="atrasoate"></div>
                        </td>
                        <td>
                            <div id="valordividade"></div>
                        </td>
                        <td>
                            <div id="valordividaate"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <input type="button" id="novo" name="novo" value="Novo" />
                            <input type="hidden" id="salvar" name="salvar" value="Salvar" />
                            <input type="hidden" id="cancelar" name="cancelar" value="Cancelar" />
                            <input type="button" id="alterar" name="alterar" value="Alterar" />
                            <input type="button" id="excluir" name="excluir" value="Excluir"  />
                        </td>
                    </tr>
                </table>
                <div id="jqxgrid" class="jqxgridsegmentacao"></div>
            </form>
        </div>
    </body>
</html>