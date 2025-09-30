package Pet_shop.repository;

import Pet_shop.model.Produto;

public interface ProdutoRepository {
	
	public void listarTodos();
	public void cadastrar(Produto produto);
	public void atualizar(Produto produto);
	public void procurarPorId(int id);
	public void deletar(int id);
	

}
