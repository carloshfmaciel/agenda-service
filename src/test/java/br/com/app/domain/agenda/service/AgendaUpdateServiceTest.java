package br.com.app.domain.agenda.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import br.com.app.domain.agenda.validation.AgendaUpdateVoteTimeValidation;
import br.com.app.domain.agenda.vo.AgendaVO;

@DisplayName("Writing assertions to update agenda")
class AgendaUpdateServiceTest {

	@Mock
	AgendaRepository agendaRepository;

	@Mock
	AgendaUpdateVoteTimeValidation agendaUpdateVoteTimeValidation;

	@InjectMocks
	AgendaUpdateService agendaUpdateService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Writing assertions to update agenda with success")
	void updating_agenda_with_success() {

		UUID agendaId = UUID.randomUUID();

		AgendaVO agendaRequest = AgendaBuilder.buildVO();
		agendaRequest.setStatus(AgendaStatus.ATIVO);
		agendaRequest.setId(agendaId);

		Agenda agenda = AgendaBuilder.buildEntity();
		agenda.setStatus(AgendaStatus.ATIVO);
		agenda.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.of(agenda));
		when(agendaRepository.save(any(Agenda.class))).thenReturn(agenda);

		AgendaVO agendaUpdated = agendaUpdateService.update(agendaRequest);
		// @formatter:off
		assertAll(() -> assertNotNull(agendaUpdated, "agendaUpdated object should not be null"),
				() -> assertNotNull(agendaUpdated.getId(), "agendaUpdated.getId() should not be null"),
				() -> assertEquals(agendaRequest.getQuestion(), agendaUpdated.getQuestion(),
						String.format("question should be %s", agendaRequest.getQuestion())),
				() -> assertEquals(agendaRequest.getStatus(), agendaUpdated.getStatus(),
						String.format("status should be %s", agendaRequest.getStatus())));
		// @formatter:on

