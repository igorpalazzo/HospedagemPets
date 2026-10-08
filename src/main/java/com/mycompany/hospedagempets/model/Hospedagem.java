package com.mycompany.hospedagempets.model;
import java.util.List;
import java.util.ArrayList;


public class Hospedagem {
    private Long id;
    private String responsavel;
    private Animal animal;
    private TipoAcomodacao tipo; //Tipo da acomodacao (Standard ou Premium) --- Usar enum 
    private Integer qtdDiarias; //No final vai ser calculado o valor total usando qtdDiaria * valorDiaria
    private List<ServicoAdicional> servicos = new ArrayList<>();
    
    public Hospedagem() {
    }

    public Hospedagem(Long id, String responsavel, Animal animal, TipoAcomodacao tipo, Integer qtdDiarias) {
        this.id = id;
        this.responsavel = responsavel;
        this.animal = animal;
        this.tipo = tipo;
        this.qtdDiarias = qtdDiarias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public TipoAcomodacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcomodacao tipo) {
        this.tipo = tipo;
    }

    public Integer getQtdDiarias() {
        return qtdDiarias;
    }

    public void setQtdDiarias(Integer qtdDiarias) {
        this.qtdDiarias = qtdDiarias;
    }
    
    public double valorTotal() {
        Double valorTotal = 0.0;
        
        valorTotal = (qtdDiarias * tipo.getValorDiaria());
        if(tipo == TipoAcomodacao.PREMIUM) {
            valorTotal += 25.0;
        }
        
        for(ServicoAdicional servico : servicos) {
            valorTotal += servico.getValor();
        }
        
        return valorTotal;
    }
    
    public List<ServicoAdicional> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoAdicional> servicos) {
        this.servicos = servicos;
    }
    
}
