package br.com.casadocodigo.loja.daos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

public interface IProdutoDAO {

	void gravar(Produto produto);

	List<Produto> listar();

	Produto find(Integer id);
}
