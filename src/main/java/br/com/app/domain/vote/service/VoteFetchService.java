package br.com.app.domain.vote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public VotingsSummaryResponse fetchCountVotingsSummary(UUID agendaId) {
		int yesCount = voteRepository.fetchCountYesVotingsSummary(agendaId);
		int noCount = voteRepository.fetchCountNoVotingsSummary(agendaId);

		return new VotingsSummaryResponse(yesCount, noCount);
	}

}
