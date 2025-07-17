package br.senac.rj.catalogo.janelas;

import br.senac.rj.catalogo.AppCatalogoMusical;
import br.senac.rj.catalogo.modelo.Artista;
import javax.swing.*;
import java.awt.*;

public class JanelaArtista extends JFrame {
    // Componentes da UI como campos da classe
    private JTextField jTextId;
    private JTextField jTextNome;
    private JTextField jTextNacionalidade;
    private JTextField jTextGeneroMusical;
    private JTextField jTextGenero;
    private JButton botaoConsultar;
    private JButton botaoCadastrar;
    private JButton botaoAtualizar;
    private JButton botaoExcluir;

    public JanelaArtista() {
        setTitle("Gerenciamento de Artistas");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 350);

        Container caixa = getContentPane();
        caixa.setLayout(null);

        // Labels e Campos de Texto
        add(new JLabel("ID:")).setBounds(30, 30, 150, 20);
        jTextId = new JTextField();
        jTextId.setBounds(200, 30, 50, 20);
        add(jTextId);

        add(new JLabel("Nome:")).setBounds(30, 60, 100, 20);
        jTextNome = new JTextField();
        jTextNome.setBounds(200, 60, 200, 20);
        add(jTextNome);
        
        add(new JLabel("Nacionalidade:")).setBounds(30, 90, 100, 20);
        jTextNacionalidade = new JTextField();
        jTextNacionalidade.setBounds(200, 90, 200, 20);
        add(jTextNacionalidade);

        add(new JLabel("Gênero Musical:")).setBounds(30, 120, 150, 20);
        jTextGeneroMusical = new JTextField();
        jTextGeneroMusical.setBounds(200, 120, 200, 20);
        add(jTextGeneroMusical);

        add(new JLabel("Gênero:")).setBounds(30, 150, 150, 20);
        jTextGenero = new JTextField();
        jTextGenero.setBounds(200, 150, 200, 20);
        add(jTextGenero);

        // Botões
        botaoConsultar = new JButton("Consultar");
        botaoCadastrar = new JButton("Cadastrar");
        botaoAtualizar = new JButton("Atualizar");
        botaoExcluir = new JButton("Excluir");
        JButton botaoLimpar = new JButton("Limpar");

        botaoConsultar.setBounds(280, 30, 100, 20);
        botaoCadastrar.setBounds(30, 220, 100, 30);
        botaoAtualizar.setBounds(140, 220, 100, 30);
        botaoExcluir.setBounds(250, 220, 100, 30);
        botaoLimpar.setBounds(360, 220, 100, 30);
        
        add(botaoConsultar);
        add(botaoCadastrar);
        add(botaoAtualizar);
        add(botaoExcluir);
        add(botaoLimpar);

        Artista artista = new Artista();
        limparTudo(); // Estado inicial

        // Action Listeners
        botaoConsultar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(jTextId.getText());
                if (artista.consultarArtista(id)) {
                    jTextNome.setText(artista.getNome());
                    jTextNacionalidade.setText(artista.getNacionalidade());
                    jTextGeneroMusical.setText(artista.getGeneroMusical());
                    jTextGenero.setText(artista.getGenero());
                    configurarBotoes(false, true, true, true);
                    habilitarCampos(true);
                    jTextId.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Artista não encontrado. Preencha os dados para cadastrar.");
                    limparCampos();
                    configurarBotoes(true, false, false, true);
                    habilitarCampos(true);
                }
                botaoConsultar.setEnabled(false);
                jTextNome.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.");
            }
        });

        botaoCadastrar.addActionListener(e -> {
            if (validarCampos()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Confirmar cadastro deste artista?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(jTextId.getText());
                    if (artista.cadastrarArtista(id, jTextNome.getText(), jTextNacionalidade.getText(), jTextGeneroMusical.getText(), jTextGenero.getText())) {
                        JOptionPane.showMessageDialog(this, "Artista cadastrado com sucesso!");
                        
                        AppCatalogoMusical.atualizarTabelaArtista();
                        
                        dispose();
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao cadastrar artista. Verifique se o ID já existe.");
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            if (validarCampos()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Confirmar atualização deste artista?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(jTextId.getText());
                    if (artista.atualizarArtista(id, jTextNome.getText(), jTextNacionalidade.getText(), jTextGeneroMusical.getText(), jTextGenero.getText())) {
                        JOptionPane.showMessageDialog(this, "Artista atualizado com sucesso!");
                        
                        AppCatalogoMusical.atualizarTabelaArtista();
                        
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao atualizar artista.");
                    }
                }
            }
        });

        botaoExcluir.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirmar exclusão deste artista?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(jTextId.getText());
                if (artista.excluirArtista(id)) {
                    JOptionPane.showMessageDialog(this, "Artista excluído com sucesso!");
                    
                    AppCatalogoMusical.atualizarTabelaArtista();
                    
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao excluir artista.");
                }
            }
        });

        botaoLimpar.addActionListener(e -> limparTudo());
    }
    
    public void preencherEabrir(String id, String nome, String nacionalidade, String generoMusical, String genero) {
        limparTudo();
        jTextId.setText(id);
        jTextNome.setText(nome);
        jTextNacionalidade.setText(nacionalidade);
        jTextGeneroMusical.setText(generoMusical);
        jTextGenero.setText(genero);

        jTextId.setEnabled(false);
        botaoConsultar.setEnabled(false);
        configurarBotoes(false, true, true, true);
        habilitarCampos(true);
        setVisible(true);
    }
    
    private void limparCampos() {
        jTextNome.setText("");
        jTextNacionalidade.setText("");
        jTextGeneroMusical.setText("");
        jTextGenero.setText("");
    }

    private void habilitarCampos(boolean habilitar) {
        jTextNome.setEnabled(habilitar);
        jTextNacionalidade.setEnabled(habilitar);
        jTextGeneroMusical.setEnabled(habilitar);
        jTextGenero.setEnabled(habilitar);
    }
    
    private void configurarBotoes(boolean cadastrar, boolean atualizar, boolean excluir, boolean limpar) {
        botaoCadastrar.setEnabled(cadastrar);
        botaoAtualizar.setEnabled(atualizar);
        botaoExcluir.setEnabled(excluir);
    }

    public void limparTudo() {
        jTextId.setText("");
        limparCampos();
        jTextId.setEnabled(true);
        habilitarCampos(false);
        botaoConsultar.setEnabled(true);
        configurarBotoes(false, false, false, true);
        jTextId.requestFocus();
    }

    private boolean validarCampos() {
        // Validações futuras
        return true;
    }
}

