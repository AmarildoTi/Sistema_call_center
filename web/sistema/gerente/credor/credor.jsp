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
        <script type="text/javascript" src="/CallCenter/util/jquery_credor.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.autotab-1.1b.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="credor" items="${listaCredor}">
                    var row = {};
                    row['id']="${credor.id}";
                    row['cnpj']="${credor.CNPJ}";
                    row['nomefantasia']="${credor.nomefantasia}";
                    row['razaosocial']="${credor.razaosocial}";
                    row['endereco']="${credor.endereco}";
                    row['complemento']="${credor.complemento}";
                    row['bairro']="${credor.bairro}";
                    row['cidade']="${credor.cidade}";
                    row['uf']="${credor.uf}";
                    row['cep']="${credor.CEP}";
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>
                $.iniciarCredor("pfc",data);
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
                <div class="titulocredor">CADASTRO DE CREDORES</div>
                <table class="tabelacredor">
                    <tr>
                        <td ><label>CNPJ:</label></td>
                        <td colspan="2"><label>Nome Fantasia:</label></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="cnpj" name="cnpj" onkeypress="return somenteNumeros(event)" />
                        </td>
                        <td colspan="2">
                            <input type="text" id="nomefantasia" name="nomefantasia" maxlength="80" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <label>Raz&atilde;o Social:</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="text" id="razaosocial" name="razaosocial" maxlength="80" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><label>Endere&ccedil;o</label></td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="text" id="endereco" name="endereco" maxlength="80" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><label>Bairro</label></td>
                        <td colspan="1"><label >Complemento:</label></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="text" name="bairro" id="bairro" />
                        </td>
                        <td colspan="1">
                            <input type="text" name="complemento" id="complemento" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1"><label>CEP:</label></td>
                        <td colspan="1"><label>Cidade:</label></td>
                        <td colspan="1"><label >Estado:</label></td>
                    </tr>
                    <tr>
                        <td colspan="1">
                            <input type="text" name="cep" id="cep" onkeypress="return somenteNumeros(event)" />
                        </td>
                        <td colspan="1">
                            <input type="text" name="cidade" id="cidade" />
                        </td>
                        <td colspan="1">
                            <input type="text" name="uf" id="uf"  maxlength="2"/>
                        </td>
                    </tr>
                    <tr>
                    <td colspan="3" align="center">
                            <input type="button" id="novo" name="novo" value="Novo" />
                            <input type="hidden" id="salvar" name="salvar" value="Salvar" />
                            <input type="hidden" id="cancelar" name="cancelar" value="Cancelar" />
                            <input type="button" id="alterar" name="alterar" value="Alterar" />
                        </td>
                    </tr>
                </table>
                <div id="jqxgrid" class="jqxgridcredor"></div>
            </form>
        </div>
    </body>
</html>