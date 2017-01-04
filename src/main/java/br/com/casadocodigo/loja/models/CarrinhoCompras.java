package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)// TARGET_CLASS o proprio spring vai criar um proxy para resolver as denpendencias de escopo.Assim não será necessário ficar alterando o escopo de todos os controlles que utilizam esta classe.; 
public class CarrinhoCompras implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2106272778536854933L;
	
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem,Integer>();
	
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	

	public void add(CarrinhoItem item){
		itens.put(item, getQuantidade(item)+1);
	}
	
	public Integer getQuantidade(CarrinhoItem item){
		if(!itens.containsKey(item)){
			itens.put(item,0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade(){
		return itens.values().stream().reduce(0,(proximo,acumulador) -> proximo + acumulador);
	}

	public BigDecimal getTotal(CarrinhoItem item){
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for(CarrinhoItem item: itens.keySet()){
			total = total.add(getTotal(item));
		}
		return total;
	}


	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto,tipoPreco));
		
	}
	
}
