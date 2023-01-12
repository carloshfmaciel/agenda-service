package br.com.app.domain.agenda.builder;

import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.vo.AgendaVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgendaBuilder {
	
	public static AgendaVO buildVO() {
		return AgendaVO.builder()
					.question("Você está de acordo com a pintura do prédio?")
					.build();
	}
	
	public static Agenda buildEntity() {
		return Agenda.builder()
					.question("Você está de acordo com a pintura do prédio?")
					.build();
	}

}
