
package br.upe.model;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaTableModel extends AbstractTableModel {

    private List<Tarefa> tarefasFinalizadas;
    private List<Tarefa> tarefasAtivas;

    private boolean exibirFinalizadas;

    public TarefaTableModel() {
        tarefasAtivas = new ArrayList<>();
        tarefasFinalizadas = new ArrayList<>();
    }

    private List<Tarefa> getTarefasExibidas() {
        List<Tarefa> tarefas = new ArrayList<>(tarefasAtivas);

        if (exibirFinalizadas) {
            tarefas.addAll(tarefasFinalizadas);
        }

        return tarefas;
    }

    @Override
    public int getRowCount() {
        return getTarefasExibidas().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarefa tarefa = getTarefasExibidas().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tarefa.isFinalizada();

            case 1:
                return tarefa.getHora();

            case 2:
                return tarefa.getData();

            case 3:
                return tarefa.getDescricao();

            case 4:
                return tarefa.getDuracaoTarefa() + " min";

            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int c) {
        switch (c) {
            case 0:
                return Boolean.class;
            case 1:
                return LocalTime.class;
            case 2:
                return LocalDate.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex != 0 || !(aValue instanceof Boolean)) {
            return;
        }

        Tarefa tarefa = getTarefasExibidas().get(rowIndex);

        boolean finalizada = (Boolean) aValue;

        tarefa.setFinalizada(finalizada);

        if (finalizada) {
            tarefasAtivas.remove(tarefa);

            if (!tarefasFinalizadas.contains(tarefa)) {
                tarefasFinalizadas.add(tarefa);
            }
        } else {
            tarefasFinalizadas.remove(tarefa);

            if (!tarefasAtivas.contains(tarefa)) {
                tarefasAtivas.add(tarefa);
            }
        }

        fireTableDataChanged();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        int indice = tarefasAtivas.size();

        tarefasAtivas.add(tarefa);

        fireTableRowsInserted(indice, indice);
    }

    public void setExibirFinalizadas(boolean selecionado) {
        this.exibirFinalizadas = selecionado;
        fireTableDataChanged();
    }

    public List<Tarefa> getTarefasFinalizadas() {
        return tarefasFinalizadas;
    }

    public void setTarefasFinalizadas(List<Tarefa> tarefasFinalizadas) {
        this.tarefasFinalizadas = tarefasFinalizadas;
        fireTableDataChanged();
    }

    public List<Tarefa> getTarefasAtivas() {
        return tarefasAtivas;
    }

    public void setTarefasAtivas(List<Tarefa> tarefasAtivas) {
        this.tarefasAtivas = tarefasAtivas;
        fireTableDataChanged();
    }

    public boolean isExibirFinalizadas() {
        return exibirFinalizadas;
    }
}
