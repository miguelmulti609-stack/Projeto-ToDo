package br.upe.controller;

import br.upe.model.Tarefa;
import br.upe.model.TarefaTableModel;
import br.upe.persistencia.Persistencia;

import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class TarefaControlador {

    // Atributos
    private TarefaTableModel tarefaTableModel;

    //Construtor
    public TarefaControlador() {
        tarefaTableModel = new TarefaTableModel();
        carregarTarefas();
    }

    //Metodos de negocio
    public void adicionarTarefaAtiva(Tarefa tarefa) {
        this.tarefaTableModel.getTarefasAtivas().add(tarefa);
        salvarTarefas();
    }

    public void exibirFinalizadas(boolean exibir) {
        tarefaTableModel.setExibirFinalizadas(exibir);
    }

    // Junta as duas listas (sem repetir) e salva no arquivo
    public void salvarTarefas() {
        List<Tarefa> todas = new ArrayList<>(tarefaTableModel.getTarefasFinalizadas());
        for (Tarefa t : tarefaTableModel.getTarefasAtivas()) {
            if (!todas.contains(t)) {
                todas.add(t);
            }
        }
        Persistencia.salvar(todas);
    }

    // Le o arquivo e coloca cada tarefa na lista certa
    private void carregarTarefas() {
        List<Tarefa> salvas = Persistencia.carregar();
        for (Tarefa t : salvas) {
            if (Boolean.TRUE.equals(t.isFinalizada())) {
                tarefaTableModel.getTarefasFinalizadas().add(t);
            } else {
                tarefaTableModel.getTarefasAtivas().add(t);
            }
        }
    }

    //Getter e Setters

    public TarefaTableModel getTarefaTableModel() {
        return tarefaTableModel;
    }

    public void setTarefaTableModel(TarefaTableModel tarefaTableModel) {
        this.tarefaTableModel = tarefaTableModel;
    }

}