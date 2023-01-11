package br.com.app.domain.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.exception.UserNotFoundException;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.model.UserStatus;
import br.com.app.domain.user.repository.UserRepository;
import br.com.app.domain.user.repository.UserSpecification;
import br.com.app.domain.user.vo.UserVO;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserFetchService {

	UserRepository userRepository;

	public UserVO fetchById(UUID userId) {
		User user = userRepository.findByIdAndUserStatusIsActive(userId)
				.orElseThrow(() -> new UserNotFoundException(Error.USER_NOT_FOUND.getMessage(),
						Error.USER_NOT_FOUND.getCode()));
		return UserConverter.toVO(user);
	}

	public Page<User> fetch(String username, String cpf, UserStatus status, Pageable pageRequest) {
		return userRepository.findAll(new UserSpecification().with(username, cpf, status), pageRequest);
	}

}
