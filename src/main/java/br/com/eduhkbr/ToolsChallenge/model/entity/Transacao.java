package br.com.eduhkbr.ToolsChallenge.model.entity;

import br.com.eduhkbr.ToolsChallenge.model.Descricao;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    private Long id;
    private String cartao;

    private Descricao descricao;
    private FormaPagamento formaPagamento;

    public Transacao() {
    }

    public Transacao(Long id, String cartao, Descricao descricao, FormaPagamento formaPagamento) {
        this.id = id;
        this.cartao = cartao;
        this.descricao = descricao;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return cartao == transacao.cartao && Objects.equals(id, transacao.id) && Objects.equals(descricao, transacao.descricao) && Objects.equals(formaPagamento, transacao.formaPagamento);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "cartao=" + cartao +
                ", id=" + id +
                ", descricao=" + descricao +
                ", formaPagamento=" + formaPagamento +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartao, descricao, formaPagamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Descricao getDescricao() {
        return descricao;
    }

    public void setDescricao(Descricao descricao) {
        this.descricao = descricao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
