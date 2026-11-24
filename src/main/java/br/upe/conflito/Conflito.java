package br.upe.conflito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;
import br.upe.model.Tarefa;

public class Conflito {
    public static boolean detectarConflito(Integer duracaoTarefa, LocalTime hora, List<Tarefa> listaTarefas, LocalDate data) {
        TemporalAmount emMinutos = Duration.ofMinutes(duracaoTarefa);
        int quantidade = 0;

        for (Tarefa i : listaTarefas) {
            if (!data.isEqual(i.getData()) || hora.plus(emMinutos).isBefore(i.getHora())  || hora.isAfter(i.getHora().plus(Duration.ofMinutes(i.getDuracaoTarefa())))) {
                quantidade++;
            }
        }

        if (listaTarefas.size() == quantidade) {
            return false;
        }

        return  true;
    }


}
