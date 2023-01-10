package br.com.app.domain.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.vote.controller.commons.converter.VoteConverter;
import br.com.app.domain.vote.controller.commons.validation.VoteCreateValidation;
import br.com.app.domain.vote.model.Vote;
import br.com.app.domain.vote.repository.VoteRepository;
import br.com.app.domain.vote.vo.VoteVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class VoteCreateService {

	VoteRepository voteRepository;
	
	VoteCreateValidation voteCreateValidation;

	public VoteVO create(VoteVO voteVO) {
		voteCreateValidation.validate(voteVO);
		Vote vote = VoteConverter.toEntity(voteVO);
		voteRepository.save(vote);
		return VoteConverter.toVO(vote);
	}

}
