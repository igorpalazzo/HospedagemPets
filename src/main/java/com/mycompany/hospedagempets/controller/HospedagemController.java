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
                // Cadastrar a hospedagem
                service.cadastrar(hospedagem);

                // Atualizar a tabela
                view.atualizarTabela(service.listagem());

                // Mostrar mensagem de sucesso
                view.mostrarSucesso("Cadastro realizado com sucesso!");

                // Limpar o formulário
                 view.limparCampos();
            });
            
            view.adicionarListenerExcluir(e -> {

                Long id = view.getIdHospedagemSelecionada();

                if (id == null) {
                    view.mostrarErro("Selecione uma hospedagem para excluir! Dê um duplo-clique na hospedagem selecionada.");
                    return;
                }

                if (!view.confirmarExclusao()) {
                    return;
                }

                service.remocao(id);

                view.atualizarTabela(service.listagem());

                view.mostrarSucesso("Hospedagem excluída com sucesso!");

            });
            
            view.adicionarListenerPesquisar(e -> {

                System.out.println("BOTAO PESQUISAR CLICADO!");

                String nome = view.getTextoPesquisa().trim();

                if (nome.isBlank()) {
                    view.mostrarErro("Digite o nome de um animal para pesquisar!");
                    return;
                }

                view.atualizarTabela(service.pesquisa(nome));

            });

            view.adicionarListenerExibirTodos(e -> {

            System.out.println("BOTAO EXIBIR TODOS CLICADO!");

            view.atualizarTabela(service.listagem());

            });
        }
    }