package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemIdRequest {

    @NotBlank(message = "Id is required")
    private Long id;
}
