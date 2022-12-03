package br.unifei.imc.fabricas;

import br.unifei.imc.jogos.Jogo;
import br.unifei.imc.jogos.JogoMultiplataforma;

public class FabMultiplataforma extends FabricaDeJogo{
    @Override
    public Jogo criaJogos(String nome, Double valor, String desc, String fabricante, int qtd) {
        return new JogoMultiplataforma(nome, valor, desc, fabricante, qtd);
    }
}
