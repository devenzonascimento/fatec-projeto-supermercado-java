package Tela.Utilitarios;

import javax.swing.*;

public class MostrarPopUp {
    public void mensagemDeSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
