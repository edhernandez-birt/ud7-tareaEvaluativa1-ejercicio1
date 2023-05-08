package eus.birt.dam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "email"})})

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	
	private String apellidos;
	
	private String email;

	//@JsonManagedReference (value="comentarios-usuario")
	@OneToMany (mappedBy = "usuario",cascade = CascadeType.ALL)
	List <Comentario> comentarios = new ArrayList<>();

	public Usuario(String nombre, String apellidos, String email) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}
	
	
}
