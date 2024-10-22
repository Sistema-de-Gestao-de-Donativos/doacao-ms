package com.pes.doacao_ms.controller.response;

import lombok.Data;

@Data
public class DoadorResponse {

    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;
    private Long nroDoacoes;
}
