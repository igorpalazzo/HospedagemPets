package com.mycompany.hospedagempets.model;

public class Animal {
    private Long id;
    private String nome;
    private String especie;

    public Animal() {
    }

    public Animal(Long id, String nome, String especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    
    
    
}
