    package com.mycompany.hospedagempets.controller;

    import com.mycompany.hospedagempets.model.Animal;
    import com.mycompany.hospedagempets.model.Hospedagem;
    import com.mycompany.hospedagempets.model.ServicoAdicional;
    import com.mycompany.hospedagempets.model.TipoAcomodacao;
    import com.mycompany.hospedagempets.services.HospedagemService;
    import com.mycompany.hospedagempets.view.HospedagemView;
    import java.util.List;

    public class HospedagemController {

        private HospedagemView view;
        private HospedagemService service;

        public HospedagemController(HospedagemView view, HospedagemService service) {
            this.view = view;
            this.service = service;

            view.adicionarListenerCadastrar(e -> {

                String nomeAnimal = view.getNomeAnimal();
                if(nomeAnimal.isBlank()) {
                    view.mostrarErro("ERRO: Nome do animal e obrigatorio!");
                    return;
                }
                System.out.println("Animal: " + nomeAnimal);

                
                String nomeResponsavel = view.getNomeResponsavel();
                if(nomeResponsavel.isBlank()) {
                    view.mostrarErro("ERRO: Nome do responsavel e obrigatorio!");
                    return;
                }
                System.out.println("Responsavel: " + nomeResponsavel);

                Integer qtdDiarias = view.getQtdDiarias();
                System.out.println("Quantidade de diarias: " + qtdDiarias);

                String especieAnimal = view.getEspecie();
                if (especieAnimal == null) {
                    view.mostrarErro("Uma especie deve ser selecionada!");
                    return;
                }
                System.out.println("Especie do animal: " + especieAnimal);

                TipoAcomodacao tipoAcomodacao= view.getTipoAcomodacao();
                System.out.println("Tipo da acomdacao: " + tipoAcomodacao);

                List<ServicoAdicional> servicos = view.getServicos();
                System.out.println("Servicos adicionais: " + servicos);

                Animal animal = new Animal(null, nomeAnimal, especieAnimal);
                Hospedagem hospedagem = new Hospedagem(null, nomeResponsavel, animal, tipoAcomodacao, qtdDiarias);
                hospedagem.setServicos(servicos);
                
                //Adicionar cadastro local na lista de cadastros de hospedagens
                service.cadastrar(hospedagem);
                
                
                System.out.println("Hospedagens cadastradas: " + service.listagem().size());
                
                view.mostrarSucesso("Cadastro realizado com sucesso!");
                view.limparCampos();
            });
        }
    }