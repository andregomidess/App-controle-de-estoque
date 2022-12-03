package br.unifei.imc.fabricas;

import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoPlay5;

public class FabricaJogosPlay5 extends FabricaDeJogo{
    @Override
    public Jogo criaJogos(String nome, Double valor, String desc, String fabricante, int qtd) {
        return new JogoPlay5(nome, valor, desc, fabricante, qtd);
    }
}
