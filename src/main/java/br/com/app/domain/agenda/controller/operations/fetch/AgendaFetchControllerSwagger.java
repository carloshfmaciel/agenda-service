package br.com.app.domain.agenda.controller.operations.fetch;

import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_404;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_500;
import static br.com.app.infrastructure.swagger.MessageReturnOperations.RETURN_ID_400;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.app.domain.agenda.controller.commons.response.AgendaResponse;
import br.com.app.domain.agenda.model.AgendaStatus;
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
public interface AgendaFetchControllerSwagger {
	
	@Operation(summary = "Finds agenda by id", description = "REST Endpoint that finds agenda by id")
	@Tags(value = @Tag(name = "agendas", description = "Everything about agendas"))
	@GetMapping(value = "/{agendaId}", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK",
					content = @Content(schema = @Schema(implementation = AgendaResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(schema = @Schema(example = RETURN_ID_400))),
			@ApiResponse(responseCode = "404", description = "The specified resource was not found",
					content = @Content(schema = @Schema(example = RETURN_404))),
			@ApiResponse(responseCode = "500", description = "The specified resource internal error",
					content = @Content(schema = @Schema(example = RETURN_500))) })
	public AgendaResponse fetchById(@PathVariable("agendaId") UUID agendaId);
	
	@Operation(summary = "Finds agendas by status and pagination", description = "REST Endpoint that finds agendas by status and pagination")
	@Tags(value = @Tag(name = "agendas", description = "Everything about agendas"))
	@GetMapping(value = "/{agendaId}", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(schema = @Schema(example = RETURN_ID_400))),
			@ApiResponse(responseCode = "404", description = "The specified resource was not found",
					content = @Content(schema = @Schema(example = RETURN_404))),
			@ApiResponse(responseCode = "500", description = "The specified resource internal error",
					content = @Content(schema = @Schema(example = RETURN_500))) })
	public Page<AgendaResponse> fetch(@RequestParam(value = "status", required = false) AgendaStatus status, 
			@RequestParam(defaultValue = "0") Integer page, 
			@RequestParam(defaultValue = "30") Integer pageSize, 
			@RequestParam(defaultValue = "question") String sortBy);

}
