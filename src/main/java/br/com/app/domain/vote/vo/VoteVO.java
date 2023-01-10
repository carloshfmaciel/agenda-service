package br.com.app.domain.vote.vo;

import java.util.UUID;

import br.com.app.domain.vote.model.VoteAnswer;
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
public class VoteVO {
	
	UUID userId;
	
	UUID agendaId;
	
	VoteAnswer answer;

}
