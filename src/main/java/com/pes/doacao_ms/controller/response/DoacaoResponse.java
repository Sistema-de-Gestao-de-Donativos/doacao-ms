package com.pes.doacao_ms.controller.response;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class DoacaoResponse {

    private Long idDoacao;
    private Long codCD;
    private Long idDoador;
    private String idItem;
    private Integer qtd;
    private ZonedDateTime dataDoacao;
}
