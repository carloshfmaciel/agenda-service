package br.com.app.domain.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.controller.commons.converter.AgendaConverter;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.vo.AgendaVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaCreateService {

	AgendaRepository agendaRepository;

	public AgendaVO create(AgendaVO agendaVO) {
		Agenda agenda = AgendaConverter.toEntity(agendaVO);
		agenda = agendaRepository.save(agenda);
		return AgendaConverter.toVO(agenda);
	}
}
