package com.pes.doacao_ms.mapper;

import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.DoadorResponse;
import com.pes.doacao_ms.domain.Doador;

public class DoadorMapper {

    public static DoadorResponse toResponse(Doador doador) {
        DoadorResponse doadorResponse = new DoadorResponse();

        doadorResponse.setId(doador.getId());
        doadorResponse.setName(doador.getName());
        doadorResponse.setDocument(doador.getDocument());
        doadorResponse.setEmail(doador.getEmail());
        doadorResponse.setPhone(doador.getPhone());
        doadorResponse.setNroDoacoes(doador.getNroDoacoes());

        return doadorResponse;
    }

    public static DoadorIdResponse toIdResponse(Doador doador) {
        DoadorIdResponse doadorIdResponse = new DoadorIdResponse();

        doadorIdResponse.setId(doador.getId());

        return doadorIdResponse;
    }

    public static Doador toEntity(IncludeDoadorRequest request) {
        Doador entity = new Doador();

        entity.setName(request.getName());
        entity.setDocument(request.getDocument());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());

        return entity;
    }
}
