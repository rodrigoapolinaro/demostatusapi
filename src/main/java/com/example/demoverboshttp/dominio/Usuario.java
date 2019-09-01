package com.example.demoverboshttp.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
	
	@Id @GeneratedValue
	Long id;
	
	@NotNull @NotBlank
	String nome;
	
	@NotNull @NotBlank
	String senha;
	
	@NotNull @NotBlank
	String email;
}
