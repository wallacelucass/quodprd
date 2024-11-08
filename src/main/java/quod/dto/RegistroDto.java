package quod.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import quod.dao.CsvDao;

import java.lang.reflect.Field;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RegistroDto {

    private Long idLinha;

    // INICIO DO REGISTRO 01
    private String codigoOperacao;        // Código da operação (I - Inclusão, E - Exclusão, R - Reforço)
    private String documentoCredor;       // Número do documento do Informante/Credor
    private String tipoParticipante;      // Tipo de Participante (P - Principal, A - Avalista)

    private String tipoDocumentoDevedor;  // Tipo de Pessoa (F - Física, J - Jurídica)
    private String documentoDevedor;      // Número do documento do Devedor
    private String nomeDevedor;           // Nome / Razão Social

    private String naturezaOperacao;      // Natureza da Operação

    private String numAdm;                // Número do Contrato
    private String dataOcorrenciaDivida;  // Data da Ocorrência da Dívida
    private String valorDivida;           // Valor da Dívida (sem ponto ou vírgula, 2 casas decimais)

    private String tipoLogradouro;        // Tipo de Logradouro
    private String logradouro;            // Logradouro
    private String numeroLogradouro;      // Número do logradouro
    private String complemento;           // Complemento
    private String bairro;                // Bairro correspondente
    private String municipio;             // Município correspondente
    private String uf;                    // Sigla da Unidade da Federação
    private String cep;                   // CEP

    private String emissaoBoleto;         // Emissão de Boleto
    private String tipoComunicado;        // Tipo de Comunicado Gerado
    private String modeloComunicado;      // Modelo do Comunicado

    // INICIO DO REGISTRO 02


    private String telefone;              // Número do telefone
    private String email;                 // E-mail


    public RegistroDto(CsvDao csvDao) {
        this.idLinha = csvDao.getIdLinha();

        this.codigoOperacao = "I";
        this.documentoCredor = "08170849000115";
        this.tipoParticipante = "P";
        this.naturezaOperacao = "TI";
        this.tipoLogradouro = "o";
        this.emissaoBoleto = "N";
        this.tipoComunicado = "E";
        this.modeloComunicado = "00001";

        this.tipoDocumentoDevedor = csvDao.getTipodocumento();
        setDocumentoDevedor(csvDao.getDocumento());

        this.nomeDevedor = csvDao.getNome();
        this.numAdm = csvDao.getNum_adm();

        setDataOcorrenciaDivida(csvDao.getCob_mais_velha());
        setValorDivida(csvDao.getValor_divida());

        this.logradouro = csvDao.getEndereco();
        this.numeroLogradouro = csvDao.getNumero();
        this.complemento = "";
        this.bairro = csvDao.getBairro();
        this.municipio = csvDao.getCidade();
        this.uf = csvDao.getEstado();
        setCep(csvDao.getCep());

        setTelefone(csvDao.getTelefones(), csvDao.getTipodocumento());
        setEmail(csvDao.getEmail(), csvDao.getTipodocumento());
    }

    public void setDocumentoDevedor(String documentoDevedor) {
        try {
            String documentoNumerico = documentoDevedor.replaceAll("[^a-zA-Z0-9]", "");
            this.documentoDevedor = String.format("%15s", documentoNumerico).replace(' ', '0');
        } catch (Exception e) {
            log.error("Erro campo documento. Mensagem: " + e.getMessage());
            this.documentoDevedor = "DOC-ERRO";
        }
    }

    public void setDataOcorrenciaDivida(String dataOcorrenciaDivida) {
        try {
            this.dataOcorrenciaDivida = dataOcorrenciaDivida.replace("/", "").trim();
        } catch (NumberFormatException e) {
            System.out.println("Erro data.");
            this.dataOcorrenciaDivida = "DTV-ERRO";
        }
    }

    public void setValorDivida(String valorDivida) {
        try {
            String valorDividaStr = valorDivida.replace(",", ".").trim();
            String[] partes = valorDividaStr.split("\\.");
            String parteInteira = partes[0];
            String parteDecimal = partes.length > 1 ? partes[1] : "";

            if (parteDecimal.length() == 0) {
                parteDecimal = "00";
            } else if (parteDecimal.length() == 1) {
                parteDecimal += "0";
            } else if (parteDecimal.length() > 2) {
                parteDecimal = parteDecimal.substring(0, 2);
            }

            String valorFinal = parteInteira + parteDecimal;
            long valorLong = Long.parseLong(valorFinal);
            this.valorDivida = String.format("%015d", valorLong);
        } catch (NumberFormatException e) {
            System.out.println("Valor de dívida inválido: " + valorDivida);
            this.valorDivida = "DV-ERRO";
        }
    }

    public void setCep(String cep) {
        try {
            this.cep = cep.replaceAll("[^0-9]", "").trim();
        } catch (NumberFormatException e) {
            log.error("Erro cep.");
            this.cep = "ERRO";
        }
    }

    public void setTelefone(String telefones, String getTipoDocumentoDevedor) {
        String numeroTelefone = "0";
        if (telefones != null && !telefones.isEmpty()) {
            String[] telefonesSeparados = telefones.split(" / ");
            numeroTelefone = telefonesSeparados[0];
            for (String telefone : telefonesSeparados) {
                if (telefone.length() == 11) {
                    numeroTelefone = telefone;
                    break;
                }
            }
        }
        String telefone = String.format("%11s", Long.parseLong(numeroTelefone));

        String tipoTelefone = "";
        if (numeroTelefone.length() == 10) {
            tipoTelefone = String.format("%-1s", "F");
            if (getTipoDocumentoDevedor != null && !getTipoDocumentoDevedor.isEmpty()) {
                if (getTipoDocumentoDevedor.equals("J")) {
                    tipoTelefone = String.format("%-1s", "C");
                }
            }
        }
        if (numeroTelefone.length() == 11) {
            tipoTelefone = String.format("%-1s", "M");
        }

        this.telefone = tipoTelefone + telefone;
    }

    public void setEmail(String email, String getTipoDocumentoDevedor) {
        String tipoEmail = "P";
        if (getTipoDocumentoDevedor != null && !getTipoDocumentoDevedor.isEmpty()) {
            if (getTipoDocumentoDevedor.equals("J")) {
                tipoEmail = String.format("%-1s", "C");
            }
        }
        String strEmail = String.format("%-80s", email != null ? email : "");
        this.email = tipoEmail + strEmail;
    }
}
