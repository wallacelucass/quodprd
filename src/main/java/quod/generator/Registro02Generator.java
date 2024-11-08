package quod.generator;

import quod.dto.RegistroDto;

public class Registro02Generator {

    public String generateRegistro02(RegistroDto registro, Long sequencial) {

        StringBuilder linha = new StringBuilder();

        // 1. Código do registro (2 caracteres)
        linha.append(String.format("%-2s", "02"));

        // 2. Código da operação (1 caractere)
        linha.append(String.format("%-1s", registro.getCodigoOperacao()));

        // 3. Nome da mãe (50 caracteres, preenchido com espaços à direita)
        // 4. Estado civil (1 caractere)
        // 5. Unidade da federação do RG
        // 6. RG (15 caracteres, preenchido com zeros à esquerda)
        // 7. Unidade da federação do RG (2 caracteres)
        // 8. Naturalidade (30 caracteres, preenchido com espaços à direita)
        // 9. Unidade da federação da Naturalidade (2 caracteres)
        linha.append(String.format("%-150s", ""));

        // 10. Tipo Telefone (1 caractere)
        // 11. DDD do telefone (2 caracteres)
        // 12. Número do telefone (9 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%-12s", registro.getTelefone()));

        // 13. Tipo de E-mail (1 caractere)
        // 14. E-mail (80 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-81s", registro.getEmail()));

        // 15. Tipo Endereço (1 caractere)
        // 16. Tipo logradouro (10 caracteres, preenchido com espaços à direita)
        // 17. Logradouro (60 caracteres, preenchido com espaços à direita)
        // 18. Número do logradouro (7 caracteres, preenchido com zeros à esquerda)
        // 19. Complemento (30 caracteres, preenchido com espaços à direita)
        // 20. Bairro correspondente (30 caracteres, preenchido com espaços à direita)
        // 21. Município correspondente (30 caracteres, preenchido com espaços à direita)
        // 22. Sigla da Unidade da Federação (2 caracteres)
        // 23. CEP - Código de endereçamento postal completo (8 caracteres, preenchido com zeros à esquerda)
        // 24. Brancos (22 caracteres, preenchido com espaços à direita)
        // 25. Código(s) de retorno (45 caracteres, preenchido com espaços à direita)
        linha.append(String.format("%-245s", ""));

        // 26. Sequência do registro no arquivo (9 caracteres, preenchido com zeros à esquerda)
        linha.append(String.format("%09d", sequencial));

        return linha.toString().toUpperCase();
    }
}
