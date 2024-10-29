package com.pes.doacao_ms.controller.request;

import lombok.Data;

@Data
public class ItemFromStock {

    private String _id;
    private String nome;
    private Long quantidade;
    private String unidade;
    // private String dataValidade;
    private String categoria;
}
