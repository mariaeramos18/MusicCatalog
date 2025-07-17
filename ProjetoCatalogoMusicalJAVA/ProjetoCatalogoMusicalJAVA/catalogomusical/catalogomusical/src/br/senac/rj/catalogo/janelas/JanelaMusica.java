package br.senac.rj.catalogo.janelas;

import br.senac.rj.catalogo.AppCatalogoMusical;
import br.senac.rj.catalogo.modelo.Musica;
import javax.swing.*;
import java.awt.*;

public class JanelaMusica extends JFrame {
    // Componentes da UI como campos da classe
    private JTextField jTextId;
    private JTextField jTextTitulo;
    private JTextField jTextDuracao;
    private JTextField jTextAlbum;
    private JTextField jTextArtista;
    private JButton botaoConsultar;
    private JButton botaoCadastrar;
    private JButton botaoAtualizar;
    private JButton botaoExcluir;

    public JanelaMusica() {
        setTitle("Gerenciamento de Musicas");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        
        Container caixa = getContentPane();
        caixa.setLayout(null);

        // Labels
        JLabel labelId = new JLabel("ID:");
        JLabel labelTitulo = new JLabel("Titulo:");
        JLabel labelDuracao = new JLabel("Duração:");
        JLabel labelAlbum = new JLabel("Álbum:");
        JLabel labelArtista = new JLabel("Artista:");

        labelId.setBounds(30, 30, 150, 20);
        labelTitulo.setBounds(30, 60, 100, 20);
        labelDuracao.setBounds(30, 90, 100, 20);
        labelAlbum.setBounds(30, 120, 150, 20);
        labelArtista.setBounds(30, 150, 150, 20);

        // Campos de texto
        jTextId = new JTextField();
        jTextTitulo = new JTextField();
        jTextDuracao = new JTextField();
        jTextAlbum = new JTextField();
        jTextArtista = new JTextField();

        jTextId.setBounds(200, 30, 50, 20);
        jTextTitulo.setBounds(200, 60, 200, 20);
        jTextDuracao.setBounds(200, 90, 200, 20);
        jTextAlbum.setBounds(200, 120, 200, 20);
        jTextArtista.setBounds(200, 150, 200, 20);

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

        // Adiciona componentes
        add(labelId);
        add(labelTitulo);
        add(labelDuracao);
        add(labelAlbum);
        add(labelArtista);
        add(jTextId);
        add(jTextTitulo);
        add(jTextDuracao);
        add(jTextAlbum);
        add(jTextArtista);
        add(botaoConsultar);
        add(botaoCadastrar);
        add(botaoAtualizar);
        add(botaoExcluir);
        add(botaoLimpar);

        // Objeto musica
        Musica musica = new Musica();
        limparTudo(); // Define o estado inicial

        // Action Listeners
        botaoConsultar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(jTextId.getText());
                if (musica.consultarMusica(id)) {
                    jTextTitulo.setText(musica.getTitulo());
                    jTextDuracao.setText(musica.getDuracao());
                    jTextAlbum.setText(musica.getAlbum());
                    jTextArtista.setText(musica.getArtista());
                    configurarBotoes(false, true, true, true);
                    habilitarCampos(true);
                    jTextId.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Música não encontrada. Preencha os dados para cadastrar.");
                    configurarBotoes(true, false, false, true);
                    habilitarCampos(true);
                }
                botaoConsultar.setEnabled(false);
                jTextTitulo.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.");
            }
        });

        botaoCadastrar.addActionListener(e -> {
            if (validarCampos()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Confirmar cadastro desta música?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(jTextId.getText());
                    if (musica.cadastrarMusica(id, jTextTitulo.getText(), jTextDuracao.getText(), jTextAlbum.getText(), jTextArtista.getText())) {
                        JOptionPane.showMessageDialog(this, "Música cadastrada com sucesso!");
                        
                        AppCatalogoMusical.atualizarTabelaMusica();
                        
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao cadastrar música. Verifique se o ID já existe.");
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            if (validarCampos()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Confirmar atualização desta música?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(jTextId.getText());
                    if (musica.atualizarMusica(id, jTextTitulo.getText(), jTextDuracao.getText(), jTextAlbum.getText(), jTextArtista.getText())) {
                        JOptionPane.showMessageDialog(this, "Música atualizada com sucesso!");

                        AppCatalogoMusical.atualizarTabelaMusica();
                        
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao atualizar música.");
                    }
                }
            }
        });

        botaoExcluir.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirmar exclusão desta música?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(jTextId.getText());
                if (musica.excluirMusica(id)) {
                    JOptionPane.showMessageDialog(this, "Música excluída com sucesso!");

                    AppCatalogoMusical.atualizarTabelaMusica();
                    
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao excluir música.");
                }
            }
        });

        botaoLimpar.addActionListener(e -> limparTudo());
    }

    // Método para preencher os campos com dados da tabela e exibir a janela
    public void preencherEabrir(String id, String titulo, String duracao, String album, String artista) {
        limparTudo();
        jTextId.setText(id);
        jTextTitulo.setText(titulo);
        jTextDuracao.setText(duracao);
        jTextAlbum.setText(album);
        jTextArtista.setText(artista);
        
        jTextId.setEnabled(false);
        botaoConsultar.setEnabled(false);
        configurarBotoes(false, true, true, true);
        habilitarCampos(true);
        setVisible(true);
    }
    
    // Métodos auxiliares
    private void limparCampos() {
        jTextTitulo.setText("");
        jTextDuracao.setText("");
        jTextAlbum.setText("");
        jTextArtista.setText("");
    }

    private void habilitarCampos(boolean habilitar) {
        jTextTitulo.setEnabled(habilitar);
        jTextDuracao.setEnabled(habilitar);
        jTextAlbum.setEnabled(habilitar);
        jTextArtista.setEnabled(habilitar);
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