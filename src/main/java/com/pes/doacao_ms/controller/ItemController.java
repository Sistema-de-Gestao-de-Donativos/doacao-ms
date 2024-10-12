package com.pes.doacao_ms.controller;

import com.pes.doacao_ms.controller.request.IncludeItemRequest;
import com.pes.doacao_ms.controller.request.ItemIdRequest;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.controller.response.ItemResponse;
import com.pes.doacao_ms.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get an Item.", description = "Return a specific Item by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Invalid request body\"}"))),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Item not found\"}"))),
    })
    @GetMapping
    public ItemResponse get(@Valid @RequestBody ItemIdRequest itemIdRequest) {
        return itemService.getItem(itemIdRequest);
    }

    @Operation(summary = "Create a new Item.", description = "Create a new Item using the given request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemIdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": [\"Name is required\"]}")))
    })
    @PostMapping
    public ItemIdResponse include(@Valid @RequestBody IncludeItemRequest includeItemRequest) {
        return itemService.include(includeItemRequest);
    }
}
