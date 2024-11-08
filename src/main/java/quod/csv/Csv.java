package quod.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import quod.dao.CsvDao;
import quod.dto.RegistroDto;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class Csv {

    private static final char DELIMITADOR_CSV = ';';

    private Map<Integer, Field> mapeamentoColunas = new HashMap<>();
    private List<String> colunasIgnoradas = new ArrayList<>();  // Armazena as colunas ignoradas

    public List<RegistroDto> leitorCsv(String caminhoArquivo) throws IOException, CsvValidationException {
        List<RegistroDto> registros = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(DELIMITADOR_CSV)
                .build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(caminhoArquivo))
                .withCSVParser(csvParser)
                .build()) {

            String[] cabecalho = csvReader.readNext();  // Lê a primeira linha (cabeçalho)
            construirMapeamentoDeColunas(cabecalho);    // Mapeia as colunas relevantes

            String[] linha;
            while ((linha = csvReader.readNext()) != null) {
                Long numeroLinha = csvReader.getLinesRead();

                CsvDao csvDao = mapearLinhaParaDao(linha, numeroLinha);
                if (csvDao.isValid()) {
                    RegistroDto registro = new RegistroDto(csvDao);
                    registros.add(registro);
                } else {
                    csvDao.checkEmptyFields();
                }
            }
        }
        return registros;
    }

    private void construirMapeamentoDeColunas(String[] cabecalho) {
        for (int i = 0; i < cabecalho.length; i++) {
            String coluna = cabecalho[i].toLowerCase().trim();

            try {
                Field field = CsvDao.class.getDeclaredField(coluna);
                field.setAccessible(true);
                mapeamentoColunas.put(i, field);  // Mapeia a posição da coluna ao campo
            } catch (NoSuchFieldException e) {
                // Armazena as colunas ignoradas na lista
                colunasIgnoradas.add(coluna);
            }
        }

        if (!colunasIgnoradas.isEmpty()) {
            String message = "Colunas ignoradas: " + String.join(", ", colunasIgnoradas);
            log.warn(message);
        }
    }

    private CsvDao mapearLinhaParaDao(String[] linha, Long idLinha) {
        CsvDao csvDao = new CsvDao();
        csvDao.setIdLinha( idLinha );

        for (int i = 0; i < linha.length; i++) {
            String valor = linha[i];

            if (mapeamentoColunas.containsKey(i)) {
                Field field = mapeamentoColunas.get(i);
                try {
                    field.set(csvDao, valor);
                } catch (IllegalAccessException e) {
                    String message = "Erro ao definir valor no campo: " + e.getMessage();
                    log.error(message);
                }
            }
        }
        return csvDao;
    }


}
