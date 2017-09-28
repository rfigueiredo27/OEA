package buscaBinaria;

import java.io.RandomAccessFile;
import java.util.Scanner;

import ordenacao.Endereco;

public class Main {

	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws Exception 
	{	

		//<!>Acessando o arquivo<!>
		RandomAccessFile f = new RandomAccessFile("src/buscaBinaria/cep_ordenado.dat", "rw");
		
		//<!>Criando o Objeto Endereço<!>
		Endereco endereco = new Endereco();
		Scanner scanner = new Scanner(System.in);
		
		
		String endereco_CEP, x, aux;    
		
		//<!>Primeira Posição do Arquivo<!>
		long inicio = 0;
		
		//<!>Última Posição<!>
		long fim =  (f.length() / endereco.tamanho_linha());
		
		
		long meio, achou;

		//System.out.println(fim);

		System.out.print("Digite o CEP que está procurando: ");
		endereco_CEP = scanner.nextLine();
		x = endereco_CEP;

		System.out.println("CEP procurado: " + x);
		System.out.println("Efetuando a busca");

		
		//Iniciando a Busca Binária
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
				System.out.println("CEP Encontrado!!!\n\nSegue o Endereço completo da Pesquisa:\n");
				break;
			} 
		}

		//Imprimindo os Valores encontrados
		System.out.println("Logradouro: " + endereco.getLogradouro());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("Cidade: " + endereco.getCidade());
		System.out.println("Estado: " + endereco.getEstado());
		System.out.println("Sigla: " + endereco.getSigla());
		System.out.println("CEP: " + endereco.getCep());
		f.close();
	}
}