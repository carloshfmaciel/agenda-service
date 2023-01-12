package br.com.app.domain.agenda.controller.commons.converter;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import br.com.app.domain.agenda.controller.commons.request.AgendaCreateRequest;
import br.com.app.domain.agenda.controller.commons.request.AgendaUpdateRequest;
import br.com.app.domain.agenda.controller.commons.request.AgendaUpdateVoteTimeRequest;
import br.com.app.domain.agenda.controller.commons.response.AgendaResponse;
import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.vo.AgendaVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgendaConverter {
	
	public static AgendaVO toVO(AgendaCreateRequest agendaCreateRequest) {
		return AgendaVO.builder()
					.question(agendaCreateRequest.getQuestion())
					.status(AgendaStatus.ATIVO)
					.build();
	}
	
	public static AgendaVO toVO(AgendaUpdateRequest agendaUpdateRequest) {
		return AgendaVO.builder()
					.question(agendaUpdateRequest.getQuestion())
					.status(AgendaStatus.ATIVO)
					.build();
	}
	
	public static AgendaVO toVO(UUID agendaId, AgendaUpdateRequest agendaUpdateRequest) {
		return AgendaVO.builder()
					.id(agendaId)
					.question(agendaUpdateRequest.getQuestion())
					.status(AgendaStatus.ATIVO)
					.build();
	}
	
	public static AgendaVO toVO(UUID agendaId, AgendaUpdateVoteTimeRequest agendaUpdateVoteTimeRequest) {
		return AgendaVO.builder()
					.id(agendaId)
					.startVote(agendaUpdateVoteTimeRequest.getStartVote())
					.endVote(agendaUpdateVoteTimeRequest.getEndVote())
					.build();
	}
	
	public static AgendaVO toVO(Agenda agenda) {
		return AgendaVO.builder()
				.id(agenda.getId())
				.question(agenda.getQuestion())
				.startVote(agenda.getStartVote())
				.endVote(agenda.getEndVote())
				.status(agenda.getStatus())
				.build();
	}
	
	public static Agenda toEntity(AgendaVO agendaVO) {
		return Agenda.builder()
					.id(agendaVO.getId())
					.question(agendaVO.getQuestion())
					.startVote(agendaVO.getStartVote())
					.endVote(agendaVO.getEndVote())
					.status(agendaVO.getStatus())
					.build();
	}
	
	public static AgendaResponse toResponse(AgendaVO agendaVO) {
		return AgendaResponse.builder()
					.id(agendaVO.getId())
					.question(agendaVO.getQuestion())
					.startVote(agendaVO.getStartVote())
					.endVote(agendaVO.getEndVote())
					.status(agendaVO.getStatus())
					.build();
	}
	
	public static AgendaResponse toResponse(Agenda agenda) {
		return AgendaResponse.builder()
					.id(agenda.getId())
					.question(agenda.getQuestion())
					.startVote(agenda.getStartVote())
					.endVote(agenda.getEndVote())
					.status(agenda.getStatus())
					.build();
	}
	
	public static void copyFromFirstToSecond(AgendaVO agendaVO, Agenda agenda) {
		// @formatter:off
		if(!StringUtils.isBlank(agendaVO.getQuestion())) {
			agenda.setQuestion(agendaVO.getQuestion());
		}
		agenda.setStartVote(agendaVO.getStartVote());
		agenda.setEndVote(agendaVO.getEndVote());
		// @formatter:on
	}

}
