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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdatetimeinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcalendar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxradiobutton.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_relatorio.js"></script>
        <script type="text/javascript">
            $(function(){
                var dataCredor = new Array();
                var i = 0;
                <c:forEach var="credor" items="${listaCredor}">
                    var row = {};
                    row['id']="${credor.id}";
                    row['credor']="${credor.razaosocial}";
                    dataCredor[i]=row;
                    i = i + 1;    
                </c:forEach>                    
                $.iniciar("pfc");
                $.iniciarRelatorio("pfc",dataCredor);
            });
         </script>
    </head>
    <body>
        <c:import url="/util/menu.jsp" />
        <c:import url="/util/messageBox.jsp" />
	<div id="elemento6">
            <form id="form" name="form" method="post" action="">
                <input type="hidden" id="operacao" name="operacao" value="UsuarioAlterarSenha" />
                <input type="hidden" id="jsp" name="jsp" value="publico/divida/localizar.jsp" />
                <input type="hidden" id="relatorio" name="relatorio" value="" />
                <input type="hidden" id="idcredor" name="idcredor" value="" />
                <input type="hidden" id="parametro" name="parametro" value="Gerente" />
                <div class="titulorelatorio">RELAT&Oacute;RIOS</div>
                <table class="tabelalocalizarsub"">
                    <tr>
                        <td>
                            Relat&oacute;rio:
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="tiporelatorio"></div>
                        </td>
                    </tr>
                </table>
                <table id="tabelacredor" class="tabelalocalizarsub">
                    <tr>
                        <td>
                            Credor:
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="credor"></div>
                        </td>
                    </tr>
                </table>
                <table id="tabelaperiodo"  class="tabelalocalizarsub">
                    <tr>
                        <td>
                            <fieldset class="box">
                                <legend>Per&iacute;odo:</legend>
                                <table>
                                    <tr>
                                        <td>
                                            <div id="dia">&nbsp;Dia</div>
                                            <div id="mes">&nbsp;M&ecirc;s</div>
                                            <div id="ano">&nbsp;Ano</div>
                                            <div id="intervalo">&nbsp;Intervalo</div>
                                        </td>
                                        <td>
                                            Data Inicial:
                                        </td>
                                        <td>
                                            <div id="periodoinicial"></div>
                                        </td>
                                        <td>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Data Final:
                                        </td>
                                        <td>
                                            <div id="periodofinal"></div>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
                <table class="tabelalocalizarsub">
                    <tr>
                        <td align="center">
                            <input type="button" id="gerarrelatorio" name ="gerarrelatorio" value="Gerar Relat&oacute;rio" ></input>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>