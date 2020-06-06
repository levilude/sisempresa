package com.lude.sisempresa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lude.sisempresa.domain.Categoria;
import com.lude.sisempresa.domain.Cliente;
import com.lude.sisempresa.repositories.ClienteRepository;
import com.lude.sisempresa.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// Instanciar objeto por injeção de dependencia. O spring faz isso automático
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
