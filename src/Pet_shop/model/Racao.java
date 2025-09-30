package Pet_shop.model;

public class Racao extends Produto{
	
	private Float quilo;

	
	public Racao(int id, String nome, int categoria, float preco, float quilo) {
		super(id, nome, categoria, preco);
		this.quilo = quilo;
	}


	public Float getQuilo() {
		return quilo;
	}


	public void setQuilo(Float quilo) {
		this.quilo = quilo;
	}

	
}
