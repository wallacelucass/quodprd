package quod.generator;

import quod.dto.RegistroDto;

public class Registro01Generator {

    public String generateRegistro01(RegistroDto registro, Long sequencial) {

        StringBuilder linha = new StringBuilder();

        // 1. Código do registro (2 caracteres)
        linha.append(String.format("%-2s", "01"));

        // 2. Código da operação (1 caractere)
        linha.append(String.format("%-1s", registro.getCodigoOperacao()));

        // 3. Número do documento do Informante/Credor (15 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%015d", Long.parseLong(registro.getDocumentoCredor())));

        // 4. Tipo de Participante (1 caractere)
        linha.append(String.format("%-1s", registro.getTipoParticipante()));

        // 5. Tipo de Pessoa (1 caractere)
        linha.append(String.format("%-1s", registro.getTipoDocumentoDevedor()));

        // 6. Número do documento do Devedor (15 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%-15s", registro.getDocumentoDevedor()));

        // 7. Nome / Razão Social (70 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-70s", registro.getNomeDevedor()));

        // 8. Data de nascimento (8 caracteres, formato DDMMAAAA)
        linha.append(String.format("%-8s", ""));

        // 9. Natureza da Operação (3 caracteres)
        linha.append(String.format("%-3s", registro.getNaturezaOperacao()));

        // 10. Número do Contrato (20 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%020d", Long.parseLong(registro.getNumAdm())));

        // 11. Data da Ocorrência da Dívida (8 caracteres, formato DDMMAAAA)
        linha.append(String.format("%-8s", registro.getDataOcorrenciaDivida()));

        // 12. Valor da Dívida (15 caracteres, sem ponto ou vírgula, preenchido com zeros à esquerda)
        linha.append(String.format("%-15s", registro.getValorDivida()));

        // 13. Nova Data da Ocorrência da Dívida (8 caracteres, preenchido com espaços, formarto DDMMAAAA)
        // 14. Novo Valor da Dívida (15 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%-23s", ""));

        // 15. Tipo Logradouro (10 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-10s", registro.getTipoLogradouro()));

        // 16. Logradouro (60 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-60s", registro.getLogradouro()));

        // 17. Número do logradouro (7 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-7s", registro.getNumeroLogradouro()));

        // 18. Complemento (30 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-30s", registro.getComplemento()));

        // 19. Bairro correspondente (30 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-30s", registro.getBairro()));

        // 20. Município correspondente (30 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-30s", registro.getMunicipio()));

        // 21. Sigla da Unidade da Federação (2 caracteres)
        linha.append(String.format("%-2s", registro.getUf()));

        // 22. CEP (8 caracteres)
        linha.append(String.format("%-8s", registro.getCep()));

        // 23. Motivo da baixa (2 caracteres, preenchido com espaços se não aplicável)
        linha.append(String.format("%-2s", ""));

        // 24. Emissão de Boleto (1 caractere)
        linha.append(String.format("%-1s", registro.getEmissaoBoleto()));

        // 25. Tipo de Comunicado Gerado (1 caractere)
        linha.append(String.format("%-1s", registro.getTipoComunicado()));

        // 26. Campo em branco (1 caractere)
        linha.append(String.format("%-1s", " "));

        // 27. Identificação do Modelo de Comunicado (5 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-5s", registro.getModeloComunicado()));

        // 28. Exigir Tipo de Comunicado Específico (1 caractere, preenchido com espaços se não aplicável)
        // 29. Campo em branco (18 caracteres)
        // 30. Dado Complementar Divida (15 caracteres, preenchido com espaços à direita)
        // 31. Tipo Pessoa Principal (1 caractere)
        // 32. CPF/CNPJ Principal (15 caracteres, preenchido com zeros à esquerda)
        // 33. Campo em branco (27 caracteres)
        // 34. Código(s) de retorno (45 caracteres, preenchido com espaços à direita)

        linha.append(String.format("%-122s", ""));

        // 35. Sequência do registro no arquivo (9 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%09d", sequencial));

        return linha.toString().toUpperCase();
    }
}