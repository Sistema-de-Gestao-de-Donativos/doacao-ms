package com.pes.doacao_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pes.doacao_ms.controller.request.DoacaoRequest;
import com.pes.doacao_ms.controller.request.GetDoacaoRequest;
import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.domain.Item;
import com.pes.doacao_ms.mapper.ItemMapper;
import com.pes.doacao_ms.service.DoacaoService;

import jakarta.validation.Valid;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/doacao")
@CrossOrigin(origins = "*")
public class DoacaoController {
    
    @Autowired
    DoacaoService doacaoService;

    @PostMapping
    public List<DoacaoResponse> receiveDoacao(@Valid @RequestBody DoacaoRequest doacao){
        Long codCD = doacao.getCodCD();
        Long codDoador = doacao.getCodDoador();
        List<ItemDoado> itens = doacao.getItens();
        Item item;
        List<DoacaoResponse> responses = new LinkedList<>();
        for (ItemDoado it : itens) {
            // item = ItemMapper::meuMap(it);
            item = ItemMapper.donatedToItem(it);
            
            responses.add(doacaoService.saveDoacao(codCD, codDoador, item.getId(), item.getQuantity()));
        }
        return responses;
    }

    @GetMapping
    public DoacaoResponse getDoacao(@Valid @RequestBody GetDoacaoRequest getDoacao){
        return doacaoService.getDoacao(getDoacao.getIdDoacao());
    }
}
