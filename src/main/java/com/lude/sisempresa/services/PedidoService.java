package com.lude.sisempresa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lude.sisempresa.domain.Pedido;
import com.lude.sisempresa.repositories.PedidoRepository;
import com.lude.sisempresa.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Instanciar objeto por injeção de dependencia. O spring faz isso automático
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}

}
