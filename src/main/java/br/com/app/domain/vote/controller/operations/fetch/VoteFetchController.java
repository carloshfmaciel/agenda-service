package br.com.app.domain.vote.controller.operations.fetch;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.vote.controller.commons.response.VotingsSummaryResponse;
import br.com.app.domain.vote.service.VoteFetchService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/votings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class VoteFetchController implements VoteFetchControllerSwagger {
	
	VoteFetchService voteFetchService;
	
	@GetMapping("/summaries/agendas/{agendaId}")
	@ResponseStatus(HttpStatus.OK)
	public VotingsSummaryResponse fetchCountVotingsSummary(@PathVariable("agendaId") UUID agendaId) {
		return voteFetchService.fetchCountVotingsSummary(agendaId);
	}

}
