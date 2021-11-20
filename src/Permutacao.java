import java.util.ArrayList;

public class Permutacao {
	private int[] N;
	private int n;
	private ArrayList<Item> entrada;
	private int r;
	public ArrayList<String> x = new ArrayList<String>();
	
    /**
	 * entrada é seu vetor de elementos e r é o tamanho da permutacao. Aqui 
	 * obrigatoriamente deve ser maior que zero.
	 */
	public Permutacao(ArrayList<Item> itens, int r) {
		this.r = r;
		this.n = itens.size();
		this.N = new int[r + 1];
		this.entrada = itens;
	}

	public boolean hasNext() {
		return this.N[this.r] == 0;
	}

	public String[] next(float sum) {
        String[] saida = new String[this.r] ;
        int i, j ;
        float total = 0;
  
        
   
        for(i=0, j=this.r-1; i < this.r; i++) {
        	 float money = entrada.get(this.N[i]).getPrice();
        	 total += money;
        	 saida[j] = entrada.get(this.N[i]).getName() + " " + Float.toString(entrada.get(this.N[i]).getPrice());
            j-=1 ;
        }
    
        
        
        this.N[0] += 1 ;
 
        for(i=0; i < this.r; i++) {
            if(this.N[i] == this.n) {
                this.N[i] = 0;
                this.N[i+1] += 1 ;
            }
        }
        
   
        
        return saida;
    }
}