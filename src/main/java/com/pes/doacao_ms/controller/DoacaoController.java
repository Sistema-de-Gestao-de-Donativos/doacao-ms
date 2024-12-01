package com.pes.doacao_ms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pes.doacao_ms.controller.request.DoacaoRequest;
import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.service.DoacaoService;
import com.pes.doacao_ms.service.DoadorService;
import com.pes.doacao_ms.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/doacao")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class DoacaoController {

    @Autowired
    DoacaoService doacaoService;

    @Autowired
    ItemService itemService;

    @Autowired
    DoadorService doadorService;

    @Operation(summary = "Post a Doacao.", description = "Post a new Doacao into Estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doacao created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoacaoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = """
                    {"error": ["codDoador is required",
                    codCD is required",
                    "itens is required"]}""")))
    })
    @PostMapping
    public  ResponseEntity<Void>  receiveDoacao(@Valid @RequestBody DoacaoRequest doacao) {
        doacaoService.saveDoacao(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a Doacao.", description = "Return a specific Doacao by idDoacao.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doacao found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoacaoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Invalid request body\"}"))),
            @ApiResponse(responseCode = "404", description = "Doacao not found", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Doacao not found\"}"))),
    })
    @GetMapping("/{idDoacao}")
    public ResponseEntity<DoacaoResponse> getDoacao(@PathVariable Long idDoacao) {
        DoacaoResponse doacaoResponse = doacaoService.getDoacao(idDoacao);
        return ResponseEntity.ok(doacaoResponse);
    }

    @Operation(summary = "Get lista de doações por data.", description = "Return a specific Doacao list by date. Format: yyyy-mm-dd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doacao found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoacaoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Invalid request body\"}"))),
            @ApiResponse(responseCode = "404", description = "Doacao not found", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Doacao not found\"}"))),
    })
    @GetMapping("/date")
    public ResponseEntity<List<DoacaoResponse>> getDoacaoPorData(@RequestParam(value = "startDate") LocalDate startDate, @RequestParam(value = "endDate",required = false) LocalDate endDate) {
        List<DoacaoResponse> doacoes = doacaoService.buscarPorData(startDate, endDate);
        return ResponseEntity.ok(doacoes);
    }
}
