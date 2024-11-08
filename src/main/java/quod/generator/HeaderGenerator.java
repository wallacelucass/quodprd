package quod.generator;

import quod.dto.HeaderDto;

public class HeaderGenerator {

    public String generateHeader(HeaderDto headerDto, Long sequencial) {
        StringBuilder linha = new StringBuilder();

        // 1. Código do registro = '00' (zero)
        linha.append(String.format("%-2s", "00"));

        // 2. CNPJ
        linha.append(String.format("%015d", headerDto.getCnpj()));

        // 3. Data do movimento (formato DDMMAAAA)
        linha.append(String.format("%-8s", headerDto.getDataMovimento()));

        // 4. Identificação do arquivo
        linha.append(String.format("%-15s", headerDto.getIdentificacaoArquivo()));

        // 5. Número da remessa
        linha.append(String.format("%06d", headerDto.getNumRemessa()));

        // 6. Código de Remessa
        linha.append(String.format("%-2s", headerDto.getCodigoRemessa()));

        // 7. Área Informante
        linha.append(String.format("%-4s", headerDto.getAreaInformante()));

        // 8. Em branco (394 espaços)
        linha.append(String.format("%-394s", " "));

        // 9. Código(s) de retorno
        linha.append(String.format("%-45s", " "));

        // 10. Sequência do registro no arquivo igual a 000000001 para o header
        linha.append(String.format("%09d", sequencial));

        return linha.toString().toUpperCase();
    }
}
