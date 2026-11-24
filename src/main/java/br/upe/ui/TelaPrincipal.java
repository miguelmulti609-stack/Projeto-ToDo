package br.upe.ui;

import br.upe.conflito.*;
import br.upe.controller.TarefaControlador;
import br.upe.model.Tarefa;
import br.upe.model.TarefaTableModel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal {
    private JPanel pnlMain;
    private JTextField txtDescricaoTarefa;
    private JTextField txtData;
    private JTextField txtHora;
    private JButton btnAdicionarTarefa;
    private JPanel pnlAdicionar;
    private JTable tblTarefas;
    private JCheckBox chkExibirFinalizadas;
    private JComboBox comboBox1;
    private Integer duracaoTarefa = 15;

    private TarefaControlador controlador;


    public TelaPrincipal() {
        super();


        btnAdicionarTarefa.addActionListener(e -> {
            try {
                LocalTime hora = LocalTime.parse(txtHora.getText());

                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate Date = LocalDate.parse(txtData.getText(), formatador);

                List<Tarefa> tarefasAtivas =
                        controlador.getTarefaTableModel().getTarefasAtivas();

                if (!Conflito.detectarConflito(duracaoTarefa, hora, tarefasAtivas, Date)) {
                    adicionarTarefa(
                            txtDescricaoTarefa.getText(),
                            Date,
                            hora,
                            duracaoTarefa
                    );

                    txtDescricaoTarefa.setText("");
                    txtData.setText("");
                    txtHora.setText("");

                }else {
                    JOptionPane.showMessageDialog(
                            pnlMain,
                            "Erro, essa atividade está conflitando com outras",
                            "Conflito",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        pnlMain,
                        "Erro, campo preenchido incorretamente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        chkExibirFinalizadas.addActionListener(e -> {
            boolean selecionado = ((JCheckBox) e.getSource()).isSelected();
            controlador.exibirFinalizadas(selecionado);
        });

        comboBox1.addActionListener(e -> {
            String selecionado = comboBox1.getSelectedItem().toString();

            duracaoTarefa = Integer.parseInt(
                    selecionado.replace(" min", "")
            );
        });
    }

    private void adicionarTarefa(String texto, LocalDate data, LocalTime hora, Integer duracao) {

        Tarefa tarefa = new Tarefa(texto, controlador.getTarefaTableModel().getTarefasAtivas().size(), data, hora, duracao);
        controlador.adicionarTarefaAtiva(tarefa);
        tblTarefas.revalidate();
        tblTarefas.repaint();
    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    private void createUIComponents() {
        controlador = new TarefaControlador();
        tblTarefas = new JTable(controlador.getTarefaTableModel());
        tblTarefas.getColumnModel().getColumn(0).setMaxWidth(20);
        controlador.getTarefaTableModel().addTableModelListener(e -> controlador.salvarTarefas());
        tblTarefas.getColumnModel().getColumn(1).setMaxWidth(200);
        tblTarefas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTarefas.getColumnModel().getColumn(2).setMaxWidth(300);
        tblTarefas.getColumnModel().getColumn(2).setPreferredWidth(100);



    }




}
