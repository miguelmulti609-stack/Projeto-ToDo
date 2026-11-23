package br.upe.conflito;

import java.time.LocalTime;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;
import br.upe.model.Tarefa;

public class Conflito {
    public static boolean detectarConflito(Integer duracaoTarefa, LocalTime hora, List<Tarefa> listaTarefas) {
        TemporalAmount emMinutos = Duration.ofMinutes(duracaoTarefa);
        int quantidade = 0;

        for (Tarefa i : listaTarefas) {
            if (hora.plus(emMinutos).isBefore(i.getHora()) || hora.isAfter(i.getHora().plus(Duration.ofMinutes(i.getDuracaoTarefa())))) {
                quantidade++;
            }
        }

        if (listaTarefas.size() == quantidade) {
            return true;
        }

        return  false;
    }


}
