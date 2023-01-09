package br.com.app.domain.agenda.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.domain.agenda.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {

}
