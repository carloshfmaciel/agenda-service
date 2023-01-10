package br.com.app.domain.agenda.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;

public class AgendaSpecification implements Specification<Agenda> {

	private static final long serialVersionUID = -4167377309180481269L;

	public static final String STATUS = "status";

	private AgendaStatus status;

	public AgendaSpecification with(AgendaStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public Predicate toPredicate(Root<Agenda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();

		if (status != null) {
			predicates.add(criteriaBuilder.equal(root.get(STATUS), status));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
