import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//import permutação.Permutacao;

public class Carrinho {
	public ArrayList<Item> TodosOsItens = new ArrayList<Item>();
	private Mochila[][] matriz = null;
	public ArrayList<String> x = new ArrayList<String>();

	//LER O TXT COM OS PRODUTOS
	public void LerItens() {
		Boolean stop = false;
		Scanner entrada = new Scanner(System.in);
		File file = new File("D:\\PAA\\dados.txt");
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = buffer.readLine()) != null) {
				System.out.println(line);
				String[] splited = line.split("\\s+");
				String nome = splited[0];
				int value = Integer.parseInt(splited[1]);
				int peso = Integer.parseInt(splited[2]);
				TodosOsItens.add(new Item(nome, value, peso, 0));
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int auxVu = 0; auxVu < TodosOsItens.size(); auxVu++) {
			TodosOsItens.get(auxVu)
					.setValorUtilidade(TodosOsItens.get(auxVu).getPeso() / TodosOsItens.get(auxVu).getPrice());
		}
	}

	//ALGORITMO DE FORÇA BRUTA
	public Mochila BrutalForce() throws IOException {

		//Ordenar pelo preço em ordem Crescente
		Collections.sort(TodosOsItens, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Item a1 = (Item) o1;
				Item a2 = (Item) o2;
				return Float.compare(a1.getPrice(), a2.getPrice());
			}

		});

		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		System.out.println("Produto  Peso  Preço  Valor Utilidade");
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		for (int auxiliar = 0; auxiliar < TodosOsItens.size(); auxiliar++) {
			System.out.println(TodosOsItens.get(auxiliar).getName() + "   " + TodosOsItens.get(auxiliar).getPeso()
					+ "   " + TodosOsItens.get(auxiliar).getPrice() + "      "
					+ TodosOsItens.get(auxiliar).getValorUtilidade());
		}
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");

		Scanner entrada = new Scanner(System.in);
		System.out.print("Informe quanto pode gastar:");
		float credito = entrada.nextFloat();
		Mochila mochila = new Mochila(credito);
		float auxiliacredito = credito;

		// ordenar pelo MENOR preço
		Collections.sort(TodosOsItens, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Item a1 = (Item) o1;
				Item a2 = (Item) o2;
				return Float.compare(a1.getPrice(), a2.getPrice());
			}

		});

		float limite = TodosOsItens.get(0).getPrice();
        System.out.println(limite);
		ArrayList<Item> str = TodosOsItens;
		String[] saida;

		/**
		 * Primeiro parametro: O vetor de elementos Segundo parametro: O tamanho de r.
		 * Esse tamanho deve ser maior que zero.
		 */

		FileWriter arqBruteForce = new FileWriter("D:\\PAA\\bruteforce.txt");
		PrintWriter gravararqBruteForce = new PrintWriter(arqBruteForce);

		int aux = (int) (credito / TodosOsItens.get(0).getPrice());
		int controlezinho = 0;

		long start = System.currentTimeMillis();
		while (controlezinho <= aux) {
			Permutacao p = new Permutacao(str, controlezinho);

			while (p.hasNext()) {
				saida = p.next(credito);

				for (String e : saida) {
					gravararqBruteForce.printf((e + "," + " "));
				}
				gravararqBruteForce.printf("%n");
			}
			controlezinho++;
		}
		long elapsed = System.currentTimeMillis() - start;
		gravararqBruteForce.print(elapsed);
		arqBruteForce.close();
        
		System.out.println("Tempo gasto na execução do Brutal Force em milisegundos: " + elapsed);
		return mochila;
	}

	
	// Solução usando algoritmo Guloso

	public Mochila Guloso() throws IOException{
		//ORDENAR PELO VALOR DE UTILIDADE
		Collections.sort(TodosOsItens, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Item a1 = (Item) o1;
				Item a2 = (Item) o2;
				return Float.compare(a2.getValorUtilidade(), a1.getValorUtilidade());
			}

		});

		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		System.out.println("Produto  Peso  Preço  Valor Utilidade");
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		for (int auxiliar = 0; auxiliar < TodosOsItens.size(); auxiliar++) {
			System.out.println(TodosOsItens.get(auxiliar).getName() + "   " + TodosOsItens.get(auxiliar).getPeso()
					+ "   " + TodosOsItens.get(auxiliar).getPrice() + "      "
					+ TodosOsItens.get(auxiliar).getValorUtilidade());
		}
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");

		
		//DIGITAR QUANTO PODE GASTAR
		Scanner entrada = new Scanner(System.in);
		System.out.print("Informe quanto pode gastar:");
		float credito = entrada.nextFloat();
		float limite = 0;
		int auxMenor = 0;
		Mochila mochila = new Mochila(credito);
		
		//PARA DEFINIR O PRODUTO COM MENOR PREÇO
				Collections.sort(TodosOsItens, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						Item a1 = (Item) o1;
						Item a2 = (Item) o2;
						return Float.compare(a1.getPrice(), a2.getPrice());
					}

				});

				float lim = TodosOsItens.get(0).getPrice();
		        System.out.println(lim);
	    
		      //ORDENAR PELO VALOR DE UTILIDADE
				Collections.sort(TodosOsItens, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						Item a1 = (Item) o1;
						Item a2 = (Item) o2;
						return Float.compare(a2.getValorUtilidade(), a1.getValorUtilidade());
					}

				});
		        
		        
		//APENAS PARA ESCREVER UM TXT
		FileWriter arqGuloso = new FileWriter("D:\\PAA\\guloso.txt");
		PrintWriter gravararqGuloso = new PrintWriter(arqGuloso);
		
		//PARA MEDIR O TEMPO DE EXECUÇÃO
		long start = System.currentTimeMillis();
		if(credito >= lim) {
		while (credito >= lim) {
			for (int i = 0; i < TodosOsItens.size(); i++) {
				if (TodosOsItens.get(i).getPrice() <= credito) {
					gravararqGuloso.printf((TodosOsItens.get(i).getName() + "," + " "));
					credito = credito - TodosOsItens.get(i).getPrice();
					i = -1;
				}
				gravararqGuloso.printf("%n");
			}
		}
		long elapsed = System.currentTimeMillis() - start;
		gravararqGuloso.print(elapsed);
		arqGuloso.close();
		System.out.println("O tempo de execução do algoritmo guloso em milissegundos: " + elapsed);
		}
		
		return mochila;
	}

}
