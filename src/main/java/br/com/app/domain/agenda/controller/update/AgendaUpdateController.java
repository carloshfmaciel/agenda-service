package br.com.app.domain.agenda.controller.update;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.agenda.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.commons.request.AgendaUpdateRequest;
import br.com.app.domain.agenda.commons.response.AgendaResponse;
import br.com.app.domain.agenda.service.AgendaUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/agendas")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaUpdateController {

	AgendaUpdateService agendaUpdateService;

	@PutMapping("/{agendaId}")
	@ResponseStatus(HttpStatus.OK)
	public AgendaResponse create(@PathVariable("agendaId") UUID agendaId,
			@RequestBody AgendaUpdateRequest agendaUpdateRequest) {
		return AgendaConverter.toResponse(agendaUpdateService.update(AgendaConverter.toVO(agendaUpdateRequest)));
	}

}
