package br.upe.gamificacao;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Gamificacao {

    private static int pontos = lerPontos();


    public static int getPontos() {
        return pontos;

    }

    public static void adicionarPontos(int quantidade) {
        pontos = pontos + quantidade;
        if (pontos < 0) {
            pontos = 0;

        }
        salvarPontos();
    }

    public static String getPatente(){
        if (pontos >= 400) {
            return "Diamante - Guardião do Tempo";
        }

        else if (pontos >= 200){
            return "Platina - Especialista em Organização";
        }
        else if (pontos >= 100){
            return "Ouro - Mestre da Rotina";
        }

        else if (pontos >= 50) {
            return "Prata - Organizador Disciplinado";
        }
        else {
            return "Bronze - Organizador Iniciante";

        }

    }

    private static void salvarPontos() {
        try {
            PrintWriter escritor = new PrintWriter("pontos.txt");
            escritor.println(pontos);
            escritor.close();
        }

        catch (Exception e) {
            System.out.println("Erro ao salvar os pontos");

        }
    }


    private static int lerPontos() {
        try {
            File arquivo = new File("pontos.txt");
            Scanner leitor = new Scanner(arquivo);
            int valor = leitor.nextInt();
            leitor.close();
            return valor;
        }
        catch (Exception e) {
            return 0;

        }


    }

}