		verify(agendaRepository, times(1)).findByIdAndAgendaStatusIsActive(agendaId);
		verify(agendaRepository, times(1)).save(any());
	}

	@Test
	@DisplayName("When try update agenda whose id doesnt exist it should throw AgendaNotFoundException")
	void when_try_update_agenda_whose_id_doesnt_exist_should_throw_AgendaNotFoundException() {

		UUID agendaId = UUID.randomUUID();

		AgendaVO agendaRequest = AgendaBuilder.buildVO();
		agendaRequest.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.empty());

		assertThrows(AgendaNotFoundException.class, () -> agendaUpdateService.update(agendaRequest),
				"It should throw AgendaNotFoundException");

		verify(agendaRepository, times(1)).findByIdAndAgendaStatusIsActive(agendaId);
	}

	@Test
	@DisplayName("Writing assertions to update vote period time with success")
	void updating_vote_period_time_with_success() {

		UUID agendaId = UUID.randomUUID();
		
		LocalDateTime startVote = LocalDateTime.now();
		LocalDateTime endVote = LocalDateTime.now().plusHours(24);

		AgendaVO agendaRequest = AgendaBuilder.buildVO();
		agendaRequest.setStatus(AgendaStatus.ATIVO);
		agendaRequest.setStartVote(startVote);
		agendaRequest.setEndVote(endVote);
		agendaRequest.setId(agendaId);
		
		Agenda agendaWithNoDates = AgendaBuilder.buildEntity();
		agendaWithNoDates.setStatus(AgendaStatus.ATIVO);
		agendaWithNoDates.setId(agendaId);

		Agenda agendaWihDates = AgendaBuilder.buildEntity();
		agendaWihDates.setStatus(AgendaStatus.ATIVO);
		agendaWihDates.setStartVote(startVote);
		agendaWihDates.setEndVote(endVote);
		agendaWihDates.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.of(agendaWithNoDates));
		when(agendaRepository.save(any(Agenda.class))).thenReturn(agendaWihDates);

		AgendaVO agendaUpdated = agendaUpdateService.updateVoteTime(agendaRequest);
		// @formatter:off
		assertAll(() -> assertNotNull(agendaUpdated, "agendaUpdated object should not be null"),
				() -> assertNotNull(agendaUpdated.getId(), "agendaUpdated.getId() should not be null"),
				() -> assertEquals(agendaRequest.getQuestion(), agendaUpdated.getQuestion(),
						String.format("question should be %s", agendaRequest.getQuestion())),
				() -> assertEquals(agendaRequest.getStatus(), agendaUpdated.getStatus(),
						String.format("status should be %s", agendaRequest.getStatus())),
				() -> assertEquals(agendaRequest.getStartVote(), agendaUpdated.getStartVote(),
						String.format("startVote should be %s", agendaRequest.getStartVote())),
				() -> assertEquals(agendaRequest.getEndVote(), agendaUpdated.getEndVote(),
						String.format("endVote should be %s", agendaRequest.getEndVote())));
		// @formatter:on

		verify(agendaRepository, times(1)).findByIdAndAgendaStatusIsActive(agendaId);
		verify(agendaRepository, times(1)).save(any());
	}
	
	@Test
	@DisplayName("When trying to update vote period time informing a invalid agenda id it should throw AgendaNotFoundException")
	void when_trying_to_update_vote_period_time_informing_invalid_agenda_id_it_should_throw_AgendaNotFoundException() {

		UUID agendaId = UUID.randomUUID();

		AgendaVO agendaRequest = AgendaBuilder.buildVO();
		agendaRequest.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.empty());

		assertThrows(AgendaNotFoundException.class, () -> agendaUpdateService.updateVoteTime(agendaRequest),
				"It should throw AgendaNotFoundException");

		verify(agendaRepository, times(1)).findByIdAndAgendaStatusIsActive(agendaId);
	}
	
	@Test
	@DisplayName("When trying to update vote period time informing no period it should define a period of one minute from now!")
	void when_trying_to_update_vote_period_time_informing_no_period_it_should_define_a_period_of_one_minute_from_now() {

		UUID agendaId = UUID.randomUUID();
		
		int currentMinuteMoreOne = LocalDateTime.now().getMinute() + 1;

		AgendaVO agendaRequest = AgendaBuilder.buildVO();
		agendaRequest.setStatus(AgendaStatus.ATIVO);
		agendaRequest.setId(agendaId);
		
		Agenda agendaWithNoDates = AgendaBuilder.buildEntity();
		agendaWithNoDates.setStatus(AgendaStatus.ATIVO);
		agendaWithNoDates.setId(agendaId);

		Agenda agendaWihDates = AgendaBuilder.buildEntity();
		agendaWihDates.setStatus(AgendaStatus.ATIVO);
		agendaWihDates.setStartVote(LocalDateTime.now());
		agendaWihDates.setEndVote(LocalDateTime.now().plusMinutes(1));
		agendaWihDates.setId(agendaId);

		when(agendaRepository.findByIdAndAgendaStatusIsActive(agendaId)).thenReturn(Optional.of(agendaWithNoDates));
		when(agendaRepository.save(any(Agenda.class))).thenReturn(agendaWihDates);

		AgendaVO agendaUpdated = agendaUpdateService.updateVoteTime(agendaRequest);
		// @formatter:off
		assertAll(() -> assertNotNull(agendaUpdated, "agendaUpdated object should not be null"),
				() -> assertNotNull(agendaUpdated.getId(), "agendaUpdated.getId() should not be null"),
				() -> assertEquals(agendaRequest.getQuestion(), agendaUpdated.getQuestion(),
						String.format("question should be %s", agendaRequest.getQuestion())),
				() -> assertEquals(agendaRequest.getStatus(), agendaUpdated.getStatus(),
						String.format("status should be %s", agendaRequest.getStatus())),
				() -> assertNotNull(agendaUpdated.getStartVote(), String.format("startVote should not be null")),
				() -> assertNotNull(agendaUpdated.getEndVote(), String.format("endVote should not be null")),
				() -> assertEquals(currentMinuteMoreOne, agendaUpdated.getEndVote().getMinute(),
						String.format("endVote should be %s", LocalDateTime.now().plusMinutes(1))));
		// @formatter:on

		verify(agendaRepository, times(1)).findByIdAndAgendaStatusIsActive(agendaId);
		verify(agendaRepository, times(1)).save(any());
	}

}
