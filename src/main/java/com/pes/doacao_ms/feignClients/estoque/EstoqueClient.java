package com.pes.doacao_ms.feignClients.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pes.doacao_ms.dtos.EstoqueItemDTO;

import jakarta.websocket.server.PathParam;

@FeignClient(value = "estoque-ms", path = "/v1/stock", url = "${estoque-ms.url:#{null}}")
public interface EstoqueClient {

    @PostMapping(value = "/{codCd}")
    void cadastrarItem(@PathVariable("codCd") Integer codCd, @RequestBody final List<EstoqueItemDTO> item);

    @GetMapping(value = "/{codCd}/{nameItem}")
    List<EstoqueItemDTO> buscarItem(@PathVariable("nameItem") final String nameItem,
                              @PathVariable("codCd") final Long codigoCD);

    // @GetMapping(value = "/{codCd}/{nameItem}/")
    // Long verificaItemEstoque(@PathVariable("nameItem") final String nameItem,
    //                         @PathVariable("codCd") final Long codigoCD);

    // @PutMapping(value = "/item/update")
    // void atualizarItem(@RequestBody final EstoqueItemDTO item);
}