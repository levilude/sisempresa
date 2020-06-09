package com.lude.sisempresa.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lude.sisempresa.domain.Categoria;
import com.lude.sisempresa.domain.ItemPedido;
import com.lude.sisempresa.domain.PagamentoComBoleto;
import com.lude.sisempresa.domain.Pedido;
import com.lude.sisempresa.domain.enums.EstadoPagamento;
import com.lude.sisempresa.repositories.ItemPedidoRepository;
import com.lude.sisempresa.repositories.PagamentoRepository;
import com.lude.sisempresa.repositories.PedidoRepository;
import com.lude.sisempresa.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Instanciar objeto por injeção de dependencia. O spring faz isso automático
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		//Data de vencimento com boleto vamos adotar como uma semana depois da data do pedido
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante()); //metodo que vai preencher no pagto a data de vencimento
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
