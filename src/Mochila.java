import java.util.ArrayList;

public class Mochila {
	  private  ArrayList<Item> Itens = new ArrayList<Item>();
	  private float tamanho;
	  
	  public Mochila(float tamanho) {
		  this.tamanho = tamanho;
	  }
	  
	  public ArrayList<Item> Listar(){
		  return this.Itens;
	  }
	  
	  public Boolean In(Item i){
		  if(this.Cabe(i)) {
			  this.Itens.add(i);
			  return true;
		  }
		  return false;
	  }
	  
	  public Boolean Out(Item i){
		  int id = 0;
		  for (Item Item: Itens) {
			    if(i.getName() == Item.getName()){
			    	this.Itens.remove(id);
			    	return true;
			    }
			    id++;
		  }
		  return false;
	  }
	  
	 // Tentativa de unir duas Mochilas
	  public Boolean Unir(Mochila m){
		  if((this.tamanho - this.pesar()) >= m.pesar()) {
			  for (Item Item: m.Listar()) {
				    this.In(Item);
			  }
			  return true;
		  }
		  return false;
	  }
	  
	  private Boolean Cabe(Item i){
		  float tPeso = i.getPeso() + this.pesar();
		  return tPeso <= this.tamanho;
	  }
	  
	  public float pesar() {
		  float tPeso = 0;
		  for (Item Item: this.Itens) {
			    tPeso = tPeso + Item.getPeso();
		  }
		  return tPeso;
	  }
	  
	  public float verificar() {
		  float tValor = 0;
		  for (Item Item: this.Itens) {
			    tValor = tValor + Item.getPrice();
		  }
		  return tValor;
	  }
	  
	  public float getSize() {
		  return this.tamanho;
	  }
}
