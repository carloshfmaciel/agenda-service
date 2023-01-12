package br.com.app.domain.agenda.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import br.com.app.domain.agenda.builder.AgendaBuilder;
import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.agenda.vo.AgendaVO;

@DisplayName("Writing assertions to fetch agenda")
class AgendaFetchServiceTest {

	@Mock
	AgendaRepository agendaRepository;

	@InjectMocks
	AgendaFetchService agendaFetchService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("When inform a valida agenda id it should return with success")
	void fetch_agenda_by_id_with_success() {

		UUID agendaId = UUID.randomUUID();

		Agenda agenda = AgendaBuilder.buildEntity();
		agenda.setStatus(AgendaStatus.ATIVO);
		agenda.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.of(agenda));

		AgendaVO agendaReturned = agendaFetchService.fetchById(agendaId);

		assertAll(() -> assertNotNull(agendaReturned, "agendaReturned object should not be null"),
				() -> assertNotNull(agendaReturned.getId(), "agendaReturned.getId() should not be null"),
				() -> assertEquals(agenda.getQuestion(), agendaReturned.getQuestion(),
						String.format("question should be %s", agenda.getQuestion())),
				() -> assertEquals(agenda.getStatus(), agendaReturned.getStatus(),
						String.format("status should be %s", agenda.getStatus())));
	}

	@Test
	@DisplayName("When inform invalid agenda id it must throws AgendaNotFoundException")
	void when_inform_invalid_agenda_id_it_must_throws_AgendaNotFoundException() {

		UUID agendaId = UUID.randomUUID();

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.empty());

		assertThrows(AgendaNotFoundException.class, () -> agendaFetchService.fetchById(agendaId),
				"It must throw AgendaNotFoundException");
	}
	
	@Test
	@DisplayName("When fetching by status it should return with success")
	void fetch_agenda_by_status_it_should_return_with_success() {
		
		Agenda agendaOne = AgendaBuilder.buildEntity();
		agendaOne.setStatus(AgendaStatus.ATIVO);
		agendaOne.setId(UUID.randomUUID());
		
		Agenda agendaTwo = AgendaBuilder.buildEntity();
		agendaTwo.setStatus(AgendaStatus.ATIVO);
		agendaTwo.setId(UUID.randomUUID());
		
		List<Agenda> agendas = List.of(agendaOne, agendaTwo);
		Page<Agenda> pages = new PageImpl<Agenda>(agendas);
		
		when(agendaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pages);

		Page<Agenda> agendaPage = agendaFetchService.fetch(AgendaStatus.ATIVO, PageRequest.of(0, 30, Sort.by("question")));

		assertAll(() -> assertNotNull(agendaPage, "Agenda page should not be null"),
				() -> assertEquals(2, agendaPage.getTotalElements(), "total elements should be 2"));

		verify(agendaRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
	}
	
	@Test
	@DisplayName("When inform invalid agenda id it must throws AgendaNotFoundException")
	void when_there_is_no_agenda_with_active_status_it_must_throws_AgendaNotFoundException() {
		
		Page<Agenda> pages = Page.empty();
		when(agendaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pages);

		Page<Agenda> agendaPage = agendaFetchService.fetch(AgendaStatus.ATIVO, PageRequest.of(0, 30, Sort.by("question")));

		assertAll(() -> assertNotNull(agendaPage, "Plan page should not be null"),
				() -> assertEquals(0, agendaPage.getTotalElements(), "total elements should be 0"));

		verify(agendaRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
	}

}
