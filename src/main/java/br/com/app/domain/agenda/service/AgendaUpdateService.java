package br.com.app.domain.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.infrastructure.errorhandling.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaUpdateService {

	AgendaRepository agendaRepository;

	public AgendaVO update(AgendaVO agendaVO) {
		Agenda agenda = agendaRepository.findByIdAndAgendaStatusIsActive(agendaVO.getId())
				.orElseThrow(() -> new AgendaNotFoundException(Error.AGENDA_NOT_FOUND.getMessage(),
						Error.AGENDA_NOT_FOUND.getCode()));
		AgendaConverter.copyFromFirstToSecond(agendaVO, agenda);
		agendaRepository.save(agenda);
		return AgendaConverter.toVO(agenda);
	}

}
