package com.example.demoverboshttp.api.modelo;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoverboshttp.dominio.Usuario;
import com.example.demoverboshttp.dominio.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {
	
	@Autowired
	private UsuarioRepository repository;

	@PostConstruct
	private void criarMassaDados() {
		Usuario rodrigo = Usuario.builder()
				.nome("Rodrigo")
				.senha("123456")
				.email("rodrigoapolinaro@hotmail.com")
				.build();
		
		Usuario java = Usuario.builder()
				.nome("Java")
				.senha("8a1248")
				.email("java@t.com")
				.build();
		
		salvar(rodrigo);
		salvar(java);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Usuario> pesquisar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario pesquisarPorId(@PathVariable Long id) {
		Usuario usuarioVazio = Usuario.builder().build();
		return repository.findById(id).orElse(usuarioVazio);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@Valid @RequestBody Usuario usuario) {
		return repository.save(usuario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		usuario.setId(id);
		return repository.save(usuario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
