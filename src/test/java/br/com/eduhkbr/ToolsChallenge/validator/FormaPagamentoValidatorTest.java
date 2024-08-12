package br.com.eduhkbr.ToolsChallenge.validator;

import br.com.eduhkbr.ToolsChallenge.enums.TipoPagamento;
import br.com.eduhkbr.ToolsChallenge.exceptions.InvalidDataException;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FormaPagamentoValidatorTest {

    @Test
    public void testValidarFormaPagamentoValida() {
        FormaPagamento formaPagamento = new FormaPagamento(TipoPagamento.AVISTA.name(), 1);
        assertDoesNotThrow(() -> FormaPagamentoValidator.validarFormaPagamento(formaPagamento));
    }

    @Test
    public void testValidarFormaPagamentoInvalida() {
        FormaPagamento formaPagamento = new FormaPagamento("INVALIDO", 1);
        Exception exception = assertThrows(InvalidDataException.class, () ->
                FormaPagamentoValidator.validarFormaPagamento(formaPagamento)
        );
        assertEquals("Tipo de forma de pagamento inválido: INVALIDO", exception.getMessage());
    }
}

