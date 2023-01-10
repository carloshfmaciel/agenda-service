package br.com.app.domain.agenda.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.app.domain.agenda.model.AgendaStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgendaVO implements Serializable {

	private static final long serialVersionUID = -3396779646053788700L;

	UUID id;
	
	String question;
	
	LocalDateTime startVote;
	
	LocalDateTime endVote;
	
	AgendaStatus status;
	
}
