package br.com.app.domain.agenda.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Entity(name = "agenda")
@EntityListeners(AuditingEntityListener.class)
public class Agenda implements Serializable {
	 
	private static final long serialVersionUID = 6418466396426326444L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	UUID id;
	
	@Column(name = "question", nullable = false)
	String question;
	
	@Column(name = "start_vote")
	LocalDateTime startVote;
	
	@Column(name = "end_vote")
	LocalDateTime endVote;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	AgendaStatus status;
	
	@LastModifiedDate
	@Column(name = "last_modification_date", nullable = false)
	LocalDateTime lastModificationDate;

	@Version
	Long revision;

}
