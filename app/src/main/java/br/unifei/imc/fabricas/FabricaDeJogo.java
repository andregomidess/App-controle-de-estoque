package br.unifei.imc.fabricas;

import br.unifei.imc.jogos.Jogo;

public abstract class FabricaDeJogo {

    public abstract Jogo criaJogos(String nome, Double valor, String desc, String fabricante, int qtd);

}
