package br.unifei.imc.vendas;

import java.util.List;

public class Caixas implements JogoVendido {

//    Composite
    private List<JogoVendido> jogos;

    public Caixas(List<JogoVendido> jogos){
        this.jogos = jogos;
    }

    public void addJogo(JogoVendido jogo){
        this.jogos.add(jogo);
    }


    @Override
    public double calculaPrecoFinal() {
        double precoFinal = 0;
        for(JogoVendido j : jogos){
            precoFinal += j.calculaPrecoFinal();
        }
        return precoFinal;
    }
}
