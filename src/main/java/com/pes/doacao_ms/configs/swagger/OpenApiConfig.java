package com.pes.doacao_ms.configs.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Doação",
                version = "1.0.0",
                description = "API para os serviços de doação e doadores"
        ))
public class OpenApiConfig {
    // Outras configurações específicas podem ser adicionadas aqui.
}
