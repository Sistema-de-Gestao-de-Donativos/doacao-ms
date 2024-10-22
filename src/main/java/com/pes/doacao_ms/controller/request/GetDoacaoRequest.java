package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetDoacaoRequest {
    
    @NotBlank
    private long idDoacao;
}
