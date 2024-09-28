package com.pes.doacao_ms.service;

import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.request.IncludeItemRequest;
import com.pes.doacao_ms.controller.request.ItemIdRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.controller.response.ItemResponse;
import com.pes.doacao_ms.domain.Doador;
import com.pes.doacao_ms.domain.Item;
import com.pes.doacao_ms.mapper.ItemMapper;
import com.pes.doacao_ms.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.pes.doacao_ms.mapper.DoadorMapper.toEntity;
import static com.pes.doacao_ms.mapper.DoadorMapper.toIdResponse;
import static com.pes.doacao_ms.mapper.ItemMapper.toEntity;
import static com.pes.doacao_ms.mapper.ItemMapper.toIdResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse getItem(ItemIdRequest itemIdRequest) {
        return itemRepository.findById(itemIdRequest.getId())
                .map(ItemMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Item not found"));
    }

    public ItemIdResponse include(IncludeItemRequest request) {
        Item item = toEntity(request);

        itemRepository.save(item);

        return toIdResponse(item);
    }
}
