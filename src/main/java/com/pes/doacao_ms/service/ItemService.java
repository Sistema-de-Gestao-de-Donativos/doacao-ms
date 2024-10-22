package com.pes.doacao_ms.service;

import com.pes.doacao_ms.controller.request.IncludeOrUpdateItemRequest;
import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.domain.Item;
import com.pes.doacao_ms.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pes.doacao_ms.mapper.ItemMapper.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemIdResponse include(IncludeOrUpdateItemRequest request) {
        Item item;
        Optional<Item> optionalItem = itemRepository.findById(request.getId());

        // Não passou ID no request ou o ID informado não existe, cria novo Item
        if ((request.getId() == null) || (optionalItem.isEmpty())) {
            item = toEntity(request);
        } else {
            // Item existe, incrementa quantidade
            item = toModify(optionalItem.get(), request);
        }

        itemRepository.save(item);
        return toIdResponse(item);
    }

    public ItemIdResponse includeName(ItemDoado request) {
        Item item;
        Optional<Item> optionalItem = itemRepository.findByName(request.getNome());

        // Item recebido não existe, cria novo Item
        if ((optionalItem.isEmpty())) {
            item = donatedToItem(request);
        } else {
            // Item existe, incrementa quantidade
            item = donatedToModify(optionalItem.get(), request);
        }

        itemRepository.save(item);
        return toIdResponse(item);
    }
}
