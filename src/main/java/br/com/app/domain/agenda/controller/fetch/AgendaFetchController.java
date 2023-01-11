package br.com.app.domain.agenda.controller.fetch;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.agenda.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.commons.response.AgendaResponse;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.service.AgendaFetchService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/agendas")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaFetchController implements AgendaFetchControllerSwagger {

	AgendaFetchService agendaFetchService;

	@GetMapping("/{agendaId}")
	@ResponseStatus(HttpStatus.OK)
	public AgendaResponse fetchById(@PathVariable("agendaId") UUID agendaId) {
		return AgendaConverter.toResponse(agendaFetchService.fetchById(agendaId));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<AgendaResponse> fetch(AgendaStatus status, Integer page, Integer pageSize, String sortBy) {
		return agendaFetchService.fetch(status, PageRequest.of(page, pageSize, Sort.by(sortBy)))
				.map(AgendaConverter::toResponse);
	}

}
