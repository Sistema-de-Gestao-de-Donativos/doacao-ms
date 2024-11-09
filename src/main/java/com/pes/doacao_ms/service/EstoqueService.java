package com.pes.doacao_ms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.domain.EstoqueItem;
import com.pes.doacao_ms.feignClients.estoque.EstoqueRepository;

@Service
public class EstoqueService {
    
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void cadastrarItem(Integer codCd, List<ItemDoado> itemDoado){
        List<EstoqueItem> estoqueItem = itemDoado.stream().map(i -> EstoqueItem.builder()
        .nome(i.getNome())
        .unidade(i.getUnidade())
        .quantidade(i.getQtd())
        .categoria(i.getCategoria())
        .dataValidade(LocalDateTime.now())
        .build()).toList(); 
        estoqueRepository.cadastrarItem(codCd, estoqueItem);
    }

    public List<EstoqueItem> buscarItemEstoque(final Long codCd, final String codigoItem){
        return estoqueRepository.buscarItemEstoque(codCd, codigoItem);
    }

    // public Long verifyItemInStock(Long codCd, String nameItem){
    //     return estoqueRepository.verificaItemEstoque(codCd, nameItem);
    // }

    // public void atualizarItem(ItemDoado itemDoado) {
    //     EstoqueItem estoqueItem = new EstoqueItem(null, null, null, null);
    //     estoqueRepository.atualizarItem(estoqueItem);
    // }
}
