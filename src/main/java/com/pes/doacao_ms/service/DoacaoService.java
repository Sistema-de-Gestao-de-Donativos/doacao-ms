package com.pes.doacao_ms.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.domain.Doacao;
import com.pes.doacao_ms.mapper.DoacaoMapper;
import com.pes.doacao_ms.repository.DoacaoRepository;

@Service
public class DoacaoService {
    
    @Autowired
    DoacaoRepository doacaoRepository;
    

    public DoacaoResponse saveDoacao(Long codCD, Long codDoador, Long itemID, Integer qtd){
        Doacao d = DoacaoMapper.toEntity(codCD, codDoador, String.valueOf(itemID), qtd);
        
        doacaoRepository.save(d);

        return DoacaoMapper.toResponse(d);
    }

    public DoacaoResponse getDoacao(Long idDoacao){
        return doacaoRepository.findByIdDoacao(idDoacao)
            .map(DoacaoMapper::toResponse)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Doacao not found"));
    }
}
