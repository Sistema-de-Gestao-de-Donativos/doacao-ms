package com.pes.doacao_ms.feignClients.estoque;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.pes.doacao_ms.domain.EstoqueItem;
import com.pes.doacao_ms.dtos.EstoqueItemDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstoqueConverter {

    EstoqueItem estoqueItemDTOToEstoqueItem(EstoqueItemDTO estoqueItemDTO);

    EstoqueItemDTO estoqueItemToEstoqueItemDTO(EstoqueItem estoqueItem);
}
