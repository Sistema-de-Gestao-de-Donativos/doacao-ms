package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemDoado {
    
    @NotBlank
    private String nome;
    @NotBlank
    private String validade;
    @NotBlank
    private String unidade;
    @NotBlank
    private Integer qtd;
    @NotBlank
    private String categoria;
}
