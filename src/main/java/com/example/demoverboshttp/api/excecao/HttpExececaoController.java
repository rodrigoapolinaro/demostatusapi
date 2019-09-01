package com.example.demoverboshttp.api.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.demoverboshttp.dominio.RespostaError;

@ControllerAdvice
public class HttpExececaoController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RespostaError> requisicaoComCamposInvalidos(MethodArgumentNotValidException ex) {
		StringBuilder msg = new StringBuilder();
		
		ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.forEach(FieldError -> msg.append(FieldError.getField())
						.append(" ")
						.append(FieldError.getDefaultMessage())
						.append(", "));
		
		RespostaError error = RespostaError
				.builder()
				.status(BAD_REQUEST.value())
				.msg(msg.toString())
				.doc("htpps://api.rodrigoapolinaro.com.br/erros/")
				.build();
		
		return new ResponseEntity<>(error, BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<RespostaError> requisicaoComCamposInvalidos(IllegalAccessException ex) {
		
		RespostaError error = RespostaError
				.builder()
				.status(BAD_REQUEST.value())
				.msg(ex.getMessage())
				.build();
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RespostaError> erroRecursoNaoEncontrado(ResourceNotFoundException ex) {
		
		RespostaError error = RespostaError
				.builder()
				.status(NOT_FOUND.value())
				.msg(ex.toString())
				.doc("htpps://ap.rodrigoapolinaro.com.br/erros/")
				.build();
		
		return new ResponseEntity<>(error, NOT_FOUND);	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaError> erroInterno() {
		
		RespostaError error = RespostaError
				.builder()
				.status(INTERNAL_SERVER_ERROR.value())
				.msg("Ops! Aconteceu um erro interno")
				.build();
		
		return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
	}

}
