package br.upe.controller;

import br.upe.model.Tarefa;
import br.upe.notificacao.Notificar;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LembreteControlador {

    private final TarefaControlador controlador;

    private final ScheduledExecutorService agendador;

    private final Set<Integer> tarefasNotificadas = new HashSet<>();

    public LembreteControlador(TarefaControlador controlador) {
        this.controlador = controlador;

        agendador = Executors.newSingleThreadScheduledExecutor();

        agendador.scheduleAtFixedRate(
                this::verificarLembretes,
                0,
                30,
                TimeUnit.SECONDS
        );
    }

    private void verificarLembretes() {

            LocalDateTime agora = LocalDateTime.now();

        List<Tarefa> tarefas = controlador.getTarefaTableModel().getTarefasAtivas();

        for (Tarefa tarefa : tarefas) {

            LocalDateTime horarioTarefa = LocalDateTime.of(tarefa.getData(), tarefa.getHora());

            LocalDateTime horarioLembrete = horarioTarefa.minusMinutes(10);

            if (!tarefasNotificadas.contains(tarefa.getOrdem()) && !agora.isBefore(horarioLembrete) && agora.isBefore(horarioTarefa)) {

                tarefasNotificadas.add(tarefa.getOrdem());

                Notificar.notificacao(
                        "Lembrete de atividade",
                        tarefa.getDescricao() + " começa em 10 minutos."
                );
            }
        }
    }

    public void encerrar() {
        agendador.shutdown();
    }
}

