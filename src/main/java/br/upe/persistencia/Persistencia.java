package br.upe.persistencia;

import br.upe.model.Tarefa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static void salvar(List<Tarefa> tarefas) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter("tarefas.txt"));

            for (Tarefa t : tarefas) {
                String descricao = t.getDescricao().replace(";", ",");

                LocalDate data = t.getData();
                if (data == null) {
                    data = null ;
                }
                String hora = t.getHora();
                String finalizada = "false";
                if (t.isFinalizada() != null && t.isFinalizada()) {
                    finalizada = "true";
                }

                escritor.println(descricao + ";" + hora + ";" + data + ";" + t.getOrdem() + ";" + finalizada);
            }

            escritor.close();
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

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
            String linha = leitor.readLine();

            while (linha != null) {
                String[] partes = linha.split(";", -1);
                LocalDate data = LocalDate.parse(partes[2]);
                if (partes.length == 4) {
                    Tarefa t = new Tarefa(partes[0], Integer.parseInt(partes[3]), data ,  partes[1]);
                    t.setFinalizada(partes[3].equals("true"));
                    lista.add(t);
                }

                linha = leitor.readLine();
            }

            leitor.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }

        return lista;
    }
}