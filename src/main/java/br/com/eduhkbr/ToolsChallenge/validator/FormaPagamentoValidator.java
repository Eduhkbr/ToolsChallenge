package br.com.eduhkbr.ToolsChallenge.validator;

import br.com.eduhkbr.ToolsChallenge.enums.TipoPagamento;
import br.com.eduhkbr.ToolsChallenge.exceptions.InvalidDataException;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;

public class FormaPagamentoValidator {

    public static void validarFormaPagamento(FormaPagamento formaPagamento) {
        if (!isValidFormaPagamento(formaPagamento.getTipo())) {
            throw new InvalidDataException("Tipo de forma de pagamento inválido: " + formaPagamento.getTipo());
        }
    }

    private static boolean isValidFormaPagamento(String tipo) {
        try {
            TipoPagamento.fromTipo(tipo);
        }catch (IllegalArgumentException ignored){
            return false;
        }
        return true;
    }

}
