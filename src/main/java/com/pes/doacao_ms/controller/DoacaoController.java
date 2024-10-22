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
import com.pes.doacao_ms.service.DoacaoService;
import com.pes.doacao_ms.service.DoadorService;
import com.pes.doacao_ms.service.ItemService;

import jakarta.validation.Valid;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/doacao")
@CrossOrigin(origins = "*")
public class DoacaoController {
    
    @Autowired
    DoacaoService doacaoService;

    @Autowired
    ItemService itemService;

    @Autowired
    DoadorService doadorService;

    @PostMapping
    public List<DoacaoResponse> receiveDoacao(@Valid @RequestBody DoacaoRequest doacao){
        Long codCD = doacao.getCodCD();
        Long codDoador = doacao.getCodDoador();
        List<ItemDoado> itens = doacao.getItens();
        Long idItem;
        List<DoacaoResponse> responses = new LinkedList<>();
        for (ItemDoado it : itens) {
            idItem = itemService.includeName(it).getId();// TODO perguntar ao estoque pela existencia do item
            
            responses.add(doacaoService.saveDoacao(codCD, codDoador, idItem, it.getQtd()));
        }
        doadorService.atualizaQtd(codDoador, responses.size());
        // TODO enviar os itens para o estoque 
        return responses;
    }

    // TODO adicionar outras formas de get. Ex.: data, CD, doador, item
    @GetMapping
    public DoacaoResponse getDoacao(@Valid @RequestBody GetDoacaoRequest getDoacao){
        return doacaoService.getDoacao(getDoacao.getIdDoacao());
    }
}
