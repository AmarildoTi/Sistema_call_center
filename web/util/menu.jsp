<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="elemento5">
    <img alt="" src="/CallCenter/imagens/mundo.jpg" class="image2" />
    <h3 class="usuario">
        ${login.tipo}: ${login.nome}
    </h3>
    <h3 class="usuario2">
       Logado as ${login.data}
    </h3>
</div>
<div id="elemento7">
<div id='jqxMenu' style="visibility: hidden;">
    <ul>
        <li><a href="/CallCenter/sistema/publico/login/home.jsp">Home</a></li>
        <c:if test="${login.tipo == 'Supervisor' || login.tipo == 'Gerente'}">
            <li>
                <a href='#'>Cadastro</a>
                <ul>
                    <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','UsuarioListar','supervisor/usuario/usuario.jsp')">Usu&aacute;rios</a></li>
                    <c:if test="${login.tipo == 'Gerente'}">
                        <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','CredorListar','gerente/credor/credor.jsp')">Credores</a></li>
                        <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','PerfilNegociacaoListar','gerente/credor/perfilnegociacao.jsp')">Perfis de Negocia&ccedil;&atilde;o</a></li>
                        <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','CredorListar','gerente/divida/importar.jsp')">D&iacute;vidas</a></li>
                    </c:if>
                    <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','SegmentacaoListar','supervisor/segmentacao/segmentacao.jsp')">Segmenta&ccedil;&atilde;o do Atendimento</a></li>
                </ul>
            </li>
            <li>
                <a href='#'>Pagamento</a>
                <ul>
                    <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','DividaListar','supervisor/pagamento/pagamento.jsp')">Confirma&ccedil;&atilde;o Manual</a></li>
                </ul>
            </li>
            <li>
                <a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','CredorListar','supervisor/relatorio/relatorio.jsp')">Relat&oacute;rio</a>
            </li>
        </c:if>
        <c:if test="${login.tipo == 'Operador'}">
            <li>
                <a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','AtendimentoIniciar','operador/atendimento/atendimento.jsp')">Atendimento</a>
            </li>
        </c:if>
            <li>
                <a href="#">Localizar</a>
                <ul>
                    <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','DividaListar','publico/divida/localizar.jsp')">D&iacute;vida</a></li>
                </ul>
            </li>
            <li><a href="#">Configura&ccedil;&otilde;es</a>
            <ul>
                <li><a href="#" onclick="mudaAction('//localhost:8084/CallCenter/mvc','CredencialListar','publico/login/alterarSenha.jsp')">Alterar Senha</a></li>
            </ul>
        </li>
	<li><a href='/CallCenter/sistema/publico/login/logout.jsp'>Sair</a></li>
    </ul>
</div>
</div>
