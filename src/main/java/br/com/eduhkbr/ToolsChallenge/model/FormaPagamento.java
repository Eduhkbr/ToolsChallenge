package br.com.eduhkbr.ToolsChallenge.model;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class FormaPagamento {
    private String tipo;
    private Integer parcelas;

    public FormaPagamento() {
    }

    public FormaPagamento(String tipo, Integer parcelas) {
        this.tipo = tipo;
        this.parcelas = parcelas;
    }

    @Override
    public String toString() {
        return "FormaPagamento{" +
                "tipo='" + tipo + '\'' +
                ", parcelas=" + parcelas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaPagamento that = (FormaPagamento) o;
        return Objects.equals(tipo, that.tipo) && Objects.equals(parcelas, that.parcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, parcelas);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }
}
