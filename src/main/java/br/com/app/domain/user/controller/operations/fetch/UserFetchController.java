package br.com.app.domain.user.controller.operations.fetch;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.controller.commons.response.UserResponse;
import br.com.app.domain.user.model.UserStatus;
import br.com.app.domain.user.service.UserFetchService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserFetchController implements UserFetchControllerSwagger {

	UserFetchService userFetchService;

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse fetchById(@PathVariable("userId") UUID userId) {
		return UserConverter.toResponse(userFetchService.fetchById(userId));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<UserResponse> fetch(String username, String cpf, UserStatus status, Integer page, Integer pageSize, String sortBy) {
		return userFetchService.fetch(username, cpf, status, PageRequest.of(page, pageSize, Sort.by(sortBy)))
				.map(UserConverter::toResponse);
	}

}
