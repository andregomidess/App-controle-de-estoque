package br.unifei.imc.vendas;

import br.unifei.imc.jogos.Jogo;

import java.util.List;

public class VendaUnitaria implements JogoVendido{

//    Composite

    private List<Jogo> jogos;
    private double preco=0;

    public VendaUnitaria(List<Jogo> jogos){
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
