package buscaBinaria;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws Exception 
	{	


		RandomAccessFile f = new RandomAccessFile("cep_ordenado.dat", "rw");
		Endereco endereco = new Endereco();
		Scanner scanner = new Scanner(System.in);
		String endereco_CEP, x, aux;    
		long inicio = 0;
		long fim =  (f.length() / endereco.tamanho_linha());
		long meio, achou;

		//System.out.println(fim);

		System.out.print("Digite o CEP que está procurando: ");
		endereco_CEP = scanner.nextLine();
		x = endereco_CEP;

		System.out.println("CEP procurado: " + x);
		System.out.println("Efetuando a busca");

		//Busca Binária cortando de metade em metade
		while (inicio <= fim)
		{
			meio = (inicio + fim) / 2;
			f.seek(meio * endereco.tamanho_linha());
			endereco.leEndereco(f);
			System.out.println("CEP encontrado na interação da busca binária: " + endereco.getCep());
			aux = endereco.getCep();

			if (aux.compareTo(x) < 0)
			{
				inicio = meio + 1;
				} 
			else if (aux.compareTo(x) > 0)
			{
				fim = meio - 1;
				} 
			else 
			{
				achou = meio;
				System.out.println("CEP Encontrado\n\nSegue o Endereço completo da Pesquisa:\n");
				break;
			} 
		}

		//Informações recuperadas do CEP buscado
		System.out.println("Logradouro: " + endereco.getLogradouro());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("Cidade: " + endereco.getCidade());
		System.out.println("Estado: " + endereco.getEstado());
		System.out.println("Sigla: " + endereco.getSigla());
		System.out.println("CEP: " + endereco.getCep());
		f.close();
	}
}