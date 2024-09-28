package com.pes.doacao_ms.mapper;

import com.pes.doacao_ms.controller.request.IncludeItemRequest;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.controller.response.ItemResponse;
import com.pes.doacao_ms.domain.Item;

public class ItemMapper {

    public static ItemResponse toResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();

        itemResponse.setId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setQuantity(item.getQuantity());

        return itemResponse;
    }

    public static ItemIdResponse toIdResponse(Item item) {
        ItemIdResponse itemIdResponse = new ItemIdResponse();

        itemIdResponse.setId(item.getId());

        return itemIdResponse;
    }

    public static Item toEntity(IncludeItemRequest request) {
        Item itemEntity = new Item();

        itemEntity.setName(request.getName());

        if (request.getQuantity() != null) {
            itemEntity.setQuantity(request.getQuantity());
        } else {
            itemEntity.setQuantity(0);
        }

        return itemEntity;
    }
}
