package br.upe.notificacao;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Notificar {

    private static SystemTray tray;
    private static TrayIcon image;

    public static void inicializar(JFrame frame) throws IOException {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();

            URL caminho = Notificar.class.getResource("/imagens/icone.png");

            if (caminho == null) {
                throw new IllegalArgumentException("Imagem não encontrada!");
            }

            Image imagem = ImageIO.read(caminho);

            if (imagem == null) {
                throw new IllegalArgumentException("Não foi possível interpretar a imagem!");
            }

            image = new TrayIcon(imagem, "ToDo");
            image.setImageAutoSize(true);



            image.addActionListener(e -> {
                frame.setVisible(true);
                frame.setState(JFrame.NORMAL);
            });


            try {
                tray.add(image);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public static void notificacao(String titulo, String mensagem) {
            if (image != null) {
                image.displayMessage(
                        titulo,
                        mensagem,
                        TrayIcon.MessageType.INFO
                );
            }
        }
    }
