package br.com.app.domain.agenda.validation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.domain.agenda.exception.AgendaValidationException;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.domain.vote.repository.VoteRepository;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaUpdateVoteTimeValidation {

	VoteRepository voteRepository;

	public void validate(AgendaVO agendaVO) {
		// SE UM DATA FOR INFORMADA A OUTRA TAMBEM DEVE SER
		validateIfBothDatesWereInformed(agendaVO);

		// DATAS NAO PODEM SER MENOR QUE ATUAL
		validateIfDatesAreHigherThanNow(agendaVO);

		// DATAS NAO PODEM SER IGUAIS
		validateIfDateIsEquals(agendaVO);

		// DATA INICIAL DEVE SER MENOR QUE DATA FINAL
		validateIfInitialDateIsLessThanEndDate(agendaVO);

		// SE JA HOUVER VOTOS A DATA NAO PODE SER ALTERADA
		validateIfThereIsAlreadyVote(agendaVO);
	}

	private void validateIfBothDatesWereInformed(AgendaVO agendaVO) {
		if (agendaVO.getStartVote() != null && agendaVO.getEndVote() == null) {
			throw new AgendaValidationException(Error.DATE_NOT_INFORMED.getMessage(),
					Error.DATE_NOT_INFORMED.getCode());
		}
		if (agendaVO.getEndVote() != null && agendaVO.getStartVote() == null) {
			throw new AgendaValidationException(Error.DATE_NOT_INFORMED.getMessage(),
					Error.DATE_NOT_INFORMED.getCode());
		}
	}

	private void validateIfDatesAreHigherThanNow(AgendaVO agendaVO) {
		if (hasDatesToValidate(agendaVO) && (agendaVO.getStartVote().isBefore(LocalDateTime.now())
				|| agendaVO.getEndVote().isBefore(LocalDateTime.now()))) {
			throw new AgendaValidationException(Error.DATE_IS_LESS_THAN_NOW.getMessage(),
					Error.DATE_IS_LESS_THAN_NOW.getCode());
		}
	}

	private void validateIfDateIsEquals(AgendaVO agendaVO) {
		if (hasDatesToValidate(agendaVO) && agendaVO.getStartVote().equals(agendaVO.getEndVote())) {
			throw new AgendaValidationException(Error.DATES_IS_EQUALS.getMessage(), Error.DATES_IS_EQUALS.getCode());
		}
	}

	private void validateIfInitialDateIsLessThanEndDate(AgendaVO agendaVO) {
		if (hasDatesToValidate(agendaVO) && !agendaVO.getStartVote().isBefore(agendaVO.getEndVote())) {
			throw new AgendaValidationException(Error.DATE_INITIAL_MUST_BE_LESS.getMessage(),
					Error.DATE_INITIAL_MUST_BE_LESS.getCode());
		}
	}

	private void validateIfThereIsAlreadyVote(AgendaVO agendaVO) {
		if (voteRepository.existsSomeVoteByAgendaId(agendaVO.getId())) {
			throw new AgendaValidationException(Error.THERE_IS_VOTE_FOR_AGENDA.getMessage(),
					Error.THERE_IS_VOTE_FOR_AGENDA.getCode());
		}
	}

	private boolean hasDatesToValidate(AgendaVO agendaVO) {
		return (agendaVO.getStartVote() != null && agendaVO.getEndVote() != null);
	}
}
