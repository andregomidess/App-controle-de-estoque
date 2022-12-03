package br.unifei.imc.arquivo;

import br.unifei.imc.jogos.Games;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SalvaArquivo {

    public void salvaJogo(Games jogo, String csv) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        boolean exists = (new File(csv)).exists();
        if (exists) {
            List<Games> listaAtual = LerArquivo.readProdutoCsv(csv);
            listaAtual.add(jogo);
            Writer writer = Files.newBufferedWriter(Paths.get(csv));
            StatefulBeanToCsv<Games> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(listaAtual);
            writer.flush();
            writer.close();
        }

    }
}
