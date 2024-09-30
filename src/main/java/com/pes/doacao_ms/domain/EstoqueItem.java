package com.pes.doacao_ms.domain;

import java.time.LocalDate;

public record EstoqueItem(
    String codCd,
    String name,
    Integer quantidade,
    LocalDate dataDoacao
){
    
}
