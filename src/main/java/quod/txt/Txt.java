package quod.txt;

import quod.dto.HeaderDto;
import quod.dto.RegistroDto;
import quod.dto.TrailerDto;
import quod.generator.HeaderGenerator;
import quod.generator.Registro01Generator;
import quod.generator.Registro02Generator;
import quod.generator.TrailerGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Txt {

    private HeaderGenerator headerGenerator = new HeaderGenerator();
    private Registro01Generator registro01Generator = new Registro01Generator();
    private Registro02Generator registro02Generator = new Registro02Generator();
    private TrailerGenerator trailerGenerator = new TrailerGenerator();

    public void exportarParaTxt(List<RegistroDto> registros, HeaderDto header, String caminhoArquivo) throws IOException {
        Long sequencial = 1L;
        Long totalRegistros = 0L;
        Long qtdInclusoes = 0L;
        Long qtdExclusoes = 0L;
        Long qtdAlteracoes = 0L;
        double totalRemessa = 0;
        Long qtdReforcos = 0L;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            writer.write(headerGenerator.generateHeader(header, sequencial));
            writer.newLine();
            sequencial++;

            for (RegistroDto registro : registros) {

                String registro01 = registro01Generator.generateRegistro01(registro, sequencial);
                writer.write(registro01);
                writer.newLine();
                sequencial++;

                String registro02 = registro02Generator.generateRegistro02(registro, sequencial);
                writer.write(registro02);
                writer.newLine();
                sequencial++;

                totalRegistros++;

                if (registro.getCodigoOperacao().equals("I")) {
                    qtdInclusoes++;
                }

                if (registro.getCodigoOperacao().equals("E")) {
                    qtdExclusoes++;
                }
            }

            TrailerDto trailerDto = new TrailerDto(
                    "99",  // Código do registro
                    totalRegistros,    // Total de registros da remessa
                    qtdInclusoes,      // Quantidade de inclusões
                    qtdExclusoes,      // Quantidade de exclusões
                    qtdAlteracoes,     // Quantidade de alterações
                    totalRemessa,      // Total do valor da remessa (double)
                    qtdReforcos,       // Quantidade de reforços de comunicação
                    ""                 // Códigos de retorno
            );

            writer.write(trailerGenerator.generateTrailer(trailerDto, sequencial));
        }
    }
}
