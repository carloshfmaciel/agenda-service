package br.com.app.domain.vote.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.domain.vote.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

}
