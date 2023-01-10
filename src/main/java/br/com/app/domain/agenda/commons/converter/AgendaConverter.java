package br.com.app.domain.agenda.commons.converter;

import br.com.app.domain.agenda.commons.request.AgendaCreateRequest;
import br.com.app.domain.agenda.commons.response.AgendaResponse;
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

}
