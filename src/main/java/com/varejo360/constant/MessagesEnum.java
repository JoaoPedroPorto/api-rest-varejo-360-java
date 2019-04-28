package com.varejo360.constant;

public enum MessagesEnum {
	
	// GENERAL MESSAGES
	FILE_NOT_FOUND("Arquivo não foi encontrado..."),
	PARAMETER_EMPTY_OR_NULL("Existem parâmetros que não foram passados no corpo da requisição..."),
	ERRO_SOLICITATION("Ocorreu um erro ao realizar requisição...");
	
	private String message;
	
	private MessagesEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}