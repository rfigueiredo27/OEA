package ordenacao;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception 
	{	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);  

		//Atributo para definir o tamanho do bloco
		int tamanho_bloco;

		//Lê o arquivo Original
		RandomAccessFile arquivo_original = new RandomAccessFile("src/ordenacao/cep.dat", "r");

		//Gera o arquivo ordenado
		RandomAccessFile arquivo_bloco_ordenado = new RandomAccessFile("src/ordenacao/cep_blocos.dat", "rw"); 

		//Escolhe o tamanho do bloco
		System.out.println("Entre com o tamanho do Bloco:");
		tamanho_bloco=scanner.nextInt();

		int contador = 0;
		String linha_arquivo = "";

		System.out.println("Aguarde enquanto o arquivo é separado e ordenado em blocos");

		//Loop para contar as linhas, conforme exemplo em sala de aula
		while((linha_arquivo = arquivo_original.readLine()) != null)
		{
			contador++; 
		}

		//Posição inicial
		arquivo_original.seek(0);

		//Essa parte trata a parte menor que vai sobrar no final
		int parte_inteira = 0;
		int parte_final = 0;

		parte_inteira = contador/tamanho_bloco;
		parte_final = contador % tamanho_bloco;

		boolean ultimo = false;

		if(parte_final != 0)
		{
			ultimo = true;
			parte_inteira = parte_inteira + 1;
		}

		List<Endereco> lista_enderecos = new ArrayList<Endereco>(tamanho_bloco + 1);


		for(int posicao = 0; posicao < parte_inteira; posicao++) 
		{
			//coloco o número do bloco de acordo com a posição
			arquivo_bloco_ordenado.writeBytes((posicao+1) + "\n" );

			for(int i = 0; i < tamanho_bloco; i = i++) {
				Endereco endereco = new Endereco();
				endereco.leEndereco(arquivo_original);
				lista_enderecos.add(endereco);
			}

			//Ordenação da coleção junto com o comparator
			Collections.sort(lista_enderecos, Endereco.COMPARA_CEP);

			for(int i = 0; i < tamanho_bloco; i++) {
				//insere os dados no novo arquivo ordenado
				arquivo_bloco_ordenado.writeBytes(lista_enderecos.get(i).getCep() + lista_enderecos.get(i).getLogradouro()
						+ lista_enderecos.get(i).getBairro() + lista_enderecos.get(i).getCidade()
						+ lista_enderecos.get(i).getSigla() + "\n" ); 
			}

			lista_enderecos.clear();
		}

		//Resolve o problema da última parte do arquivo
		if(ultimo)
		{
			//último_bloco
			arquivo_bloco_ordenado.writeBytes((parte_inteira) +"\n" );

			for(int i = 0; i < parte_final; i++) {
				Endereco endereco = new Endereco();
				endereco.leEndereco(arquivo_original);
				lista_enderecos.add(endereco);
			}

			//ordenação da última parte
			Collections.sort(lista_enderecos, Endereco.COMPARA_CEP);

			for(int i = 0; i < parte_final; i = i++) {
				arquivo_bloco_ordenado.writeBytes(lista_enderecos.get(i).getCep() + lista_enderecos.get(i).getLogradouro()
						+ lista_enderecos.get(i).getBairro() + lista_enderecos.get(i).getCidade() 
						+ lista_enderecos.get(i).getSigla()	+ "\n" );

			}
		}

		arquivo_original.close();
		arquivo_bloco_ordenado.close();
	}

}
