package com.pes.doacao_ms.controller.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoacaoRequest {

    @NotBlank
    private Long codDoador;

    @NotBlank
    private Long codCD;

    @NotNull
    private List<ItemDoado> itens;
}
