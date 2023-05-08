package eus.birt.dam.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="noticia")
public class Noticia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	
	@Lob
	private String texto;
	
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate fechaNoticia = LocalDate.now();
	
	//https://es.stackoverflow.com/questions/495994/415-unsupported-media-type-java-springboot-entidades-con-listas-de-entidades
	//@JsonManagedReference (value="comentarios-noticia")
	@OneToMany (mappedBy = "noticia",cascade = CascadeType.ALL)
	List <Comentario> comentarios = new ArrayList<>();

	public Noticia(String titulo, String texto, LocalDate fecha) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fechaNoticia = fecha;
	}

}
