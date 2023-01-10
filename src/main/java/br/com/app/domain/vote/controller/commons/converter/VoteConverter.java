package br.com.app.domain.vote.controller.commons.converter;

import br.com.app.domain.vote.controller.commons.request.VoteCreateRequest;
import br.com.app.domain.vote.controller.commons.response.VoteResponse;
import br.com.app.domain.vote.model.Vote;
import br.com.app.domain.vote.vo.VoteVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteConverter {
	
	public static VoteVO toVO(VoteCreateRequest voteCreateRequest) {
		return VoteVO.builder()
					.userId(voteCreateRequest.getUserId())
					.agendaId(voteCreateRequest.getAgendaId())
					.answer(voteCreateRequest.getAnswer())
					.build();
	}
	
	public static VoteVO toVO(Vote vote) {
		return VoteVO.builder()
					.userId(vote.getUserId())
					.agendaId(vote.getAgendaId())
					.answer(vote.getAnswer())
					.build();
	}
	
	public static Vote toEntity(VoteVO voteVO) {
		return Vote.builder()
				.userId(voteVO.getUserId())
				.agendaId(voteVO.getAgendaId())
				.answer(voteVO.getAnswer())
				.build();
	}
	
	public static VoteResponse toResponse(VoteVO voteVO) {
		return VoteResponse.builder()
				.userId(voteVO.getUserId())
				.agendaId(voteVO.getAgendaId())
				.answer(voteVO.getAnswer())
				.build();
	}
	
	public static VoteResponse toResponse(Vote vote) {
		return VoteResponse.builder()
				.userId(vote.getUserId())
				.agendaId(vote.getAgendaId())
				.answer(vote.getAnswer())
				.build();
	}

}
