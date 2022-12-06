package br.unifei.imc.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_JOGOS";
    public static String TABELA_JOGOS = "jogos";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_JOGOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " plataforma TEXT NOT NULL, nome TEXT NOT NULL UNIQUE, valor REAL NOT NULL, descricao TEXT," +
                " fabricante TEXT NOT NULL, qtd INTEGER ); ";

        try{
            db.execSQL(sql);
        }catch (Exception e){
            Log.i("INFO DB", "Ero ao criar a tabela " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
