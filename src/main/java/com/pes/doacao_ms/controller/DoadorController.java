package com.pes.doacao_ms.controller;

import com.pes.doacao_ms.controller.request.EmailRequest;
import com.pes.doacao_ms.controller.request.IncludeDoadorRequest;
import com.pes.doacao_ms.controller.response.DoadorIdResponse;
import com.pes.doacao_ms.controller.response.DoadorResponse;
import com.pes.doacao_ms.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/doador")
@CrossOrigin(origins = "*")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    @ResponseStatus(OK)
    public DoadorResponse get(@Valid @RequestBody EmailRequest emailRequest) {
        return doadorService.getDoador(emailRequest);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DoadorIdResponse include(@Valid @RequestBody IncludeDoadorRequest includeDoadorRequest) {
        return doadorService.include(includeDoadorRequest);
    }
}
