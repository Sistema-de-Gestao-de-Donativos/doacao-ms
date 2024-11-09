package com.pes.doacao_ms.domain;

import java.time.LocalDateTime;

// import java.time.LocalDate;

import lombok.Builder;

@Builder
public record EstoqueItem(
    // String codCd,
    String _id,
    String nome,
    String unidade,
    Integer quantidade,
    String categoria,
    LocalDateTime dataValidade
){
    
}
