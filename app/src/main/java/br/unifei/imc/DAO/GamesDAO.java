package br.unifei.imc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.unifei.imc.jogos.Games;
import br.unifei.imc.jogos.Jogo;

public class GamesDAO implements IGamesDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public GamesDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Jogo jogo, String plataforma) {

        ContentValues cv = new ContentValues();
        cv.put("plataforma", plataforma);
        cv.put("nome", jogo.getNome());
        cv.put("valor", jogo.getValor());
        cv.put("descricao", jogo.getDescricao());
        cv.put("fabricante", jogo.getFabricante());
        cv.put("qtd", jogo.getQtd());

        try {
            escreve.insert(DbHelper.TABELA_JOGOS, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar a tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Jogo jogo, String plataforma) {
        ContentValues cv = new ContentValues();
        cv.put("plataforma", plataforma);
        cv.put("nome", jogo.getNome());
        cv.put("valor", jogo.getValor());
        cv.put("descricao", jogo.getDescricao());
        cv.put("fabricante", jogo.getFabricante());
        cv.put("qtd", jogo.getQtd());

        try {
            String[] args = {jogo.getNome(), plataforma};
            escreve.update(DbHelper.TABELA_JOGOS, cv, "nome=? AND plataforma=?", args);
            Log.i("INFO", "Jogo atualizada com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar a Jogo " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Jogo jogo, String plataforma) {
        return false;
    }

    @Override
    public List<Games> consultar(String plat) {

        List<Games> jogos = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_JOGOS + " WHERE plataforma = " + "'" + plat + "'" + " ;";
        Cursor c = le.rawQuery(sql, null);
        while (c.moveToNext()){
            Games jogo = new Games();
            String nome = c.getString( c.getColumnIndex("nome"));
            Double valor = c.getDouble( c.getColumnIndex("valor"));
            String desc = c.getString( c.getColumnIndex("descricao"));
            String fabricante = c.getString( c.getColumnIndex("fabricante"));
            int qtd = c.getInt( c.getColumnIndex("qtd"));

            jogo.setNome(nome);
            jogo.setValor(valor);
            jogo.setDescricao(desc);
            jogo.setFabricante(fabricante);
            jogo.setQtd(qtd);

            jogos.add(jogo);

        }

        return jogos;
    }
}
