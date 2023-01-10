package br.com.app.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.repository.UserRepository;
import br.com.app.domain.user.vo.UserVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserCreateService {

	UserRepository userRepository;

	public UserVO create(UserVO userVO) {
		User user = UserConverter.toEntity(userVO);
		userRepository.save(user);
		return UserConverter.toVO(user);
	}

}
