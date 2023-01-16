package br.com.app.domain.agenda.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import br.com.app.domain.vote.message.VoteHasFinishedProducer;
import br.com.app.domain.vote.service.VoteFetchService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaAdvisorVotingsHasFinishedService {
	
	VoteFetchService voteFetchService;
	
	VoteHasFinishedProducer voteHasFinishedProducer;

	public void adviseAll(UUID agendaId) {
		VotingsSummaryResponse votingsSummary = voteFetchService.fetchCountVotingsSummary(agendaId);
		voteHasFinishedProducer.send(votingsSummary);
	}
	
	public void adviseAll(Set<UUID> agendaIds) {
		agendaIds.forEach(agendaId -> {
			this.adviseAll(agendaId);
		});
	}
	
}
