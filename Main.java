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
		
		//<!>Criando o Objeto Endere�o<!>
		Endereco endereco = new Endereco();
		Scanner scanner = new Scanner(System.in);
		
		
		String endereco_CEP, x, aux;    
		
		//<!>Primeira Posi��o do Arquivo<!>
		long inicio = 0;
		
		//<!>�ltima Posi��o<!>
		long fim =  (f.length() / endereco.tamanho_linha());
		
		
		long meio, achou;

		//System.out.println(fim);

		System.out.print("Digite o CEP que est� procurando: ");
		endereco_CEP = scanner.nextLine();
		x = endereco_CEP;

		System.out.println("CEP procurado: " + x);
		System.out.println("Efetuando a busca");

		
		//Iniciando a Busca Bin�ria
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
				System.out.println("CEP Encontrado!!!\n\nSegue o Endere�o completo da Pesquisa:\n");
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