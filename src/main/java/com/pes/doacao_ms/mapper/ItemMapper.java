package com.pes.doacao_ms.mapper;

import com.pes.doacao_ms.controller.request.IncludeOrUpdateItemRequest;
import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.response.ItemIdResponse;
import com.pes.doacao_ms.controller.response.ItemResponse;
import com.pes.doacao_ms.domain.Item;

public class ItemMapper {
    private static final int ONE_ITEM = 1;

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

    public static Item toEntity(IncludeOrUpdateItemRequest request) {
        Item itemEntity = new Item();

        itemEntity.setName(request.getName());

        if (request.getQuantity() != null) {
            itemEntity.setQuantity(request.getQuantity());
        } else {
            itemEntity.setQuantity(ONE_ITEM);
        }

        return itemEntity;
    }

    public static Item toModify(Item item, IncludeOrUpdateItemRequest request) {
        if (request.getQuantity() != null) {
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            item.setQuantity(item.getQuantity() + ONE_ITEM);
        }

        return item;
    }

    public static Item donatedToItem(ItemDoado itemDoado){
        Item item = new Item();

        // item.setCodBarras(itemDoado.getCodBarras());
        item.setCategoria(itemDoado.getCategoria());
        item.setName(itemDoado.getNome());
        item.setQuantity(itemDoado.getQtd());
        item.setUnidade(itemDoado.getUnidade());
        // item.setValidade(itemDoado.getValidade());

        return item;
    }

    public static Item donatedToModify(Item item, ItemDoado request) {
        if (request.getQtd() != null) {
            item.setQuantity(item.getQuantity() + request.getQtd());
        } else {
            item.setQuantity(item.getQuantity() + ONE_ITEM);
        }

        return item;
    }
}
