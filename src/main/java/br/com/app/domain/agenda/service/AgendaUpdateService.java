package br.com.app.domain.agenda.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.controller.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.validation.UpdateAgendaVoteTimeValidation;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaUpdateService {

	AgendaRepository agendaRepository;
	
	UpdateAgendaVoteTimeValidation updateAgendaVoteTimeValidation;

	public AgendaVO update(AgendaVO agendaVO) {
		Agenda agenda = agendaRepository.findByIdAndAgendaStatusIsActive(agendaVO.getId())
				.orElseThrow(() -> new AgendaNotFoundException(Error.AGENDA_NOT_FOUND.getMessage(),
						Error.AGENDA_NOT_FOUND.getCode()));
		AgendaConverter.copyFromFirstToSecond(agendaVO, agenda);
		agendaRepository.save(agenda);
		return AgendaConverter.toVO(agenda);
	}

	public AgendaVO updateVoteTime(AgendaVO agendaVO) {
		updateAgendaVoteTimeValidation.validate(agendaVO);
		
		Agenda agenda = agendaRepository.findByIdAndAgendaStatusIsActive(agendaVO.getId())
				.orElseThrow(() -> new AgendaNotFoundException(Error.AGENDA_NOT_FOUND.getMessage(),
						Error.AGENDA_NOT_FOUND.getCode()));

		// ADDS ONE MINUTE WHEN NO DATETIME IS INFORMED
		addsOneMinute(agendaVO);

		AgendaConverter.copyFromFirstToSecond(agendaVO, agenda);
		agendaRepository.save(agenda);
		return AgendaConverter.toVO(agenda);
	}

	private void addsOneMinute(AgendaVO agendaVO) {
		if (agendaVO.getStartVote() == null && agendaVO.getEndVote() == null) {
			agendaVO.setStartVote(LocalDateTime.now());
			agendaVO.setEndVote(LocalDateTime.now().plusMinutes(1));
		}
	}

}
