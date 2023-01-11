package br.com.app.domain.agenda.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaDeleteService {

	AgendaRepository agendaRepository;

	public void delete(UUID agendaId) {
		Agenda agenda = agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)
				.orElseThrow(() -> new AgendaNotFoundException(Error.AGENDA_NOT_FOUND.getMessage(),
						Error.AGENDA_NOT_FOUND.getCode()));
		agenda.setStatus(AgendaStatus.INATIVO);
		agendaRepository.save(agenda);
	}

}
