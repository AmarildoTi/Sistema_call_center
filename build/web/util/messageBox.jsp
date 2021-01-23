<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="messageBoxSimNao">
    <div>
    </div>
    <div>
        <center>
            <div id="textoMessageBoxSimNao">
                ${mensagem}
            </div>
        </center>
        <div>
            <div>&nbsp;</div>
            <div style="float: right;">
                <input type="button" id="sim" name="sim" value="Sim" />
                <input type="button" id="nao" name="nao" value="N&atilde;o" />
            </div>
        </div>
    </div>
</div>
<div id="messageBoxOK">
    <div>
    </div>
    <div>
        <center>
            <div id="textoMessageBoxOK">
                 ${mensagem}
           </div>
        </center>
        <div>
            <div>&nbsp;</div>
            <div style="float: right;">
                <input type="button" id="ok" name="ok" value="OK" />
            </div>
        </div>
    </div>
</div>
<div id="messageBox">
    <div>
    </div>
    <div>
        <center>
            <div id="textoMessageBox">
                 ${mensagem}
           </div>
        </center>
        <div>
            <div>&nbsp;</div>
        </div>
    </div>
</div>           