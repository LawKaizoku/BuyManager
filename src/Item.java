
public class Item {
	private float price;
	private float peso;
	private float valorUtilidade;
	private String nome;
	public Boolean adicionado = false;
	
	public Item( String name,float peso,float price,float valorUtilidade) {
		this.nome = name;
		this.peso = peso;
		this.price = price;
		this.valorUtilidade = valorUtilidade;
	}
	
	public String getName() {
		return nome;
	}
	public void setName(String name) {
		this.nome = name;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getValorUtilidade() {
		return valorUtilidade;
	}
	
	public void setValorUtilidade(float valorUtilidade) {
		this.valorUtilidade = valorUtilidade;
		
	}
}
