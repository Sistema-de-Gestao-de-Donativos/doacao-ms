package com.pes.doacao_ms.repository;

import com.pes.doacao_ms.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);
    Optional<Item> findByName(String name);
}
