package com.pes.doacao_ms.feignClients.estoque;

import org.springframework.stereotype.Repository;

import com.pes.doacao_ms.domain.EstoqueItem;
import com.pes.doacao_ms.dtos.EstoqueItemDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EstoqueRepository {

    private final EstoqueClient estoqueClient;
    private final EstoqueConverter estoqueConverter;

    public void cadastrarItem(final EstoqueItem item){
        EstoqueItemDTO estoqueItemDTO = estoqueConverter.estoqueItemToEstoqueItemDTO(item);
        estoqueClient.cadastrarItem(estoqueItemDTO);
    }

    public EstoqueItem buscarItemEstoque(final String codigoItem){
        EstoqueItemDTO estoqueItemDTO = estoqueClient.buscarItem(codigoItem);
        return estoqueConverter.estoqueItemDTOToEstoqueItem(estoqueItemDTO);
    }
    
}
