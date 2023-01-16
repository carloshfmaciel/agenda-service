package br.com.app.domain.vote.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteHasFinishedProducer {

	@Value("${message.topic.agenda-finish-votings}")
	private String topic;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(VotingsSummaryResponse message) {
		
		log.info(String.format("Sending message %s to topic %s", message, topic));
		
		try {
			jmsTemplate.convertAndSend(topic, message);
		} catch (Exception e) {
			log.error(String.format("Error to sending message %s to topic %s", message, topic), e);
		}		
	}

}
