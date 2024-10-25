package com.pes.doacao_ms.service;

import static org.springframework.http.HttpStatus.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pes.doacao_ms.controller.request.DoacaoRequest;
import com.pes.doacao_ms.controller.request.ItemDoado;
import com.pes.doacao_ms.controller.response.DoacaoResponse;
import com.pes.doacao_ms.domain.Doacao;
import com.pes.doacao_ms.mapper.DoacaoMapper;
import com.pes.doacao_ms.repository.DoacaoRepository;

@Service
public class DoacaoService {
    
    @Autowired
    DoacaoRepository doacaoRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    DoadorService doadorService;

    @Autowired
    EstoqueService estoqueService;

    public void saveDoacao(DoacaoRequest doacao){

        doacao.getItens().stream()
        .forEach(item -> {
            Long idItem = itemService.includeName(item).getId();
            Doacao d = DoacaoMapper.toEntity(doacao.getCodCD(), doacao.getCodDoador(), idItem, item.getQtd());
            doacaoRepository.save(d);

            // comentado pois é necessario ajustar as urls no EstoqueClient, confirmar isso com time de estoque
            //atualizaEstoque(item, doacao.getCodCD());
        });

        doadorService.updateDonationsNumber(doacao.getCodDoador(),  doacao.getItens().size());
    }

    public DoacaoResponse getDoacao(Long idDoacao){
        return doacaoRepository.findByIdDoacao(idDoacao)
            .map(DoacaoMapper::toResponse)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Doacao not found"));
    }

    public List<DoacaoResponse> buscarPorData(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate == null ? startDate.atTime(LocalTime.MAX) : endDate.atTime(LocalTime.MAX);
        
        return doacaoRepository.findByDataDoacaoBetween(startOfDay, endOfDay).stream()
        .map(DoacaoMapper::toResponse)
        .toList();
    }

    private void atualizaEstoque(ItemDoado itemDoado,Long codCd){
        Long idItem = estoqueService.verifyItemInStock(codCd, itemDoado.getNome());

        if(idItem < 0){
            estoqueService.cadastrarItem(itemDoado);
        }
        else{
            estoqueService.atualizarItem(itemDoado);
        }
    }
}
