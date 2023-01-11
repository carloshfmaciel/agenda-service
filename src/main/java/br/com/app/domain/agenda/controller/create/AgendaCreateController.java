package br.com.app.domain.agenda.controller.create;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.agenda.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.commons.request.AgendaCreateRequest;
import br.com.app.domain.agenda.commons.response.AgendaResponse;
import br.com.app.domain.agenda.service.AgendaCreateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/agendas")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaCreateController implements AgendaCreateControllerSwagger {

	AgendaCreateService agendaCreateService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AgendaResponse create(@Valid @RequestBody AgendaCreateRequest agendaCreateRequest) {
		return AgendaConverter.toResponse(agendaCreateService.create(AgendaConverter.toVO(agendaCreateRequest)));
	}

}
