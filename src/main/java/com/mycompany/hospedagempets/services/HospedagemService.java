//ESSE SERVICE SERVE BASICAMENTE PRA COLOCAR A HOSPEDAGEM JA CRIADA DENTRO -
//- DE UMA LISTA QUE FOI CRIADA JA NO CONTROLLER/VIEW

package com.mycompany.hospedagempets.services;

import com.mycompany.hospedagempets.model.Animal;
import com.mycompany.hospedagempets.model.Hospedagem;
import java.util.ArrayList;
import java.util.List;

public class HospedagemService {
    private List<Hospedagem> hospedagens;
    private Long proximoId = 1L;
    
    // CONSTRUTOR DO SERVICO
    public HospedagemService() {
        hospedagens = new ArrayList<>();
    }
    
    public void cadastrar(Hospedagem hospedagem) {
        hospedagem.setId(proximoId);
        proximoId++;       
        hospedagens.add(hospedagem);
    }
    
    public List<Hospedagem> listagem() {
        return hospedagens; //so passa a lista inteira de hospedagens
    }
    
    public void alteracao(Hospedagem hospedagem) {
        /*    
        //Pensando que ele ja vai receber a hospedagem atualizada
        // Recebe atualizada + id -> Reseta a antiga que tem o mesmo id -> cadastra a atualizada
        Hospedagem hospedagemTemp = hospedagem;
        Long idTemp = hospedagem.getId();
        hospedagens.remove(hospedagem.getId());
        //hospedagens.add()
        */
        
        Long idAlterado = hospedagem.getId();
        
        for (int i = 0; i < hospedagens.size(); i++) {
    
            Long idAntigo = hospedagens.get(i).getId();
    
            if (idAntigo.equals(idAlterado)) {
                hospedagens.set(i, hospedagem);
                break;
            }
        }
        
    }
    
    public void remocao(Long id) {
        for (int i = 0; i < hospedagens.size(); i++) {
            Long idTemp = hospedagens.get(i).getId();
            
            if(idTemp.equals(id)) {
                hospedagens.remove(i);
                
                break;
            }
            
        }
    }
    
    public List<Hospedagem> pesquisa(String nomeAnimal) {
        List<Hospedagem> resultadoPesquisa = new ArrayList<>();
        
        for(int i = 0; i < hospedagens.size(); i++) {        
            String nomeTemp = hospedagens.get(i).getAnimal().getNome();
            
            if(nomeTemp.toLowerCase().contains(nomeAnimal.toLowerCase())) {
                resultadoPesquisa.add(hospedagens.get(i));
            }
        }
        
        return resultadoPesquisa;
    }   
    
}
