package br.upe.persistencia;

import br.upe.model.Tarefa;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persistencia {


    public static void salvar(List<Tarefa> tarefas) {
        try {
            PrintWriter escritor = new PrintWriter("tarefas.txt");

            for (Tarefa t : tarefas) {

                String descricao = t.getDescricao().replace(";", ",");

                String hora = "";
                if (t.getHora() != null) {
                    hora = t.getHora().toString();
                }

                String data = "";
                if (t.getData() != null) {
                    data = t.getData().toString();

                }

                boolean finalizada = Boolean.TRUE.equals(t.isFinalizada());

                escritor.println(descricao + ";" + hora + ";" + data + ";"
                        + t.getOrdem() + ";" + finalizada + ";" + t.getDuracaoTarefa());
            }

            escritor.close();
        }
        catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }

    }


    public static List<Tarefa> carregar() {
        List<Tarefa> lista = new ArrayList<>();

        File arquivo = new File("tarefas.txt");
        if (!arquivo.exists()) {
            return lista;
        }

        try {
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split(";", -1);

                if (partes.length == 6) {
                    try {
                        String descricao = partes[0];
                        LocalTime hora = LocalTime.parse(partes[1]);

                        LocalDate data = null;
                        if (!partes[2].isEmpty()) {
                            data = LocalDate.parse(partes[2]);
                        }

                        int ordem = Integer.parseInt(partes[3]);
                        boolean finalizada = Boolean.parseBoolean(partes[4]);
                        int duracao = Integer.parseInt(partes[5]);

                        Tarefa t = new Tarefa(descricao, ordem, data, hora, duracao);
                        t.setFinalizada(finalizada);
                        lista.add(t);
                    }
                    catch (Exception e) {
                        System.out.println("Linha invalida ignorada: " + linha);

                    }

                }
            }

        }
        catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }

        return lista;
    }
}