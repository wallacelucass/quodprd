package quod.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrailerDto {

    private String codigoRegistro;
    private Long totalRegistrosRemessa;
    private Long quantidadeInclusoes;
    private Long quantidadeExclusoes;
    private Long quantidadeAlteracoes;
    private Double totalValorRemessa;
    private Long quantidadeReforcos;
    private String codigosRetorno;

}

