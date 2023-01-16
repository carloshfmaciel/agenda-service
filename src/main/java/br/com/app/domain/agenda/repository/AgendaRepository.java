package br.com.app.domain.agenda.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.app.domain.agenda.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID>, JpaSpecificationExecutor<Agenda> {
	
	@Query("SELECT a FROM br.com.app.domain.agenda.model.Agenda a WHERE a.id = :agendaId AND a.status = 'ATIVO' ")
	Optional<Agenda> findByIdAndAgendaStatusIsActive(@Param("agendaId") UUID agendaId);

	@Query("SELECT a FROM br.com.app.domain.agenda.model.Agenda a "
			+ "WHERE a.id = :agendaId AND a.status = 'ATIVO' AND CURRENT_TIMESTAMP BETWEEN a.startVote AND a.endVote ")
	Optional<Agenda> findByIdAndAgendaStatusIsActiveAndAgendaIsReceivingVotings(@Param("agendaId") UUID agendaId);

	@Query("SELECT a FROM br.com.app.domain.agenda.model.Agenda a WHERE a.endVote < CURRENT_TIMESTAMP AND a.status = 'ATIVO' ")
	List<Agenda> findAgendaVotingsFinalizedAndStatusIsActive();

	
}
