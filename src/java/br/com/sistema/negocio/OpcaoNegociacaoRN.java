package br.com.sistema.negocio;

import Util.Funcoes;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.OpcaoNegociacao;
import br.com.sistema.modelo.PerfilNegociacao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class OpcaoNegociacaoRN {
    
    public static ArrayList<OpcaoNegociacao> calcular(Atendimento atendimento) throws Exception{
            ArrayList<OpcaoNegociacao> lista = new ArrayList<OpcaoNegociacao>();
            Divida divida = atendimento.getDivida();
            Date dataPagamento;
            double valorEntrada;
            double desconto;
            if(atendimento.getOpcaoNegociacao().isEmpty()){
                dataPagamento = new Date();
                valorEntrada = 0.0;
                desconto = 0.0;
            }else{
                dataPagamento = atendimento.getOpcaoNegociacao().get(0).getData();
                valorEntrada = atendimento.getOpcaoNegociacao().get(0).getValorEntrada();
                desconto = atendimento.getOpcaoNegociacao().get(0).getMaximoDesconto();                
            }
            try{
                OpcaoNegociacao opcao = new OpcaoNegociacao();
                long dias = Funcoes.dateDiff(divida.getData(), dataPagamento);
                double atraso = Double.parseDouble(String.valueOf(dias));
                PerfilNegociacao perfil = new PerfilNegociacao();
                for(PerfilNegociacao p:divida.getCredor().getPerfilNegociacao()){
                    if (dias >= p.getAtrasoDe() && dias <= p.getAtrasoAte()){
                       perfil = p;
                    }
                }
                double fatorMulta;
                if (atraso < 30){
                    fatorMulta = 1;
                }
                else{
                    fatorMulta = 1 + perfil.getMulta() / 100;
                }
                double meses = Funcoes.arredondamento(atraso / 30, 2);
                double fatorEncargos = Funcoes.arredondamento(Math.pow(1 + perfil.getJuros() / 100,meses),6);
                double dividaAtualizada =  Funcoes.arredondamento(divida.getValor() * fatorEncargos * fatorMulta,2);
                double descontoEncargos, descontoPrincipal, maximoDesconto, valorNegociacao;
                descontoEncargos = Funcoes.arredondamento((dividaAtualizada - divida.getValor()) * perfil.getDescontoEncargos() / 100,2);
                descontoPrincipal = Funcoes.arredondamento(divida.getValor() * perfil.getDescontoValorPrincipal() / 100,2);
                maximoDesconto = descontoEncargos+descontoPrincipal;
                if (desconto > maximoDesconto){
                    desconto = maximoDesconto;
                }
                valorNegociacao = dividaAtualizada - desconto;
                opcao.setMaximoDesconto(maximoDesconto);
                opcao.setValorCorrigido(dividaAtualizada);
                opcao.setValorCorrecao(dividaAtualizada-divida.getValor());
                opcao.setValorParcela(valorNegociacao);
                opcao.setValorNegociacao(valorNegociacao);
                if (valorEntrada != 0.0 && valorEntrada < perfil.getValorMinimoEntrada()){
                    valorEntrada = perfil.getValorMinimoEntrada();
                }
                opcao.setValorEntrada(valorEntrada);
                opcao.setData(dataPagamento);
                opcao.setParcelamento(0);
                lista.add(opcao);
                for(int i = 2;i <= perfil.getMaximoParcelas();i++){
                    opcao = new OpcaoNegociacao();
                    double saldoAParcelar = valorNegociacao - valorEntrada;
                    int parcelamento = i;
                    if (valorEntrada > 0){
                        parcelamento--;
                    }
                    fatorEncargos = Funcoes.arredondamento(Math.pow( 1 + perfil.getJurosPorParcela() / 100,  parcelamento),6);
                    double valorParcela = Funcoes.arredondamento(saldoAParcelar * (perfil.getJurosPorParcela() / 100) * (fatorEncargos / (fatorEncargos-1)),2);
                    opcao.setValorCorrigido(dividaAtualizada);
                    opcao.setValorNegociacao(valorNegociacao);
                    opcao.setValorEntrada(valorEntrada);
                    opcao.setValorParcela(valorParcela);
                    opcao.setParcelamento(parcelamento);
                    if (valorParcela >= perfil.getValorMinimoParcela()){
                        lista.add(opcao);
                    }
                }
            }
            catch (ParseException e){
                throw new Exception("Calcular Opção Negociação: " + e.getMessage());
            } catch (NumberFormatException e) {
                throw new Exception("Calcular Opção Negociação: " + e.getMessage());
        }
            return lista;
        }

}
