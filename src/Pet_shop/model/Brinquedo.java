package Pet_shop.model;

public class Brinquedo extends Produto {
	
	private Integer quantidade;

	public Brinquedo(int id, String nome, int categoria, float preco,Integer quantidade) {
		super(id, nome, categoria, preco);
		this.quantidade = quantidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	

}
