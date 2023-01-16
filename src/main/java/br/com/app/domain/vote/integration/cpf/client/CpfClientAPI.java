package br.com.app.domain.vote.integration.cpf.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.app.domain.vote.integration.cpf.exception.CpfIntegrationException;
import br.com.app.domain.vote.integration.cpf.response.CpfFetchResponse;
import br.com.app.domain.vote.integration.cpf.response.CpfStatus;
import br.com.app.infrastructure.exception.Error;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CpfClientAPI {

	@Value("${integration.api.cpf-validation.url}")
	String url;

	@Autowired
	RestTemplate restTemplate;

	public boolean isCpfAbleToVote(String cpf) {

		String requestUrl = url + "/" + cpf;

		try {
			ResponseEntity<CpfFetchResponse> response = restTemplate.getForEntity(requestUrl, CpfFetchResponse.class);

			if (!response.getStatusCode().is2xxSuccessful()) {
				throw new CpfIntegrationException(Error.CPF_INTEGRATION_ERROR.getMessage(),
						Error.CPF_INTEGRATION_ERROR.getCode());
			}

			return response.getBody().getStatus().equals(CpfStatus.ABLE_TO_VOTE);

		} catch (HttpClientErrorException e) {

			log.error("Error to request cpf api!", e);
			
			throw new CpfIntegrationException(Error.CPF_INTEGRATION_ERROR.getMessage(),
					Error.CPF_INTEGRATION_ERROR.getCode());
		}

	}

}
