<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <% 
            response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Sistema CALLCENTER - Home</title>
        <link rel="stylesheet" href="/CallCenter/util/estilo.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.base.css" type="text/css" />
        <link rel="stylesheet" href="/CallCenter/util/jqwidgets/jqwidgets/styles/jqx.pfc.css" type="text/css" />
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/scripts/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript">
            $(function(){
                $.iniciar("pfc");
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
            <form action="" method="post" id="form" name="form">
                <input name="operacao" id="operacao" type="hidden" value="" />
                <input name="id" id="id" type="hidden" />
                <input type="hidden" name="jsp" id="jsp" value="" />
            </form>
        </div>
    </body>
</html>
