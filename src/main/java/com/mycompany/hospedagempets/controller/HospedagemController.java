package com.mycompany.hospedagempets.controller;

import com.mycompany.hospedagempets.services.HospedagemService;
import com.mycompany.hospedagempets.view.HospedagemView;

public class HospedagemController {

    private HospedagemView view;
    private HospedagemService service;

    public HospedagemController(HospedagemView view, HospedagemService service) {
        this.view = view;
        this.service = service;

        view.adicionarListenerCadastrar(e -> {
            
            String nomeAnimal = view.getNomeAnimal();
            System.out.println("Animal: " + nomeAnimal);
            
            String nomeResponsavel = view.getNomeResponsavel();
            System.out.println("Responsavel: " + nomeResponsavel);
        
        });
    }
}