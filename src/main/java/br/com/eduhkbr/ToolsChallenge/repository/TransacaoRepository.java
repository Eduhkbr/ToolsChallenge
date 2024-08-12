package br.com.eduhkbr.ToolsChallenge.repository;

import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
