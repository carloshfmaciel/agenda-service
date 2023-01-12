package br.com.app.domain.agenda.validation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.app.domain.agenda.exception.AgendaValidationException;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.infrastructure.exception.Error;

@Component
public class UpdateAgendaVoteTimeValidation {

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
		if(agendaVO.getStartVote().isBefore(LocalDateTime.now()) || agendaVO.getEndVote().isBefore(LocalDateTime.now())) {
			throw new AgendaValidationException(Error.DATE_IS_LESS_THAN_NOW.getMessage(),
					Error.DATE_IS_LESS_THAN_NOW.getCode());
		}
	}

	private void validateIfDateIsEquals(AgendaVO agendaVO) {
		if (agendaVO.getStartVote().equals(agendaVO.getEndVote())) {
			throw new AgendaValidationException(Error.DATES_IS_EQUALS.getMessage(), Error.DATES_IS_EQUALS.getCode());
		}
	}
	
	private void validateIfInitialDateIsLessThanEndDate(AgendaVO agendaVO) {
		if(!agendaVO.getStartVote().isBefore(agendaVO.getEndVote())) {
			throw new AgendaValidationException(Error.DATE_INITIAL_MUST_BE_LESS.getMessage(), Error.DATE_INITIAL_MUST_BE_LESS.getCode());
		}
	}
}
