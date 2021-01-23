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
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxtabs.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdatetimeinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcalendar.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/globalization/globalize.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.sort.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.pager.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.selection.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.edit.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxgrid.columnsresize.js"></script> 
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxmaskedinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxnumberinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jqwidgets/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="/CallCenter/util/util.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery.js"></script>
        <script type="text/javascript" src="/CallCenter/util/jquery_atendimento.js"></script>
        <script type="text/javascript">
            $(function() {
                $.iniciar("pfc");
                var data = new Array();
                var i = 0;
                <c:forEach var="status" items="${listaStatus}">
                    var row = {};
                    row['id']="${status.id}";
                    row['status']="${status.descricao}";
                    data[i]=row;
                    i = i + 1;    
                </c:forEach>
                var data2 = new Array();
                i = 0;
                <c:forEach var="contato" items="${atendimento.divida.devedor.contato}">
                    var row = {};
                    row['tipo']="${contato.tipo}";
                    row['contato']="${contato.contato}";
                    data2[i]=row;
                    i = i + 1;    
                </c:forEach>
                var datax = "${atendimento.opcaoNegociacao[0].dataString}";
                var dmy = datax.split("/");
                datax = dmy[2]+", "+(parseInt(dmy[1], 10)-1).toString()+", "+dmy[0];
                var devedor = {};
                devedor["endereco"] = "${atendimento.divida.devedor.endereco}";
                devedor["complemento"] = "${atendimento.divida.devedor.complemento}";
                devedor["bairro"] = "${atendimento.divida.devedor.bairro}";
                devedor["cidade"] = "${atendimento.divida.devedor.cidade}";
                devedor["cep"] = "${atendimento.divida.devedor.cep}";
                devedor["uf"] = "${atendimento.divida.devedor.uf}";
                var dataAtendimento =  new Array();
                i = 0;
                <c:forEach var="atendimentos" items="${listaAtendimento}">
                    var row = {};
                    row['operador']="${atendimentos.credencial.nome}";
                    row['data']="${atendimentos.data}";
                    row['status']="${atendimentos.status.descricao}";
                    row['descricao']="${atendimentos.descricao}";
                    dataAtendimento[i]=row;
                    i = i + 1;
                </c:forEach>
                var dataNegociacao =  new Array();
                i = 0;
                <c:forEach var="negociacao" items="${listaNegociacao}">
                    var row = {};
                    row['status']="${negociacao.status}";
                    row['parcelamento']="${negociacao.parcelamento}";
                    row['valorcorrigido']="${negociacao.valorCorrigidoString}";
                    row['valornegociado']="${negociacao.valorNegociadoString}";
                    row['data']="${negociacao.dataString}";
                    dataNegociacao[i]=row;
                    i = i + 1;
                </c:forEach>
                $.iniciarAtendimento("pfc",data,datax,"${valorentrada}","${desconto}","${atendimento.opcaoNegociacao[0].valorNegociacao}","${atendimento.opcaoNegociacao[0].maximoDesconto}",data2,devedor,dataAtendimento,dataNegociacao);
                $('#jqxAbas').jqxTabs('select',"${aba}");
                if(${mensagem != "" && mensagem != null}){
                    $("#messageBoxOK").jqxWindow("open");
                };
            });
        </script>
    </head>
    <body>
        <c:import url="/util/menu.jsp" />
        <c:import url="/util/messageBox.jsp" />
        <div id="elemento6">
            <form id="form" name="form" method="post" action="" >
                <input type="hidden" name="contato" id="contato" value="${tipocontato}" />
                <input type="hidden" name="idstatus" id="idstatus" value="" />
                <input type="hidden" name="operacao" id="operacao" value="" />
                <input type="hidden" name="jsp" id="jsp" value="atendimento/atendimento.jsp" />
                <input type="hidden" name="divida" id="divida" value="${atendimento.divida.id}" />
                <input type="hidden" name="dataatendimento" id="dataatendimento" value="${atendimento.data}" />
                <div id="jqxAbas">
                    <ul>
                        <li style="margin-left: 10px;">Atendimento</li>
                        <li>Negociação</li>
                        <li>Atualização de Cadastro</li>
                        <li>Hist&oacute;rico</li>
                    </ul>
                    <div>
                        <c:if test="${atendimento.divida != null}">
                            <fieldset class="box">
                                <legend>Dados do Devedor:</legend>
                                    CPF: ${atendimento.divida.devedor.CPF} <br/>
                                    Nome: ${atendimento.divida.devedor.nome}<br/>
                                    <br/>
                                    Endereço: <br/>
                                    ${atendimento.divida.devedor.endereco} &nbsp;&nbsp; ${atendimento.divida.devedor.complemento} &nbsp;&nbsp; ${atendimento.divida.devedor.bairro}<br/>
                                    ${atendimento.divida.devedor.cep} &nbsp;&nbsp; ${atendimento.divida.devedor.cidade} &nbsp;&nbsp; ${atendimento.divida.devedor.uf} <br/>
                                    <br/>
                                    <c:if test="${atendimento.divida.devedor.contato != null}">
                                        Telefone(s):<br/>
                                        <c:forEach var="telefone" items="${atendimento.divida.devedor.contato}">
                                            ${telefone.tipo}: ${telefone.contato}<br/>
                                        </c:forEach>
                                    </c:if>
                                </fieldset>
                                <fieldset class="box">
                                    <legend>Detalhes da dívida:</legend>
                                    Credor: ${atendimento.divida.credor.razaosocial}<br/>
                                    Contrato: ${atendimento.divida.contrato}<br/>
                                    Valor da Dívida: ${atendimento.divida.valorString}<br/>
                                    Data da Dívida: ${atendimento.divida.dataString}<br/>
                                </fieldset>
                                <fieldset class="box">
                                    <table>
                                        <tr>                                            
                                            <td>
                                                <label>Descrição do atendimento</label>
                                            </td>
                                            <td>
                                                <label>Status do atendimento</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td rowspan="3">
                                                <textarea id="descricao" name="descricao" rows="5" cols="50" onkeypress="return maximoCaracteres(this,250)"></textarea>
                                            </td>
                                            <td>
                                                <div id="statusatendimento"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="button" id="cadastrar" name="cadastrar" value="Cadastrar Descrição"></input>
                                            </td>
                                        </tr>
                                    </table><br/>
                                </fieldset>
                        </c:if>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <td>
                                    <table>
                                        <tr >
                                            <td align="right" class="corvermelho">
                                                <label class="labelatendimento">Valor Original da Dívida:</label>
                                            </td>                                
                                            <td class="corvermelho">
                                                <input type="text" name="valoridivida" id="valordivida" value="${atendimento.divida.valorString}" class="txtatendimento" readonly="readonly" style="color: red;" />
                                            </td>
                                            <td rowspan="13">
                                                &nbsp;
                                            </td>
                                            <td rowspan="13">
                                                <c:if test="${atendimento.opcaoNegociacao != null}">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <fieldset class="box">
                                                                <legend>Op&ccedil;&otilde;es de Pagamento:</legend>
                                                                <c:forEach var="opcao" items="${atendimento.opcaoNegociacao}">
                                                                    <c:if test="${opcao.parcelamento==0}">
                                                                        <input type="radio" id="parcelamento" name="parcelamento" value="${opcao.parcelamentoMaisValor}" checked="checked">&nbsp;${opcao.opcao}</input><br/>
                                                                    </c:if>
                                                                    <c:if test="${opcao.parcelamento!=0}">
                                                                        <input type="radio" id="parcelamento" name="parcelamento" value="${opcao.parcelamentoMaisValor}">&nbsp;${opcao.opcao}</input><br/>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                </table>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="corvermelho">
                                            <td align="right">
                                                <label class="labelatendimento">Valor da Dívida Corrigida:</label>
                                            </td>                                
                                            <td>
                                                <input type="text" name="valorcorrigido" id="valorcorrigido" value="${atendimento.opcaoNegociacao[0].valorCorrigidoString}" class="txtatendimento" readonly="readonly" style="color: red;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">&nbsp;</td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td align="right">
                                                <label class="labelatendimento">Data para Pagamento:</label>
                                            </td>
                                            <td>
                                                <div id="datapagamento"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">&nbsp;</td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td align="right">
                                                <div id="checkdesconto">Efetuar Desconto:</div>
                                            </td>
                                            <td>
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr class="corverde">
                                            <td align="right">
                                                <label class="labelatendimento">Valor Máximo de Desconto:</label>
                                            </td>
                                            <td>
                                                <input type="text" name="maximodesconto" id="maximodesconto" value="${atendimento.opcaoNegociacao[0].maximoDescontoString}" class="txtatendimento" readonly="readonly" style="color: green;" />
                                            </td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td align="right">
                                                <label class="labelatendimento">Valor do Desconto:</label>
                                            </td>
                                            <td>
                                                <div id="desconto"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">&nbsp;</td>
                                        </tr>
                                        <tr class="corazul">
                                            <td align="right">
                                                <label class="labelatendimento">Valor da Negociação:</label>
                                            </td>
                                            <td>
                                                <input type="text" name="valornegociacao" id="valornegociacao" value="${atendimento.opcaoNegociacao[0].valorNegociacaoString}" class="txtatendimento" readonly="readonly" style="color: blue;" />
                                            </td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td align="right">
                                                <label class="labelatendimento">Valor de Entrada:</label>
                                            </td>
                                            <td>
                                                <div id="valorentrada"></div>
                                            </td>
                                        </tr>
                                        <tr>                                            
                                            <td colspan="2">&nbsp;</td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td align="right">
                                                <div id="checkemail">Enviar por E-mail:</div>
                                            </td>
                                            <td>
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr class="fontebold">
                                            <td colspan="2" align="right">
                                                <input type="text" id="email" name="email"/>
                                            </td>
                                            <td>
                                                &nbsp;
                                            </td>
                                            <td align="center">
                                                <input type="button" id="NegociacaoConcluir" name="NegociacaoConcluir" value="  Concluir Negociação  " /><br/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <fieldset class="box">
                            <div id="checkendereco">Editar os dados abaixo:</div>
                            <table>
                                <tr>
                                    <td colspan="2">Endereço:</td>
                                    <td colspan="2">Complemento:</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="text" id="endereco" name="endereco" value="${atendimento.divida.devedor.endereco}" class="jqxmaiuscula"/>
                                    </td>
                                    <td colspan="2">
                                        <input type="text" id="complemento" name="complemento" value="${atendimento.divida.devedor.complemento}" class="jqxmaiuscula"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Bairro:</td>
                                    <td>Cidade:</td>
                                    <td>UF:</td>
                                    <td>CEP:</td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" id="bairro" name="bairro" value="${atendimento.divida.devedor.bairro}" class="jqxmaiuscula"/>
                                    </td>
                                    <td>
                                        <input type="text" id="cidade" name="cidade" value="${atendimento.divida.devedor.cidade}" class="jqxmaiuscula"/>
                                    </td>
                                    <td>
                                        <input type="text" id="uf" name="uf" value="${atendimento.divida.devedor.uf}" class="jqxmaiuscula"/>
                                    </td>
                                    <td>
                                        <input type="text" id="cep" name="cep" value="${atendimento.divida.devedor.cep}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="center">
                                        <input type="button" id="atualizarendereco" name="atualizarendereco" value="Alterar" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <fieldset class="box">
                            <div id="checkcontato">Editar os contatos abaixo:</div>
                            <table>
                                <tr>
                                    <td>
                                        <div id="tipo">Editar os contatos abaixo:</div>                                       
                                    </td>
                                    <td>
                                        <input type="text" id="contatonovo" name="contatonovo" value="" />
                                    </td>
                                    <td>
                                        <input type="button" id="atualizarcontato" name="atualizarcontato" value="Alterar" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </div>
                    <div>
                        <fieldset class="box">
                            <legend>Hist&oacute;rico Atendimento:</legend>
                            <div id="historicoatendimento"></div>
                            <div id="detalheatendimento"></div>
                        </fieldset>
                        <fieldset class="box">
                            <legend>Hist&oacute;rico Negocia&ccedil;&atilde;o:</legend>
                            <div id="historiconegociacao"></div>
                            <div id="detalhenegociacao"></div>
                        </fieldset>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>