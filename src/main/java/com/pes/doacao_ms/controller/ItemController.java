package com.pes.doacao_ms.controller;

import com.pes.doacao_ms.controller.request.IncludeItemRequest;
import com.pes.doacao_ms.controller.request.ItemIdRequest;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.controller.response.ItemResponse;
import com.pes.doacao_ms.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @ResponseStatus(OK)
    public ItemResponse get(@Valid @RequestBody ItemIdRequest itemIdRequest) {
        return itemService.getItem(itemIdRequest);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ItemIdResponse include(@Valid @RequestBody IncludeItemRequest includeItemRequest) {
        return itemService.include(includeItemRequest);
    }
}
