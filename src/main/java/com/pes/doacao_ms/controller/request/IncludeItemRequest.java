package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IncludeItemRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private Integer quantity;
}
