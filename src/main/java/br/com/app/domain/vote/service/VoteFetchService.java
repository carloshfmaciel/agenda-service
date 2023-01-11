package br.com.app.domain.vote.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import br.com.app.domain.vote.repository.VoteRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class VoteFetchService {

	VoteRepository voteRepository;

	AgendaRepository agendaRepository;
	
	public VotingsSummaryResponse fetchCountVotingsSummary(UUID agendaId) {
		Integer yesCount = voteRepository.fetchCountYesVotingsSummary(agendaId);
		Integer noCount = voteRepository.fetchCountNoVotingsSummary(agendaId);
		
		yesCount = yesCount == null ? 0 : yesCount;
		noCount = noCount == null ? 0 : noCount;

		Optional<Agenda> agenda = agendaRepository.findByIdAndAgendaStatusIsActiveAndAgendaIsReceivingVotings(agendaId);
		
		return new VotingsSummaryResponse(agendaId, agenda.get().getQuestion(), yesCount, noCount);
	}

}
