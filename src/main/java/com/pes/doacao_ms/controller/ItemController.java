// package com.pes.doacao_ms.controller;

// import com.pes.doacao_ms.controller.request.IncludeOrUpdateItemRequest;
// import com.pes.doacao_ms.controller.response.ItemIdResponse;
// import com.pes.doacao_ms.service.ItemService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import jakarta.validation.Valid;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/v1/item")
// @CrossOrigin(origins = "*")
// public class ItemController {

//     @Autowired
//     private ItemService itemService;

//     @Operation(summary = "Create or update Item.", description = "Create a new Item or update an existing one using the given request.")
//     @ApiResponses(value = {
//             @ApiResponse(responseCode = "200", description = "Item updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemIdResponse.class))),
//             @ApiResponse(responseCode = "201", description = "Item created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemIdResponse.class))),
//             @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": [\"Name is required\"]}")))
//     })
//     @PostMapping
//     public ItemIdResponse includeOrUpdate(@Valid @RequestBody IncludeOrUpdateItemRequest includeOrUpdateItemRequest) {
//         return itemService.include(includeOrUpdateItemRequest);
//     }
// }
