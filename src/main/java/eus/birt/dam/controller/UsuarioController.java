package eus.birt.dam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eus.birt.dam.domain.Usuario;
import eus.birt.dam.repository.UsuarioRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping({ "/", "" })
	public List<Usuario> index() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public Usuario show(@PathVariable("id") Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@PostMapping({ "/", "" })
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		Usuario tempUsuario = usuarioRepository.findById(id).orElse(null);

		tempUsuario.setNombre(usuario.getNombre());
		tempUsuario.setApellidos(usuario.getApellidos());
		tempUsuario.setEmail(usuario.getEmail());
		// Al ser un id diferente, el método save hace en realidad un update
		return usuarioRepository.save(tempUsuario);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
	}

}
