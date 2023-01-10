package br.com.app.domain.agenda.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.repository.AgendaSpecification;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.infrastructure.errorhandling.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaFetchService {

	AgendaRepository agendaRepository;

	public AgendaVO fetchById(UUID agendaId) {
		Agenda agenda = agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)
				.orElseThrow(() -> new AgendaNotFoundException(Error.AGENDA_NOT_FOUND.getMessage(),
						Error.AGENDA_NOT_FOUND.getCode()));
		return AgendaConverter.toVO(agenda);
	}

	public Page<Agenda> fetch(AgendaStatus status, Pageable pageRequest) {
		return agendaRepository.findAll(new AgendaSpecification().with(status), pageRequest);
	}

}
