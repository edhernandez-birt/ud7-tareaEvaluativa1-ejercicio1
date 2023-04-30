package eus.birt.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.birt.dam.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
}