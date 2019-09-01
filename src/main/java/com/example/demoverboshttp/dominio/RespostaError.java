package com.example.demoverboshttp.dominio;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RespostaError {
	
	private Integer status;
	private String msg;
	private String doc;
}
