package br.unifei.imc.fabricas;

import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoPc;

public class FabricaJogosPc extends FabricaDeJogo{
    @Override
    public Jogo criaJogos(String nome, Double valor, String desc, String fabricante, int qtd) {
        return new JogoPc(nome, valor, desc, fabricante, qtd);
    }
}

