package br.unifei.imc.jogos;

import android.content.Context;

import java.util.List;

import br.unifei.imc.DAO.GamesDAO;

public class JogoXbox implements Jogo{

    private String nome;
    private Double valor;
    private String descricao;
    private String fabricante;
    private int qtd;

    public JogoXbox(String nome, Double valor, String descricao, String fabricante, int qtd) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.qtd = qtd;
    }

    @Override
    public void registra(Context context) {
        GamesDAO gamesDAO = new GamesDAO(context);
        gamesDAO.salvar(this, "Xbox");
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Double getValor() {
        return valor;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getFabricante() {
        return fabricante;
    }

    @Override
    public int getQtd() {
        return qtd;
    }
}
