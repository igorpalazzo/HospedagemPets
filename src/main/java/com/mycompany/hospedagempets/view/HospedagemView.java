package com.mycompany.hospedagempets.view;

import com.mycompany.hospedagempets.model.ServicoAdicional;
import com.mycompany.hospedagempets.model.TipoAcomodacao;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mycompany.hospedagempets.tablemodel.HospedagemTableModel;
import com.mycompany.hospedagempets.model.Hospedagem;

public class HospedagemView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HospedagemView.class.getName());

    private HospedagemTableModel tableModel = new HospedagemTableModel();
    
    public void atualizarTabela(List<Hospedagem> hospedagens) {
        tableModel.setHospedagens(hospedagens);
    }
    /**
     * Creates new form HospedagemView
     */
    public HospedagemView() {
        initComponents();
        
        for(TipoAcomodacao acomodacao : TipoAcomodacao.values()) {
            cmbAcomodacao.addItem(acomodacao);
        }
        
            tblHospedagens.setModel(tableModel);
                
    }
    
    public void adicionarListenerCadastrar(ActionListener listener) {
            btnCadastrar.addActionListener(listener);
        }

    public String getNomeAnimal() {
        return txtNomeAnimal.getText();
    }
    
    public String getNomeResponsavel() {
        return txtNomeResponsavel.getText();
    }
    
    public Integer getQtdDiarias() {
        return (Integer)spnDiarias.getValue();
    }
    
    public String getEspecie() {
        String especie;
        if (rdbCachorro.isSelected()) {
            especie = "Cachorro";
            return especie;
        }
        if(rdbGato.isSelected()) {
            especie = "Gato";
            return especie;
        }
        return null;
    }
    
    public TipoAcomodacao getTipoAcomodacao() {
        return (TipoAcomodacao)cmbAcomodacao.getSelectedItem();
    }
    
    public List<ServicoAdicional> getServicos() {
        List<ServicoAdicional> lista = new ArrayList<>();
        
        if (chkBanho.isSelected()) {
            lista.add(ServicoAdicional.BANHO);
        }
        
        if(chkTosa.isSelected()) {
            lista.add(ServicoAdicional.TOSA);
        }
        
        if(chkPasseio.isSelected()) {
            lista.add(ServicoAdicional.PASSEIO);
        }
        
        if(chkAcompVeterinario.isSelected()) {
            lista.add(ServicoAdicional.ACOMPANHAMENTO_VETERINARIO);
        }
        
        return lista;
    }
    
    public void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarSucesso(String mensagem) {
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro concluido", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void limparCampos() {
        // Campos de texto
        txtNomeAnimal.setText("");
        txtNomeResponsavel.setText("");

        // Espécie
        btnGrupoEspecie.clearSelection();

        // Quantidade de diárias
        spnDiarias.setValue(1);

        // Tipo de acomodação
        cmbAcomodacao.setSelectedIndex(0);

        // Serviços adicionais
        chkBanho.setSelected(false);
        chkTosa.setSelected(false);
        chkPasseio.setSelected(false);
        chkAcompVeterinario.setSelected(false);
    }
    
    public void adicionarListenerExcluir(ActionListener listener) {
        btnExcluir.addActionListener(listener);
    }

    public Long getIdHospedagemSelecionada() {
        int linha = tblHospedagens.getSelectedRow();

        if (linha == -1) {
            return null;
        }

        int linhaModelo = tblHospedagens.convertRowIndexToModel(linha);

        return (Long) tblHospedagens.getModel().getValueAt(linhaModelo, 0);
    }
    
    public boolean confirmarExclusao() {
    int resposta = JOptionPane.showConfirmDialog(
     this,
          "Deseja realmente excluir esta hospedagem?",
            "Confirmar exclusão",
         JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
        );

        return resposta == JOptionPane.YES_OPTION;
    }
    
    public String getTextoPesquisa() {
        return txtPesquisa.getText();
    }

    public void adicionarListenerPesquisar(ActionListener listener) {
        btnPesquisar.addActionListener(listener);
    }

    public void adicionarListenerExibirTodos(ActionListener listener) {
        btnExibirTodos.addActionListener(listener);
    }
    
    public void adicionarListenerEditar(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public void adicionarListenerSalvarAlteracoes(ActionListener listener) {
        btnSalvarAlteracoes.addActionListener(listener);
    }

    public void preencherCampos(Hospedagem h) {

        txtNomeAnimal.setText(h.getAnimal().getNome());
        txtNomeResponsavel.setText(h.getResponsavel());

        spnDiarias.setValue(h.getQtdDiarias());

        btnGrupoEspecie.clearSelection();

        if (h.getAnimal().getEspecie().equalsIgnoreCase("Cachorro")) {
            rdbCachorro.setSelected(true);
        } else if (h.getAnimal().getEspecie().equalsIgnoreCase("Gato")) {
            rdbGato.setSelected(true);
        }

        cmbAcomodacao.setSelectedItem(h.getTipo());

        chkBanho.setSelected(h.getServicos().contains(ServicoAdicional.BANHO));
        chkTosa.setSelected(h.getServicos().contains(ServicoAdicional.TOSA));
        chkPasseio.setSelected(h.getServicos().contains(ServicoAdicional.PASSEIO));
        chkAcompVeterinario.setSelected(
            h.getServicos().contains(ServicoAdicional.ACOMPANHAMENTO_VETERINARIO)
    );
}
    
    public void adicionarListenerResumo(ActionListener listener) {
        btnResumo.addActionListener(listener);
    }   

    public void mostrarResumo(Hospedagem h) {

        String resumo =
            "RESUMO DA HOSPEDAGEM\n\n" +
            "Código: " + h.getId() + "\n" +
            "Animal: " + h.getAnimal().getNome() + "\n" +
            "Espécie: " + h.getAnimal().getEspecie() + "\n" +
            "Responsável: " + h.getResponsavel() + "\n" +
            "Acomodação: " + h.getTipo() + "\n" +
            "Quantidade de diárias: " + h.getQtdDiarias() + "\n" +
            "Serviços adicionais: " + h.getServicos() + "\n\n" +
            "VALOR TOTAL: R$ " + String.format("%.2f", h.valorTotal());

        JOptionPane.showMessageDialog(
          this,
            resumo,
           "Resumo da Hospedagem",
         JOptionPane.INFORMATION_MESSAGE
        );
    }   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupoEspecie = new javax.swing.ButtonGroup();
        jCheckBox3 = new javax.swing.JCheckBox();
        btnExcluir1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNomeAnimal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeResponsavel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        spnDiarias = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        rdbCachorro = new javax.swing.JRadioButton();
        rdbGato = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cmbAcomodacao = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        chkBanho = new javax.swing.JCheckBox();
        chkTosa = new javax.swing.JCheckBox();
        chkAcompVeterinario = new javax.swing.JCheckBox();
        chkPasseio = new javax.swing.JCheckBox();
        btnCadastrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHospedagens = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        txtPesquisa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnExibirTodos = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvarAlteracoes = new javax.swing.JButton();
        btnResumo = new javax.swing.JButton();

        jCheckBox3.setText("jCheckBox3");

        btnExcluir1.setText("Excluir");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Animal:");

        jLabel2.setText("Responsavel:");

        txtNomeResponsavel.addActionListener(this::txtNomeResponsavelActionPerformed);

        jLabel3.setText("Qtd. Diárias:");

        spnDiarias.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel4.setText("Especie do Animal:");

        btnGrupoEspecie.add(rdbCachorro);
        rdbCachorro.setText("Cachorro");

        btnGrupoEspecie.add(rdbGato);
        rdbGato.setText("Gato");

        jLabel5.setText("Tipo de Acomodação:");

        jLabel6.setText("Servicos Adicionais:");

        chkBanho.setText("Banho");

        chkTosa.setText("Tosa");

        chkAcompVeterinario.setText("Acomp. Veterinário");

        chkPasseio.setText("Passeio");

        btnCadastrar.setText("Cadastrar");

        tblHospedagens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblHospedagens);

        btnExcluir.setText("Excluir");

        btnPesquisar.setText("Pesquisa");

        jLabel7.setText("Pesquisar Animal:");

        btnExibirTodos.setText("Exibir Todos");

        btnEditar.setText("Editar");

        btnSalvarAlteracoes.setText("Salvar Alteracoes");

        btnResumo.setText("Ver resumo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNomeResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txtNomeAnimal)
                                            .addComponent(spnDiarias)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdbCachorro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdbGato))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAcomodacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkBanho)
                                            .addComponent(chkTosa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkAcompVeterinario)
                                            .addComponent(chkPasseio))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExibirTodos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResumo)
                            .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spnDiarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdbCachorro)
                    .addComponent(rdbGato))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbAcomodacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(chkBanho)
                    .addComponent(chkPasseio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAcompVeterinario)
                    .addComponent(chkTosa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResumo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPesquisar)
                            .addComponent(btnEditar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarAlteracoes)
                        .addGap(14, 14, 14)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnExibirTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeResponsavelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new HospedagemView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnExibirTodos;
    private javax.swing.ButtonGroup btnGrupoEspecie;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnResumo;
    private javax.swing.JButton btnSalvarAlteracoes;
    private javax.swing.JCheckBox chkAcompVeterinario;
    private javax.swing.JCheckBox chkBanho;
    private javax.swing.JCheckBox chkPasseio;
    private javax.swing.JCheckBox chkTosa;
    private javax.swing.JComboBox<TipoAcomodacao> cmbAcomodacao;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbCachorro;
    private javax.swing.JRadioButton rdbGato;
    private javax.swing.JSpinner spnDiarias;
    private javax.swing.JTable tblHospedagens;
    private javax.swing.JTextField txtNomeAnimal;
    private javax.swing.JTextField txtNomeResponsavel;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
