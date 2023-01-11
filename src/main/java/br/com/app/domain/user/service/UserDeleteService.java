package br.com.app.domain.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.agenda.exception.AgendaNotFoundException;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.model.UserStatus;
import br.com.app.domain.user.repository.UserRepository;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserDeleteService {

	UserRepository userRepository;

	public void delete(UUID userId) {
		User user = userRepository.findByIdAndUserStatusIsActive(userId)
				.orElseThrow(() -> new AgendaNotFoundException(Error.USER_NOT_FOUND.getMessage(),
						Error.USER_NOT_FOUND.getCode()));
		user.setStatus(UserStatus.INATIVO);
		userRepository.save(user);
	}

}
