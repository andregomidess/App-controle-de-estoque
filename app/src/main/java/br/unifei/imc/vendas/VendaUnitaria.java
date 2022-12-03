package br.unifei.imc.vendas;

import br.unifei.imc.jogos.Games;

import java.util.List;

public class VendaUnitaria implements JogoVendido{

//    Composite

    private List<Games> jogos;
    private double preco=0;

    public VendaUnitaria(List<Games> jogos){
        this.jogos = jogos;
    }


    @Override
    public double calculaPrecoFinal() {

        jogos.forEach(p->{
            preco += p.getValor();
        });

        return preco;
    }
}
