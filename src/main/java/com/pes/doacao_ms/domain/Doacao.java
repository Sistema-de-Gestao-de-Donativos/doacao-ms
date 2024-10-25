package com.pes.doacao_ms.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Doacoes")
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoacao;

    private Long codCD;
    private Long idDoador;
    private String idItem;
    private Integer qtd;
    
    @Column(name = "data_doacao")
    private LocalDateTime dataDoacao;


    @PrePersist
    public void preInclusao() {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        this.dataDoacao = ZonedDateTime.now(zoneId).toLocalDateTime();
    }
}
