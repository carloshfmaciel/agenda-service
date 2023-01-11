package br.com.app.domain.user.controller.create;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.controller.commons.request.UserCreateRequest;
import br.com.app.domain.user.controller.commons.response.UserResponse;
import br.com.app.domain.user.service.UserCreateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserCreateController implements UserCreateControllerSwagger {

	UserCreateService userCreateService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
		return UserConverter.toResponse(userCreateService.create(UserConverter.toVO(userCreateRequest)));
	}

}
