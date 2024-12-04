package com.pes.doacao_ms.service;

import static com.pes.doacao_ms.mapper.DoadorMapper.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.DoadorResponse;
import com.pes.doacao_ms.domain.Doador;
import com.pes.doacao_ms.mapper.DoadorMapper;
import com.pes.doacao_ms.repository.DoadorRepository;

@Service
public class DoadorService {

    @Autowired
    DoadorRepository doadorRepository;

    public DoadorResponse getDoador(String email) {
        return doadorRepository.findByEmailIgnoreCase(email)
                .map(DoadorMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Doador not found"));
    }

    public DoadorIdResponse include(IncludeDoadorRequest request) {
        Doador doador = toEntity(request);
        doador.setDonationsNumber(0L);

        doadorRepository.save(doador);

        return toIdResponse(doador);
    }

    public void updateDonationsNumber(Long codDoador, int nroDoacoes){
        Doador doador = doadorRepository.findById(codDoador).get();
        doador.setDonationsNumber(doador.getDonationsNumber() + Integer.toUnsignedLong(nroDoacoes));
        doadorRepository.save(doador);
    }
}
