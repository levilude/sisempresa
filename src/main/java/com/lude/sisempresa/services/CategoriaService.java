package com.lude.sisempresa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lude.sisempresa.domain.Categoria;
import com.lude.sisempresa.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	//Instanciar objeto por injeção de dependencia. O spring faz isso automático
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
