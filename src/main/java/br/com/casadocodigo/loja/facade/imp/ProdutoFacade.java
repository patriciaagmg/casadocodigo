package br.com.casadocodigo.loja.facade.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.casadocodigo.loja.daos.IProdutoDAO;
import br.com.casadocodigo.loja.facade.IProdutoFacade;
import br.com.casadocodigo.loja.models.Produto;

@Component
public class ProdutoFacade implements IProdutoFacade {

	
	@Autowired  /* injeta o produto dao*/
	private IProdutoDAO produtoDAO;
	
	@Override
	public void gravar(Produto produto){
		produtoDAO.gravar(produto);		
	}

	@Override
	public List<Produto> listar() {
		return produtoDAO.listar();
	}

	@Override
	public Produto find(Integer id) {
		//return manager.find(Produto.class, id); Este da erro de LazyInitializationException.
		return produtoDAO.find(id);
	}


}
