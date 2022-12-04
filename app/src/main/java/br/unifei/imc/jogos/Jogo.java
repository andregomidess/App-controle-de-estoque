package br.unifei.imc.jogos;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

public interface Jogo{
    

    //void jogo(String nome,Double valor,String descricao,String fabricante);

    void registra(Context context);
    void atualiza (Context context);
    void deleta (Context context);

    String getNome();

    Double getValor();

    String getDescricao();

    String getFabricante();

    int getQtd();

    void setNome(String nome);

    void setValor(Double valor);

    void setDescricao(String desc);

    void setFabricante(String fabricante);

    void setQtd(int qtd);
}


