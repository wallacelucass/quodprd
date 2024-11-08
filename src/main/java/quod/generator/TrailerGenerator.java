package quod.generator;

import quod.dto.TrailerDto;

public class TrailerGenerator {

    public String generateTrailer(TrailerDto trailerDto, Long sequencial) {
        StringBuilder linha = new StringBuilder();

        // 1. Código do registro = '99'
        linha.append(String.format("%-2s", trailerDto.getCodigoRegistro()));

        // 2. Total de registros da remessa
        linha.append(String.format("%07d", trailerDto.getTotalRegistrosRemessa()));

        // 3. Quantidade de inclusões da Remessa
        linha.append(String.format("%07d", trailerDto.getQuantidadeInclusoes()));

        // 4. Quantidade de exclusões da Remessa
        linha.append(String.format("%07d", trailerDto.getQuantidadeExclusoes()));

        // 5. Quantidade de Alterações da Remessa
        linha.append(String.format("%07d", trailerDto.getQuantidadeAlteracoes()));

        // 6. Total do valor da Remessa com duas casas decimais
        linha.append(String.format("%030d", Math.round(trailerDto.getTotalValorRemessa() * 100)));

        // 7. Quantidade de Reforços de Comunicação
        linha.append(String.format("%07d", trailerDto.getQuantidadeReforcos()));

        // 8. Em branco (379 espaços)
        linha.append(String.format("%-379s", " "));

        // 9. Código(s) de retorno
        linha.append(String.format("%-45s", trailerDto.getCodigosRetorno()));

        // 10. Sequência do registro no arquivo
        linha.append(String.format("%09d", sequencial));

        return linha.toString().toUpperCase();
    }
}
