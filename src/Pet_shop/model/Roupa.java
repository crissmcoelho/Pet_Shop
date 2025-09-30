package Pet_shop.model;

public class Roupa extends Produto {
	
	private String tamanho;

	public Roupa(int id, String nome, int categoria, float preco, String tamanho) {
		super(id, nome, categoria, preco);
		
		this.tamanho = tamanho;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	

}
