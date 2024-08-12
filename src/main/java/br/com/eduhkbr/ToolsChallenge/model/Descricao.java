package br.com.eduhkbr.ToolsChallenge.model;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Descricao {

    private BigDecimal valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private String status;

    public Descricao() {
    }

    public Descricao(BigDecimal valor, String dataHora, String estabelecimento, String nsu, String codigoAutorizacao, String status) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.estabelecimento = estabelecimento;
        this.nsu = nsu;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Descricao{" +
                "valor=" + valor +
                ", dataHora='" + dataHora + '\'' +
                ", estabelecimento='" + estabelecimento + '\'' +
                ", nsu='" + nsu + '\'' +
                ", codigoAutorizacao='" + codigoAutorizacao + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descricao descricao = (Descricao) o;
        return nsu == descricao.nsu && codigoAutorizacao == descricao.codigoAutorizacao && Objects.equals(valor, descricao.valor) && Objects.equals(dataHora, descricao.dataHora) && Objects.equals(estabelecimento, descricao.estabelecimento) && Objects.equals(status, descricao.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
    }


    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
