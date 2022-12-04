package br.unifei.imc.facade;

import android.content.Context;

import br.unifei.imc.fabricas.*;
import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoMultiplataforma;
import br.unifei.imc.jogos.JogoPc;
import br.unifei.imc.jogos.JogoPlay5;
import br.unifei.imc.jogos.JogoXbox;

import java.util.Objects;

public class Facade {
    private String nome;
    private Double valor;
    private String descricao;
    private String fabricante;
    private int qtd;
    private FabricaDeJogo fab;

    public Facade(String nome, Double valor, String descricao, String fabricante, int qtd) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.qtd = qtd;
    }


    public void registrar(String plataforma, Context context){


        // Fazer if no ANDROID STUDIO para saber qual é a plataforma do jogo
        if (Objects.equals(plataforma, "Pc")) {
            fab = new FabricaJogosPc();
            JogoPc jg = (JogoPc) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.registra(context);

        } else if (Objects.equals(plataforma, "Multiplataforma")) {
            fab = new FabMultiplataforma();
            JogoMultiplataforma jg = (JogoMultiplataforma) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.registra(context);

        } else if (Objects.equals(plataforma, "Xbox")) {
            fab = new FabricaJogosXbox();
            JogoXbox jg = (JogoXbox) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.registra(context);

        } else if (Objects.equals(plataforma, "Playstation")) {
            fab = new FabricaJogosPlay5();
            JogoPlay5 jg = (JogoPlay5) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.registra(context);
        }

    }

    public Jogo inicializa(String plataforma){

        if (Objects.equals(plataforma, "Pc")) {
            fab = new FabricaJogosPc();
            return fab.criaJogos(nome, valor, descricao, fabricante, qtd);

        } else if (Objects.equals(plataforma, "Multiplataforma")) {
            fab = new FabMultiplataforma();
            return fab.criaJogos(nome, valor, descricao, fabricante, qtd);

        } else if (Objects.equals(plataforma, "Xbox")) {
            fab = new FabricaJogosXbox();
            return fab.criaJogos(nome, valor, descricao, fabricante, qtd);

        } else if (Objects.equals(plataforma, "Playstation")) {
            fab = new FabricaJogosPlay5();
            return fab.criaJogos(nome, valor, descricao, fabricante, qtd);
        }
        return null;
    }

    public void atualiza( String plataforma, Context context){
        if (Objects.equals(plataforma, "Pc")) {
            fab = new FabricaJogosPc();
            JogoPc jg = (JogoPc) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.atualiza(context);

        } else if (Objects.equals(plataforma, "Multiplataforma")) {
            fab = new FabMultiplataforma();
            JogoMultiplataforma jg = (JogoMultiplataforma) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.atualiza(context);

        } else if (Objects.equals(plataforma, "Xbox")) {
            fab = new FabricaJogosXbox();
            JogoXbox jg = (JogoXbox) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.atualiza(context);

        } else if (Objects.equals(plataforma, "Playstation")) {
            fab = new FabricaJogosPlay5();
            JogoPlay5 jg = (JogoPlay5) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.atualiza(context);
        }
    }

    public void deleta( String plataforma, Context context){
        if (Objects.equals(plataforma, "Pc")) {
            fab = new FabricaJogosPc();
            JogoPc jg = (JogoPc) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.deleta(context);

        } else if (Objects.equals(plataforma, "Multiplataforma")) {
            fab = new FabMultiplataforma();
            JogoMultiplataforma jg = (JogoMultiplataforma) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.deleta(context);

        } else if (Objects.equals(plataforma, "Xbox")) {
            fab = new FabricaJogosXbox();
            JogoXbox jg = (JogoXbox) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.deleta(context);

        } else if (Objects.equals(plataforma, "Playstation")) {
            fab = new FabricaJogosPlay5();
            JogoPlay5 jg = (JogoPlay5) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            jg.deleta(context);
        }
    }
}
