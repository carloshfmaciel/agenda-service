package br.com.app.domain.user.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.vo.AgendaVO;
import br.com.app.domain.user.model.UserStatus;
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
public class UserVO implements Serializable {

	private static final long serialVersionUID = 3692236230960662285L;

	UUID id;
	
	String username;
	
	String cpf;
	
	UserStatus status;
	
}
