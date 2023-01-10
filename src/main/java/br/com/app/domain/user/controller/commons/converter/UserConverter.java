package br.com.app.domain.user.controller.commons.converter;

import br.com.app.domain.user.controller.commons.request.UserCreateRequest;
import br.com.app.domain.user.controller.commons.request.UserUpdateRequest;
import br.com.app.domain.user.controller.commons.response.UserResponse;
import br.com.app.domain.user.model.User;
import br.com.app.domain.user.model.UserStatus;
import br.com.app.domain.user.vo.UserVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {
	
	public static UserVO toVO(UserCreateRequest userCreateRequest) {
		return UserVO.builder()
					.username(userCreateRequest.getUsername())
					.cpf(userCreateRequest.getCpf())
					.status(UserStatus.ATIVO)
					.build();
	}
	
	public static UserVO toVO(UserUpdateRequest userUpdateRequest) {
		return UserVO.builder()
					.username(userUpdateRequest.getUsername())
					.cpf(userUpdateRequest.getCpf())
					.build();
	}
	
	public static UserVO toVO(User user) {
		return UserVO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.cpf(user.getCpf())
				.status(user.getStatus())
				.build();
	}
	
	public static User toEntity(UserVO userVO) {
		return User.builder()
					.id(userVO.getId())
					.username(userVO.getUsername())
					.cpf(userVO.getCpf())
					.build();
	}
	
	public static UserResponse toResponse(UserVO userVO) {
		return UserResponse.builder()
					.id(userVO.getId())
					.username(userVO.getUsername())
					.cpf(userVO.getCpf())
					.status(userVO.getStatus())
					.build();
	}
	
	public static UserResponse toResponse(User user) {
		return UserResponse.builder()
					.id(user.getId())
					.username(user.getUsername())
					.cpf(user.getCpf())
					.status(user.getStatus())
					.build();
	}
	
	public static void copyFromFirstToSecond(UserVO userVO, User user) {
		// @formatter:off
		user.setUsername(userVO.getUsername());
		user.setCpf(userVO.getCpf());
		// @formatter:on
	}

}
