package br.unifei.imc.fabricas;

import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoXbox;


public class FabricaJogosXbox extends FabricaDeJogo{
    @Override
    public Jogo criaJogos(String nome, Double valor, String desc, String fabricante, int qtd) {
        return new JogoXbox(nome, valor, desc, fabricante, qtd);
    }
}
