package br.com.app.domain.user.controller.operations.update;

import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_404;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_409;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_424;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_500;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_ID_400;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.app.domain.user.controller.commons.request.UserUpdateRequest;
import br.com.app.domain.user.controller.commons.response.UserResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@OpenAPIDefinition(info = @Info(title = "by Carlos Maciel - OpenAPI 3.0", version = "v1.0", 
description = "App used to manage agenda sessions."))
public interface UserUpdateControllerSwagger {
	
	@Operation(summary = "Updates user", description = "REST Endpoint that updates user")
	@Tags(value = @Tag(name = "users", description = "Everything about users"))
	@GetMapping(value = "/{userId}", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "UPDATED",
					content = @Content(schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(schema = @Schema(example = RETURN_ID_400))),
			@ApiResponse(responseCode = "404", description = "The specified resource was not found",
					content = @Content(schema = @Schema(example = RETURN_404))),
			@ApiResponse(responseCode = "409", description = "The specified resource was conflit",
					content = @Content(schema = @Schema(example = RETURN_409))),
			@ApiResponse(responseCode = "424",
					description = "The specified resource was failed with external integration",
					content = @Content(schema = @Schema(example = RETURN_424))),
			@ApiResponse(responseCode = "500", description = "The specified resource internal error",
					content = @Content(schema = @Schema(example = RETURN_500))) })
	public UserResponse update(@PathVariable("userId") UUID userId,
			@Valid @RequestBody UserUpdateRequest userUpdateRequest);

}
