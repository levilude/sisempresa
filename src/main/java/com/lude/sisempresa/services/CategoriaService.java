package com.lude.sisempresa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lude.sisempresa.domain.Categoria;
import com.lude.sisempresa.repositories.CategoriaRepository;
import com.lude.sisempresa.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// Instanciar objeto por injeção de dependencia. O spring faz isso automático
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
