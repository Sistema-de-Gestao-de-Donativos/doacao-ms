package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IncludeOrUpdateItemRequest {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Integer quantity;
}
