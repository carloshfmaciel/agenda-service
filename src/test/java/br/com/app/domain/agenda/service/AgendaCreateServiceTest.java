package br.com.app.domain.agenda.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.domain.agenda.builder.AgendaBuilder;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.vo.AgendaVO;

@DisplayName("Writing assertions to create agenda")
class AgendaCreateServiceTest {
	
	@Mock
	AgendaRepository agendaRepository;

	@InjectMocks
	AgendaCreateService agendaCreateService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Writing assertions to create agenda with success")
	void creating_agenda_with_success() {
		
		AgendaVO agendaVORequest = AgendaBuilder.buildVO();
		
		Agenda agendaCreated = AgendaBuilder.buildEntity();
		agendaCreated.setId(UUID.randomUUID());
		
		when(agendaRepository.save(any(Agenda.class))).thenReturn(agendaCreated);
		
		AgendaVO agendaVOCreated = agendaCreateService.create(agendaVORequest);
		
		assertAll(() -> assertNotNull(agendaVOCreated, "agendaVOCreated object should not be null"),
				() -> assertNotNull(agendaVOCreated.getId(), "agendaVOCreated.getId() should not be null"),
				() -> assertEquals(agendaVORequest.getQuestion(), agendaVOCreated.getQuestion(),
						String.format("question should be %s", agendaVORequest.getQuestion())),
				() -> assertNull(agendaVOCreated.getStartVote(), String.format("startVote should be null")),
				() -> assertNull(agendaVOCreated.getEndVote(), String.format("endVote should be null")));
	}

}
