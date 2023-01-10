package br.com.app.domain.vote.controller.create;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.domain.vote.controller.commons.converter.VoteConverter;
import br.com.app.domain.vote.controller.commons.request.VoteCreateRequest;
import br.com.app.domain.vote.controller.commons.response.VoteResponse;
import br.com.app.domain.vote.service.VoteCreateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@CrossOrigin
@RestController
@RequestMapping("/votings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class VoteCreateController {

	VoteCreateService voteCreateService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VoteResponse create(@Valid @RequestBody VoteCreateRequest voteCreateRequest) {
		return VoteConverter.toResponse(voteCreateService.create(VoteConverter.toVO(voteCreateRequest)));
	}

}
