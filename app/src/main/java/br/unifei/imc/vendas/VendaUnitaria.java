package br.unifei.imc.vendas;
import br.unifei.imc.jogos.Jogo;


public class VendaUnitaria implements JogoVendido{

//    Composite

    private Jogo jogo;
    private double preco=0;

    public VendaUnitaria(Jogo jogo){
        this.jogo = jogo;
    }


    @Override
    public double calculaPrecoFinal() {

        return jogo.getValor();

    }
}
