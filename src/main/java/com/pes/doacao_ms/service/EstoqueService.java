package com.pes.doacao_ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.domain.EstoqueItem;
import com.pes.doacao_ms.feignClients.estoque.EstoqueRepository;

@Service
public class EstoqueService {
    
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void cadastrarItem(ItemDoado itemDoado){
        EstoqueItem estoqueItem = new EstoqueItem(null, null, null, null);
        estoqueRepository.cadastrarItem(estoqueItem);
    }

    public EstoqueItem buscarItemEstoque(final String codigoItem){
        return estoqueRepository.buscarItemEstoque(codigoItem);
    }

    public Long verifyItemInStock(Long codCd, String nameItem){
        return estoqueRepository.verificaItemEstoque(codCd, nameItem);
    }

    public void atualizarItem(ItemDoado itemDoado) {
        EstoqueItem estoqueItem = new EstoqueItem(null, null, null, null);
        estoqueRepository.atualizarItem(estoqueItem);
    }
}
