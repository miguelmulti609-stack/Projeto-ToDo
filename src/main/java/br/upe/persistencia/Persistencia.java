
package br.upe.persistencia;

import br.upe.model.Tarefa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static void salvar(List<Tarefa> tarefas) {
        try (BufferedWriter escritor =
                     new BufferedWriter(new FileWriter("tarefas.txt"))) {

            for (Tarefa t : tarefas) {

                String descricao = t.getDescricao().replace(";", ",");
                String hora = t.getHora() == null ? "" : t.getHora().toString();
                String data = t.getData() == null ? "" : t.getData().toString();

                String finalizada = Boolean.TRUE.equals(t.isFinalizada())
                        ? "true"
                        : "false";

                escritor.write(
                        descricao + ";" +
                                hora + ";" +
                                data + ";" +
                                t.getOrdem() + ";" +
                                finalizada
                );

                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static List<Tarefa> carregar() {
        List<Tarefa> lista = new ArrayList<>();

        File arquivo = new File("tarefas.txt");

        if (!arquivo.exists()) {
            return lista;
        }

        try (BufferedReader leitor =
                     new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {

                String[] partes = linha.split(";", -1);

                if (partes.length != 5) {
                    continue;
                }

                try {
                    String descricao = partes[0];
                    LocalTime hora = LocalTime.parse(partes[1]);

                    LocalDate data = partes[2].isEmpty()
                            ? null
                            : LocalDate.parse(partes[2]);

                    int ordem = Integer.parseInt(partes[3]);

                    boolean finalizada = Boolean.parseBoolean(partes[4]);

                    Tarefa t = new Tarefa(
                            descricao,
                            ordem,
                            data,
                            hora
                    );

                    t.setFinalizada(finalizada);

                    lista.add(t);

                } catch (RuntimeException e) {
                    System.out.println(
                            "Erro ao interpretar tarefa: " + linha
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }

        return lista;
    }
}
