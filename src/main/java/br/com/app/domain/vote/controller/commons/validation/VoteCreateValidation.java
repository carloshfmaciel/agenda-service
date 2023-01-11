package br.com.app.domain.vote.controller.commons.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.repository.AgendaRepository;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.repository.UserRepository;
import br.com.app.domain.vote.exception.VoteValidationException;
import br.com.app.domain.vote.model.Vote;
import br.com.app.domain.vote.repository.VoteRepository;
import br.com.app.domain.vote.vo.VoteVO;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class VoteCreateValidation {

	AgendaRepository agendaRepository;

	UserRepository userRepository;

	VoteRepository voteRepository;

	public void validate(VoteVO voteVO) {

		// // VERIFY IF AGENDA IS VALID AND IS RECEIVING VOTINGS
		Optional<Agenda> agenda = agendaRepository
				.findByIdAndAgendaStatusIsActiveAndAgendaIsReceivingVotings(voteVO.getAgendaId());
		if (agenda.isEmpty()) {
			throw new VoteValidationException(Error.AGENDA_NOT_FOUND.getMessage(), Error.AGENDA_NOT_FOUND.getCode());
		}

		// VERIFY IF USER IS VALID
		Optional<User> user = userRepository.findByIdAndUserStatusIsActive(voteVO.getUserId());
		if (user.isEmpty()) {
			throw new VoteValidationException(Error.USER_NOT_FOUND.getMessage(), Error.USER_NOT_FOUND.getCode());
		}

		// VERIFY IF USER HAS ALREADY VOTED
		Optional<Vote> vote = voteRepository.findByUserIdAndAgendaId(voteVO.getUserId(), voteVO.getAgendaId());
		if (vote.isPresent()) {
			throw new VoteValidationException(Error.USER_HAS_ALREADY_VOTED.getMessage(),
					Error.USER_HAS_ALREADY_VOTED.getCode());
		}

	}

}
