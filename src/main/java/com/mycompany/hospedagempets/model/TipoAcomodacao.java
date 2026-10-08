package com.mycompany.hospedagempets.model;

public enum TipoAcomodacao {
    STANDARD(60.0),
    PREMIUM(100.0);
    
    private final double valorDiaria;
    
    TipoAcomodacao(double valorDiaria){
        this.valorDiaria = valorDiaria;
    }
    
    public double getValorDiaria() {
        return valorDiaria;
    }
}