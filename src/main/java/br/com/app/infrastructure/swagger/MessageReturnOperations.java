package br.com.app.infrastructure.swagger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageReturnOperations {

	public static final String RETURN_ID_400 = "{\n \" error_items\": {\n\"code\": \"invalid_param\",\n \"message\": \"Parametro [id] nao informado ou invalido\"\n }\n }";

	public static final String RETURN_404 = "{\n \"error_items \": {\n  \"code\": \"entity_not_found\",\n \"message\": \"Entity nao encontrada.\"\n }\n}";

	public static final String RETURN_409 = "{\n\" error_items \": {\n  \"code\": \"entity_duplicated\",\n \"message\": \"Erro ao criar entidy. Existe uma entity com esse nome.\"\n}\n}";

	public static final String RETURN_424 = "{\n\" error_items \": {\n  \"code\": \"dependency_failed\",\n \"message\": \"Erro de integracao.\"\n}\n}";

	public static final String RETURN_500 = "{\n \"error_items \" : {\n   \"code\": \"system_error\",\n \"message\": \"Erro Interno.\"\n}\n}";

}
