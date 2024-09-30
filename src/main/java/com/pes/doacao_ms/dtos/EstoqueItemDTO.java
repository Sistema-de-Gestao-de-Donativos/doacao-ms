package com.pes.doacao_ms.dtos;

import java.time.LocalDate;

public record EstoqueItemDTO(
    String codCd,
    String name,
    Integer quantidade,
    LocalDate dataDoacao
) {
    
}
