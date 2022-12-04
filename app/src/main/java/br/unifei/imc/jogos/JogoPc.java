package br.unifei.imc.jogos;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.unifei.imc.DAO.GamesDAO;

public class JogoPc implements Jogo, Serializable {

    private String nome;
    private Double valor;
    private String descricao;
    private String fabricante;
    private int qtd;
    private GamesDAO gamesDAO;

    public JogoPc(String nome, Double valor, String descricao, String fabricante, int qtd) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.qtd = qtd;
    }

    public JogoPc() {
    }

    @Override
    public void registra(Context context) {
        gamesDAO = new GamesDAO(context);
        gamesDAO.salvar(this, "Pc");
    }

    @Override
    public void atualiza(Context context) {
        gamesDAO = new GamesDAO(context);
        if(gamesDAO.atualizar(this, "Pc")){
            Toast.makeText(context,
                    "Sucesso ao atualizar Jogo",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context,
                    "Erro ao atualizar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleta(Context context) {
        gamesDAO = new GamesDAO(context);
        if(gamesDAO.deletar(this, "Pc")){
            Toast.makeText(context,
                    "Jogo excluido com sucesso",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context,
                    "Erro ao excluir o jogo",
                    Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    @Override
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
