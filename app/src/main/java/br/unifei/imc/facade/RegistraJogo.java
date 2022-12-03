package br.unifei.imc.facade;

import android.content.Context;

import br.unifei.imc.DAO.GamesDAO;
import br.unifei.imc.arquivo.CriaArquivo;
import br.unifei.imc.arquivo.SalvaArquivo;
import br.unifei.imc.fabricas.*;
import br.unifei.imc.jogos.Games;
import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoMultiplataforma;
import br.unifei.imc.jogos.JogoPc;
import br.unifei.imc.jogos.JogoPlay5;
import br.unifei.imc.jogos.JogoXbox;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RegistraJogo {
    private String nome;
    private Double valor;
    private String descricao;
    private String fabricante;
    private int qtd;
    private FabricaDeJogo fab;

    public RegistraJogo(String nome, Double valor, String descricao, String fabricante, int qtd) {
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

    public Jogo atualiza(String plataforma, Context context){
        if (Objects.equals(plataforma, "Pc")) {
            fab = new FabricaJogosPc();
            return (JogoPc) fab.criaJogos(nome, valor, descricao, fabricante, qtd);

        } else if (Objects.equals(plataforma, "Multiplataforma")) {
            fab = new FabMultiplataforma();
            JogoMultiplataforma jg = (JogoMultiplataforma) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            return jg;

        } else if (Objects.equals(plataforma, "Xbox")) {
            fab = new FabricaJogosXbox();
            JogoXbox jg = (JogoXbox) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            return jg;

        } else if (Objects.equals(plataforma, "Playstation")) {
            fab = new FabricaJogosPlay5();
            JogoPlay5 jg = (JogoPlay5) fab.criaJogos(nome, valor, descricao, fabricante, qtd);
            return jg;
        }
        return null;
    }
}
