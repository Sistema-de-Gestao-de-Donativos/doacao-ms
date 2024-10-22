package com.pes.doacao_ms.service;

import com.pes.doacao_ms.controller.request.EmailRequest;
import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.DoadorResponse;
import com.pes.doacao_ms.domain.Doador;
import com.pes.doacao_ms.mapper.DoadorMapper;
import com.pes.doacao_ms.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.pes.doacao_ms.mapper.DoadorMapper.toEntity;
import static com.pes.doacao_ms.mapper.DoadorMapper.toIdResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DoadorService {

    @Autowired
    DoadorRepository doadorRepository;

    public DoadorResponse getDoador(EmailRequest emailRequest) {
        return doadorRepository.findByEmailIgnoreCase(emailRequest.getEmail())
                .map(DoadorMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Doador not found"));
    }

    public DoadorIdResponse include(IncludeDoadorRequest request) {
        Doador doador = toEntity(request);
        doador.setNroDoacoes(0L);

        doadorRepository.save(doador);

        return toIdResponse(doador);
    }

    public void atualizaQtd(Long codDoador, int nroDoacoes){
        Doador doador = doadorRepository.findById(codDoador).get();
        doador.setNroDoacoes(doador.getNroDoacoes() + Integer.toUnsignedLong(nroDoacoes));
        doadorRepository.save(doador);
    }
}
