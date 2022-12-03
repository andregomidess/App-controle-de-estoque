package br.unifei.imc.arquivo;

import br.unifei.imc.jogos.Games;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LerArquivo {

    public static List<Games> readProdutoCsv(String csv){
        Path arquivo = Paths.get(csv);
        List<Games> jogoList = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(arquivo);
            CsvToBean<Games> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Games.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            jogoList = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogoList;
    }
}
