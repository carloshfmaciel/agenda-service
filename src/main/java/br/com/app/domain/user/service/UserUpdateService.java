package br.com.app.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.exception.UserNotFoundException;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.repository.UserRepository;
import br.com.app.domain.user.vo.UserVO;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserUpdateService {

	UserRepository userRepository;

	public UserVO update(UserVO userVO) {
		User user = userRepository.findByIdAndUserStatusIsActive(userVO.getId())
				.orElseThrow(() -> new UserNotFoundException(Error.USER_NOT_FOUND.getMessage(),
						Error.USER_NOT_FOUND.getCode()));
		UserConverter.copyFromFirstToSecond(userVO, user);
		userRepository.save(user);
		return UserConverter.toVO(user);
	}

}
