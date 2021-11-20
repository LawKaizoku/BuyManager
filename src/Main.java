import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		// Gerar elementos aleatórios
		// Gerar String aleatória

		FileWriter alimentos = new FileWriter("D:\\PAA\\dados.txt");
		PrintWriter gravarAlimentos = new PrintWriter(alimentos);

		System.out.println("Digite a quantidade de alimentos disponiveis no mercado");
		Scanner quantidade = new Scanner(System.in);
		int qtd;
		qtd = quantidade.nextInt();

		for (int aux = 0; aux < qtd; aux++) {
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();
			StringBuilder buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String generatedString = buffer.toString();
			System.out.println(generatedString);

			// Gerar peso
			Random aleatorio = new Random();
			int valorDoPeso = aleatorio.nextInt(100*qtd) + 1;
			// Gerar preço
			int valorDoPreco = aleatorio.nextInt(100*qtd) + 1; 
			System.out.println(valorDoPreco);
			System.out.println(valorDoPeso);
			gravarAlimentos.printf(generatedString + " " + valorDoPeso + " " + valorDoPreco + "\n");

		}

		gravarAlimentos.close();

		Carrinho carrinho = new Carrinho();
		carrinho.LerItens();
		Mochila m2 = carrinho.BrutalForce();
		Mochila m1 = carrinho.Guloso();

	}

}