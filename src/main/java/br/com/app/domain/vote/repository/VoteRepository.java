package br.com.app.domain.vote.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.app.domain.vote.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
	
	@Query("SELECT COUNT(v.answer) FROM br.com.app.domain.vote.model.Vote v "
			+ "WHERE v.agendaId = :agendaId AND v.answer = 'YES' GROUP BY v.answer")
	int fetchCountYesVotingsSummary(@Param("agendaId") UUID agendaId);
	
	@Query("SELECT COUNT(v.answer) FROM br.com.app.domain.vote.model.Vote v "
			+ "WHERE v.agendaId = :agendaId AND v.answer = 'NO' GROUP BY v.answer")
	int fetchCountNoVotingsSummary(@Param("agendaId") UUID agendaId);
	
	Optional<Vote> findByUserIdAndAgendaId(UUID userId, UUID agendaId);

}
