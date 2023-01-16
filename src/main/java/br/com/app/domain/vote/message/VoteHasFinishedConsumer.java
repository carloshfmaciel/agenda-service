package br.com.app.domain.vote.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import br.com.app.domain.vote.service.VoteHasFinishedService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteHasFinishedConsumer {

	VoteHasFinishedService voteHasFinishedService;

	@JmsListener(destination = "${message.topic.agenda-finish-votings}")
	public void consume(@Payload VotingsSummaryResponse message) {

		log.info(String.format("Receing message %s", message));

		voteHasFinishedService.sendEmail(message);
	}

}
