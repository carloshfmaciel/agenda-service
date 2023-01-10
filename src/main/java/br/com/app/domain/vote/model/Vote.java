package br.com.app.domain.vote.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "vote")
@EntityListeners(AuditingEntityListener.class)
public class Vote implements Serializable {
	 
	private static final long serialVersionUID = -5534240726090770665L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	UUID id;
	
	@Column(name = "id_user", nullable = false)
	UUID userId;
	
	@Column(name = "id_agenda", nullable = false)
	UUID agendaId;
	
	@Column(name = "yes_answer")
	Boolean yesAnswer;
	
	@Column(name = "no_answer")
	Boolean noAnswer;
	
	@LastModifiedDate
	@Column(name = "last_modification_date", nullable = false)
	LocalDateTime lastModificationDate;

	@Version
	Long revision;

}
