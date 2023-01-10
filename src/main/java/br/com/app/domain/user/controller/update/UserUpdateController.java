package br.com.app.domain.user.controller.update;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.user.controller.commons.converter.UserConverter;
import br.com.app.domain.user.controller.commons.request.UserUpdateRequest;
import br.com.app.domain.user.controller.commons.response.UserResponse;
import br.com.app.domain.user.service.UserUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserUpdateController {

	UserUpdateService userUpdateService;

	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse update(@PathVariable("userId") UUID userId,
			@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
		return UserConverter.toResponse(userUpdateService.update(UserConverter.toVO(userUpdateRequest)));
	}

}
