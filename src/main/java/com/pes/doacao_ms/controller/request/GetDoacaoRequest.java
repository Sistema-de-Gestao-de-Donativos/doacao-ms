package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetDoacaoRequest {
    
    @NotNull
    private long idDoacao;
}
