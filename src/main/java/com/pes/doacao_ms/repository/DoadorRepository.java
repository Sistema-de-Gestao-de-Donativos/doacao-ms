package com.pes.doacao_ms.repository;

import com.pes.doacao_ms.domain.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoadorRepository extends JpaRepository<Doador, Long> {

    Optional<Doador> findByEmailIgnoreCase(String email);

    Optional<Doador> findByDocument(String document);
}
