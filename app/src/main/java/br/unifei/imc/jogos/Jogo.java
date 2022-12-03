package br.unifei.imc.jogos;

import android.content.Context;

import java.util.List;

public interface Jogo {
    

    //void jogo(String nome,Double valor,String descricao,String fabricante);

    void registra(Context context);

    String getNome();

    Double getValor();

    String getDescricao();

    String getFabricante();

    int getQtd();
}


