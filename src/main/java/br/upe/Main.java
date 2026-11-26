package br.upe;

import br.upe.notificacao.Notificar;
import br.upe.ui.TelaPrincipal;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {



        TelaPrincipal pnlMain = new TelaPrincipal();

        JFrame frame = new JFrame("ToDo");
        frame.setContentPane(pnlMain.getPnlMain());
        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Notificar.inicializar(frame);

    }
}