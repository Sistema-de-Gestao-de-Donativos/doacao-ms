package com.pes.doacao_ms.feignClients.estoque;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pes.doacao_ms.domain.EstoqueItem;
import com.pes.doacao_ms.dtos.EstoqueItemDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstoqueConverter {

    List<EstoqueItem> estoqueItemDTOToEstoqueItem(List<EstoqueItemDTO> estoqueItemDTO);

    List<EstoqueItemDTO> estoqueItemToEstoqueItemDTO(List<EstoqueItem> estoqueItem);
}
