package br.com.app.domain.agenda.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.domain.agenda.builder.AgendaBuilder;
import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.vo.AgendaVO;

@DisplayName("Writing assertions to inactivate agenda")
class AgendaDeleteServiceTest {

	@Mock
	AgendaRepository agendaRepository;

	@InjectMocks
	AgendaDeleteService agendaDeleteService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Writing assertions to inactivate agenda with success")
	void inactivate_agenda_with_success() {

		UUID agendaId = UUID.randomUUID();

		Agenda agenda = AgendaBuilder.buildEntity();
		agenda.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.of(agenda));

		agenda.setStatus(AgendaStatus.INATIVO);

		when(agendaRepository.save(any(Agenda.class))).thenReturn(agenda);

		AgendaVO agendaInactivate = agendaDeleteService.delete(agendaId);

		assertAll(() -> assertNotNull(agendaInactivate, "agendaInactivate object should not be null"),
				() -> assertNotNull(agendaInactivate.getId(), "agendaVOCreated.getId() should not be null"),
				() -> assertEquals(AgendaStatus.INATIVO, agendaInactivate.getStatus(),
						String.format("status should be INATIVO")));
	}

	@Test
	@DisplayName("When inform invalid agenda id it must throws AgendaNotFoundException")
	void when_inform_invalid_agenda_id_it_must_throws_AgendaNotFoundException() {

		UUID agendaId = UUID.randomUUID();

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.empty());

		assertThrows(AgendaNotFoundException.class, () -> agendaDeleteService.delete(agendaId),
				"It must throw AgendaNotFoundException");
	}

}
