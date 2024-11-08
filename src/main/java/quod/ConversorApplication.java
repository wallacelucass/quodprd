package quod;

import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import quod.csv.Csv;
import quod.dto.HeaderDto;
import quod.dto.RegistroDto;
import quod.txt.Txt;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class ConversorApplication {

    private static LocalDateTime inicio;

    public static void main(String[] args) {
        String caminhoCsv = "LAYOUT_TESTES_20241009.csv";  // caminho do arquivo CSV de entrada
        String nomeTxt = "GIC_Neg_P_C008170849000115";
        String dataTxt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")); // Formato da data
        String caminhoTxt = nomeTxt.concat("_").concat(dataTxt).concat("_ED.txt"); // Define o nome do arquivo TXT

        log.info("Processo iniciado");

        try {
            // Dados da empresa
            String dataMovimento = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            HeaderDto headerDto = new HeaderDto(
                    "08170849000115",     // CNPJ
                    dataMovimento,             // Data do movimento
                    "GIC-NEGATIVACAO",         // Identificação do arquivo
                    "1",                       // Número da remessa
                    "ED",                      // Código de Remessa
                    "0000"                     // Área Informante
            );

            Csv csv = new Csv();
            List<RegistroDto> registros = csv.leitorCsv(caminhoCsv);

            Txt txt = new Txt();
            txt.exportarParaTxt(registros, headerDto, caminhoTxt);

            log.info("Arquivo exportado com sucesso: " + caminhoTxt);

        } catch (IllegalArgumentException e) {
            log.error("Erro de argumentos: " + e.getMessage());
        } catch (IOException e) {
            log.error("Erro ao acessar o arquivo: " + e.getMessage());
        } catch (CsvValidationException e) {
            log.error("Erro na validação do CSV: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro inesperado: " + e.getMessage());
        }
        log.info("Processo finalizado");
    }
}
