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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_usuario.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.autotab-1.1b.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="usuario" items="${listaUsuario}">
                    var row = {};
                    row['id']="${usuario.id}";
                    row['cpf']="${usuario.CPF}";
                    row['nome']="${usuario.nome}";
                    row['usuario']="${usuario.usuario}";
                    row['tipo']="${usuario.tipo}";
                    row['status']="${usuario.status}";
                    row['senha']="******";
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>
                $.iniciarUsuario("pfc",data,${login.id},"${login.tipo}");
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
                <input type="hidden" name="operacao" id="operacao" value="" />
                <input type="hidden" name="jsp" id="jsp" value="" />
                <div class="titulousuario">CADASTRO DE USUÁRIOS</div>
                <table class="tabelausuario">
                    <tr>
                        <td><label>CPF:</label></td>
                        <td colspan="3"><label>Nome:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="cpf" id="cpf" onkeypress="return somenteNumeros(event)" style="width: 140px;" />
                        </td>
                        <td colspan="3">
                            <input type="text" name="nome" id="nome" maxlength="80" style="width: 310px;" />
                        </td>
                    </tr>
                    <tr>
                        <td><label>Tipo:</label></td>
                        <td><label>Usu&aacute;rio:</label></td>
                        <td><label>Senha:</label></td>
                        <td><label>Status:</label></td>
                    </tr>
                    <tr>
                        <td  style="width: 143px;">
                            <div id="tipo"></div>
                        </td>
                        <td>
                            <input type="text" maxlength="10" name="usuario" id="usuario" onkeypress="return semCaracteresEspeciais(event)" style="width: 140px;"/>
                            
                        </td>
                        <td>
                            <input type="password" maxlength="128" name="senha" id="senha"/>
                        </td>
                        <td  style="width: 143px;">
                            <div id="status"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <input type="button" id="novo" name="novo" value="Novo" />
                            <input type="hidden" id="salvar" name="salvar" value="Salvar" />
                            <input type="hidden" id="cancelar" name="cancelar" value="Cancelar" />
                            <input type="button" id="alterar" name="alterar" value="Alterar" />               
                        </td>
                    </tr>
                </table>
                <div id="jqxgrid" class="jqxgridusuario"></div>
            </form>
        </div>
    </body>
</html>