package eus.birt.dam.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="comentario")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate fechaComentario = LocalDate.now();
	
	@JsonBackReference (value="noticia-comentarios")
	@ManyToOne
	@JoinColumn (name = "noticia_id")
	private Noticia noticia;
	
	@JsonBackReference (value="usuario-comentarios")
	@ManyToOne
	@JoinColumn (name = "usuario_id")
	private Usuario usuario;
	
	//Añade propiedad noticiaTitulo a JSON 
	@JsonProperty("noticiaTitulo")
	public String getNoticiaTitulo() {
	    return noticia != null ? noticia.getTitulo() : null;
	}
	//Lo mismo para usuario
	@JsonProperty("usuarioEmail")
	public String getUsuarioEmail() {
	    return usuario != null ? usuario.getEmail() : null;
	}

	public Comentario(String texto,Noticia noticia,Usuario usuario) {
		super();
		this.texto = texto;
		this.noticia = noticia;
		this.usuario = usuario;
	}
}
