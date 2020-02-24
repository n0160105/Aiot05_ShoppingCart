package shopping;

public class FruitsModel {
	
	protected int id;
	protected String name;
	protected double prob;
	protected double price;
	
	public FruitsModel() {
	}
	
	public FruitsModel(int id, String name, double prob, double price) {
		super();
		this.id = id;
		this.name = name;
		this.prob = prob;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
