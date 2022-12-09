package br.unifei.imc.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unifei.imc.jogos.Jogo;

public interface IGamesDAO {

    public boolean salvar (Jogo jogo, String plataforma);
    public boolean atualizar (Jogo jogo, String plataforma);
    public boolean deletar (Jogo jogo, String plataforma);
    public List<Jogo> consultar(String plataforma);
    public Jogo consultaVenda (String plataforma, String nomeJogo);
    public void atualizaQtdVendas(Map dict, String plataforma);
}
