package Tela;

import DAO.FornecedorDAO;
import Controle.Endereco;
import Entidade.Fornecedor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaFornecedor {

    private JButton salvarBtn;
    private JTextField nomeTxt;
    private JTextField cnpjTxt;
    private JTextField telefoneTxt;
    private JTextField emailTxt;
    private JTextField logradouroTxt;
    private JTextField numeroTxt;
    private JTextField complementoTxt;
    private JTextField bairroTxt;
    private JTextField cidadeTxt;
    private JTextField ufTxt;
    private JTextField cepTxt;
    private JLabel erroMsg;


    public TelaFornecedor() {
        salvarBtn = new JButton();

        nomeTxt = new JTextField(30);
        cnpjTxt = new JTextField(30);
        telefoneTxt = new JTextField(30);
        emailTxt = new JTextField(30);
        logradouroTxt = new JTextField(30);
        numeroTxt = new JTextField(30);
        complementoTxt = new JTextField(30);
        bairroTxt = new JTextField(30);
        cidadeTxt = new JTextField(30);
        ufTxt = new JTextField(30);
        cepTxt = new JTextField(30);
        erroMsg = new JLabel();
    }

    public void mostrar() {
        JFrame frame = new JFrame("Cadastro de Fornecedor");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);


        JPanel painelCadastro = criarPainelCadastro();

        ListagemFornecedores listagemFornecedores = new ListagemFornecedores();

        JPanel listagem = listagemFornecedores.criarListagem();

        JTabbedPane abas = new JTabbedPane();

        abas.add("Fornecedores", listagem);

        abas.add("Cadastrar Fornecedor", painelCadastro);

        frame.add(abas);

        frame.setVisible(true);


    }

    private JPanel criarPainelCadastro() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        layout.gridx = 1;
        layout.weightx = 1.0;
        layout.insets = new Insets(0, 200, 0, 200);

        layout.gridy = 0;
        JPanel painelTitulo = criarPainelTitulo();
        panel.add(painelTitulo, layout);

        layout.gridy = 1;
        JPanel painelCadastroDadosBasicos = criarPainelCadastroDadosBasicos();
        panel.add(painelCadastroDadosBasicos, layout);

        layout.gridy = 2;
        JPanel painelCadastroDadosEndereco = criarPainelCadastroDadosEndereco();
        panel.add(painelCadastroDadosEndereco, layout);

        layout.gridy = 3;
        JPanel painelMensagemDeErro = criarPainelMensagemDeErro();
        panel.add(painelMensagemDeErro, layout);

        layout.gridy = 4;
        JPanel painelCadastroBotoes = criarPainelCadastroBotoes();
        panel.add(painelCadastroBotoes, layout);

        return panel;
    }

    private JPanel criarPainelTitulo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel tituloMsg = new JLabel("Cadastro de Fornecedor");

        tituloMsg.setFont(new Font("ARIAL", 0, 28));

        panel.add(tituloMsg);

        return panel;
    }

    private JPanel criarPainelCadastroDadosBasicos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados Cadastrais", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints layout = new GridBagConstraints();

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5, 5, 5, 5);
        layout.gridx = 0;
        layout.weightx = 0.1;

        // Campos para Nome, CNPJ, Telefone e Email
        layout.gridy = 0;
        panel.add(new JLabel("Nome:"), layout);

        layout.gridy = 1;
        panel.add(new JLabel("CNPJ:"), layout);

        layout.gridy = 2;
        panel.add(new JLabel("Telefone:"), layout);

        layout.gridy = 3;
        panel.add(new JLabel("Email:"), layout);

        layout.gridx = 1;
        layout.weightx = 1.0;

        layout.gridy = 0;
        panel.add(nomeTxt, layout);

        layout.gridy = 1;
        panel.add(cnpjTxt, layout);

        layout.gridy = 2;
        panel.add(telefoneTxt, layout);

        layout.gridy = 3;
        panel.add(emailTxt, layout);

        return panel;
    }

    private JPanel criarPainelCadastroDadosEndereco() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados de Endereço", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints layout = new GridBagConstraints();

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5, 5, 5, 5);
        layout.gridx = 0;
        layout.weightx = 0.1;
        // Campos para Nome, CNPJ, Telefone e Email

        layout.gridy = 0;
        panel.add(new JLabel("Logradouro:"), layout);

        layout.gridy = 1;
        panel.add(new JLabel("Numero:"), layout);

        layout.gridy = 2;
        panel.add(new JLabel("Complemento:"), layout);

        layout.gridy = 3;
        panel.add(new JLabel("Bairro:"), layout);

        layout.gridy = 4;
        panel.add(new JLabel("Cidade:"), layout);

        layout.gridy = 5;
        panel.add(new JLabel("UF:"), layout);

        layout.gridy = 6;
        panel.add(new JLabel("CEP:"), layout);

        layout.gridx = 1;
        layout.weightx = 1.0;

        layout.gridy = 0;
        panel.add(logradouroTxt, layout);

        layout.gridy = 1;
        panel.add(numeroTxt, layout);

        layout.gridy = 2;
        panel.add(complementoTxt, layout);

        layout.gridy = 3;
        panel.add(bairroTxt, layout);

        layout.gridy = 4;
        panel.add(cidadeTxt, layout);

        layout.gridy = 5;
        panel.add(ufTxt, layout);

        layout.gridy = 6;
        panel.add(cepTxt, layout);

        return panel;
    }

    private JPanel criarPainelCadastroBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        salvarBtn.setText("Salvar");

        salvarBtn.addActionListener(this::aoClicarEmSalvar);

        panel.add(salvarBtn);

        return panel;
    }

    private JPanel criarPainelMensagemDeErro() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        erroMsg.setBackground(Color.WHITE);

        erroMsg.setForeground(Color.RED);

        erroMsg.setVisible(false);

        panel.add(erroMsg);

        return panel;
    }

    private void aoClicarEmSalvar(ActionEvent event) {
        String nome = nomeTxt.getText();
        String cnpj = cnpjTxt.getText();
        String telefone = telefoneTxt.getText();
        String email = emailTxt.getText();
        String logradouro = logradouroTxt.getText();
        String numero = numeroTxt.getText();
        String complemento = complementoTxt.getText();
        String bairro = bairroTxt.getText();
        String cidade = cidadeTxt.getText();
        String uf = ufTxt.getText();
        String cep = cepTxt.getText();

        try {
            Endereco manipularEndereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);

            String endereco = manipularEndereco.converter();

            Fornecedor fornecedor = new Fornecedor(nome, cnpj, telefone, email, endereco);

            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            fornecedorDAO.inserir(fornecedor);
        } catch (Exception e) {
            String mensagemDeErro = e.getMessage();

            erroMsg.setText(mensagemDeErro);

            erroMsg.setVisible(true);
        }
    }
}
