package br.com.app.domain.user.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.app.domain.user.model.User;
import br.com.app.domain.user.model.UserStatus;

public class UserSpecification implements Specification<User> {

	private static final long serialVersionUID = -4167377309180481269L;

	public static final String USERNAME_FIELD = "username";

	public static final String CPF_FIELD = "cpf";

	public static final String STATUS_FIELD = "status";

	private String username;

	private String cpf;

	private UserStatus status;

	public UserSpecification with(String username, String cpf, UserStatus status) {
		this.username = username;
		this.cpf = cpf;
		this.status = status;
		return this;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isBlank(username)) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(USERNAME_FIELD)),
					"%" + username.toLowerCase() + "%"));
		}
		
		if (!StringUtils.isBlank(cpf)) {
			predicates.add(criteriaBuilder.equal(root.get(CPF_FIELD), cpf));
		}

		if (status != null) {
			predicates.add(criteriaBuilder.equal(root.get(STATUS_FIELD), status));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
