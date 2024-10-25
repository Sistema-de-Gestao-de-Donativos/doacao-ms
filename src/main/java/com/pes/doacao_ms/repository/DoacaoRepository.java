package com.pes.doacao_ms.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pes.doacao_ms.domain.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
    
    Optional<Doacao> findByIdDoacao(Long idDoacao);
    
    List<Doacao> findByIdDoador(Long idDoador);
    List<Doacao> findByIdItem(String idItem);


    // TODO arrumar esses finds by data
    List<Doacao> findByDataDoacaoBetween(LocalDateTime startDate, LocalDateTime endDate);
    // List<Doacao> findAllByOrderByDataDoacaoAsc();

}
