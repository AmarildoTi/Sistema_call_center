<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <% 
            response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Sistema CALLCENTER</title>
	<link href="/CallCenter/util/estilo.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.base.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.pfc.css" type="text/css" />
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/scripts/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpasswordinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_index.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $.iniciar("pfc");
                $.iniciarIndex("pfc");
                if(${mensagem != null && mensagem != ""}){
                    $("#messageBoxOK").jqxWindow("open");                  
                };
            });
         </script>
    </head>
        <body onload="document.form.usuario.focus();">
        <c:import url="/util/messageBox.jsp" />
            <div id="elemento1">
                <img src="/CallCenter/imagens/mundo.jpg" class="image1" alt=""/>
            </div>
            <div id="elemento2">
                <p>CALL CENTER - Acesso</p>
            </div>
            <div id="elemento3">
                <form id="form" name="form" action="" method="post" class="formlogin">
                    <table>
                        <tr>
                            <td>
                                Usu&aacute;rio:
                            </td>
                            <td>
                                <input type="text" id="usuario" name="usuario" maxlength="10" class="tamtxt"  onkeypress="return semCaracteresEspeciais(event)" value="${usuario}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Senha:
                            </td>
                            <td>
                                <input type="password" id="senha" name="senha" maxlength="20" class="tamtxt" onkeypress="return semCaracteresEspeciais(event)" value=""/>
                            </td>
                        </tr>
                            <!--
                            <tr>
                            <td colspan="2">
                                <img alt="" src="/CallCenter/Kaptcha.jpg"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Texto imagem:
                            </td>
                            <td>
                                <input type="text" id="kaptcha" name="kaptcha" class="tamtxt"  maxlength="5" onkeypress="return semCaracteresEspeciais(event)" />
                            </td>
                        </tr>-->
                        <tr>
                            <td colspan="2">
                                <input type="button" id="login" name="login" value="Entrar" class="tamtxt" />
                            </td>
                        </tr>
                    </table>
                    <input name="operacao" value="UsuarioEfetuarLogin" type="hidden" />
                </form>
            </div>
            <div id="elemento4">
                <p>:: GRUPO 03 ::</p>
            </div>
        </body>
</html>
