package br.unifei.imc.DAO;

import java.util.List;

import br.unifei.imc.jogos.Games;
import br.unifei.imc.jogos.Jogo;

public interface IGamesDAO {

    public boolean salvar (Jogo jogo, String plataforma);
    public boolean atualizar (Jogo jogo, String plataforma);
    public boolean deletar (Jogo jogo, String plataforma);
    public List<Jogo> consultar(String plataforma);
}
