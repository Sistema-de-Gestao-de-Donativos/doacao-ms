package com.pes.doacao_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pes.doacao_ms.domain.Doacao;
import java.util.Optional;
import java.util.List;
import java.time.ZonedDateTime;




public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
    
    Optional<Doacao> findByIdDoacao(Long idDoacao);
    
    List<Doacao> findByIdDoador(Long idDoador);
    List<Doacao> findByIdItem(String idItem);


    // TODO arrumar esses finds by data
    List<Doacao> findByDataDoacao(ZonedDateTime dataDoacao);
    List<Doacao> findByDataDoacaoBetween(ZonedDateTime startDate,ZonedDateTime endDate);
    List<Doacao> findAllByOrderByDataDoacaoAsc();

}
