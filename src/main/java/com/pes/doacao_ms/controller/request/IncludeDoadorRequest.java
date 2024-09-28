package com.pes.doacao_ms.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IncludeDoadorRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Document is required")
    private String document;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;
}
