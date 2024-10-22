package com.pes.doacao_ms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.ZonedDateTime;

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
    private ZonedDateTime dataDoacao;
}
