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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxprogressbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_importardivida.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.autotab-1.1b.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.maskedinput.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="credor" items="${listaCredor}">
                    var row = {};
                    row['id']="${credor.id}";
                    row['credor']="${credor.razaosocial}";
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>                    
                $.iniciarImportarDivida("pfc",data);
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
                <input type="hidden" name="id3" id="id3" value="${id3}" />
                <input type="hidden" name="indexcredor3" id="indexcredor3" value="${indexcredor3}" />
                <input type="hidden" name="operacao" id="operacao" value="" />
                <input type="hidden" name="jsp" id="jsp" value="" />
                <div class="tituloperfil">IMPORTAR ARQUIVO TEXTO COM AS D&Iacute;VIDAS</div>
                <table class="tabelaperfil">
                    <tr>
                        <td><label>Credor:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <div id="credor3"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Arquivo Texto com as D&iacute;vidas:</label></td>
                    </tr>
                    <tr>
                        <td><input type="file" id="arquivo" name="arquivo" value="Localizar Arquivo" /></td>
                    </tr>
                    <tr>
                        <td><input type="button" id="importar" name="importar" value="Importar Arquivo" onclick="alert(this.previousSibling.value);"/></td>
                    </tr>
                </table>
                <div id="display"></div>
            </form>
        </div>
    </body>
</html>
