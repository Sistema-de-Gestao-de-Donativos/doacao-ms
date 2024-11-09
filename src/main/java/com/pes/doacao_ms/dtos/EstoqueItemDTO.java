package com.pes.doacao_ms.dtos;

import java.time.LocalDateTime;

public record EstoqueItemDTO(
    // String codCd,
    String _id,
    String nome,
    String unidade,
    Integer quantidade,
    String categoria,
    LocalDateTime dataValidade
) {
    
}
