package br.com.app.domain.user.controller.operations.delete;

import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_404;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_500;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_ID_400;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
public interface UserDeleteControllerSwagger {
	
	@Operation(summary = "It inactives an user by id", description = "REST Endpoint that inactivates an user by id")
	@Tags(value = @Tag(name = "users", description = "Everything about agendas"))
	@DeleteMapping(value = "/{userId}")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content"),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(schema = @Schema(example = RETURN_ID_400))),
			@ApiResponse(responseCode = "404", description = "The specified resource was not found",
					content = @Content(schema = @Schema(example = RETURN_404))),
			@ApiResponse(responseCode = "500", description = "The specified resource internal error",
					content = @Content(schema = @Schema(example = RETURN_500))) })
	public void delete(@PathVariable(value = "userId") UUID userId);

}
