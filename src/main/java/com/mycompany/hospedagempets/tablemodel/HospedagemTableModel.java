package com.mycompany.hospedagempets.tablemodel;

import com.mycompany.hospedagempets.model.Hospedagem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.mycompany.hospedagempets.model.Hospedagem;
import com.mycompany.hospedagempets.tablemodel.HospedagemTableModel;

public class HospedagemTableModel extends AbstractTableModel {
    
    private List<Hospedagem> hospedagens = new ArrayList<>();

    private final String[] colunas = {
        "ID",
        "Animal",
        "Responsável",
        "Espécie",
        "Acomodação",
        "Diárias",
        "Serviços",
        "Total"
    };

    @Override
    public int getRowCount() {
        return hospedagens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Hospedagem hospedagem = hospedagens.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return hospedagem.getId();

            case 1:
                return hospedagem.getAnimal().getNome();

            case 2:
                return hospedagem.getResponsavel();

            case 3:
                return hospedagem.getAnimal().getEspecie();

            case 4:
                return hospedagem.getTipo();

            case 5:
                return hospedagem.getQtdDiarias();

            case 6:
                return hospedagem.getServicos();

            case 7:
                return hospedagem.valorTotal();

            default:
                return null;
        }
    }

    public void setHospedagens(List<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
        fireTableDataChanged();
    }
}