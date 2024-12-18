package com.pes.doacao_ms.feignClients.estoque;

import java.util.List;

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

    public void cadastrarItem(final Integer codCd, final List<EstoqueItem> item){
        log.info("Cadastrando item no estoque: {}", item);
        List<EstoqueItemDTO> estoqueItemDTO = estoqueConverter.estoqueItemToEstoqueItemDTO(item);
        estoqueClient.cadastrarItem(codCd,estoqueItemDTO);
    }

    public List<EstoqueItem> buscarItemEstoque(final Long codCd, final String codigoItem){
        log.info("Buscando item no estoque com código: {}", codigoItem);
        List<EstoqueItemDTO> estoqueItemDTO = estoqueClient.buscarItem(codigoItem, codCd);
        return estoqueConverter.estoqueItemDTOToEstoqueItem(estoqueItemDTO);
    }

    // public Long verificaItemEstoque(Long codCd, String nameItem) {

    //     try {
    //         log.info("Verificando item no estoque com nome {} no CD {}", nameItem, codCd);
    //         return estoqueClient.verificaItemEstoque(nameItem, codCd);
    //     } catch (Exception e) {
    //         log.info("Item não encontrado: {}", e.getMessage());
    //         return -1L;
    //     }
    // }

    // public void atualizarItem(final EstoqueItem item) {
    //     log.info("Atualizando item no estoque: {}", item);
    //     EstoqueItemDTO estoqueItemDTO = estoqueConverter.estoqueItemToEstoqueItemDTO(item);
    //     estoqueClient.atualizarItem(estoqueItemDTO);
    // }
    
}
