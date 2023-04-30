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

import eus.birt.dam.domain.Noticia;
import eus.birt.dam.repository.NoticiaRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/noticias")
public class NoticiaController {

	@Autowired
	NoticiaRepository noticiaRepository;

	@GetMapping({ "/", "" })
	public List<Noticia> index() {
		return noticiaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Noticia show(@PathVariable("id") Long id) {
		return noticiaRepository.findById(id).orElse(null);
	}

	@PostMapping({ "/", "" })
	@ResponseStatus(HttpStatus.CREATED)
	public Noticia create(@RequestBody Noticia noticia) {
		return noticiaRepository.save(noticia);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Noticia update(@RequestBody Noticia noticia, @PathVariable("id") Long id) {
		Noticia tempNoticia = noticiaRepository.findById(id).orElse(null);

		tempNoticia.setTitulo(noticia.getTitulo());
		tempNoticia.setTexto(noticia.getTexto());
		tempNoticia.setFechaNoticia(noticia.getFechaNoticia());
		// Al ser un id diferente, el método save hace en realidad un update
		return noticiaRepository.save(tempNoticia);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		noticiaRepository.deleteById(id);
	}

}
