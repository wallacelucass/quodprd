package quod.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CsvDao {

    private Long idLinha;

    // INICIO DO REGISTRO 01
    private String documento;             // Número do documento do Devedor
    private String tipodocumento;         // Tipo do documento do Devedor

    private String nome;                  // Nome / Razão Social

    private String num_adm;               // Número do Contrato
    private String cob_mais_velha;        // Data da Ocorrência da Dívida
    private String valor_divida;          // Valor da Dívida (sem ponto ou vírgula, 2 casas decimais)

    private String endereco;              // Logradouro
    private String numero;                // Número do logradouro
    private String bairro;                // Bairro correspondente
    private String cidade;                // Município correspondente
    private String estado;                // Sigla da Unidade da Federação
    private String cep;                   // CEP

    // INICIO DO REGISTRO 02
    private String telefones;             // Número do telefone
    private String email;                 // E-mail

    public boolean isValid() {
        return documento != null && !documento.isEmpty()
                && tipodocumento != null && !tipodocumento.isEmpty()
                && nome != null && !nome.isEmpty()
                && num_adm != null && !num_adm.isEmpty()
                && cob_mais_velha != null && !cob_mais_velha.isEmpty()
                && valor_divida != null && !valor_divida.isEmpty()
                && endereco != null && !endereco.isEmpty()
                && numero != null && !numero.isEmpty()
                && bairro != null && !bairro.isEmpty()
                && cidade != null && !cidade.isEmpty()
                && estado != null && !estado.isEmpty()
                && cep != null && !cep.isEmpty()
                && telefones != null && !telefones.isEmpty()
                && email != null && !email.isEmpty();
    }

    public void checkEmptyFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringJoiner camposFaltantes = new StringJoiner(" | ");

        for (Field field : fields) {
            field.setAccessible(true); // Permite acesso a campos privados
            try {
                Object value = field.get(this);

                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    camposFaltantes.add(field.getName());  // Adiciona o campo faltante
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Se houver campos faltantes, na linha.
        if (camposFaltantes.length() > 0) {
            String message = "Linha ignorado: " + this.getIdLinha() + ", campos ausentes.: " + camposFaltantes;
            log.error(message);
        }
    }
}
