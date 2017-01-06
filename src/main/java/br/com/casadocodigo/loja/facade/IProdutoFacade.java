package br.com.casadocodigo.loja.facade;

import java.util.List;

import br.com.casadocodigo.loja.models.Produto;

public interface IProdutoFacade {

	void gravar(Produto produto);

	List<Produto> listar();

	Produto find(Integer id);
}
