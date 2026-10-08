package com.mycompany.hospedagempets.model;

public enum ServicoAdicional {

    BANHO(40.0),
    TOSA(35.0),
    PASSEIO(20.0),
    ACOMPANHAMENTO_VETERINARIO(80.0);

    private final double valor;

    ServicoAdicional(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
