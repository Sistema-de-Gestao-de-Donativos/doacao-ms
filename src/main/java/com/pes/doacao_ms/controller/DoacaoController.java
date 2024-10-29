package com.pes.doacao_ms.controller;

import com.pes.doacao_ms.controller.request.DoacaoRequest;
import com.pes.doacao_ms.controller.request.GetDoacaoRequest;
import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.request.ItemFromStock;
import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.mapper.DoacaoMapper;
import com.pes.doacao_ms.service.DoacaoService;
import com.pes.doacao_ms.service.DoadorService;
import com.pes.doacao_ms.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/doacao")
@CrossOrigin(origins = "*")
public class DoacaoController {

    private final int port = 5000;
    private final String baseUrl = "http://localhost:"+port;
    private final String path = "/v1/stock/";
    
    @Autowired
    DoacaoService doacaoService;

    @Autowired
    ItemService itemService;

    @Autowired
    DoadorService doadorService;

    @Autowired
    RestTemplate restTemplate;

    @Operation(summary = "Post a Doacao.", description = "Post a new Doacao into Estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doacao created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoacaoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = """
                    {"error": ["codDoador is required",
                    codCD is required",
                    "itens is required"]}""")))
    })
    @PostMapping
    public List<DoacaoResponse> receiveDoacao(@Valid @RequestBody DoacaoRequest doacao) {
        Long codCD = doacao.getCodCD();
        Long codDoador = doacao.getCodDoador();
        List<ItemDoado> itens = doacao.getItens();
        String idItem;
        List<DoacaoResponse> responses = new ArrayList<>();
        List<ItemFromStock> itens2 = new ArrayList<>();
        for (ItemDoado it : itens) {
            // idItem = itemService.includeName(it).getId();
            // TODO perguntar ao estoque pela existencia do item

            idItem = verifyItemInStock(it.getNome(),codCD);
            itens2.add(DoacaoMapper.toStock(it));
            
            responses.add(doacaoService.saveDoacao(codCD, codDoador, idItem, it.getQtd()));
        }
        doadorService.updateDonationsNumber(codDoador, responses.size());
        // TODO enviar os itens para o estoque 
        
        
        sendItemsToStock(codCD, itens2);
        return responses;
    }

    private String verifyItemInStock(String nameItem, Long codCd){
        // http:localhost:5000/v1/stock/codCd/nameItem
        String url = baseUrl+path+Long.toString(codCd)+"/"+nameItem;
        System.out.println("url: "+url);
        ResponseEntity<ItemFromStock[]> response = restTemplate.getForEntity(url, ItemFromStock[].class);
        return response.getBody()[0].get_id();
    }

    private void sendItemsToStock(Long codCd, List<ItemFromStock> items){
        // http:localhost:5000/v1/stock/codCD
        String url = baseUrl+path+Long.toString(codCd);
        System.out.println("url: "+url);
        restTemplate.postForEntity(url, items, Void.class);
    }

    // TODO adicionar outras formas de get. Ex.: data, CD, doador, item
    @Operation(summary = "Get a Doacao.", description = "Return a specific Doacao by idDoacao.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doacao found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoacaoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Invalid request body\"}"))),
            @ApiResponse(responseCode = "404", description = "Doacao not found", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Doacao not found\"}"))),
    })
    @GetMapping
    public DoacaoResponse getDoacao(@Valid @RequestBody GetDoacaoRequest getDoacao) {
        return doacaoService.getDoacao(getDoacao.getIdDoacao());
    }
}
