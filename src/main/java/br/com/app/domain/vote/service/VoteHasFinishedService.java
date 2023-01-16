package br.com.app.domain.vote.service;

import org.springframework.stereotype.Service;

import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VoteHasFinishedService {
	
	/**
	 * It sends email for all users with votings summary count result
	 * @param votingsSummaryResponse
	 */
	public void sendEmail(VotingsSummaryResponse votingsSummaryResponse) {
		log.info("Sending voting summary by email!");
	}
	

}
