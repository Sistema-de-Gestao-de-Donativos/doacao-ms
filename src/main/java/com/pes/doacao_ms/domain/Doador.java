package com.pes.doacao_ms.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doador")
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String document;
    private String email;
    private String phone;
}
