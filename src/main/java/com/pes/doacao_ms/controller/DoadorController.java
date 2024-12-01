package com.pes.doacao_ms.controller;

import com.pes.doacao_ms.controller.request.EmailRequest;
import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.DoadorResponse;
import com.pes.doacao_ms.service.DoadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doador")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @Operation(summary = "Get a Doador.", description = "Return a specific Doador by Email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doador found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoadorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Invalid request body\"}"))),
            @ApiResponse(responseCode = "404", description = "Doador not found", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Doador not found\"}"))),
    })
    @GetMapping
    public DoadorResponse get(@Valid @RequestBody EmailRequest emailRequest) {
        return doadorService.getDoador(emailRequest);
    }

    @Operation(summary = "Create a new Doador.", description = "Create a new Doador using the given request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doador created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoadorIdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": [\"Name is required\",\n" + "\"Document is required\"]}")))
    })
    @PostMapping
    public DoadorIdResponse include(@Valid @RequestBody IncludeDoadorRequest includeDoadorRequest) {
        return doadorService.include(includeDoadorRequest);
    }
}
