package com.pes.doacao_ms.mapper;

import java.time.ZonedDateTime;

import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.request.ItemFromStock;
import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.domain.Doacao;

public class DoacaoMapper {
    
    public static Doacao toEntity(Long codCD, Long codDoador, String idItem, Integer qtd){
        Doacao entity = new Doacao();

        entity.setCodCD(codCD);
        entity.setIdDoador(codDoador);
        entity.setIdItem(idItem);
        

        entity.setQtd(qtd);
        // entity.setIdItem(item.get);
        entity.setDataDoacao(ZonedDateTime.now());

        return entity;
    }

    public static DoacaoResponse toResponse(Doacao doacao){
        DoacaoResponse response = new DoacaoResponse();

        response.setIdDoacao(doacao.getIdDoacao());
        response.setCodCD(doacao.getCodCD());
        response.setIdDoador(doacao.getIdDoador());
        response.setDataDoacao(doacao.getDataDoacao());
        response.setIdItem(doacao.getIdItem());
        response.setQtd(doacao.getQtd());

        return response;
    }

    public static ItemFromStock toStock(ItemDoado itemDoado){
        ItemFromStock itemStock = new ItemFromStock();

        itemStock.setCategoria(itemDoado.getCategoria());
        // itemStock.setDataValidade(itemDoado.getValidade());
        itemStock.setNome(itemDoado.getNome());
        itemStock.setQuantidade(itemDoado.getQtd().longValue());
        itemStock.setUnidade(itemDoado.getUnidade());

        return itemStock;
    }
}
