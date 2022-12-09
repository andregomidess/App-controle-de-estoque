package br.unifei.imc.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unifei.imc.facade.Facade;
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

        try {
            String[] args = {jogo.getNome(), plataforma};
            escreve.delete(DbHelper.TABELA_JOGOS, "nome=? AND plataforma=?", args);
            Log.i("INFO", "Jogo remover com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover a Jogo " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Jogo> consultar(String plat) {

        List<Jogo> jogos = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_JOGOS + " WHERE plataforma = " + "'" + plat + "'" + " ;";
        Cursor c = le.rawQuery(sql, null);
        while (c.moveToNext()){
            @SuppressLint("Range") String nome = c.getString( c.getColumnIndex("nome"));
            @SuppressLint("Range") Double valor = c.getDouble( c.getColumnIndex("valor"));
            @SuppressLint("Range") String desc = c.getString( c.getColumnIndex("descricao"));
            @SuppressLint("Range") String fabricante = c.getString( c.getColumnIndex("fabricante"));
            @SuppressLint("Range") int qtd = c.getInt( c.getColumnIndex("qtd"));
            Facade facade = new Facade(nome, valor, desc, fabricante, qtd);
            Jogo jogo = facade.inicializa(plat);

            jogo.setNome(nome);
            jogo.setValor(valor);
            jogo.setDescricao(desc);
            jogo.setFabricante(fabricante);
            jogo.setQtd(qtd);

            jogos.add(jogo);

        }

        return jogos;
    }

    @Override
    public Jogo consultaVenda(String plataforma, String nomeJogo) {

        String sql = "SELECT * FROM " + DbHelper.TABELA_JOGOS + " WHERE plataforma = "
                + "'" + plataforma + "'" + " AND nome = " + "'" + nomeJogo + "'" + " ;";
        Cursor c = le.rawQuery(sql, null);
        Jogo jogo = null;
        while (c.moveToNext()) {
            @SuppressLint("Range") String nome = c.getString(c.getColumnIndex("nome"));
            @SuppressLint("Range") Double valor = c.getDouble(c.getColumnIndex("valor"));
            @SuppressLint("Range") String desc = c.getString(c.getColumnIndex("descricao"));
            @SuppressLint("Range") String fabricante = c.getString(c.getColumnIndex("fabricante"));
            @SuppressLint("Range") int qtd = c.getInt(c.getColumnIndex("qtd"));
            Facade facade = new Facade(nome, valor, desc, fabricante, qtd);
            jogo = facade.inicializa(plataforma);

            jogo.setNome(nome);
            jogo.setValor(valor);
            jogo.setDescricao(desc);
            jogo.setFabricante(fabricante);
            jogo.setQtd(qtd);

        }

        return jogo;
    }

    @Override
    public void atualizaQtdVendas(Map dict, String plataforma) {
        ContentValues cv = new ContentValues();
        dict.forEach((key, value) -> {
            if ((Integer)value <= 0){
                try {
                    String[] args = {(String) key, plataforma};
                    escreve.delete(DbHelper.TABELA_JOGOS, "nome=? AND plataforma=?", args);
                    Log.i("INFO", "Jogo remover com sucesso");
                }catch (Exception e){
                    Log.e("INFO", "Erro ao remover a Jogo " + e.getMessage());
                }
            } else {
                cv.put("nome", (String) key);
                cv.put("qtd", (Integer) value);
                try {
                    String[] args = {(String) key, plataforma};
                    escreve.update(DbHelper.TABELA_JOGOS, cv, "nome=? AND plataforma=?", args);
                    Log.i("INFO", "Jogo atualizada com sucesso");
                }catch (Exception e){
                    Log.e("INFO", "Erro ao atualizar a Jogo " + e.getMessage());
                }}

        });
    }
}
