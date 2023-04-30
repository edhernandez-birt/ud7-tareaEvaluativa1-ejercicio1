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

import eus.birt.dam.domain.Comentario;
import eus.birt.dam.repository.ComentarioRepository;
import eus.birt.dam.repository.NoticiaRepository;
import eus.birt.dam.repository.UsuarioRepository;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping ("api/comentarios")
public class ComentarioController {

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired
	NoticiaRepository noticiaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@GetMapping({"/",""})
	public List <Comentario> index() {
		return comentarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Comentario show(@PathVariable("id") Long id) {
		return comentarioRepository.findById(id).orElse(null);
	}
	
	@PostMapping({"","/"})
	@ResponseStatus (HttpStatus.CREATED)
	public Comentario create(@RequestBody Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public Comentario update(@RequestBody Comentario comentario, @PathVariable("id") Long id) {
		Comentario tempComentario = comentarioRepository.findById(id).orElse(null);
		
		tempComentario.setTexto(comentario.getTexto());
		tempComentario.setFechaComentario(comentario.getFechaComentario());
		tempComentario.setUsuario(comentario.getUsuario());
		tempComentario.setNoticia(comentario.getNoticia());
		
		return comentarioRepository.save(tempComentario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		comentarioRepository.deleteById(id);
	}
}