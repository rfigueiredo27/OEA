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

		System.out.print("Digite o CEP que est� procurando: ");
		endereco_CEP = scanner.nextLine();
		x = endereco_CEP;

		System.out.println("CEP procurado: " + x);
		System.out.println("Efetuando a busca");

		//Busca Bin�ria cortando de metade em metade
		while (inicio <= fim)
		{
			meio = (inicio + fim) / 2;
			f.seek(meio * endereco.tamanho_linha());
			endereco.leEndereco(f);
			System.out.println("CEP encontrado na intera��o da busca bin�ria: " + endereco.getCep());
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
				System.out.println("CEP Encontrado\n\nSegue o Endere�o completo da Pesquisa:\n");
				break;
			} 
		}

		//Informa��es recuperadas do CEP buscado
		System.out.println("Logradouro: " + endereco.getLogradouro());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("Cidade: " + endereco.getCidade());
		System.out.println("Estado: " + endereco.getEstado());
		System.out.println("Sigla: " + endereco.getSigla());
		System.out.println("CEP: " + endereco.getCep());
		f.close();
	}
}