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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_alterarsenha.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                var tipo = "${login.tipo}";
                if(tipo === "Operador"){
                    var row = {};
                    row['usuario']="${login.usuario}";
                    data[i]=row;
                }else{
                <c:forEach var="credencial" items="${listaCredencial}">
                    var row = {};
                    row['usuario']="${credencial.usuario}";
                    if(tipo === "Supervisor"){
                        if("${credencial.tipo}" === "Operador" || "${credencial.usuario}" === "${login.usuario}"){
                            data[i]=row;
                            i = i + 1;
                        }
                    }
                    if(tipo === "Gerente"){
                        if("${credencial.tipo}" !== "Gerente" || "${credencial.usuario}" === "${login.usuario}"){
                            data[i]=row;
                            i = i + 1;
                        }
                    }
                </c:forEach>
                }
                $.iniciarAlterarSenha("pfc", data, tipo);
                if(${mensagem != "" && mensagem != null}){
                    $("#messageBoxOK").jqxWindow("open");
                };
                $("#jqxMenu").jqxMenu({ width: '148', mode: 'vertical', theme: "pfc"});
                $("#jqxMenu").css('visibility', 'visible');
            });
        </script>
    </head>
    <body onload="document.form.senhaatual.focus();">
        <c:import url="/util/menu.jsp" />
        <c:import url="/util/messageBox.jsp" />
	<div id="elemento6">
            <form id="form" name="form" method="post" action="" >
                <input type="hidden" id="operacao" name="operacao" value="UsuarioAlterarSenha" />
                <input type="hidden" id="jsp" name="jsp" value="" />
                <div class="titulosenha">ALTERAR SENHA</div>
                <table class="tabelasenha" >
                    <tr>
                        <td>
                            Usu&aacute;rio:</td>
                        <td>
                            <div id="usuario"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>Senha Atual:</td>
                        <td><input type="password" maxlength="20" id="senhaatual" name="senhaatual" /></td>
                    </tr>
                    <tr>
                        <td>Nova Senha:</td>
                        <td><input type="password" maxlength="20" id="senhanova" name="senhanova" /></td>
                    </tr>
                    <tr>
                        <td>Confirmar Nova Senha:</td>
                        <td><input type="password" maxlength="20" id="senha" name="senha" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" id="alterar" name="alterar" value=" Alterar Senha " />
                            <input type="reset" id="cancelar" name="cancelar" value=" Cancelar " />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>