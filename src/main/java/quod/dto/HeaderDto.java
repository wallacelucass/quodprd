package quod.dto;

import lombok.Data;

@Data
public class HeaderDto {
    private String cnpj;
    private String dataMovimento;
    private String identificacaoArquivo;
    private String numRemessa;
    private String codigoRemessa;
    private String areaInformante;

    public HeaderDto(String cnpj, String dataMovimento, String identificacaoArquivo,
                     String numRemessa, String codigoRemessa, String areaInformante) {
        setCnpj(cnpj);
        setDataMovimento(dataMovimento);
        setIdentificacaoArquivo(identificacaoArquivo);
        setNumRemessa(numRemessa);
        setCodigoRemessa(codigoRemessa);
        setAreaInformante(areaInformante);
    }

    public Long getCnpj() {
        return Long.parseLong(cnpj);
    }

    public Long getNumRemessa() {
        return Long.parseLong(numRemessa);
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            throw new IllegalArgumentException("CNPJ inválido. Deve ter 14 dígitos.");
        }
        this.cnpj = cnpj;
    }

    public void setDataMovimento(String dataMovimento) {
        if (dataMovimento == null || dataMovimento.length() != 8 || !dataMovimento.matches("\\d+")) {
            throw new IllegalArgumentException("Data do movimento inválida. Deve estar no formato DDMMAAAA.");
        }
        this.dataMovimento = dataMovimento;
    }

    public void setIdentificacaoArquivo(String identificacaoArquivo) {
        if (identificacaoArquivo == null || identificacaoArquivo.length() > 15) {
            throw new IllegalArgumentException("Identificação do arquivo inválida. Máximo de 15 caracteres.");
        }
        this.identificacaoArquivo = identificacaoArquivo;
    }

    public void setNumRemessa(String numRemessa) {
        if (numRemessa == null || numRemessa.length() > 6 || !numRemessa.matches("\\d+")) {
            throw new IllegalArgumentException("Número da remessa inválido.");
        }
        this.numRemessa = numRemessa;
    }

    public void setCodigoRemessa(String codigoRemessa) {
        if (codigoRemessa == null || codigoRemessa.length() != 2) {
            throw new IllegalArgumentException("Código de remessa inválido. Deve ter 2 caracteres.");
        }
        this.codigoRemessa = codigoRemessa;
    }

    public void setAreaInformante(String areaInformante) {
        if (areaInformante == null || areaInformante.length() > 4) {
            throw new IllegalArgumentException("Área informante inválida. Máximo de 4 caracteres.");
        }
        this.areaInformante = areaInformante;
    }
}