package br.unifei.imc.arquivo;

import com.opencsv.CSVWriter;
import br.unifei.imc.jogos.Games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CriaArquivo {

    public void criaArquivo(String csv){
        boolean exists = (new File(csv)).exists();

        try{
            if (exists){
                // faz nd
            } else{
                FileWriter arquivo;
                arquivo = new FileWriter(csv);
                CSVWriter writer = new CSVWriter(arquivo);
                String[] cabecalho = {"Nome", "Valor", "Descricao", "Fabricante"};
                writer.writeNext(cabecalho);
                writer.close();
            }
    }catch (
    IOException e){
        e.printStackTrace();
    }
        }

}
